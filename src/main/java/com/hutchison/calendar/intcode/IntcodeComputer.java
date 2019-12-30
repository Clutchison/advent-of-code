package com.hutchison.calendar.intcode;

import com.sun.deploy.util.StringUtils;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void run(List<Integer> inputs) {
        codes = codes.toBuilder().inputs(inputs).build();
        run();
    }

    public void run() {
        while (!codes.isStopped()) {
            codes = codes.performNextOperation();
        }
    }

    private void printCodes() {
        System.out.println("Codes: " + StringUtils.join(codes.getCodes().stream()
                .map(String::valueOf)
                .collect(Collectors.toList()), ","));
    }
}
