package com.hutchison.calendar.days.y2019.day10;

import lombok.Value;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Value
public class AsteroidMap {

    private static final char ASTEROID_CHAR = '#';
    List<Coordinate> asteroids;

    public CoordinateAsteroidsInSightMapping getBestMonitoringAsteroid() {
        return asteroids.stream()
                .map(a -> new CoordinateAsteroidsInSightMapping(a, asteroids.stream()
                                .filter(a1 -> a1 != a) // Skip itself
                                .map(a::getSlope)
                                .distinct()
                                .count()
                        )
                )
                .max(Comparator.comparing(coordinateAsteroidsInSightMapping -> coordinateAsteroidsInSightMapping.getAsteroidsInSight()))
                .orElseThrow(() -> new RuntimeException("Failed to find best asteroid spot."));
    }

    public Coordinate getDestroyedAsteroidCoordinates(Coordinate laserLocation, int asteroidNumToReturn) {
        // Get unsorted map.
        Map<Slope, List<Coordinate>> slopeCoordMap = asteroids.stream()
                .filter(asteroid -> !asteroid.equals(laserLocation))
                .collect(Collectors.groupingBy(laserLocation::getSlope));
        // Sort the lists by distance.
        slopeCoordMap.entrySet()
                .forEach(es -> es.setValue(es.getValue().stream()
                        .sorted(Comparator.comparing(laserLocation::getDistance))
                        .collect(Collectors.toList())));
        // Get sorted list of slopes.
        AtomicReference<List<Slope>> sortedSlopes = new AtomicReference<>(slopeCoordMap.keySet().stream()
                .sorted(Slope::compareTo)
                .collect(Collectors.toList()));

        AtomicReference<Coordinate> lastAsteroid = new AtomicReference<>();
        IntStream.range(0, asteroidNumToReturn)
                .forEach(i -> {
                    destroyAsteroid(slopeCoordMap, sortedSlopes, lastAsteroid, i);
                });

        return lastAsteroid.get();
    }

    private void destroyAsteroid(Map<Slope, List<Coordinate>> slopeCoordMap,
                                 AtomicReference<List<Slope>> sortedSlopes,
                                 AtomicReference<Coordinate> lastAsteroid,
                                 int i) {
        Slope s = sortedSlopes.get().get(i % sortedSlopes.get().size());
        lastAsteroid.set(slopeCoordMap.get(s).get(0));
        System.out.println("Asteroid destroyed at :" + lastAsteroid.get().toString());
        slopeCoordMap.get(s).remove(lastAsteroid.get());
        if (i == sortedSlopes.get().size()) sortedSlopes.set(sortedSlopes.get().stream()
                .filter(slope -> slopeCoordMap.get(slope).size() > 0)
                .collect(Collectors.toList()));
    }


    public static AsteroidMap fromInput(List<List<Character>> input) {
        return new AsteroidMap(IntStream.range(0, input.size())
                .boxed()
                .flatMap(y -> IntStream.range(0, input.get(y).size())
                        .filter(x -> input.get(y).get(x) == ASTEROID_CHAR)
                        .mapToObj(x -> new Coordinate(x, y)))
                .collect(Collectors.toList()));
    }

    @Value
    static class CoordinateAsteroidsInSightMapping {
        Coordinate coordinate;
        Long asteroidsInSight;
    }
}
