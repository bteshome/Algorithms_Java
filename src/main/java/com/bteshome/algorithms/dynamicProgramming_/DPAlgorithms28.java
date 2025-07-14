package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms28 {
    /* https://leetcode.com/problems/stone-game-v/ */
    public static int stoneGameVTopDown(int[] stoneValue) {
        if (stoneValue == null || stoneValue.length == 0)
            return 0;

        int n = stoneValue.length;
        Integer[][] dp = new Integer[n][n];
        int[] prefixSums = new int[n];
        prefixSums[0] = stoneValue[0];

        for (int i = 1; i < n; i++)
            prefixSums[i] = stoneValue[i] + prefixSums[i - 1];

        return stoneGameVTopDown(stoneValue, prefixSums, 0, n - 1, dp);
    }

    private static int stoneGameVTopDown(int[] stoneValue, int[] prefixSums, int start, int end, Integer[][] dp) {
        if (start == end)
            return 0;

        if (dp[start][end] == null) {
            int value = 0;

            for (int i = start; i < end; i++) {
                int leftSum = prefixSums[i] - (start == 0 ? 0 : prefixSums[start - 1]);
                int rightSum = prefixSums[end] - prefixSums[i];
                if (leftSum < rightSum) {
                    value = Math.max(value, leftSum + stoneGameVTopDown(stoneValue, prefixSums, start, i, dp));
                } else if (rightSum < leftSum) {
                    value = Math.max(value, rightSum + stoneGameVTopDown(stoneValue, prefixSums, i + 1, end, dp));
                } else {
                    value = Math.max(value, leftSum + stoneGameVTopDown(stoneValue, prefixSums, start, i, dp));
                    value = Math.max(value, rightSum + stoneGameVTopDown(stoneValue, prefixSums, i + 1, end, dp));
                }
            }

            dp[start][end] = value;
        }

        return dp[start][end];
    }

    /* https://leetcode.com/problems/stone-game-vii/ */
    public static int stoneGameVIITopDown(int[] stones) {
        if (stones == null)
            return 0;

        int n = stones.length;

        int[] prefixSums = new int[n];
        prefixSums[0] = stones[0];
        for (int i = 1; i < n; i++)
            prefixSums[i] = stones[i] + prefixSums[i - 1];

        return stoneGameVIITopDown(stones, 0, n - 1, prefixSums, new Integer[n][n]);
    }

    private static int stoneGameVIITopDown(int[] stones, int start, int end, int[] prefixSums, Integer[][] dp) {
        if (start >= end)
            return 0;

        if (dp[start][end] == null) {
            int sum = prefixSums[end] - (start == 0 ? 0 : prefixSums[start - 1]);
            int leftDiff = sum - stones[start] - stoneGameVIITopDown(stones, start + 1, end, prefixSums, dp);
            int rightDiff = sum - stones[end] - stoneGameVIITopDown(stones, start, end - 1, prefixSums, dp);
            dp[start][end] = Math.max(leftDiff, rightDiff);
        }

        return dp[start][end];
    }

    public static int stoneGameVIIBottomUp(int[] stones) {
        if (stones == null)
            return 0;

        int n = stones.length;
        int[][] dp = new int[n][n];
        int[] prefixSums = new int[n];

        prefixSums[0] = stones[0];
        for (int i = 1; i < n; i++)
            prefixSums[i] = stones[i] + prefixSums[i - 1];

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                int sum = prefixSums[j] - (i == 0 ? 0 : prefixSums[i - 1]);
                int leftDiff = sum - stones[i] - dp[i + 1][j];
                int rightDiff = sum - stones[j] - dp[i][j - 1];
                dp[i][j] = Math.max(leftDiff, rightDiff);
            }
        }

        return dp[0][n - 1];
    }


}