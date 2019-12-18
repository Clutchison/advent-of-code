package com.hutchison.calendar.days.y2019.day2.intcode;

import com.hutchison.calendar.days.y2019.day2.intcode.operation.Operation;
import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.hutchison.calendar.days.y2019.day2.intcode.OpType.STOP;

public class IntcodeComputer {

    private final List<Integer> codes;
    private int cursor;
    private final boolean debug;

    public IntcodeComputer(List<Integer> codes) {
        this.codes = codes == null ? new ArrayList<>() : codes;
        this.cursor = 0;
        this.debug = false;
    }

    public IntcodeComputer(List<Integer> codes, boolean debug) {
        this.codes = codes == null ? new ArrayList<>() : codes;
        this.cursor = 0;
        this.debug = debug;
        if (debug) printCodes();
    }

    public void run() {
        Operation operation = new Operation(this, cursor);
        while (operation.opType != STOP) {
            operation.perform();
            if (debug) printCodes();
            operation = new Operation(this, cursor);
        }

    }

    private void printCodes() {
        System.out.println("Codes: " + StringUtils.join(codes.stream()
                .map(String::valueOf)
                .collect(Collectors.toList()), ","));
    }

    public int getCode(int position) {
        if (position < 0 || position >= codes.size()) throw new RuntimeException("Position out of bounds.");
        return codes.get(position);
    }

    public void setCode(int position, int code) {
        if (position < 0 || position >= codes.size()) throw new RuntimeException("Position out of bounds.");
        codes.set(position, code);
    }


}
