package com.hutchison.day.day2;


import com.hutchison.day.Day;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 extends Day {

    private static final String INPUT_LOCATION = "C:\\Users\\sean.hutchison\\Projects\\IntelliJ\\personal\\advent-of-code\\src\\main\\java\\com\\hutchison\\day\\day2\\input.txt";
    private static final String INPUT_SUFFIX = "\\day2\\input.txt";

    @Override
    public void part1() {
        List<String> ids = super.getInput(INPUT_SUFFIX);
        IdCount idCount = IdCount.count(new HashSet<>(ids));
        console.print("Hash Function: " + idCount.getTwoCount() + " x " + idCount.getThreeCount() + " = " + idCount.getProduct());
    }

    @Override
    public void part2() {
        List<String> ids = super.getInput(INPUT_SUFFIX)
                .stream()
                .sorted(Comparator.comparingInt(this::getIntValue))
                .collect(Collectors.toList());
        String answer = extractAnswer(ids);
        console.print("Correct answer: " + answer);
    }

    private String extractAnswer(List<String> ids) {
        int distance = 1;
        while (distance < ids.size()) {
            for (int i = 0; i + distance < ids.size(); i++) {
                String id1 = ids.get(i);
                String id2 = ids.get(i + distance);

                String overlappingChars = getOverlappingChars(id1, id2);
                if (!overlappingChars.equals("")) return overlappingChars;
            }
            distance++;
        }
        throw new RuntimeException("No answer found!");
    }

    private String getOverlappingChars(String id1, String id2) {
        boolean diffCharFound = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < id1.length(); i++) {
            if (id1.charAt(i) != id2.charAt(i)) {
                if (diffCharFound) {
                    return "";
                } else diffCharFound = true;
            } else {
                sb.append(id1.charAt(i));
            }
        }
        return sb.toString();
    }

    private int getIntValue(String s) {
        return s.chars().sum();
    }

    private boolean checkIds(String id1, String id2) {
        return false;
    }
}
