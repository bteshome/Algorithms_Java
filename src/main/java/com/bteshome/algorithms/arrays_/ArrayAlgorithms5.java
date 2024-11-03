package com.bteshome.algorithms.arrays_;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;

public class ArrayAlgorithms5 {
    /**
     * https://leetcode.com/problems/missing-ranges/
     * */
    public static List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        var list = new ArrayList<List<Integer>>();

        if (lower > upper) {
            return list;
        }

        if (nums == null || nums.length == 0) {
            list.add(List.of(lower, upper));
            return list;
        }

        if (nums[0] > lower) {
            list.add(List.of(lower, nums[0] - 1));
        }

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1] + 1) {
                list.add(List.of(nums[i - 1] + 1, nums[i] - 1));
            }
        }

        if (nums[nums.length - 1] < upper) {
            list.add(List.of(nums[nums.length - 1] + 1, upper));
        }

        return list;
    }

    /**
     * https://leetcode.com/problems/count-and-say/
     * */
    public static String countAndSay(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n is less than 1.");
        }

        return countAndSayRle(n);
    }

    private static String countAndSayRle(int n) {
        if (n == 1) {
            return "1";
        }

        var prevRle = countAndSayRle(n - 1);
        StringBuilder builder = new StringBuilder();
        char c = prevRle.charAt(0);
        int count = 1;

        for (int i = 1; i < prevRle.length(); i++) {
            if (prevRle.charAt(i) == c) {
                count++;
            } else {
                builder.append(count);
                builder.append(c);
                c = prevRle.charAt(i);
                count = 1;
            }
        }

        builder.append(count);
        builder.append(c);

        return builder.toString();
    }

    /**
     * https://leetcode.com/problems/merge-intervals/
     * */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length < 2) {
            return intervals;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        int[][] merged = new int[intervals.length][];
        int i = 0;
        merged[0] = new int[]{intervals[0][0], intervals[0][1]};

        for (int j = 1; j < intervals.length; j++) {
            int[] interval = intervals[j];
            if (merged[i][1] >= interval[0]) {
                merged[i][1] = Math.max(merged[i][1], interval[1]);
            } else {
                i++;
                merged[i] = new int[]{interval[0], interval[1]};
            }
        }

        return Arrays.copyOf(merged, i + 1);
    }
}