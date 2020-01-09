package com.hutchison.calendar.days.y2019.day10;


import lombok.Getter;
import lombok.Value;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public enum Quadrant {
    FIRST(true, true, q -> getDegreesFromQuotient(q.getQuotient())),
    SECOND(false, true, q -> 180 + getDegreesFromQuotient(q.getQuotient())), // 180 - angle.abs
    THIRD(false, false, q -> 180 + getDegreesFromQuotient(q.getQuotient())), // angle + 180
    FOURTH(true, false, q -> 360 + getDegreesFromQuotient(q.getQuotient())); // 360 - angle.abs

    final boolean positiveY;
    final boolean positiveX;
    @Getter
    final Function<Slope, Double> quadrantAngle;
    private static final Map<Direction, Quadrant> map = new HashMap<>();

    static {
        Stream.of(Quadrant.values()).forEach(q -> map.put(new Direction(q.positiveY, q.positiveX), q));
    }

    Quadrant(boolean positiveY, boolean positiveX, Function<Slope, Double> quadrantAngle) {
        this.positiveY = positiveY;
        this.positiveX = positiveX;
        this.quadrantAngle = quadrantAngle;
    }

    public static Quadrant fromRiseRun(int rise, int run) {
        return map.get(new Direction(rise >= 0, run >= 0));
    }

    public static int indexOf(Quadrant quadrant) {
        return Arrays.asList(Quadrant.values()).indexOf(quadrant);
    }

    private static double getDegreesFromQuotient(Float quotient) {
        double v = Math.atan(quotient) * 180 / Math.PI;
        return v;
    }

    @Value
    static class Direction {
        boolean positiveY;
        boolean positiveX;
    }
}
