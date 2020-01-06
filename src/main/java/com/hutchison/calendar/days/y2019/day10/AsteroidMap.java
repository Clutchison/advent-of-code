package com.hutchison.calendar.days.y2019.day10;

import lombok.Value;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Value
public class AsteroidMap {

    private static final char ASTEROID_CHAR = '#';
    List<Coordinate> asteroids;


    public static AsteroidMap fromInput(List<List<Character>> input) {
//        List<Coordinate> list = new ArrayList<>();
//        for (int y = 0; y < input.size(); y++) {
//            List<Character> row = input.get(y);
//            for (int x = 0; x < row.size(); x++) {
//                list.add(new Coordinate(x, y));
//            }
//        }
//        return new AsteroidMap(list);
        return new AsteroidMap(IntStream.range(0, input.size())
                .boxed()
                .flatMap(y -> IntStream.range(0, input.get(y).size())
                        .filter(x -> input.get(y).get(x) == ASTEROID_CHAR)
                        .mapToObj(x -> new Coordinate(x, y)))
                .collect(Collectors.toList()));
    }

    public CoordinateAsteroidsInSightMapping getBestMonitoringAsteroid() {
        return asteroids.stream()
                .map(a -> new CoordinateAsteroidsInSightMapping(a, asteroids.stream()
                                .filter(a1 -> a1 != a) // Skip itself
                                .map(a::getSlope)
                                .distinct()
                                .count()
                        )
                )
                .max(Comparator.comparing(CoordinateAsteroidsInSightMapping::getAsteroidsInSight))
                .orElseThrow(() -> new RuntimeException("Failed to find best asteroid spot."));
    }

    @Value
    static class CoordinateAsteroidsInSightMapping {
        Coordinate coordinate;
        Long asteroidsInSight;
    }
}
