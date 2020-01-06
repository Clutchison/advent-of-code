package com.hutchison.calendar.days.y2019.day9;

import com.hutchison.calendar.Day;
import com.hutchison.calendar.intcode.IntcodeComputer;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class Day9 extends Day {
    @Override
    public void part1() {
        run(1.0);
    }

    @Override
    public void part2() {
        run(2.0);
    }

    private void run(double input) {
        IntcodeComputer computer = IntcodeComputer.fromList(
                Arrays.stream(getInput()
                        .get(0)
                        .split(","))
                        .map(Double::valueOf)
                        .collect(Collectors.toList()),
                false
        );
        double output = computer.run(Collections.singletonList(input));
        System.out.println(String.format("Output: %.0f", output));
    }
}
