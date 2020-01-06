package com.hutchison.calendar.days.y2019.day10;

import lombok.Value;

@Value
public class Coordinate {

    int x;
    int y;

    public Slope getSlope(Coordinate c) {
        return new Slope(this.y - c.y, this.x - c.x);
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
