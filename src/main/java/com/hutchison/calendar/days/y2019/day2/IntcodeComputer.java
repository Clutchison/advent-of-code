package com.hutchison.calendar.days.y2019.day2;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.hutchison.calendar.days.y2019.day2.OpType.STOP;

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
        Operation operation = new Operation(cursor);
        while (operation.opType != STOP) {
            operation.perform();
            if (debug) printCodes();
            operation = new Operation(cursor);
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

    class Operation {

        private int position1;
        private int position2;
        private int endPosition;
        private final OpType opType;
        private boolean performed;

        Operation(int cursor) {
            this.opType = OpType.fromCode(codes.get(cursor));
            if (opType != STOP) {
                if (cursor >= codes.size() - 3) throw new RuntimeException("Cursor too close to end: " + cursor);
                position1 = codes.get(cursor + 1);
                position2 = codes.get(cursor + 2);
                endPosition = codes.get(cursor + 3);
            }
            performed = false;
        }

        public void perform() {
            if (performed) throw new RuntimeException("Operation already performed.");
            switch (opType) {
                case STOP:
                    return;
                case ADD:
                    add();
                    break;
                case MULTIPLY:
                    multiply();
                    break;
            }
            cursor = cursor + 4;
            performed = true;
        }

        private void add() {
            int newSum = codes.get(position1) + codes.get(position2);
            if (debug) System.out.println(String.format("%d[%d] + %d[%d] = %d[%d]",
                    codes.get(position1),
                    position1,
                    codes.get(position2),
                    position2,
                    newSum,
                    endPosition));
            codes.set(endPosition, newSum);
        }

        private void multiply() {
            int newProduct = codes.get(position1) * codes.get(position2);
            if (debug) System.out.println(String.format("%d[%d] * %d[%d] = %d[%d]",
                    codes.get(position1),
                    position1,
                    codes.get(position2),
                    position2,
                    newProduct,
                    endPosition));
            codes.set(endPosition, newProduct);
        }

    }


}
