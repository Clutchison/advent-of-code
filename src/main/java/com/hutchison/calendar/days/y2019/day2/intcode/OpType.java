package com.hutchison.calendar.days.y2019.day2.intcode;

public enum OpType {
    ADD, MULTIPLY, STOP;

    public static OpType fromCode(int code) {
        if (code > 99) code = code % 100;
        switch (code) {
            case 1:
                return ADD;
            case 2:
                return MULTIPLY;
            case 99:
                return STOP;
            default:
                throw new RuntimeException("Invalid opcode: " + code);
        }
    }
}
