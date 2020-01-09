package com.hutchison.calendar.intcode;

import com.hutchison.calendar.intcode.operation.Operation;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.hutchison.calendar.intcode.operation.Operation.OUTPUT;

@Getter
public class IntcodeComputer {

    private Codes codes;
    boolean stopOnOutput;

    private IntcodeComputer(Codes codes, Boolean stopOnOutput) {
        this.codes = codes;
        this.stopOnOutput = stopOnOutput;
    }

    public static IntcodeComputer fromList(List<Double> codes, boolean seriesMode) {
        return new IntcodeComputer(Codes.builder()
                .codes(new ArrayList<>(codes))
                .cursor(0)
                .stopped(false)
                .build(),
                seriesMode);
    }

    public boolean hasStarted() {
        return codes.getCursor() > 0;
    }

    public double run() {
        return run(new ArrayList<>());
    }

    public double run(List<Double> inputs) {
        codes = codes.toBuilder().inputs(inputs).build();
        while (!codes.isStopped()) {
            Operation operation = codes.getOpType();
            codes = operation.apply(codes);
            if (operation.equals(OUTPUT) && stopOnOutput)
                return codes.getLastOutput();
        }
        return codes.getLastOutput();
    }

    public boolean isRunning() {
        return !codes.isStopped();
    }
}
