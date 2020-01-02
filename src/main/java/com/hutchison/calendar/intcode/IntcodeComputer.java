package com.hutchison.calendar.intcode;

import com.hutchison.calendar.intcode.operation.OpType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.hutchison.calendar.intcode.operation.OpType.OUTPUT;

@Getter
public class IntcodeComputer {

    private Codes codes;

    private IntcodeComputer(Codes codes) {
        this.codes = codes;
    }

    public static IntcodeComputer fromList(List<Double> codes) {
        return fromList(codes, null);
    }

    public static IntcodeComputer fromList(List<Double> codes, List<Double> inputs) {
        return new IntcodeComputer(Codes.builder()
                .codes(new ArrayList<>(codes))
                .cursor(0)
                .inputs(inputs)
                .stopped(false)
                .build());
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
            boolean shouldReturn = opType.equals(OUTPUT);
            codes = opType.getOp().apply(codes);
            if (shouldReturn) return codes.getLastOutput();
        }
        return -1;
    }
}
