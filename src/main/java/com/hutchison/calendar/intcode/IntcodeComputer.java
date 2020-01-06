package com.hutchison.calendar.intcode;

import com.hutchison.calendar.intcode.operation.OpType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.hutchison.calendar.intcode.operation.OpType.OUTPUT;

@Getter
public class IntcodeComputer {

    private Codes codes;
    boolean seriesMode;

    private IntcodeComputer(Codes codes, Boolean seriesMode) {
        this.codes = codes;
        this.seriesMode = seriesMode;
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
            OpType opType = codes.getOpType();
            codes = opType.getOp().apply(codes);
            if (opType.equals(OUTPUT) && seriesMode)
                return codes.getLastOutput();
        }
        return codes.getLastOutput();
    }

    public boolean isRunning() {
        return !codes.isStopped();
    }
}
