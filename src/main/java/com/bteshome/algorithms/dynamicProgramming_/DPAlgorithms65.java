package com.bteshome.algorithms.dynamicProgramming_;

import java.util.Arrays;

public class DPAlgorithms65 {
    /**
     * https://leetcode.com/problems/minimum-incompatibility
     * NOTE:
     *      - the mas-only-as-dp-key is accepted
     *      - the bruteforce and all-states-as-dp-keys are not accepted
     *      - it can also be solved using a different, probably more efficient, approach
     * */
    public static class MinimumIncompatibility_MaskOnlyAsDPKey {
        public int minimumIncompatibility(int[] nums, int k) {
            if (nums == null || k < 1 || nums.length == 0 || nums.length % k != 0)
                return -1;

            Arrays.sort(nums);

            int n = nums.length;
            int mask = (1 << n) - 1;
            Integer[] dp = new Integer[mask + 1];

            return minimumIncompatibility(nums, k, 0, 0, 0, Integer.MAX_VALUE, Integer.MIN_VALUE, 0, mask, null, dp);
        }

        private int minimumIncompatibility(int[] nums, int k, int numPos, int partitionPos, int partitionSize, int partitionMin, int partitionMax, int incompatibilitySum, int mask, Integer lastUsedNum, Integer[] dp) {
            int n = nums.length;

            if (partitionPos == k)
                return incompatibilitySum;
            if (partitionSize == n / k)
                return minimumIncompatibility(nums, k, 0, partitionPos + 1, 0, Integer.MAX_VALUE, Integer.MIN_VALUE, incompatibilitySum + partitionMax - partitionMin, mask, null, dp);
            if (numPos == n)
                return -1;
            if (partitionSize + Integer.bitCount(mask) < n / k)
                return -1;
            if (partitionSize == 0 && dp[mask] != null)
                return dp[mask];

            for (; numPos < n; numPos++)
                if ((mask & (1 << numPos)) != 0)
                    break;

            if (numPos == n)
                return -1;

            int minIncompatibilitySum = Integer.MAX_VALUE;

            int next = minimumIncompatibility(nums, k, numPos + 1, partitionPos, partitionSize, partitionMin, partitionMax, incompatibilitySum, mask, lastUsedNum, dp);
            if (next != -1)
                minIncompatibilitySum = next;

            int numMask = 1 << numPos;
            int num = nums[numPos];
            if (lastUsedNum == null || num != lastUsedNum) {
                int newPartitionMin = Math.min(partitionMin, num);
                int newPartitionMax = Math.max(partitionMax, num);
                int newNumMask = mask & ~numMask;
                next = minimumIncompatibility(nums, k, numPos + 1, partitionPos, partitionSize + 1, newPartitionMin, newPartitionMax, incompatibilitySum, newNumMask, num, dp);
                if (next != -1)
                    minIncompatibilitySum = Math.min(minIncompatibilitySum, next);
            }

            if (partitionSize == 0)
                dp[mask] = minIncompatibilitySum == Integer.MAX_VALUE ? -1 : minIncompatibilitySum;

            return minIncompatibilitySum == Integer.MAX_VALUE ? -1 : minIncompatibilitySum;
        }
    }

    public static class MinimumIncompatibility_AllStateVarsAsDPKey {
        public int minimumIncompatibility(int[] nums, int k) {
            if (nums == null || k < 1 || nums.length == 0 || nums.length % k != 0)
                return -1;

            Arrays.sort(nums);

            int n = nums.length;
            int mask = (1 << n) - 1;
            int maxNum = nums[n - 1];
            Integer[][][][] dp = new Integer[mask + 1][maxNum + 1][maxNum + 1][maxNum + 1];

            return minimumIncompatibility(nums, k, 0, 0, 0, nums[n - 1], nums[0], 0, mask, null, dp);
        }

        private int minimumIncompatibility(int[] nums, int k, int numPos, int partitionPos, int partitionSize, int partitionMin, int partitionMax, int incompatibilitySum, int mask, Integer lastUsedNum, Integer[][][][] dp) {
            int n = nums.length;

            if (partitionPos == k)
                return incompatibilitySum;
            if (partitionSize == n / k)
                return minimumIncompatibility(nums, k, 0, partitionPos + 1, 0, 0, 0, incompatibilitySum + partitionMax - partitionMin, mask, null, dp);
            if (numPos == n)
                return -1;
            if (partitionSize + Integer.bitCount(mask) < n / k)
                return -1;

            int lastUsedNumIndex = lastUsedNum == null ? 0 : lastUsedNum;
            if (dp[mask][partitionMin][partitionMax][lastUsedNumIndex] != null)
                return dp[mask][partitionMin][partitionMax][lastUsedNumIndex];

            for (; numPos < n; numPos++)
                if ((mask & (1 << numPos)) != 0)
                    break;

            if (numPos == n)
                return -1;

            int minIncompatibilitySum = Integer.MAX_VALUE;

            int next = minimumIncompatibility(nums, k, numPos + 1, partitionPos, partitionSize, partitionMin, partitionMax, incompatibilitySum, mask, lastUsedNum, dp);
            if (next != -1)
                minIncompatibilitySum = next;

            int numMask = 1 << numPos;
            int num = nums[numPos];
            if (lastUsedNum == null || num != lastUsedNum) {
                int newPartitionMin = partitionMin == 0 ? num : Math.min(partitionMin, num);
                int newPartitionMax = partitionMax == 0 ? num : Math.max(partitionMax, num);
                int newNumMask = mask & ~numMask;
                next = minimumIncompatibility(nums, k, numPos + 1, partitionPos, partitionSize + 1, newPartitionMin, newPartitionMax, incompatibilitySum, newNumMask, num, dp);
                if (next != -1)
                    minIncompatibilitySum = Math.min(minIncompatibilitySum, next);
            }

            dp[mask][partitionMin][partitionMax][lastUsedNumIndex] = minIncompatibilitySum == Integer.MAX_VALUE ? -1 : minIncompatibilitySum;

            return dp[mask][partitionMin][partitionMax][lastUsedNumIndex];
        }
    }

    public static class MinimumIncompatibility_Bruteforce {
        public int minimumIncompatibility(int[] nums, int k) {
            if (nums == null || k < 1 || nums.length == 0 || nums.length % k != 0)
                return -1;

            Arrays.sort(nums);
            int mask = (1 << nums.length) - 1;

            return minimumIncompatibility(nums, k, 0, 0, 0, Integer.MAX_VALUE, Integer.MIN_VALUE, 0, mask, null);
        }

        private int minimumIncompatibility(int[] nums, int k, int numPos, int partitionPos, int partitionSize, int partitionMin, int partitionMax, int incompatibilitySum, int mask, Integer lastUsedNum) {
            if (partitionPos == k)
                return incompatibilitySum;
            if (partitionSize == nums.length / k)
                return minimumIncompatibility(nums, k, 0, partitionPos + 1, 0, Integer.MAX_VALUE, Integer.MIN_VALUE, incompatibilitySum + partitionMax - partitionMin, mask, null);
            if (numPos == nums.length)
                return -1;
            if (partitionSize + Integer.bitCount(mask) < nums.length / k)
                return -1;

            for (; numPos < nums.length; numPos++)
                if ((mask & (1 << numPos)) != 0)
                    break;

            if (numPos == nums.length)
                return -1;

            int minIncompatibilitySum = Integer.MAX_VALUE;

            int next = minimumIncompatibility(nums, k, numPos + 1, partitionPos, partitionSize, partitionMin, partitionMax, incompatibilitySum, mask, lastUsedNum);
            if (next != -1)
                minIncompatibilitySum = next;

            int numMask = 1 << numPos;
            int num = nums[numPos];
            if (lastUsedNum == null || num != lastUsedNum) {
                int newPartitionMin = Math.min(partitionMin, num);
                int newPartitionMax = Math.max(partitionMax, num);
                int newNumMask = mask & ~numMask;
                next = minimumIncompatibility(nums, k, numPos + 1, partitionPos, partitionSize + 1, newPartitionMin, newPartitionMax, incompatibilitySum, newNumMask, num);
                if (next != -1)
                    minIncompatibilitySum = Math.min(minIncompatibilitySum, next);
            }

            return minIncompatibilitySum == Integer.MAX_VALUE ? -1 : minIncompatibilitySum;
        }
    }
}