package com.hutchison.calendar.days.y2019.day10;

import com.hutchison.calendar.Day;
import com.hutchison.calendar.days.y2019.day10.AsteroidMap.CoordinateAsteroidsInSightMapping;

import java.util.List;
import java.util.stream.Collectors;

// todo: 303
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

    @Override
    public void part2() {
        AsteroidMap map = AsteroidMap.fromInput(getAsteroidInput());
        CoordinateAsteroidsInSightMapping bestLaserLocation = map.getBestMonitoringAsteroid();
        Coordinate coordinate = bestLaserLocation.getCoordinate();



    }
}
