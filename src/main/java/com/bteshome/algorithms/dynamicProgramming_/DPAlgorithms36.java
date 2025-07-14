package com.bteshome.algorithms.dynamicProgramming_;

import java.util.Arrays;

public class DPAlgorithms36 {
    private static final int MOD = 1000000007;

    /* https://leetcode.com/problems/out-of-boundary-paths */
    public static int findPathsTopDown(int m, int n, int maxMove, int startRow, int startColumn) {
        if (m < 1 || n < 1 || maxMove < 1)
            return 0;

        return findPathsTopDown(m, n, maxMove, startRow, startColumn, new Integer[m][n][maxMove + 1]);
    }

    private static int findPathsTopDown(int m, int n, int maxMove, int row, int col, Integer[][][] dp) {
        if (row < 0 || row >= m || col < 0 || col >= n)
            return 1;
        if (maxMove <= 0)
            return 0;

        if (dp[row][col][maxMove] == null) {
            long ways = 0;
            ways = (ways + findPathsTopDown(m, n, maxMove - 1, row + 1, col, dp)) % MOD;
            ways = (ways + findPathsTopDown(m, n, maxMove - 1, row - 1, col, dp)) % MOD;
            ways = (ways + findPathsTopDown(m, n, maxMove - 1, row, col + 1, dp)) % MOD;
            ways = (ways + findPathsTopDown(m, n, maxMove - 1, row, col - 1, dp)) % MOD;
            dp[row][col][maxMove] = (int)ways;
        }

        return dp[row][col][maxMove];
    }

    public static int findPathsBottomUp(int m, int n, int maxMove, int startRow, int startColumn) {
        if (m < 1 || n < 1 || maxMove < 1)
            return 0;

        int[][][] dp = new int[maxMove + 1][m  + 2][n + 2];

        for (int k = 0; k <= maxMove; k++)
            for (int j = 0; j <= n + 1; j++) {
                dp[k][0][j] = 1;
                dp[k][m + 1][j] = 1;
            }

        for (int k = 0; k <= maxMove; k++)
            for (int i = 0; i <= m + 1; i++) {
                dp[k][i][0] = 1;
                dp[k][i][n + 1] = 1;
            }

        for (int move = 1; move <= maxMove; move++) {
            for (int row = 1; row <= m ; row++) {
                for (int col = 1; col <= n; col++) {
                    long ways = 0;
                    ways = (ways + dp[move - 1][row + 1][col]) % MOD;
                    ways = (ways + dp[move - 1][row - 1][col]) % MOD;
                    ways = (ways + dp[move - 1][row][col + 1]) % MOD;
                    ways = (ways + dp[move - 1][row][col - 1]) % MOD;
                    dp[move][row][col] = (int)ways;
                }
            }
        }

        return dp[maxMove][startRow + 1][startColumn + 1];
    }

    /* https://leetcode.com/problems/count-sorted-vowel-strings */
    public static int countVowelStringsTopDown(int n) {
        if (n < 1)
            return 0;
        return countVowelStringsTopDown(n, 0, new Integer[n + 1][5]);
    }

    private static int countVowelStringsTopDown(int n, int previous, Integer[][] dp) {
        if (n == 0)
            return 1;

        if (dp[n][previous] == null) {
            int count = 0;
            for (int i = previous; i < 5; i++)
                count += countVowelStringsTopDown(n - 1, i, dp);
            dp[n][previous] = count;
        }

        return dp[n][previous];
    }

    public static int countVowelStringsBottomUp(int n) {
        if (n < 1)
            return 0;

        int[][] dp = new int[n + 1][5];
        Arrays.fill(dp[0], 1);

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 5; j++) {
                int count = 0;
                for (int k = j; k < 5; k++)
                    count += dp[i - 1][k];
                dp[i][j] = count;
            }
        }

        return dp[n][0];
    }
}
