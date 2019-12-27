package com.hutchison.util;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.ArrayList;
import java.util.List;

// Cribbed from https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
// Modified by Sean Hutchison
// Java program to print all combination of size r in an array of size n
class Combination {

    /* arr[]  ---> Input Array
    data[] ---> Temporary array to store current combination
    start & end ---> Staring and Ending indexes in arr[]
    index  ---> Current index in data[]
    r ---> Size of a combination to be printed */
    static <T> void combinationUtil(List<T> elements, List<List<T>> tempList, int start,
                                    int end, int index, long r) {
        // Current combination is ready to be printed, print it
        if (index == r) {
//            for (int j = 0; j < r; j++)
//                System.out.print(tempList[j] + " ");
//            System.out.println("");
            return;
        }

        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            tempList.get(index).add(eleme[index] = elements[i];
            combinationUtil(elements, tempList, i + 1, end, index + 1, r);
        }
    }

    // The main function that prints all combinations of size r
    // in arr[] of size n. This function mainly uses combinationUtil()
    static <T> List<List<T>> printCombination(List<T> elements, int n, int r) {

        // A temporary array to store all combination one by one
        List<List<T>> tempList = new ArrayList<>();

        // Get all combinations.
        combinationUtil(elements, tempList, 0, n - 1, 0, r);
        return tempList;
    }

    /*Driver function to check for above function*/
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5};
        int r = 3;
        int n = arr.length;
        printCombination(arr, n, r);
    }

    public static <T> List<List<T>> getCombinations(List<T> elements) {
        long factorial = CombinatoricsUtils.factorial(elements.size());
        if (factorial > Integer.MAX_VALUE) throw new RuntimeException("Potential return value is too large!");
        return printCombination(elements, elements.size(), (int) factorial);
    }

}

/* This code is contributed by Devesh Agrawal */
