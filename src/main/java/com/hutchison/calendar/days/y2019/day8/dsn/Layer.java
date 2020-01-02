package com.hutchison.calendar.days.y2019.day8.dsn;

import lombok.Value;
import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Value
public class Layer {

    List<LayerRow> rows;
    int rowLength;

    private Layer(List<LayerRow> rows, int rowLength) {
        this.rows = rows;
        this.rowLength = rowLength;
    }

    public int getIntCount(int i) {
        return (int) rows.stream()
                .flatMap(row -> row.getRow().stream())
                .map(Pixel::getValue)
                .filter(i1 -> i1 == i)
                .count();
    }

    public String printVisual() {
        return getHorizontalLine() + "\n" +
                rows.stream()
                        .map(row -> "|" + row.printVisual() + "|\n")
                        .reduce(String::concat)
                        .orElse("") +
                getHorizontalLine();
    }

    public static Layer stack(List<Layer> layers) {
        if (layers == null || layers.size() == 0) return new Layer(new ArrayList<>(), 0);
        if (layers.stream()
                .map(Layer::getDimensions)
                .distinct()
                .count() > 1) {
            throw new RuntimeException("Cannot stack layers with different dimensions.");
        }
        Dimensions dimensions = layers.get(0).getDimensions();

        List<Integer> overlapped = IntStream.range(0, dimensions.getHeight() * dimensions.getWidth())
                .mapToObj(i -> overlapPixels(layers, dimensions, i))
                .collect(Collectors.toList());
        return Layer.builder()
                .intList(overlapped)
                .rowLength(dimensions.getWidth())
                .build();
    }

    private static Integer overlapPixels(List<Layer> layers, Dimensions dimensions, int i) {
        return layers.stream()
                .map(layer -> layer.getRows()
                        .get(i / dimensions.getWidth())
                        .getRow().get(i % dimensions.getWidth()))
                .reduce(Pixel::overlap)
                .map(Pixel::getValue)
                .orElseThrow(() -> new RuntimeException("Failed to overlap pixels."));
    }

    @Override
    public String toString() {
        return rows.stream()
                .map(row -> row.toString() + "\n")
                .reduce(String::concat)
                .orElse("");
    }

    private String getHorizontalLine() {
        return "|" + IntStream.range(0, rowLength)
                .mapToObj(i -> "-")
                .reduce(String::concat)
                .orElse("") + "|";
    }

    private Dimensions getDimensions() {
        return new Dimensions(rowLength, rows.size());
    }

    public static LayerBuilder builder() {
        return new LayerBuilder();
    }

    public static class LayerBuilder {

        private List<Integer> intList;
        private Integer rowLength;

        private LayerBuilder() {
        }

        public LayerBuilder intList(List<Integer> intList) {
            this.intList = intList;
            return this;
        }

        public LayerBuilder rowLength(int rowLength) {
            this.rowLength = rowLength;
            return this;
        }

        public Layer build() {
            if (intList == null) throw new RuntimeException("Int list cannot be null.");
            if (rowLength == null) throw new RuntimeException("Row length cannot be null.");
            if (intList.size() % rowLength != 0)
                throw new RuntimeException("Int list does not divide evenly by row length.");

            List<LayerRow> layerRows = ListUtils.partition(intList, rowLength).stream()
                    .map(LayerRow::fromList)
                    .collect(Collectors.toList());
            return new Layer(layerRows, rowLength);
        }
    }

}
