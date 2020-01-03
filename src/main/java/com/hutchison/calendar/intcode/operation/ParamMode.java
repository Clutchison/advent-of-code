package com.hutchison.calendar.intcode.operation;

import com.hutchison.calendar.intcode.Codes;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public enum ParamMode {
    POSITIONAL('0', getPositionalValue(false)),
    IMMEDIATE('1', getImmediateValue()),
    RELATIVE('2', getPositionalValue(true));

    private char c;
    @Getter
    private BiFunction<Codes, Integer, Double> function;
    private static final Map<Character, ParamMode> map = new HashMap<>();

    ParamMode(char c, BiFunction<Codes, Integer, Double> function) {
        this.c = c;
        this.function = function;
    }

    static {
        Stream.of(ParamMode.values())
                .forEach(paramMode -> map.put(paramMode.c, paramMode));
    }

    public static ParamMode fromChar(char c) {
        ParamMode paramMode = map.get(c);
        if (paramMode == null) throw new RuntimeException(c + " does not map to a ParamMode");
        return paramMode;
    }

    private static BiFunction<Codes, Integer, Double> getPositionalValue(boolean shouldAddBase) {
        return (codes, index) -> codes.getCodes().get(codes.getCodes().get(index).intValue()) +
                (shouldAddBase ? codes.getRelativeBase() : 0);
    }

    private static BiFunction<Codes, Integer, Double> getImmediateValue() {
        return (codes, index) -> codes.getCodes().get(index);
    }
}
