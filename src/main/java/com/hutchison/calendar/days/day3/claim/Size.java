package com.hutchison.calendar.days.day3.claim;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;

@Value
public class Size {

    private static String SIZE_PATTERN = "^\\dx\\d$";

    private int width;
    private int height;

    @Builder(access = AccessLevel.PRIVATE)
    private Size(int width,
                 int height) {
        this.width = width;
        this.height = height;
    }

    static Size fromString(String s) {
        if (!s.matches(SIZE_PATTERN)) throw new RuntimeException("Malformed size string: " + s);

        return Size.builder()
                .width(Integer.parseInt(s.substring(0, s.indexOf("x"))))
                .height(Integer.parseInt(s.substring(s.indexOf("x") + 1)))
                .build();
    }
}
