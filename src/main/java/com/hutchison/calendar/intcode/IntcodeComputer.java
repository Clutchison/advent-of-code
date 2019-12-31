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

    public static IntcodeComputer fromList(List<Integer> codes) {
        return fromList(codes, null);
    }

    public static IntcodeComputer fromList(List<Integer> codes, List<Integer> inputs) {
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

    public int run() {
        return run(new ArrayList<>());
    }

    public int run(List<Integer> inputs) {
        codes = codes.toBuilder().inputs(inputs).build();
        while (!codes.isStopped()) {
            OpType opType = codes.getOpType();
            boolean shouldReturn = opType.equals(OUTPUT);
            opType.apply(codes);
            if (shouldReturn) return codes.getLastOutput();
        }
        return -1;
    }
}
