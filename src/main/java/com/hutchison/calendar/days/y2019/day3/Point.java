package com.hutchison.calendar.days.y2019.day3;

import lombok.Value;

@Value
public class Point {
    int x;
    int y;

    public Integer diff(Point p2) {
        int xDelta = Math.abs(p2.x - x);
        int yDelta = Math.abs(p2.y - y);
        return xDelta + yDelta;
    }

    public Point move(Direction direction) {
        switch (direction) {
            case U:
                return new Point(x, y + 1);
            case D:
                return new Point(x, y - 1);
            case R:
                return new Point(x + 1, y);
            case L:
                return new Point(x - 1, y);
            default:
                throw new RuntimeException("Bad direction: " + direction);
        }
    }

    @Override
    public String toString() {
        return "<" + x + ", " + y + ">";
    }
}

