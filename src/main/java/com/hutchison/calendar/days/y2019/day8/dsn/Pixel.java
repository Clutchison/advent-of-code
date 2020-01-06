package com.hutchison.calendar.days.y2019.day8.dsn;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public enum Pixel {
    WHITE(0, ' '),
    BLACK(1, (char) 9608),
    TRANSPARENT(2, ' ');

    @Getter
    private char character;
    @Getter
    private int value;
    private static Map<Integer, Pixel> map = new HashMap<>();

    Pixel(int value, char character) {
        this.character = character;
        this.value = value;
    }

    static {
        Stream.of(Pixel.values()).forEach(p -> map.put(p.value, p));
    }

    public static Pixel valueOf(int n) {
        return map.get(n);
    }

    public static Pixel overlap(Pixel p1, Pixel p2) {
        return p1 == TRANSPARENT ? p2 : p1;
    }
}
