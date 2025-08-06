package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms70 {
    private static final int MOD = (int)Math.pow(10, 9) + 7;

    /* https://leetcode.com/problems/profitable-schemes */
    public static int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        if (group == null || group.length == 0 || profit == null || profit.length == 0 || group.length != profit.length || n < 1)
            return 0;

        Integer[][][] dp = new Integer[group.length][n + 1][minProfit + 1];

        return profitableSchemes(n, minProfit, group, profit, 0, 0, dp);
    }

    private static int profitableSchemes(int n, int minProfit, int[] group, int[] profit, int pos, int totalProfit, Integer[][][] dp) {
        if (pos == group.length)
            return totalProfit >= minProfit ? 1 : 0;

        int cappedProfit = Math.min(minProfit, totalProfit);

        if (dp[pos][n][cappedProfit] == null) {
            long ways = profitableSchemes(n, minProfit, group, profit, pos + 1, cappedProfit, dp);
            if (n >= group[pos]) {
                int nextProfit = Math.min(minProfit, totalProfit + profit[pos]);
                ways = (ways + profitableSchemes(n - group[pos], minProfit, group, profit, pos + 1, nextProfit, dp)) % MOD;
            }
            dp[pos][n][cappedProfit] = (int)ways;
        }

        return dp[pos][n][totalProfit];
    }
}
