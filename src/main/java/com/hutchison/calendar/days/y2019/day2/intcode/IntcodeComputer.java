package com.hutchison.calendar.days.y2019.day2.intcode;

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
