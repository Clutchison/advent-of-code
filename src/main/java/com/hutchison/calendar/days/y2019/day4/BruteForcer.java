package com.hutchison.calendar.days.y2019.day4;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class BruteForcer {

    int lowerBound;
    int upperBound;
    boolean requireOneDupe;

    public List<Integer> getAnswers() {
        List<Integer> validAnswers = new ArrayList<>();
        for (int i = lowerBound; i <= upperBound; i++) {
            if (validatePassword(String.valueOf(i))) validAnswers.add(i);
        }
        return validAnswers;
    }

    private boolean validatePassword(String password) {
        boolean foundDupe = false;
        for (int i = 1; i < password.length(); i++) {
            char previous = password.charAt(i - 1);
            char current = password.charAt(i);
            if (current < previous) return false;
            if (current == previous) {
                if (!requireOneDupe || getAdjacentCharCount(password, i) == 2) foundDupe = true;
            }
        }
        return foundDupe;
    }

    private Integer getAdjacentCharCount(String s, int index) {
        int count = 1;
        char c = s.charAt(index);
        for (int i = index - 1; i >= 0; i--) {
            if (s.charAt(i) == c) count++;
            else break;
        }
        for (int i = index + 1; i < s.length(); i++) {
            if (s.charAt(i) == c) count++;
            else break;
        }
        return count;
    }
}
