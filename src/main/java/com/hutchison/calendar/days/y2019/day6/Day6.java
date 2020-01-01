package com.hutchison.calendar.days.y2019.day6;

import com.hutchison.calendar.Day;
import com.hutchison.calendar.days.y2019.day6.orbit.StarMap;

public class Day6 extends Day {
    @Override
    public void part1() {
        StarMap starMap = StarMap.fromInput(input);
        int totalOrbits = starMap.getTotalOrbits();
        console.print("Total orbits: " + totalOrbits);
    }

    @Override
    public void part2() {
        StarMap starMap = StarMap.fromInput(input);
        int orbitDistance = starMap.getTotalOrbits("YOU", "SAN");
        console.print("Total hops between you and Santa: " + orbitDistance);
    }
}
