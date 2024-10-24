package com.bteshome.algorithms.slidingWindows_;

import java.awt.geom.Arc2D;
import java.util.HashMap;
import java.util.Optional;

public class SlidingWindowAlgorithms1 {
    /**
     * https://leetcode.com/problems/contains-duplicate-ii/
     * */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        var frequencies = new HashMap<Integer, Integer>();

        for (int i = 0; i <= Math.min(k, nums.length - 1); i++) {
            int num = nums[i];
            frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);
            if (frequencies.get(num) > 1) {
                return true;
            }
        }

        for (int i = 1; i < nums.length - k; i++) {
            int leftNum = nums[i - 1];
            int rightNum = nums[i + k];
            frequencies.put(leftNum, frequencies.getOrDefault(leftNum, 0) - 1);
            frequencies.put(rightNum, frequencies.getOrDefault(rightNum, 0) + 1);
            if (frequencies.get(rightNum) > 1) {
                return true;
            }
        }

        return false;
    }

    /**
     * https://leetcode.com/problems/maximum-average-subarray-i/
     * */
    public static double findMaxAverage(int[] nums, int k) {
        if (nums == null) {
            return Integer.MIN_VALUE;
        }

        int sum = 0;
        double maxAverage = Double.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i >= k - 1) {
                double average = ((double)sum) / k;
                if (maxAverage == Double.MIN_VALUE) {
                    maxAverage = average;
                } else {
                    maxAverage = Math.max(maxAverage, average);
                }
                sum -= nums[i - k + 1];
            }
        }

        return maxAverage;
    }
}
