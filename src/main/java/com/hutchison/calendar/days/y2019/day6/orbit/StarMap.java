package com.hutchison.calendar.days.y2019.day6.orbit;

import lombok.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Value
public class StarMap {

    Map<String, Body> starMap;

    private StarMap(Map<String, Body> starMap) {
        this.starMap = starMap;
    }

    public StarMap() {
        this.starMap = new HashMap<>();
    }

    public int getTotalOrbits() {
        return starMap.values().stream()
                .map(Body::getTotalParents)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public int getTotalOrbits(String name1, String name2) {
        Body body1 = starMap.get(name1);
        Body body2 = starMap.get(name2);
        if (body1 == null) throw new RuntimeException("No body found with name: " + name1);
        if (body2 == null) throw new RuntimeException("No body found with name: " + name2);
        List<Body> body1Parents = body1.getParents();
        List<Body> body2Parents = body2.getParents();
        Body firstSharedParent = body1Parents.stream()
                .filter(body2Parents::contains)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        String.format("%s and %s do not share an indirect orbit.", name1, name2)));
        Collections.reverse(body1Parents);
        Collections.reverse(body2Parents);
        int body1ToSharedParent = body1Parents.size() - 1 - body1Parents.indexOf(firstSharedParent);
        int body2ToSharedParent = body2Parents.size() - 1 - body2Parents.indexOf(firstSharedParent);
        return body1ToSharedParent + body2ToSharedParent;
    }

    public static StarMap fromInput(List<String> input) {
        Map<String, List<String[]>> splitInput = splitAndMapInput(input);
        Map<String, Body> starMap = new HashMap<>();
        starMap.put("COM", Body.builder().name("COM").build());

        List<String[]> currentChildren = splitInput.get("COM");
        while (currentChildren.size() > 0) {
            List<String[]> nextChildren = new ArrayList<>();
            currentChildren.forEach(child -> {
                String parentName = child[0];
                Body newBody = Body.builder()
                        .parent(starMap.get(parentName))
                        .name(child[1])
                        .build();
                if (starMap.get(newBody.getName()) != null)
                    throw new RuntimeException("Input contains two bodies with the same name");
                starMap.put(newBody.getName(), newBody);
                if (splitInput.get(newBody.getName()) != null) nextChildren.addAll(splitInput.get(newBody.getName()));
            });
            currentChildren = nextChildren;
        }
        return new StarMap(Collections.unmodifiableMap(starMap));
    }

    private static Map<String, List<String[]>> splitAndMapInput(List<String> input) {
        return input.stream()
                .map(s -> s.split("\\)"))
                .collect(Collectors.groupingBy(in -> in[0],
                        Collectors.mapping(in -> in, Collectors.toList())));
    }
}
