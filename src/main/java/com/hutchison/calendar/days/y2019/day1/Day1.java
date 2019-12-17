package com.hutchison.calendar.days.y2019.day1;

import com.hutchison.calendar.Day;

public class Day1 extends Day {
    @Override
    public void part1() {
        console.print(sumTotalFuel(false));
    }

    @Override
    public void part2() {
        console.print(sumTotalFuel(true));
    }

    private Integer sumTotalFuel(boolean includeFuelInMass) {
        return getInput().stream()
                .map(Integer::parseInt)
                .map(currentFuel -> sumModuleFuel(currentFuel, includeFuelInMass))
                .reduce(0, Integer::sum);
    }

    private Integer sumModuleFuel(Integer mass, boolean includeFuelInMass) {
        console.print("--- Module mass: " + mass);
        Integer moduleFuel = calculateFuel(mass);
        console.print("Module fuel: " + moduleFuel);
        if (includeFuelInMass) moduleFuel += sumFuelForFuel(moduleFuel);
        console.print("--- Total Fuel: " + moduleFuel);
        return moduleFuel;
    }

    private Integer sumFuelForFuel(Integer currentFuel) {
        int fuel = calculateFuel(currentFuel);
        console.print("Fuel for the Fuel: " + fuel + " for " + currentFuel + ".");
        if (fuel > 0) return fuel + sumFuelForFuel(fuel);
        else return 0;
    }

    private Integer calculateFuel(int mass) {
        return (int) (Math.floor(mass / 3.0) - 2);
    }
}
