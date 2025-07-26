package com.bteshome.algorithms.dynamicProgramming_;

import java.util.HashMap;
import java.util.Map;

public class DPAlgorithms37 {
    /**
     * https://leetcode.com/problems/partition-equal-subset-sum
     * NOTE:
     *      - this one uses a 2D array to cache state, and is faster than the next one,
     *        which uses a array of maps.
     *      - But if sums were sparse, the second approach would have been faster.
     * */
    public static boolean canPartitionTopDown(int[] nums) {
        if (nums == null)
            return false;

        int overallSum = 0;
        for (int num : nums)
            overallSum += num;

        if (overallSum % 2 == 1)
            return false;

        int targetSum = overallSum / 2;
        Boolean[][] dp = new Boolean[nums.length][targetSum + 1];

        return canPartitionTopDown(nums, 0, 0, targetSum, dp);
    }

    private static boolean canPartitionTopDown(int[] nums, int pos, int currentSum, int targetSum, Boolean[][] dp) {
        if (currentSum == targetSum)
            return true;
        if (currentSum > targetSum || pos == nums.length)
            return false;

        if (dp[pos][currentSum] == null) {
            boolean can =
                    canPartitionTopDown(nums, pos + 1, currentSum, targetSum, dp) ||
                    canPartitionTopDown(nums, pos + 1, currentSum + nums[pos], targetSum, dp);
            dp[pos][currentSum] = can;
        }

        return dp[pos][currentSum];
    }

    public static boolean canPartitionTopDown2(int[] nums) {
        if (nums == null)
            return false;

        int overallSum = 0;
        for (int num : nums)
            overallSum += num;

        if (overallSum % 2 == 1)
            return false;

        int targetSum = overallSum / 2;
        Map<Integer, Boolean>[] dp = new HashMap[nums.length];
        for (int i = 0; i < nums.length; i++)
            dp[i] = new HashMap<>();

        return canPartitionTopDown2(nums, 0, 0, targetSum, dp);
    }

    private static boolean canPartitionTopDown2(int[] nums, int pos, int currentSum, int targetSum, Map<Integer, Boolean>[] dp) {
        if (currentSum == targetSum)
            return true;
        if (currentSum > targetSum || pos == nums.length)
            return false;

        if (!dp[pos].containsKey(currentSum)) {
            boolean can =
                    canPartitionTopDown2(nums, pos + 1, currentSum, targetSum, dp) ||
                    canPartitionTopDown2(nums, pos + 1, currentSum + nums[pos], targetSum, dp);
            dp[pos].put(currentSum, can);
        }

        return dp[pos].get(currentSum);
    }
}
