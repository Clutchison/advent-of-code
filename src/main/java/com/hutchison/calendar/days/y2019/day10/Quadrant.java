package com.hutchison.calendar.days.y2019.day10;


import lombok.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public enum Quadrant {
    FIRST(true, true),
    SECOND(false, true),
    THIRD(false, false),
    FOURTH(true, false),
    ;

    final boolean positiveY;
    final boolean positiveX;
    private static final Map<Direction, Quadrant> map = new HashMap<>();

    static {
        Stream.of(Quadrant.values()).forEach(q -> map.put(new Direction(q.positiveY, q.positiveX), q));
    }

    Quadrant(boolean positiveY, boolean positiveX) {
        this.positiveY = positiveY;
        this.positiveX = positiveX;
    }

    public static Quadrant fromRiseRun(int rise, int run) {
        return map.get(new Direction(rise >= 0, run >= 0));
    }

    @Value
    static class Direction {
        boolean positiveY;
        boolean positiveX;
    }
}
