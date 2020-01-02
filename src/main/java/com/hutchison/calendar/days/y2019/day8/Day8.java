package com.hutchison.calendar.days.y2019.day8;

import com.hutchison.calendar.Day;
import com.hutchison.calendar.days.y2019.day8.dsn.Layer;
import org.apache.commons.collections4.ListUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day8 extends Day {
    @Override
    public void part1() {
        List<Layer> layers = getLayers(25, 6);

        Layer bestLayer = layers.stream()
                .min(Comparator.comparingInt(layer -> layer.getIntCount(0)))
                .orElseThrow(() -> new RuntimeException("No layer in layers."));

        System.out.println(String.format("Layer with most 0s product of 1s and 2s: %d",
                bestLayer.getIntCount(1) * bestLayer.getIntCount(2)));

        System.out.println("Visual rep: \n" + bestLayer.printVisual());

    }

    @Override
    public void part2() {
        List<Layer> layers = getLayers(25, 6);
        Layer stacked = Layer.stack(layers);
        System.out.println(stacked.printVisual());
    }

    private List<Layer> getLayers(int width, int height) {
        List<List<Integer>> input = ListUtils.partition(getIntsFromInput(), width * height);
        System.out.println(String.format("%d * %d = %d", width, height, width * height));

        return input.stream()
                .map(integers -> Layer.builder()
                        .intList(integers)
                        .rowLength(width)
                        .build())
                .collect(Collectors.toList());
    }

    private List<Integer> getIntsFromInput() {
        return getInput().get(0).chars()
                .mapToObj(i -> String.valueOf((char) i))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
}
