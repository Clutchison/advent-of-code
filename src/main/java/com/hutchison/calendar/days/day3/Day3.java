package com.hutchison.calendar.days.day3;

import com.hutchison.calendar.Day;
import com.hutchison.calendar.days.day3.claim.Claim;

import java.util.Set;
import java.util.stream.Collectors;

public class Day3 extends Day {

    @Override
    public void part1() {
        Fabric fabric = makeFabric();

        long count = fabric.getFabric().entrySet().stream()
                .filter(es -> es.getValue() > 1)
                .count();
        console.print("Number of inches covered by more than one claim: " + count);
    }

    @Override
    public void part2() {
        Fabric fabric = makeFabric();
        Claim goodClaim = fabric.getClaims().stream()
                .filter(fabric::claimDoesNotOverlap)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Failed to find good claim."));
        console.print("Good claim: " + goodClaim.toString());
    }

    private Fabric makeFabric() {
        Fabric fabric = new Fabric();
        Set<Claim> claims = input.stream()
                .map(Claim::fromString)
                .collect(Collectors.toSet());
        claims.forEach(fabric::addClaim);
        return fabric;
    }
}
