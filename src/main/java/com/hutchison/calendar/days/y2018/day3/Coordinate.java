package com.hutchison.calendar.days.y2018.day3;

import lombok.Builder;
import lombok.Value;

@Value
public class Coordinate {

    private static String COORDINATE_PATTERN = "^\\d+,\\d+:$";

    private int x;
    private int y;

    @Builder
    private Coordinate(int x,
                       int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x + ":" + y;
    }


    public static Coordinate fromString(String s) {
        if (!s.matches(COORDINATE_PATTERN)) throw new RuntimeException("Malformed coordinate string: " + s);

        return Coordinate.builder()
                .x(Integer.parseInt(s.substring(0, s.indexOf(","))))
                .y(Integer.parseInt(s.substring(s.indexOf(",") + 1, s.indexOf(":"))))
                .build();
    }
}
