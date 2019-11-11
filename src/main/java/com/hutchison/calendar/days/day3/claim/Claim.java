package com.hutchison.calendar.days.day3.claim;

import lombok.Builder;
import lombok.Value;

@Value
public class Claim {

    static String ID_PATTERN = "^#\\d+$";

    private int id;
    private int topLeftX;
    private int topLeftY;
    private int width;
    private int height;

    @Builder
    private Claim(
            int id,
            int topLeftX,
            int topLeftY,
            int width,
            int height
    ) {
        this.id = id;
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.width = width;
        this.height = height;
    }

    public static Claim fromString(String s) {
        String[] split = s.split(" ");
        if (split.length != 4) throw new RuntimeException("Malformed claim string: " + s);
        Coordinate coordinate = Coordinate.fromString(split[2]);
        Size size = Size.fromString(split[3]);
        return Claim.builder()
                .id(parseId(split[0]))
                .topLeftX(coordinate.getX())
                .topLeftY(coordinate.getY())
                .width(size.getWidth())
                .height(size.getHeight())
                .build();
    }

    private static int parseId(String s) {
        if (!s.matches(ID_PATTERN)) throw new RuntimeException("Malformed id string: " + s);
        return Integer.parseInt(s.substring(1));
    }

}
