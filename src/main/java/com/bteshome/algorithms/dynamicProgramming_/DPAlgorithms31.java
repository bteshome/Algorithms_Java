package com.bteshome.algorithms.dynamicProgramming_;

import java.util.Arrays;
import java.util.List;

public class DPAlgorithms31 {
    private static final int MOD = (int) Math.pow(10, 9) + 7;

    /* https://leetcode.com/problems/count-vowels-permutation */
    public static int countVowelPermutationBottomUp(int n) {
        if (n < 1)
            return 0;

        long[][] dp = new long[n][5];
        for (int j = 0; j < 5; j++)
            dp[n - 1][j] = 1;

        for (int i = n - 2; i >= 0; i--) {
            dp[i][0] = dp[i + 1][1];
            dp[i][1] = (dp[i + 1][0] + dp[i + 1][2]) % MOD;
            dp[i][2] = (((dp[i + 1][0] + dp[i + 1][1]) % MOD + dp[i + 1][3]) % MOD + dp[i + 1][4]) % MOD;
            dp[i][3] = (dp[i + 1][2] + dp[i + 1][4]) % MOD;
            dp[i][4] = dp[i + 1][0];
        }

        long count = 0L;
        for (int j = 0; j < 5; j++)
            count = (count + dp[0][j]) % MOD;

        return (int) count;
    }

    /* https://leetcode.com/problems/maximum-value-of-k-coins-from-piles */
    public static int maxValueOfCoinsTopDown(List<List<Integer>> piles, int k) {
        if (piles == null || piles.isEmpty())
            return 0;

        int n = piles.size();
        int[][] prefixSums = new int[n][k + 1];

        // prefix sums for individual piles - optimization
        for (int i = 0; i < n; i++) {
            List<Integer> pile = piles.get(i);
            int pileSize = pile.size();
            for (int j = 1; j <= Math.min(k, pileSize); j++)
                prefixSums[i][j] = pile.get(j - 1) + prefixSums[i][j - 1];
        }

        return maxValueOfCoinsTopDown(piles, k, 0, prefixSums, new Integer[n][k + 1]);
    }

    private static int maxValueOfCoinsTopDown(List<List<Integer>> piles, int k, int pos, int[][] prefixSums, Integer[][] dp) {
        if (k == 0 || pos == piles.size())
            return 0;

        if (dp[pos][k] == null) {
            int value = 0;
            for (int i = 0; i <= Math.min(k, piles.get(pos).size()); i++)
                value = Math.max(value, prefixSums[pos][i] + maxValueOfCoinsTopDown(piles, k - i, pos + 1, prefixSums, dp));
            dp[pos][k] = value;
        }

        return dp[pos][k];
    }

    public static int maxValueOfCoinsBottomUp(List<List<Integer>> piles, int k) {
        if (piles == null || piles.isEmpty())
            return 0;

        int n = piles.size();
        int[][] dp = new int[n + 1][k + 1];
        int[][] prefixSums = new int[n][k + 1];

        // prefix sums for individual piles - optimization
        for (int i = 0; i < n; i++) {
            List<Integer> pile = piles.get(i);
            int pileSize = pile.size();
            for (int j = 1; j <= Math.min(k, pileSize); j++)
                prefixSums[i][j] = pile.get(j - 1) + prefixSums[i][j - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int pileSize = piles.get(i).size();
            for (int j = 0; j <= k; j++) {
                int max = 0;
                for (int l = 0; l <= Math.min(pileSize, j); l++)
                    max = Math.max(max, prefixSums[i][l] + dp[i + 1][j - l]);
                dp[i][j] = max;
            }
        }

        return dp[0][k];
    }
}