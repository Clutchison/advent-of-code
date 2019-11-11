package com.hutchison.calendar.days.day3.claim;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;

@Value
public class Coordinate {

    private static String COORDINATE_PATTERN = "^\\d+,\\d+:$";

    private int x;
    private int y;

    @Builder(access = AccessLevel.PRIVATE)
    private Coordinate(int x,
                       int y) {
        this.x = x;
        this.y = y;
    }

    static Coordinate fromString(String s) {
        if (!s.matches(COORDINATE_PATTERN)) throw new RuntimeException("Malformed coordinate string: " + s);

        return Coordinate.builder()
                .x(Integer.parseInt(s.substring(0, s.indexOf(","))))
                .y(Integer.parseInt(s.substring(s.indexOf(",") + 1, s.indexOf(":"))))
                .build();
    }
}
