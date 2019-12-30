package com.hutchison.calendar.days.y2019.day7;

import com.hutchison.calendar.Day;
import com.hutchison.calendar.days.y2019.day7.amplifier.AmplifierSeries;
import com.hutchison.calendar.days.y2019.day7.amplifier.PhaseSequence;
import com.hutchison.util.Combination;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day7 extends Day {
    @Override
    public void part1() {
        List<List<Integer>> allCombos = Combination.getAllCombinations(Arrays.asList(0, 1, 2, 3, 4));
        List<Integer> codes = getCodesFromInput();
        List<AmplifierSeries> seriesList = allCombos.stream()
                .map(PhaseSequence::fromList)
                .map(phaseSequence -> AmplifierSeries.builder()
                        .codes(codes)
                        .phases(phaseSequence)
                        .build())
                .collect(Collectors.toList());
        AmplifierSeries bestSeries = seriesList.stream()
                .max(Comparator.comparingInt(AmplifierSeries::getOutput))
                .orElseThrow(() -> new RuntimeException("Failed to find max series"));
        console.print("Best series output: " + bestSeries.getOutput());
    }

    private List<Integer> getCodesFromInput() {
        return Arrays.stream(input.get(0).split(","))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
    }

    @Override
    public void part2() {


    }
}
