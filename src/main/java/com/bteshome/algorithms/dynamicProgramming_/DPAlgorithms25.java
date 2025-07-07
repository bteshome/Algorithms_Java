package com.bteshome.algorithms.dynamicProgramming_;

import java.util.HashMap;
import java.util.Map;

public class DPAlgorithms25 {
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

    /**
     * https://leetcode.com/problems/tallest-billboard
     * NOTE: take a look at V2 below, which is also correct but inefficient.
     * NOTE: V2 is here just for the sake of showing why it's important to
     *       choose the cache key carefully.
     * */
    public static int tallestBillboard(int[] rods) {
        if (rods == null || rods.length < 2)
            return 0;

        Map<Integer, Integer>[] dp = new HashMap[rods.length];
        for (int i = 0; i < rods.length; i++)
            dp[i] = new HashMap<>();

        return tallestBillboard(rods, 0, 0, dp);
    }

    private static int tallestBillboard(int[] rods, int pos, int diff, Map<Integer, Integer>[] dp) {
        if (pos == rods.length)
            return diff == 0 ? 0 : -1;

        if (!dp[pos].containsKey(diff)) {
            int rod = rods[pos];
            int tallest = tallestBillboard(rods, pos + 1, diff, dp);
            int left = tallestBillboard(rods, pos + 1, diff + rod, dp);
            if (left >= 0)
                tallest = Math.max(tallest, left);
            int right = tallestBillboard(rods, pos + 1, diff - rod, dp);
            if (right >= 0)
                tallest = Math.max(tallest, right + rod);
            dp[pos].put(diff, tallest);
        }

        return dp[pos].get(diff);
    }

    public static int tallestBillboardInEfficient(int[] rods) {
        if (rods == null || rods.length < 2)
            return 0;

        return tallestBillboardInEfficient(rods, 0, 0, 0, new HashMap<>());
    }

    private static int tallestBillboardInEfficient(int[] rods, int pos, int sum1, int sum2, Map<String, Integer> dp) {
        if (pos == rods.length)
            return sum1 == sum2 ? sum1 : 0;

        String key = "%s,%s,%s".formatted(pos, sum1, sum2);

        if (!dp.containsKey(key)) {
            int tallest = tallestBillboardInEfficient(rods, pos + 1, sum1, sum2, dp);
            tallest = Math.max(tallest, tallestBillboardInEfficient(rods, pos + 1, sum1 + rods[pos], sum2, dp));
            tallest = Math.max(tallest, tallestBillboardInEfficient(rods, pos + 1, sum1, sum2 + rods[pos], dp));
            dp.put(key, tallest);
        }

        return dp.get(key);
    }
}
