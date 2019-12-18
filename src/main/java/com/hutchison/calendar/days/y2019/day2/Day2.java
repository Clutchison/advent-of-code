package com.hutchison.calendar.days.y2019.day2;

import com.hutchison.calendar.Day;
import com.hutchison.calendar.days.y2019.day2.intcode.IntcodeComputer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 extends Day {

    @Override
    public void part1() {
        List<Integer> modifiedInput = getModifiedInput(12, 2);
        IntcodeComputer computer = new IntcodeComputer(modifiedInput, false);
        computer.run();
        console.print(computer.getCode(0));
    }

    @Override
    public void part2() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                IntcodeComputer computer = new IntcodeComputer(getModifiedInput(i, j), false);
                computer.run();
                if (computer.getCode(0) == 19690720) {
                    int sum = 100 * i + j;
                    console.print("Answer: " + sum);
                }
            }
        }

    }

    private List<Integer> getModifiedInput(int noun, int verb) {
        List<Integer> mappedInput = Arrays.stream(input.get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        mappedInput.set(1, noun);
        mappedInput.set(2, verb);
        return mappedInput;
    }
}
