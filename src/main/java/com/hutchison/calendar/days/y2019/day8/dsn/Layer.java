package com.hutchison.calendar.days.y2019.day8.dsn;

import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class Layer {

    List<LayerRow> rows;

    private Layer(List<LayerRow> rows) {
        this.rows = rows;
    }

    public int getIntCount(int i) {
        return (int) rows.stream()
                .flatMap(row -> row.getRow().stream())
                .filter(i1 -> i1 == i)
                .count();
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
            List<LayerRow> layerRows = intList.stream()
                    .collect(Collectors.groupingBy(i -> intList.indexOf(i) / rowLength,
                            Collectors.toList())).values().stream()
                    .map(LayerRow::fromList)
                    .collect(Collectors.toList());
            return new Layer(layerRows);
        }
    }

}
