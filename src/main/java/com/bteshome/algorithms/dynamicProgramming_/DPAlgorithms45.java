package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms45 {
    private static final int MOD = ((int) Math.pow(10, 9)) + 7;

    public static int mincostTickets(int[] days, int[] costs) {
        /* https://leetcode.com/problems/minimum-cost-for-tickets */
        if (days == null || costs == null || costs.length != 3)
            return 0;

        return mincostTickets(days, costs, 0, new Integer[days.length]);
    }

    private static int mincostTickets(int[] days, int[] costs, int dayPos, Integer[] dp) {
        if (dayPos >= days.length)
            return 0;

        if (dp[dayPos] != null)
            return dp[dayPos];

        int day = days[dayPos];
        int minCost = costs[0] + mincostTickets(days, costs, getNextUncoveredDay(days, dayPos, day + 1), dp);
        minCost = Math.min(minCost, costs[1] + mincostTickets(days, costs, getNextUncoveredDay(days, dayPos, day + 7), dp));
        minCost = Math.min(minCost, costs[2] + mincostTickets(days, costs, getNextUncoveredDay(days, dayPos, day + 30), dp));
        dp[dayPos] = minCost;

        return dp[dayPos];
    }

    private static int getNextUncoveredDay(int[] days, int dayPos, int earliestUncoveredDay) {
        for (int i = dayPos + 1; i < days.length; i++)
            if (days[i] >= earliestUncoveredDay)
                return i;

        return days.length;
    }

    /* https://leetcode.com/problems/unique-binary-search-trees */
    public static int numTreesTopDown(int n) {
        if (n < 1)
            return 0;
        return numTreesTopDown(1, n, new Integer[n + 1][n + 1]);
    }

    private static int numTreesTopDown(int start, int end, Integer[][] dp) {
        if (start >= end)
            return 1;

        if (dp[start][end] == null){
            int numBsts = 0;
            for (int i = start; i <= end; i++)
                numBsts += numTreesTopDown(start, i - 1, dp) * numTreesTopDown(i + 1, end, dp);
            dp[start][end] = numBsts;
        }

        return dp[start][end];
    }

    /* https://leetcode.com/problems/domino-and-tromino-tiling */
    public static int numTilingsTopDown(int n) {
        if (n < 1)
            return 0;
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;

        // prevState definition: 0=fully covered, 1=top covered, 2=down covered
        return numTilingsTopDown(n, 0, 0, new Integer[n][3]);
    }

    private static int numTilingsTopDown(int n, int pos, int prevState, Integer[][] dp) {
        if (pos == n)
            return prevState == 0 ? 1 : 0;
        if (pos > n)
            return 0;
        if (pos == 0 && prevState != 0)
            return 0;

        if (dp[pos][prevState] != null)
            return dp[pos][prevState];

        long ways = 0;

        if (prevState == 0) {
            ways = numTilingsTopDown(n, pos + 1, 0, dp);
            ways = (ways + numTilingsTopDown(n, pos + 2, 0, dp)) % MOD;
            ways = (ways + numTilingsTopDown(n, pos + 2, 1, dp)) % MOD;
            ways = (ways + numTilingsTopDown(n, pos + 2, 2, dp)) % MOD;
        } else if (prevState == 1) {
            ways = numTilingsTopDown(n, pos + 1, 0, dp);
            ways = (ways + numTilingsTopDown(n, pos + 1, 2, dp)) % MOD;
        } else if (prevState == 2) {
            ways = numTilingsTopDown(n, pos + 1, 0, dp);
            ways = (ways + numTilingsTopDown(n, pos + 1, 2, dp)) % MOD;
        }

        dp[pos][prevState] = (int) ways;

        return dp[pos][prevState];
    }

    public static int numTilingsBottomUp(int n) {
        if (n < 1)
            return 0;
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;

        int[][] dp = new int[n + 2][3];
        dp[n][0] = 1;

        for (int i = n - 1; i >= 0 ; i--) {
            for (int prevState = 0; prevState < 3; prevState++) {
                long ways = 0;

                if (i == 0 && prevState > 0)
                    continue;

                if (prevState == 0) {
                    ways = dp[i + 1][0];
                    ways = (ways + dp[i + 2][0]) % MOD;
                    ways = (ways + dp[i + 2][1]) % MOD;
                    ways = (ways + dp[i + 2][2]) % MOD;
                } else if (prevState == 1) {
                    ways = dp[i + 1][0];
                    ways = (ways + dp[i  + 1][2]) % MOD;
                } else {
                    ways = dp[i + 1][0];
                    ways = (ways + dp[i + 1][2]) % MOD;
                }

                dp[i][prevState] = (int) ways;
            }
        }

        return dp[0][0];
    }
}