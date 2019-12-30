package com.hutchison.util;

import java.util.ArrayList;
import java.util.List;

// Cribbed from https://www.baeldung.com/java-array-permutations
public class Combination {

    private static <T> void findCombos(int n, List<T> elements, List<List<T>> masterList) {

        if (n == 1) {
            masterList.add(elements);
        } else {
            for (int i = 0; i < n - 1; i++) {
                findCombos(n - 1, new ArrayList<>(elements), masterList);
                if (n % 2 == 0) {
                    swap(elements, i, n - 1);
                } else {
                    swap(elements, 0, n - 1);
                }
            }
            findCombos(n - 1, new ArrayList<>(elements), masterList);
        }
    }

    private static <T> void swap(List<T> input, int a, int b) {
        T tmp = input.get(a);
        input.set(a, input.get(b));
        input.set(b, tmp);
    }

    public static <T> List<List<T>> getAllCombinations(List<T> elements) {
        if (elements.size() > 10) throw new RuntimeException("ELements size cannot exceed 10");
        List<List<T>> masterList = new ArrayList<>();
        findCombos(elements.size(), elements, masterList);
        return masterList;
    }
}

