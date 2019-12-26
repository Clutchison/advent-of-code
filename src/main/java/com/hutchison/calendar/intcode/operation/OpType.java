package com.hutchison.calendar.intcode.operation;

public enum OpType {
    ADD, MULTIPLY, INPUT, OUTPUT, JUMP_IF_TRUE, JUMP_IF_FALSE, LESS_THAN, EQUALS, STOP;

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
            case 5:
                return JUMP_IF_TRUE;
            case 6:
                return JUMP_IF_FALSE;
            case 7:
                return LESS_THAN;
            case 8:
                return EQUALS;
            case 99:
                return STOP;
            default:
                throw new RuntimeException("Invalid opcode: " + code);
        }
    }
}
