package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms33 {
    /* https://leetcode.com/problems/egg-drop-with-2-eggs-and-n-floor */
    public static int twoEggDropTopDown(int n) {
        if (n < 1)
            return 0;

        Integer[][] dp = new Integer[n + 1][3];

        return twoEggDropTopDown(2, n, dp);
    }

    private static int twoEggDropTopDown(int k, int n, Integer[][] dp) {
        if (k == 1)
            return n;
        if (n <= 1)
            return n;

        if (dp[n][k] == null) {
            int min = Integer.MAX_VALUE;

            for (int i = 1; i < n; i++) {
                int movesIfItBreaks = twoEggDropTopDown(k - 1, i - 1, dp);
                int movesIfItDoesNotBreak = twoEggDropTopDown(k, n - i,dp);
                int movesWorstCase = Math.max(movesIfItBreaks, movesIfItDoesNotBreak);
                min = Math.min(min, 1 + movesWorstCase);
            }

            dp[n][k] = min;
        }

        return dp[n][k];
    }

    public static int twoEggDropBottomUp(int n) {
        if (n < 1)
            return 0;

        // NOTE - the dp table is 2d just for clarity. It can actually be optimized to be 1d.
        int[][] dp = new int[n + 1][3];
        for (int i = 1; i <= n; i++)
            dp[i][1] = i;
        dp[1][1] = 1;
        dp[1][2] = 1;

        for (int i = 2; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j < i; j++) {
                int movesIfItBreaks = dp[j - 1][1];
                int movesIfItDoesNotBreak = dp[i - j][2];
                int movesWorstCase = Math.max(movesIfItBreaks, movesIfItDoesNotBreak);
                min = Math.min(min, 1 + movesWorstCase);
            }
            dp[i][2] = min;
        }

        return dp[n][2];
    }

    /* https://leetcode.com/problems/super-egg-drop */
    public static int superEggDropTopDown(int k, int n) {
        if (k < 1 || n < 1)
            return 0;

        return superEggDropTopDown(k, n, new Integer[n + 1][k + 1]);
    }

    private static int superEggDropTopDown(int k, int n, Integer[][] dp) {
        if (k == 1)
            return n;
        if (n == 1)
            return 1;

        if (dp[n][k] == null) {
            int minMoves = n;
            int low = 1;
            int high = n;

            while (low <= high) {
                int mid = (low + high) / 2;
                int movesIfItBreaks = superEggDropTopDown(k - 1, mid - 1, dp);
                int movesIfItDoesNotBreak = superEggDropTopDown(k, n - mid, dp);
                int movesWorstCase = Math.max(movesIfItBreaks, movesIfItDoesNotBreak);
                minMoves = Math.min(minMoves, 1 + movesWorstCase);
                if (movesIfItBreaks > movesIfItDoesNotBreak)
                    high = mid - 1;
                else
                    low = mid + 1;
            }

            dp[n][k] = minMoves;
        }

        return dp[n][k];
    }
}