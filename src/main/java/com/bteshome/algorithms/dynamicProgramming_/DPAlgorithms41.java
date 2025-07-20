package com.bteshome.algorithms.dynamicProgramming_;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class DPAlgorithms41 {
    /**
     * https://leetcode.com/problems/allocate-mailboxes
     * NOTE: this solution runs pretty fast, but there is still
     *       room to improve the space usage down to 2D
     * */
    public static int minDistanceTopDown(int[] houses, int k) {
        if (houses == null || k < 1 || houses.length == 0 || k > houses.length)
            return 0;

        Arrays.sort(houses);

        int n = houses.length;
        Integer[][][] dp = new Integer[n][n][k + 1];

        return minDistanceTopDown(houses, 0, houses.length - 1, k, dp);
    }

    private static int minDistanceTopDown(int[] houses, int start, int end, int k, Integer[][][] dp) {
        if (k == 1 && start == end)
            return 0;

        if (dp[start][end][k] == null) {
            if (k == 1) {
                int mid = (start + end) / 2;
                int box = houses[mid];
                int distance = 0;

                for (int i = start; i <= end; i++)
                    distance += Math.abs(houses[i] - box);

                dp[start][end][k] = distance;
            } else {
                int min = Integer.MAX_VALUE;

                for (int i = start; i <= end - (k - 1); i++) {
                    int cost1 = minDistanceTopDown(houses, start, i, 1, dp);
                    int cost2 = minDistanceTopDown(houses, i + 1, end, k - 1, dp);
                    min = Math.min(min, cost1 + cost2);
                }

                dp[start][end][k] = min;
            }
        }

        return dp[start][end][k];
    }

    /**
     * https://leetcode.com/problems/maximum-length-of-pair-chain
     * NOTE: this problem almost identical to the LIS problem
     * */
    public static int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length == 0)
            return 0;

        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));

        int n = pairs.length;
        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++)
                if (pairs[j][1] < pairs[i][0])
                    max = Math.max(max, dp[j]);
            dp[i] = 1 + max;
        }

        int overallMax = 0;
        for (int i = 0; i < n; i++)
            overallMax = Math.max(overallMax, dp[i]);

        return overallMax;
    }

    /**
     * https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference
     * NOTE: this solution is correct and runs in quadratic time, like standard LIS.
     *       But, it does not pass LeetCode time limit test.
     *       For comparison, see the linear hash table solution.
     * */
    public static int longestSubsequence(int[] arr, int difference) {
        if (arr == null || arr.length == 0)
            return 0;

        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++)
                if (arr[i] - arr[j] == difference)
                    max = Math.max(max, dp[j]);
            dp[i] = 1 + max;
        }

        int overallMax = 0;
        for (int i = 0; i < n; i++)
            overallMax = Math.max(overallMax, dp[i]);

        return overallMax;
    }
}