package com.hutchison.day.day1;

import com.hutchison.day.Day;
import com.hutchison.util.AocIO;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day1 extends Day {

    private static final String INPUT_LOCATION = "C:\\Users\\sean.hutchison\\Projects\\IntelliJ\\personal\\advent-of-code\\src\\com\\hutchison\\day\\day1\\frequencies";

    @Override
    public void part1() {
        List<Integer> frequencies = getFrequencies();
        int sum = frequencies.stream()
                .reduce(0, Integer::sum);
        console.print("Current frequency: " + sum);
    }

    @Override
    public void part2() {
        List<Integer> frequencies = getFrequencies();
        console.print("First repeated frequency: " + findFirstRepeatedFrequency(frequencies));
    }

    private Integer findFirstRepeatedFrequency(List<Integer> frequencies) {
        Set<Integer> pastFrequencies = new HashSet<>();
        int currentFrequency = 0;
        int loopNum = 0;
        while (loopNum < 1000) {
            for (Integer frequency : frequencies) {
                currentFrequency += frequency;
                if (pastFrequencies.contains(currentFrequency)) {
                    return currentFrequency;
                }
                pastFrequencies.add(currentFrequency);
            }
            loopNum++;
        }
        throw new RuntimeException("Frequency not found after " + loopNum + " loops");
    }

    private List<Integer> getFrequencies() {
        String input = AocIO.readFile(new File(INPUT_LOCATION));
        return Arrays.stream(input.split("\n"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
