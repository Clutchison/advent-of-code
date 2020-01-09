package com.hutchison.calendar.days.y2019.day10;

import com.hutchison.calendar.Day;
import com.hutchison.calendar.days.y2019.day10.AsteroidMap.CoordinateAsteroidsInSightMapping;

import java.util.List;
import java.util.stream.Collectors;

public class Day10 extends Day {
    @Override
    public void part1() {
        AsteroidMap map = AsteroidMap.fromInput(getAsteroidInput());
        Long mostAsteroids = map.getBestMonitoringAsteroid().getAsteroidsInSight();
        System.out.println("Most Asteroids: " + mostAsteroids);
    }

    private List<List<Character>> getAsteroidInput() {
        return getInput().stream()
                .map(s -> s.chars()
                        .mapToObj(c -> (char) c)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    @Override // todo wrong: 1603 219 1504
    public void part2() {
        AsteroidMap map = AsteroidMap.fromInput(getAsteroidInput());
        CoordinateAsteroidsInSightMapping bestLaserLocation = map.getBestMonitoringAsteroid();
        Coordinate coordinate = bestLaserLocation.getCoordinate();
//        Coordinate coordinate = new Coordinate(8, 3);
        Coordinate lastDestroyedAsteroid = map.getDestroyedAsteroidCoordinates(coordinate, 200);
        console.print(String.format("Last destroyed asteroid coords: %s. Weird math: %d",
                lastDestroyedAsteroid.toString(),
                lastDestroyedAsteroid.getX() * 100 + lastDestroyedAsteroid.getY()));


    }
}
