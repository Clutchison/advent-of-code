package com.hutchison.calendar.days.day3;

import com.hutchison.calendar.days.day3.claim.Claim;
import lombok.Value;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Value
class Fabric {

    final Map<Coordinate, Integer> fabric;
    Set<Claim> claims;

    private Fabric(Map<Coordinate, Integer> fabric,
                   Set<Claim> claims) {
        this.fabric = fabric;
        this.claims = claims;
    }

    Fabric() {
        this.fabric = new HashMap<>();
        this.claims = new HashSet<>();
    }

    void addClaim(Claim claim) {
        claim.getCoveredCoordinates().forEach(coordinate -> fabric.merge(coordinate, 1, Integer::sum));
        claims.add(claim);
    }

    boolean claimDoesNotOverlap(Claim claim) {
        for (Coordinate coordinate : claim.getCoveredCoordinates()) {
            if (fabric.get(coordinate) > 1) return false;
        }
        return true;
    }
}
