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
     * NOTE: - there is also pure backtracking version of this solution.
     *         But this DP version runs much faster
     *       - there is another DP solution (below), which is ever faster!!
     * */
    public static boolean canPartitionKSubsets_Approach1(int[] nums, int k) {
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
        reverse(nums);

        // Early pruning 3
        if (nums[0] > targetSum)
            return false;

        return canPartitionKSubsets_Approach1(nums, 0, sums, overallSum / k, new HashMap<>());
    }

    private static boolean canPartitionKSubsets_Approach1(int[] nums, int numPos, int[] sums, long targetSum, Map<String, Boolean> dp) {
        // think why we don't need to check if all sums are equal here in the base case.
        if (numPos == nums.length)
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
            if (canPartitionKSubsets_Approach1(nums, numPos + 1, sums, targetSum, dp)) {
                can = true;
                break;
            }
            sums[i] -= num;
        }

        dp.put(key, can);

        return dp.get(key);
    }

    private static void reverse(int[] arr) {
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    /**
     * https://leetcode.com/problems/partition-to-k-equal-sum-subsets
     * NOTE: - this DP version is faster than both solutions!!
     *       - the memoization key is only the mask instead of mask + partitionPos + currentSum.
     *         Why? because the partial sum and which partition we are currently
     *         filling can be deduced implicitly from the mask
     */
    public static boolean canPartitionKSubsets_Approach2(int[] nums, int k) {
        if (nums == null)
            return false;

        int n = nums.length;

        if (n == 0 || k < 1)
            return false;
        if (k == 1)
            return true;
        if (k > n)
            return false;

        int overallSum = 0;

        for (int num : nums)
            overallSum += num;

        // Early pruning 1
        if (overallSum % k != 0)
            return false;
        int targetSum = overallSum / k;

        // Early pruning 2
        Arrays.sort(nums);
        reverse(nums);

        // Early pruning 3
        if (nums[0] > targetSum)
            return false;

        int mask = (1 << n) - 1;
        Map<Integer, Boolean> dp = new HashMap<>();

        return canPartitionKSubsets_Approach2(nums, k, mask, 0, 0, targetSum, 0, dp);
    }

    private static boolean canPartitionKSubsets_Approach2(int[] nums, int k, int mask, int partitionPos, int numPos, long targetSum, int currentSum, Map<Integer, Boolean> dp) {
        if (partitionPos == k)
            return mask == 0;
        if (currentSum == targetSum)
            return canPartitionKSubsets_Approach2(nums, k, mask, partitionPos + 1, 0, targetSum, 0, dp);
        if (numPos == nums.length)
            return false;
        if (currentSum == 0 && dp.containsKey(mask))
            return dp.get(mask);

        for (int i = numPos; i < nums.length; i++) {
            int num = nums[i];
            int numMask = 1 << i;

            if ((mask & numMask) == 0)
                continue;
            // pruning 4
            if (currentSum + num > targetSum)
                continue;
            // pruning 5
            if (i > 0 && nums[i] == nums[i - 1] && (mask & (1 << (i - 1))) != 0)
                continue;

            if (canPartitionKSubsets_Approach2(nums, k, mask & ~numMask, partitionPos, i + 1, targetSum, currentSum + num, dp)) {
                if (currentSum == 0)
                    dp.put(mask, true);
                return true;
            }

            // pruning 6
            if (currentSum == 0)
                break;
        }

        if (currentSum == 0)
            dp.put(mask, false);

        return false;
    }
}