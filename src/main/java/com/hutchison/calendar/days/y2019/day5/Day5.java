package com.hutchison.calendar.days.y2019.day5;

import com.hutchison.calendar.Day;
import com.hutchison.calendar.intcode.IntcodeComputer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 extends Day {

    @Override
    public void part1() {
        run();
    }

    @Override
    public void part2() {
        run();
    }

    private void run() {
        IntcodeComputer computer = IntcodeComputer.fromList(getMappedInput(), false);
        computer.run();
        console.print("Answer: " + computer.getCodes().getCode(0));
    }

    private List<Double> getMappedInput() {
        return Arrays.stream(input.get(0).split(","))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }
}
