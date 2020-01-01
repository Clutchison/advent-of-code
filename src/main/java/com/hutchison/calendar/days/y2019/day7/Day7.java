package com.hutchison.calendar.days.y2019.day7;

import com.hutchison.calendar.Day;
import com.hutchison.calendar.days.y2019.day7.amplifier.AmplifierSeries;
import com.hutchison.calendar.days.y2019.day7.amplifier.PhaseSequence;
import com.hutchison.util.Combination;
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
        List<AmplifierSeries> seriesList = getAmplifierSeries(Arrays.asList(0, 1, 2, 3, 4));
        int bestOutput = seriesList.stream()
                .map(AmplifierSeries::getOutput)
                .max(Comparator.comparingInt(Integer::valueOf))
                .orElseThrow(() -> new RuntimeException("Failed to find max series"));
        console.print("Best series output: " + bestOutput);
    }

    private List<AmplifierSeries> getAmplifierSeries(List<Integer> phases) {
        List<PhaseSequence> allCombos = Combination.getAllCombinations(phases).stream()
                .map(PhaseSequence::fromList)
                .collect(Collectors.toList());
        List<Integer> codes = getCodesFromInput();
        PhaseSequence winningPhase = PhaseSequence.fromList(Arrays.asList(9, 7, 8, 5, 6));
        PhaseSequence other = PhaseSequence.fromList(Arrays.asList(9, 7, 8, 5, 6));
        if (winningPhase.equals(other)) {
            console.print("Sanity check.");
        }
        if (allCombos.contains(winningPhase)) {
            console.print("Success check.");
        }
        return allCombos.stream()
                .map(phaseSequence -> AmplifierSeries.builder()
                        .codes(codes)
                        .phases(phaseSequence)
                        .build())
                .collect(Collectors.toList());
    }

    private List<Integer> getCodesFromInput() {
        return Arrays.stream(input.get(0).split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public void part2() {
        List<AmplifierSeries> seriesList = apacheCombos(Arrays.asList(9, 8, 7, 6, 5), getCodesFromInput());
        List<Integer> outputs = seriesList.stream()
                .map(AmplifierSeries::getOutput)
                .sorted(Comparator.comparingInt(Integer::valueOf))
                .peek(o -> System.out.println("Output: " + o))
                .collect(Collectors.toList());
        console.print("Best output: " + outputs.get(outputs.size() - 1));
    }

    private List<AmplifierSeries> apacheCombos(List<Integer> phases, List<Integer> codes) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                new PermutationIterator<>(phases), 0), false)
                .map(phase -> AmplifierSeries.builder()
                        .codes(codes)
                        .phases(PhaseSequence.fromList(phase))
                        .build())
                .collect(Collectors.toList());
    }
}
