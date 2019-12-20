package com.hutchison.calendar.days.y2019.day5;

import com.hutchison.calendar.Day;
import com.hutchison.calendar.days.y2019.day2.intcode.IntcodeComputer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 extends Day {
    @Override
    public void part1() {
        IntcodeComputer computer = new IntcodeComputer(getMappedInput());
        computer.run();
        console.print("Answer: " + computer.getCodes().getCode(0));
    }

    private List<Integer> getMappedInput() {
        return Arrays.stream(input.get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    @Override
    public void part2() {

    }
}
