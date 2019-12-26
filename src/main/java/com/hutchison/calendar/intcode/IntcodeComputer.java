package com.hutchison.calendar.intcode;

import com.sun.deploy.util.StringUtils;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class IntcodeComputer {

    private Codes codes;

    public IntcodeComputer(List<Integer> codes) {
        this.codes = Codes.builder()
                .codes(codes)
                .cursor(0)
                .stopped(false)
                .build();
    }

    public void run() {
        int DEBUG_INDEX = 10;
        for (int i = 0; i < codes.getCodes().size(); i++) {
            if (!codes.isStopped()) {
                if (codes.getCursor() == DEBUG_INDEX) {
                    System.out.println("At " + DEBUG_INDEX);
                }
                codes = codes.performNextOperation();
            } else {
                return;
            }
        }
        System.out.println("Exceeded maximum operations.");
    }

    private void printCodes() {
        System.out.println("Codes: " + StringUtils.join(codes.getCodes().stream()
                .map(String::valueOf)
                .collect(Collectors.toList()), ","));
    }
}
