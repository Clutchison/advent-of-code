package com.hutchison.calendar.days.y2019.day9;

import com.hutchison.calendar.Day;
import com.hutchison.calendar.intcode.IntcodeComputer;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class Day9 extends Day {
    @Override
    public void part1() {
        IntcodeComputer computer = IntcodeComputer.fromList(
                Arrays.stream(getInput()
                        .get(0)
                        .split(","))
                        .map(Double::valueOf)
                        .collect(Collectors.toList())
        );
        double output = computer.run(Collections.singletonList(1.0));
        System.out.println("Output: " + output);
    }

    @Override
    public void part2() {

    }
}
