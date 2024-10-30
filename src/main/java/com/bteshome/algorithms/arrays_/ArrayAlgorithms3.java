package com.bteshome.algorithms.arrays_;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArrayAlgorithms3 {
    /**
     * leetcode https://leetcode.com/problems/pascals-triangle/description/?envType=problem-list-v2&envId=array&difficulty=EASY
     * */
    public static List<List<Integer>> generatePascalTriangle(int numRows) {
        var pascal = new ArrayList<List<Integer>>(numRows);
        for (int i = 0; i < numRows; i++) {
            var row = new ArrayList<Integer>(i + 1);
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    var prevRow = pascal.get(i - 1);
                    row.add(prevRow.get(j - 1) + prevRow.get(j));
                }
            }
            pascal.add(row);
        }

        return pascal;
    }

    /**
     * leetcode https://leetcode.com/problems/single-number/submissions/1404280892/?envType=problem-list-v2&envId=array&difficulty=EASY
     * */
    public int singleNumber(int[] nums) {
        var seen = new HashMap<Integer, Boolean>();
        for (int i = 0; i < nums.length; i++) {
            var current = nums[i];
            if (seen.containsKey(current)) {
                seen.remove(current);
            } else {
                seen.put(current, true);
            }
        }

        return seen.keySet().stream().findFirst().get();
    }

    /**
     * https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
     * */
    public static int minAddToMakeParenthesesValid(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int unclosedCount = 0;
        int invalidOpenCount = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                unclosedCount++;
            } else {
                if (unclosedCount > 0) {
                    unclosedCount--;
                } else {
                    invalidOpenCount++;
                }
            }
        }

        return unclosedCount + invalidOpenCount;
    }

    /**
     * https://leetcode.com/problems/intersection-of-two-arrays-ii/
     * */
    public static int[] intersectionOfTwoArraysII(int[] nums1, int[] nums2) {
        var d = new HashMap<Integer, Integer>();
        for (int n : nums1) {
            d.put(n, d.getOrDefault(n, 0) + 1);
        }

        var result = new ArrayList<Integer>();
        for (int n : nums2) {
            if (d.containsKey(n)) {
                result.add(n);
                if (d.get(n) == 1) {
                    d.remove(n);
                } else {
                    d.put(n, d.get(n) - 1);
                }
            }
        }

        var output = new int[result.size()];
        for (int i = 0; i < output.length; i++) {
            output[i] = result.get(i);
        }

        return output;
    }
}
