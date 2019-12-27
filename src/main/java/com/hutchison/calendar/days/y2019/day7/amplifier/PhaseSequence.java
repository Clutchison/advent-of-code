package com.hutchison.calendar.days.y2019.day7.amplifier;

import lombok.Value;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class PhaseSequence {

    static final List<Integer> LEGAL_LIST = Collections.unmodifiableList(Arrays.asList(0, 1, 2, 3, 4));
    List<Integer> phases;

    private PhaseSequence(List<Integer> phases) {
        this.phases = phases;
    }

    public static PhaseSequence fromList(List<Integer> phases) {
        validatePhases(phases);
        return new PhaseSequence(phases);
    }

    private static void validatePhases(List<Integer> phases) {
        if (phases.size() != LEGAL_LIST.size())
            throw new RuntimeException("Phase Sequence must be exactly 5 phases long! Given: " + getPhaseString(phases));
        if (new HashSet<>(phases).size() != LEGAL_LIST.size())
            throw new RuntimeException("Phases cannot contain duplicates! Given: " + getPhaseString(phases));
        if (!phases.stream().sorted().collect(Collectors.toList()).equals(LEGAL_LIST))
            throw new RuntimeException("Phases must be exactly the numbers 0-4. Given: " + getPhaseString(phases));
    }

    private static String getPhaseString(List<Integer> phases) {
        return phases.stream().map(String::valueOf).collect(Collectors.joining(", "));
    }
}
