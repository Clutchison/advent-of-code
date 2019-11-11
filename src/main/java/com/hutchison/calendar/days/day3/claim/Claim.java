package com.hutchison.calendar.days.day3.claim;

import com.hutchison.calendar.days.day3.Coordinate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
final public class Claim {

    static String ID_PATTERN = "^#\\d+$";

    final int id;
    final int topLeftX;
    final int topLeftY;
    final int width;
    final int height;
    Set<Coordinate> coveredCoordinates;

    private Claim() {
        id = 0;
        topLeftX = 0;
        topLeftY = 0;
        width = 0;
        height = 0;
        coveredCoordinates = null;
    }

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
        this.coveredCoordinates = null;
    }

    public Set<Coordinate> getCoveredCoordinates() {
        if (this.coveredCoordinates == null) calculateCoveredCoordinates();
        return coveredCoordinates;
    }

    private void calculateCoveredCoordinates() {
        Set<Coordinate> coordinates = new HashSet<>();
        for (int x = topLeftX; x < topLeftX + width; x++) {
            for (int y = topLeftY; y < topLeftY + height; y++) {
                Coordinate coordinate = Coordinate.builder()
                        .x(x)
                        .y(y)
                        .build();
                coordinates.add(coordinate);
            }
        }
        this.coveredCoordinates = coordinates;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", TopLeftX: " + topLeftX +
                ", TopLeftY: " + topLeftY +
                ", Width: " + width +
                ", Height: " + height;
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
