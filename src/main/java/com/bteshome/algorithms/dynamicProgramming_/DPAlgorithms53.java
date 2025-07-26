package com.bteshome.algorithms.dynamicProgramming_;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DPAlgorithms53 {
    /* https://leetcode.com/problems/target-sum/description/ */
    public static int findTargetSumWaysTopDown(int[] nums, int target) {
        if (nums == null)
            return 0;

        return findTargetSumWaysTopDown(nums, target, 0, new HashMap<>());
    }

    private static int findTargetSumWaysTopDown(int[] nums, int target, int pos, Map<String, Integer> dp) {
        if (pos == nums.length)
            return target == 0 ? 1 : 0;

        String key = "%s,%s".formatted(pos, target);

        if (dp.containsKey(key))
            return dp.get(key);

        int num = nums[pos];
        int plus = findTargetSumWaysTopDown(nums, target - num, pos + 1, dp);
        int minus = findTargetSumWaysTopDown(nums, target + num,  pos + 1, dp);
        dp.put(key, plus + minus);

        return dp.get(key);
    }

    /**
     * https://leetcode.com/problems/partition-to-k-equal-sum-subsets
     * NOTE: there is also pure backtracking version of this solution.
     *       But this DP version runs much faster
     * */
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null)
            return false;

        int n = nums.length;

        if (n == 0 || k < 1)
            return false;
        if (k == 1)
            return true;
        if (k > n)
            return false;

        int[] sums = new int[k];
        long overallSum = 0;

        for (int num : nums)
            overallSum += num;

        // Early pruning 1
        if (overallSum % k != 0)
            return false;
        long targetSum = overallSum / k;

        // Early pruning 2
        Arrays.sort(nums);

        // Early pruning 3
        if (nums[n - 1] > targetSum)
            return false;

        return canPartitionKSubsets(nums, n - 1, sums, overallSum / k, new HashMap<>());
    }

    private static boolean canPartitionKSubsets(int[] nums, int numPos, int[] sums, long targetSum, Map<String, Boolean> dp) {
        // think why we don't need to check if all sums are equal here in the base case.
        if (numPos == -1)
            return true;

        // NOTE: the sorting leads to a huge performance improvement.
        //       It does not matter what specific subsets the previous elements
        //       have been added to.
        int[] sumsCopy = sums.clone();
        Arrays.sort(sumsCopy);
        String key = Arrays.toString(sumsCopy) + ":" + numPos;

        if (dp.containsKey(key))
            return dp.get(key);

        int num = nums[numPos];
        boolean can = false;

        for (int i = 0; i < sums.length; i++) {
            // pruning
            if (sums[i] + num > targetSum)
                continue;
            sums[i] += num;
            if (canPartitionKSubsets(nums, numPos - 1, sums, targetSum, dp)) {
                can = true;
                break;
            }
            sums[i] -= num;
        }

        dp.put(key, can);

        return dp.get(key);
    }
}