package com.bteshome.algorithms.dynamicProgramming_;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DPAlgorithms1 {
    /**
     * leetcode https://leetcode.com/problems/climbing-stairs/submissions/1404933063/?envType=problem-list-v2&envId=dynamic-programming&difficulty=EASY
     */
    public static int climbStairsIterative(int n) {
        if (n < 0) {
            return 0;
        }

        var cache = new int[n + 1];
        cache[0] = 1;
        cache[1] = 1;

        for (int i = 2; i <= n; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }

        return cache[n];
    }

    /**
     * leetcode https://leetcode.com/problems/climbing-stairs/submissions/1404933063/?envType=problem-list-v2&envId=dynamic-programming&difficulty=EASY
     * Recursive alternative for the above one.
     */
    public static int climbStairsRecursive(int n) {
        if (n < 0) {
            return 0;
        }

        var cache = new HashMap<Integer, Integer>();
        cache.put(0, 1);
        cache.put(1, 1);

        return climbStairsRecursive(n, cache);
    }

    private static int climbStairsRecursive(int n, HashMap<Integer, Integer> cache) {
        if (!cache.containsKey(n)) {
            cache.put(n, climbStairsRecursive(n - 1, cache) + climbStairsRecursive(n - 2, cache));
        }

        return cache.get(n);
    }

    /**
     * leetcode https://leetcode.com/problems/min-cost-climbing-stairs/submissions/594279105/?envType=problem-list-v2&envId=dynamic-programming&difficulty=EASY
     */
    public static int minCostClimbingStairsBottomUp(int[] cost) {
        if (cost == null || cost.length < 2)
            return 0;

        int[] dp = new int[cost.length + 1];

        for (int i = 2; i <= cost.length; i++)
            dp[i] = Math.min(cost[i - 1] + dp[i - 1], cost[i - 2] + dp[i - 2]);

        return dp[cost.length];
    }

    public static int minCostClimbingStairsRecursive(int[] cost) {
        if (cost == null)
            return 0;

        var cache = new HashMap<Integer, Integer>(cost.length + 1);

        return minCostClimbingStairsRecursive(cost, cost.length, cache);
    }

    private static int minCostClimbingStairsRecursive(int[] cost, Integer step, HashMap<Integer, Integer> cache) {
        if (step < 2)
            return 0;

        if (!cache.containsKey(step)) {
            int minCost1 = cost[step - 1] + minCostClimbingStairsRecursive(cost, step - 1, cache);
            int minCost2 = cost[step - 2] + minCostClimbingStairsRecursive(cost, step - 2, cache);
            cache.put(step, Math.min(minCost1, minCost2));
        }

        return cache.get(step);
    }
}