package com.hutchison.calendar.days.y2019.day3;

import com.hutchison.calendar.Day;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day3 extends Day {

    @Override
    public void part1() {
        Wire wire1 = Wire.fromInstructions(Arrays.asList(input.get(0).split(",")));
        Wire wire2 = Wire.fromInstructions(Arrays.asList(input.get(1).split(",")));
        Set<Point> intersectingPoints = wire1.getIntersections(wire2.getPoints());
        Point origin = new Point(0, 0);
        intersectingPoints.remove(origin);
        Point closestToOrigin = intersectingPoints.stream()
                .sorted(Comparator.comparing(p -> p.diff(origin)))
                .collect(Collectors.toList())
                .get(0);
        console.print("Intersecting Points: ");
        intersectingPoints.forEach(pt -> console.print(pt.toString() + ", " + pt.diff(origin)));
        console.print(String.format("Closest point: %s with distance of %d", closestToOrigin.toString(), closestToOrigin.diff(origin)));
    }

    @Override
    public void part2() {
        Wire wire1 = Wire.fromInstructions(Arrays.asList(input.get(0).split(",")));
        Wire wire2 = Wire.fromInstructions(Arrays.asList(input.get(1).split(",")));
        List<Point> intersectingPoints = wire1.getIntersections(wire2.getPoints()).stream()
                .sorted(Comparator.comparing(p -> getCombinedSteps(wire1, wire2, p)))
                .collect(Collectors.toList());
        console.print("Intersecting Points: ");
        intersectingPoints.forEach(pt -> console.print(pt.toString() + ", " + getCombinedSteps(wire1, wire2, pt)));
    }

    private int getCombinedSteps(Wire w1, Wire w2, Point p) {
        int steps1 = w1.getIndexOfPoint(p);
        int steps2 = w2.getIndexOfPoint(p);
        if (steps1 == -1 || steps2 == -1) throw new RuntimeException("Point not in both wires");
        return steps1 + steps2;
    }
}
