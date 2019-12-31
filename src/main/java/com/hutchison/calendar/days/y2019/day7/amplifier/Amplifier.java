package com.hutchison.calendar.days.y2019.day7.amplifier;

import com.hutchison.calendar.intcode.IntcodeComputer;
import lombok.Builder;
import lombok.Value;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Value
public class Amplifier {

    int phase;
    IntcodeComputer computer;

    @Builder(toBuilder = true)
    private Amplifier(Integer phase, IntcodeComputer computer) {
        this.phase = phase;
        this.computer = computer;
    }

    public int compute(int input) {
        List<Integer> inputs = computer.hasStarted() ?
                Collections.singletonList(input) :
                Arrays.asList(phase, input);
        return computer.run(inputs);
    }

    static class AmplifierBuilder {
        protected Amplifier build() {
            if (phase == null) throw new RuntimeException("Phase required to build amplifier.");
            if (computer == null) throw new RuntimeException("IntcodeComputer required to build amplifier.");
            return new Amplifier(phase, computer);
        }
    }
}
