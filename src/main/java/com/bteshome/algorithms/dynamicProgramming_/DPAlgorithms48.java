package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms48 {
    private static final int MOD = ((int) Math.pow(10, 9)) + 7;

    /**
     * https://leetcode.com/problems/number-of-ways-to-divide-a-long-corridor
     * NOTE: this problem can be solved without DP using math
     * */
    public static int numberOfWaysTopDown(String corridor) {
        if (corridor == null || corridor.length() < 2)
            return 0;

        int n = corridor.length();
        Integer[][] dp = new Integer[n][3];
        int totalSeatCount = 0;

        for (int i = 0; i < n; i++)
            if (corridor.charAt(i) == 'S')
                totalSeatCount++;

        // Early pruning - if seat count is not a multiple of 2, not possible to partition
        if (totalSeatCount == 0 || totalSeatCount % 2 != 0)
            return 0;

        return numberOfWaysTopDown(corridor, 0, 0, dp);
    }

    private static int numberOfWaysTopDown(String corridor, int pos, int prevSeatCount, Integer[][] dp) {
        if (pos == corridor.length())
            return prevSeatCount == 2 ? 1 : 0;

        if (dp[pos][prevSeatCount] != null)
            return dp[pos][prevSeatCount];

        long ways = 0;
        int currentSeatCount = prevSeatCount;

        if (corridor.charAt(pos) == 'S')
            currentSeatCount++;

        if (currentSeatCount < 2)
            ways = numberOfWaysTopDown(corridor, pos + 1, currentSeatCount, dp);
        else if (currentSeatCount == 2) {
            ways = numberOfWaysTopDown(corridor, pos + 1, 0, dp);
            ways = (ways + numberOfWaysTopDown(corridor, pos + 1, currentSeatCount, dp)) % MOD;
        }

        dp[pos][prevSeatCount] = (int)ways;

        return dp[pos][prevSeatCount];
    }

    //private static final int MOD = ((int) Math.pow(10, 9)) + 7;
    public static int numberOfWaysBottomUp(String corridor) {
        if (corridor == null || corridor.length() < 2)
            return 0;

        int n = corridor.length();
        int[][] dp = new int[n + 1][3];
        int totalSeatCount = 0;

        for (int i = 0; i < n; i++)
            if (corridor.charAt(i) == 'S')
                totalSeatCount++;

        // Early pruning - if seat count is not a multiple of 2, not possible to partition
        if (totalSeatCount % 2 != 0)
            return 0;

        dp[n][2] = 1;

        for (int pos = n - 1; pos >= 0; pos--) {
            for (int prevSeatCount = 0; prevSeatCount < 3; prevSeatCount++) {
                long ways = 0;
                int currentSeatCount = prevSeatCount;

                if (corridor.charAt(pos) == 'S')
                    currentSeatCount++;

                if (currentSeatCount < 2)
                    ways = dp[pos + 1][currentSeatCount];
                else if (currentSeatCount == 2) {
                    ways = dp[pos + 1][0];
                    ways = (ways + dp[pos + 1][currentSeatCount]) % MOD;
                }

                dp[pos][prevSeatCount] = (int)ways;
            }
        }

        return dp[0][0];
    }
}