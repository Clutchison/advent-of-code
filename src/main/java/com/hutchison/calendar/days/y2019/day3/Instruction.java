package com.hutchison.calendar.days.y2019.day3;

import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
class Instruction {

    Direction direction;
    int distance;

    @Builder
    public Instruction(Direction direction, int distance) {
        this.direction = direction;
        this.distance = distance;
    }

    static Instruction fromString(String inString) {
        Direction direction = Direction.fromChar(inString.charAt(0));
        int distance = Integer.parseInt(inString.substring(1));
        return new Instruction(direction, distance);
    }

    public List<Point> getPoints(Point origin) {
        List<Point> points = new ArrayList<>();
        Point latestPoint = origin;
        for (int i = 0; i < distance; i++) {
            Point newPoint = latestPoint.move(direction);
            points.add(newPoint);
            latestPoint = newPoint;
        }
        return points;
    }
}
