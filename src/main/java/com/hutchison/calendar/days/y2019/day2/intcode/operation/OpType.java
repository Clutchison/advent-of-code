package com.hutchison.calendar.days.y2019.day2.intcode.operation;

public enum OpType {
    ADD, MULTIPLY, INPUT, OUTPUT, STOP;

    public static OpType fromCode(int code) {
        switch (code % 100) {
            case 1:
                return ADD;
            case 2:
                return MULTIPLY;
            case 3:
                return INPUT;
            case 4:
                return OUTPUT;
            case 99:
                return STOP;
            default:
                throw new RuntimeException("Invalid opcode: " + code);
        }
    }
}
