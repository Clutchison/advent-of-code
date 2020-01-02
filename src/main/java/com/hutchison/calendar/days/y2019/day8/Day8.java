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
        int width = 25;
        int height = 6;
        List<List<Integer>> input = ListUtils.partition(getIntsFromInput(), width * height);
        System.out.println(String.format("%d * %d = %d", width, height, width * height));

        List<Layer> layers = input.stream()
                .map(integers -> Layer.builder()
                        .intList(integers)
                        .rowLength(width)
                        .build())
                .collect(Collectors.toList());

        Layer bestLayer = layers.stream()
                .min(Comparator.comparingInt(layer -> layer.getIntCount(0)))
                .orElseThrow(() -> new RuntimeException("No layer in layers."));

        System.out.println(String.format("Layer with most 0s product of 1s and 2s: %d",
                bestLayer.getIntCount(1) * bestLayer.getIntCount(2)));

    }

    private int getKey(List<Integer> input, int width, int height, Integer i) {
        int i1 = input.indexOf(i) / (width * height);
        System.out.println(String.format("%d / %d = %d", input.indexOf(i), width * height, i1));
        return i1;
    }

    private List<Integer> getIntsFromInput() {
        return getInput().get(0).chars()
                .mapToObj(i -> String.valueOf((char) i))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public void part2() {

    }
}
