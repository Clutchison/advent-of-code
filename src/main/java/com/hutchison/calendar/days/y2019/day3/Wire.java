package com.hutchison.calendar.days.y2019.day3;

import lombok.Value;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

@Value
public class Wire {

    List<Point> points;
    List<Integer> vertices;

    private Wire(List<Point> points, List<Integer> vertices) {
        this.points = points;
        this.vertices = vertices;
    }

    public Set<Point> getIntersections(List<Point> incomingPoints) {
        Set<Point> copy = new HashSet<>(points);
        return copy.stream()
                .filter(incomingPoints::contains)
                .collect(Collectors.toSet());
    }

    public Integer getIndexOfPoint(Point point) {
        return points.indexOf(point);
    }

    public static Wire fromInstructions(List<String> inString) {
        List<Point> points = new ArrayList<>(singletonList(new Point(0, 0)));
        List<Integer> vertices = new ArrayList<>();
        inString.stream()
                .map(Instruction::fromString)
                .forEach(inst -> {
                    List<Point> newPoints = inst.getPoints(points.get(points.size() - 1));
                    points.addAll(newPoints);
                    if (newPoints.size() > 0) vertices.add(points.size() - 1);
                });
        return new Wire(points, vertices);
    }
}
