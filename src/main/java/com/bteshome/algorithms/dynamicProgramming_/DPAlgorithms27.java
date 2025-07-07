package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms27 {
    /**
     * https://leetcode.com/problems/stone-game-iii
     * key observations:
     * - since it's a zero-sum game, no need to store the player in the DP state
     * */
    public static String stoneGameIIITopDown(int[] stoneValue) {
        if (stoneValue == null || stoneValue.length == 0)
            return "Tie";

        int diff = stoneGameIIITopDown(stoneValue, 0, new Integer[stoneValue.length]);

        if (diff > 0)
            return "Alice";
        else if (diff == 0)
            return "Tie";
        else
            return "Bob";
    }

    private static int stoneGameIIITopDown(int[] stoneValue, int pos, Integer[] dp) {
        if (pos == stoneValue.length)
            return 0;

        if (dp[pos] == null) {
            int maxDiff = stoneValue[pos] - stoneGameIIITopDown(stoneValue, pos + 1, dp);
            if (pos < stoneValue.length - 1)
                maxDiff = Math.max(maxDiff, stoneValue[pos] + stoneValue[pos + 1] - stoneGameIIITopDown(stoneValue, pos + 2, dp));
            if (pos < stoneValue.length - 2)
                maxDiff = Math.max(maxDiff, stoneValue[pos] + stoneValue[pos + 1] + stoneValue[pos + 2]- stoneGameIIITopDown(stoneValue, pos + 3, dp));
            dp[pos] = maxDiff;
        }

        return dp[pos];
    }

    public static String stoneGameIIIBottomUp(int[] stoneValue) {
        if (stoneValue == null || stoneValue.length == 0)
            return "Tie";

        int n = stoneValue.length;
        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            int maxDiff =  stoneValue[i] - dp[i + 1];
            if (i < n - 1)
                maxDiff = Math.max(maxDiff, stoneValue[i] + stoneValue[i + 1] - dp[i + 2]);
            if (i < n - 2)
                maxDiff = Math.max(maxDiff, stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - dp[i + 3]);
            dp[i] = maxDiff;
        }

        int overallMaxDiff = dp[0];
        if (overallMaxDiff > 0)
            return "Alice";
        else if (overallMaxDiff == 0)
            return "Tie";
        else
            return "Bob";
    }

    /**
     * https://leetcode.com/problems/stone-game-iv
     * key observations:
     * - since it's a zero-sum game, no need to store the player in the DP state
     * */
    public static boolean winnerSquareGameTopDown(int n) {
        if (n < 1)
            return false;

        return winnerSquareGameTopDown(n, new Boolean[n + 1]);
    }

    private static boolean winnerSquareGameTopDown(int n, Boolean[] dp) {
        if (n == 0)
            return false;
        if (n == 1)
            return true;

        if (dp[n] == null) {
            boolean wins = false;
            for (int i = 1; i <= (int) Math.sqrt(n); i++)
                if (!winnerSquareGameTopDown(n - i * i, dp)) {
                    wins = true;
                    break;
                }
            dp[n] = wins;
        }

        return dp[n];
    }

    public static boolean winnerSquareGameBottomUp(int n) {
        if (n < 1)
            return false;

        boolean[] dp = new boolean[n + 1];
        dp[1] = true;

        for (int i = 2; i <= n; i++) {
            boolean wins = false;
            for (int j = 1; j <= (int) Math.sqrt(i); j++)
                if (!dp[i - j * j]) {
                    wins = true;
                    break;
                }
            dp[i] = wins;
        }

        return dp[n];
    }
}
