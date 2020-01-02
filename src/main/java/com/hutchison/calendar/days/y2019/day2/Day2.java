package com.hutchison.calendar.days.y2019.day2;

import com.hutchison.calendar.Day;
import com.hutchison.calendar.intcode.IntcodeComputer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 extends Day {

    @Override
    public void part1() {
        List<Double> modifiedInput = getModifiedInput(12, 2);
        IntcodeComputer computer = IntcodeComputer.fromList(modifiedInput);
        computer.run();
        console.print(computer.getCodes().getCode(0));
    }

    @Override
    public void part2() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                IntcodeComputer computer = IntcodeComputer.fromList(getModifiedInput(i, j));
                computer.run();
                if (computer.getCodes().getCode(0) == 19690720) {
                    int sum = 100 * i + j;
                    console.print("Answer: " + sum);
                }
            }
        }

    }

    private List<Double> getModifiedInput(double noun, double verb) {
        List<Double> mappedInput = Arrays.stream(input.get(0).split(","))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
        mappedInput.set(1, noun);
        mappedInput.set(2, verb);
        return mappedInput;
    }
}
