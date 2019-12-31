package com.hutchison.calendar.days.y2019.day7.amplifier;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class PhaseSequence {

    List<Integer> phases;

    @Getter(AccessLevel.NONE)
    static final List<List<Integer>> LEGAL_LISTS = Arrays.asList(
            Collections.unmodifiableList(Arrays.asList(0, 1, 2, 3, 4)),
            Collections.unmodifiableList(Arrays.asList(5, 6, 7, 8, 9))
    );

    private PhaseSequence(List<Integer> phases) {
        this.phases = phases;
    }

    public static PhaseSequence fromList(List<Integer> phases) {
        validatePhases(phases);
        return new PhaseSequence(phases);
    }

    @Override
    public String toString() {
        return getPhaseString(this.getPhases());
    }

    private static void validatePhases(List<Integer> phases) {
        List<Integer> sorted = phases.stream().sorted().collect(Collectors.toList());
        if (LEGAL_LISTS.stream().noneMatch(sorted::equals)) {
            throw new RuntimeException("Given phases are not a legal sequence: " + getPhaseString(phases) +
                    ". Legal sequences: " + LEGAL_LISTS.stream()
                    .map(PhaseSequence::getPhaseString)
                    .reduce((s1, s2) -> s1 + ", " + s2)
                    .orElseThrow(() -> new RuntimeException("Failed to parse legal lists.")));
        }
    }

    private static String getPhaseString(List<Integer> phases) {
        return "[" + phases.stream().map(String::valueOf).collect(Collectors.joining(", ")) + "]";
    }
}
