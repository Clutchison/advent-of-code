package com.hutchison.calendar.days.y2019.day4;

import com.hutchison.calendar.Day;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 extends Day {
    @Override
    public void part1() {
        List<Integer> inputs = Arrays.stream(getInput().get(0).split("-"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        List<Integer> answers = new BruteForcer(inputs.get(0), inputs.get(1), false).getAnswers();
        console.print(String.format("Answers: %s", answers.stream().map(String::valueOf).collect(Collectors.joining(", "))));
        console.print("Total: " + answers.size());
    }

    @Override
    public void part2() {
        List<Integer> inputs = Arrays.stream(getInput().get(0).split("-"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        List<Integer> answers = new BruteForcer(inputs.get(0), inputs.get(1), true).getAnswers();
        console.print(String.format("Answers: %s", answers.stream().map(String::valueOf).collect(Collectors.joining(", "))));
        console.print("Total: " + answers.size());
    }
}
