package com.hutchison.calendar.days.y2019.day10;

import lombok.Value;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@Value
public class Coordinate {

    int x;
    int y;

    public Slope getSlope(Coordinate c) {
        if (c.equals(new Coordinate(8, 0)) || c.equals(new Coordinate(8, 1))) {
            System.out.println("f");
        }
        Slope build = Slope.builder()
                .rise(this.y - c.y)
                .run(c.x - this.x)
                .build();
        return build;
    }

    public double getDistance(Coordinate c) {
        return sqrt(pow(c.y - this.y, 2) + pow(c.x - this.x, 2));
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
