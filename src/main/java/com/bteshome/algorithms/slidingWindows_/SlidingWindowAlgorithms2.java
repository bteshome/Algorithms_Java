package com.bteshome.algorithms.slidingWindows_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlidingWindowAlgorithms2 {
    /**
     * NOTE - even though this solution is under sliding windows category,
     *        the implementation is backtracking based.
     *        And it exceeds leetcode time limit.
     *        The sliding window solution below is faster.
     * https://leetcode.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/
     * */
    public static int minimumDifferenceBetweenHighestAndLowest_BacktrackingBased(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 2) {
            return 0;
        }

        return minimumDifferenceBetweenHighestAndLowest_BacktrackingBased(nums, k, 0, new ArrayList<Integer>(), Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private static int minimumDifferenceBetweenHighestAndLowest_BacktrackingBased(int[] nums, int k, int pos, List<Integer> combination, int min, int max) {
        if (combination.size() == k) {
            return max - min;
        }

        if (pos == nums.length) {
            return Integer.MAX_VALUE;
        }

        int minDifference = Integer.MAX_VALUE;

        combination.add(nums[pos]);
        minDifference = Math.min(minDifference, minimumDifferenceBetweenHighestAndLowest_BacktrackingBased(nums, k, pos + 1, combination, Math.min(min, nums[pos]), Math.max(max, nums[pos])));
        combination.removeLast();
        minDifference = Math.min(minDifference, minimumDifferenceBetweenHighestAndLowest_BacktrackingBased(nums, k, pos + 1, combination, min, max));

        return minDifference;
    }

    public static int minimumDifferenceBetweenHighestAndLowest_SlidingWindowBased(int[] nums, int k) {
        if (nums == null || nums.length < k || k < 2) {
            return 0;
        }

        Arrays.sort(nums);
        int minDifference = Integer.MAX_VALUE;

        for (int windowStart = 0; windowStart < nums.length - k + 1; windowStart++) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = windowStart; i < windowStart + k ; i++) {
                min = Math.min(min, nums[i]);
                max = Math.max(max, nums[i]);
            }
            minDifference = Math.min(minDifference, max - min);
        }

        return minDifference;
    }

    /**
     * https://leetcode.com/problems/maximum-strong-pair-xor-i/
     * */
    public static int maximumStrongPairXor(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);
        int maxXor =  Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int a = nums[i];
                int b = nums[j];
                if (Math.abs(a - b) <= Math.min(a, b)) {
                    maxXor = Math.max(maxXor, a ^ b);
                } else {
                    break;
                }
            }
        }

        return maxXor;
    }

    /**
     * https://leetcode.com/problems/diet-plan-performance/
     * */
    public static int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        if (calories == null || calories.length < k) {
            return 0;
        }

        int points = 0;
        int sum = 0;

        for (int i = 0; i < calories.length; i++) {
            sum += calories[i];
            if (i >= k) {
                sum -= calories[i - k];
            }
            if (i >= k - 1) {
                if (sum < lower) {
                    points--;
                } else if (sum > upper) {
                    points++;
                }
            }
        }

        return points;
    }
}
