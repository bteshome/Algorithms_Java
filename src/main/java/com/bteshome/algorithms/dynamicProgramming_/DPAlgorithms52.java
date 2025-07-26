package com.bteshome.algorithms.dynamicProgramming_;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DPAlgorithms52 {
    /**
     * TODO
     * https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference
     * NOTE: Below are two different solutions. Both are correct but too slow,
     *       and don't pass LeetCode time limit test
     * */
    public static int minimumDifference(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length % 2 == 1)
            return 0;

        int min = minimumDifference(nums, 0, 0, 0, new HashMap<>());

        return min;
    }

    private static int minimumDifference(int[] nums, int pos, int diff, int selectedInGroup1SoFar, Map<String, Integer> dp) {
        if (pos == nums.length)
            return Math.abs(diff);

        String key = "%s,%s,%s".formatted(pos, diff, selectedInGroup1SoFar);

        if (dp.containsKey(key))
            return dp.get(key);

        int num = nums[pos];
        int min = Integer.MAX_VALUE;
        int selectedInGroup2SoFar = pos - selectedInGroup1SoFar;

        if (selectedInGroup1SoFar < nums.length / 2)
            min = minimumDifference(nums, pos + 1, diff + num, selectedInGroup1SoFar + 1, dp);

        if (selectedInGroup2SoFar < nums.length / 2)
            min = Math.min(min, minimumDifference(nums, pos + 1, diff - num, selectedInGroup1SoFar, dp));

        dp.put(key, min);

        return dp.get(key);
    }

    public static int minimumDifference2(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length % 2 == 1)
            return 0;

        int totalSum = Arrays.stream(nums).sum();

        int sum1 = minimumDifference2(nums, 0, totalSum / 2, 0, new HashMap<>());
        int sum2 = totalSum - sum1;

        return Math.abs(sum1 - sum2);
    }

    private static int minimumDifference2(int[] nums, int pos, int target, int selectedInGroup1SoFar, Map<String, Integer> dp) {
        if (selectedInGroup1SoFar == nums.length / 2)
            return 0;
        if (pos == nums.length)
            return Integer.MIN_VALUE;

        String key = "%s,%s,%s".formatted(pos, target, selectedInGroup1SoFar);

        if (dp.containsKey(key))
            return dp.get(key);

        int selectedInGroup2SoFar = pos - selectedInGroup1SoFar;
        int remaining = nums.length - pos;
        int remainingGroup1 = nums.length / 2 - selectedInGroup1SoFar;
        int remainingGroup2 = nums.length / 2 - selectedInGroup2SoFar;

        if (remainingGroup1 + remainingGroup2 < remaining) {
            dp.put(key, Integer.MIN_VALUE);
            return dp.get(key);
        }

        int num = nums[pos];
        int closestToTarget = Integer.MIN_VALUE;

        if (selectedInGroup1SoFar < nums.length / 2) {
            int addToGroup1 = minimumDifference2(nums, pos + 1,target - num, selectedInGroup1SoFar + 1, dp);
            if (addToGroup1 != Integer.MIN_VALUE)
                closestToTarget = num + addToGroup1;
        }

        if (selectedInGroup2SoFar < nums.length / 2) {
            int addToGroup2 = minimumDifference2(nums, pos + 1, target, selectedInGroup1SoFar, dp);
            if (addToGroup2 != Integer.MIN_VALUE) {
                if (closestToTarget == Integer.MIN_VALUE || Math.abs(addToGroup2 - target) < Math.abs(closestToTarget - target))
                    closestToTarget = addToGroup2;
            }
        }

        dp.put(key, closestToTarget);

        return dp.get(key);
    }
}