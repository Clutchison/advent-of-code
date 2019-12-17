package com.hutchison.calendar.days.y2019.day3;

import com.hutchison.calendar.Day;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

public class Day3 extends Day {

    @Override
    public void part1() {
        Wire wire1 = Wire.fromInstructions(Arrays.asList(input.get(0).split(",")));
        Wire wire2 = Wire.fromInstructions(Arrays.asList(input.get(1).split(",")));
        Set<Point> intersectingPoints = wire1.getIntersections(wire2.getPoints());
        console.print("Intersecting Points: ");
        Point origin = new Point(0, 0);
        intersectingPoints.remove(origin);
        intersectingPoints.forEach(pt -> console.print(pt.toString() + ", " + pt.diff(origin)));
        Point closestToOrigin = intersectingPoints.stream()
                .sorted(Comparator.comparing(p -> p.diff(origin)))
                .collect(Collectors.toList())
                .get(0);
        console.print(String.format("Closest point: %s with distance of %d", closestToOrigin.toString(), closestToOrigin.diff(origin)));
    }

    @Override
    public void part2() {

    }
}
