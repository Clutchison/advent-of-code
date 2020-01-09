package com.hutchison.calendar.days.y2019.day10;


import lombok.Getter;
import lombok.Value;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public enum Quadrant {
    FIRST(new Direction(true, true), q -> Math.atan(q.getQuotient())),
    SECOND(new Direction(false, true), q -> 180 + Math.atan(q.getQuotient())), // 180 - angle.abs
    THIRD(new Direction(false, false), q -> 180 + Math.atan(q.getQuotient())), // angle + 180
    FOURTH(new Direction(true, false), q -> 360 + Math.atan(q.getQuotient())); // 360 - angle.abs

    private final Direction direction;
    @Getter
    private final Function<Slope, Double> quadrantAngle;

    private static final Map<Direction, Quadrant> map = new HashMap<>();

    static {
        Stream.of(Quadrant.values()).forEach(q -> map.put(q.direction, q));
    }

    Quadrant(Direction direction, Function<Slope, Double> quadrantAngle) {
        this.direction = direction;
        this.quadrantAngle = quadrantAngle;
    }

    public static Quadrant fromRiseRun(int rise, int run) {
        return map.get(new Direction(rise >= 0, run >= 0));
    }

    public static int indexOf(Quadrant quadrant) {
        return Arrays.asList(Quadrant.values()).indexOf(quadrant);
    }

    @Value
    static class Direction {
        boolean positiveY;
        boolean positiveX;
    }
}
