package com.hutchison.calendar.days.y2019.day7;

import com.hutchison.calendar.Day;
import com.hutchison.calendar.days.y2019.day7.amplifier.AmplifierSeries;
import com.hutchison.calendar.days.y2019.day7.amplifier.PhaseSequence;
import org.apache.commons.collections4.iterators.PermutationIterator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Day7 extends Day {
    @Override
    public void part1() {
        List<AmplifierSeries> seriesList = apacheCombos(Arrays.asList(0, 1, 2, 3, 4));
        double bestOutput = seriesList.stream()
                .map(AmplifierSeries::getOutput)
                .max(Comparator.comparingDouble(Double::valueOf))
                .orElseThrow(() -> new RuntimeException("Failed to find max series"));
        console.print("Best series output: " + bestOutput);
    }

    private List<Double> getCodesFromInput() {
        return Arrays.stream(input.get(0).split(","))
                .map(Double::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public void part2() {
        List<AmplifierSeries> seriesList = apacheCombos(Arrays.asList(9, 8, 7, 6, 5));
        List<Double> outputs = seriesList.stream()
                .map(AmplifierSeries::getOutput)
                .sorted(Comparator.comparingDouble(Double::valueOf))
                .peek(o -> System.out.println("Output: " + o))
                .collect(Collectors.toList());
        console.print("Best output: " + outputs.get(outputs.size() - 1));
    }

    private List<AmplifierSeries> apacheCombos(List<Integer> phases) {
        List<Double> codes = getCodesFromInput();
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                new PermutationIterator<>(phases), 0), false)
                .map(phase -> AmplifierSeries.builder()
                        .codes(codes)
                        .phases(PhaseSequence.fromList(phase.stream()
                                .map(Double::new)
                                .collect(Collectors.toList())))
                        .build())
                .collect(Collectors.toList());
    }
}
