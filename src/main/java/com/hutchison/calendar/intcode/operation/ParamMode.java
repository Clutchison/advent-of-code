package com.hutchison.calendar.intcode.operation;

import com.hutchison.calendar.intcode.Codes;

import java.util.function.BiFunction;

public enum ParamMode {
    POSITIONAL('0', (codes, index) -> codes.getCodes().get(codes.getCodes().get(index).intValue())),
    IMMEDIATE('1', (codes, index) -> codes.getCodes().get(index)),
    RELATIVE('2', (codes, index) -> codes.getCodes().get(codes.getCodes().get(index).intValue()) + codes.getRelativeBase());

    char c;
    BiFunction<Codes, Integer, Double> function;

    ParamMode(char c, BiFunction<Codes, Integer, Double> function) {
        this.c = c;
        this.function = function;
    }

    public static ParamMode fromChar(char c) {
        switch (c) {
            case '0':
                return POSITIONAL;
            case '1':
                return IMMEDIATE;
            default:
                throw new RuntimeException(c + " does not map to a ParamMode");
        }
    }
}
