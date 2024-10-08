package com.bteshome.algorithms.dynamicProgramming_;

import java.util.HashMap;

public class DPAlgorithms1 {
    /**
     * leetcode https://leetcode.com/problems/climbing-stairs/submissions/1404933063/?envType=problem-list-v2&envId=dynamic-programming&difficulty=EASY
     * */
    public static int climbStairsIterative(int n) {
        if (n < 0) {
            return 0;
        }

        var cache = new int[n + 1];
        cache[0] = 1;
        cache[1] = 1;

        for (int i = 2; i <= n; i++) {
            cache[i] = cache[i-1] + cache[i-2];
        }

        return cache[n];
    }

    /**
     * leetcode https://leetcode.com/problems/climbing-stairs/submissions/1404933063/?envType=problem-list-v2&envId=dynamic-programming&difficulty=EASY
     * Recursive alternative for the above one.
     * */
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
            cache.put(n, climbStairsRecursive(n-1, cache) + climbStairsRecursive(n-2, cache));
        }

        return cache.get(n);
    }

    /**
     * leetcode https://leetcode.com/problems/best-time-to-buy-and-sell-stock/?envType=problem-list-v2&envId=dynamic-programming&difficulty=EASY
     * */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int buy = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];
            if (price < buy) {
                buy = price;
            } else {
                maxProfit = Math.max(maxProfit, price - buy);
            }
        }

        return maxProfit;
    }

    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
     * */
    public static int maxProfitIITODO(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int buy = prices[0];
        int profit = 0;
        int totalProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            var today = prices[i];
            if (today < buy) {
                buy = today;
                totalProfit += profit;
                profit = 0;
            } else if (today > buy) {
                var newProfit = today - buy;
                if (newProfit > profit) {
                    profit = newProfit;
                } else {
                    totalProfit += profit;
                    buy = today;
                    profit = 0;
                }
            }
        }

        totalProfit += profit;

        return totalProfit;
    }

    /**
     * leetcode https://leetcode.com/problems/min-cost-climbing-stairs/submissions/594279105/?envType=problem-list-v2&envId=dynamic-programming&difficulty=EASY
     * */
    public static int minCostClimbingStairsRecursive(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }

        var cache = new HashMap<Integer, Integer>(cost.length + 1);

        return minCostClimbingStairsRecursive(cost, -1, cache);
    }

    public static int minCostClimbingStairsRecursive(int[] cost, Integer step, HashMap<Integer, Integer> cache) {
        if (step >= cost.length) {
            return 0;
        }

        if (!cache.containsKey(step)) {
            int minCost1 = minCostClimbingStairsRecursive(cost, step + 1, cache);
            int minCost2 = minCostClimbingStairsRecursive(cost, step + 2, cache);

            int minCost = Math.min(minCost1, minCost2);

            if (step > -1) {
                minCost += cost[step];
            }

            cache.put(step, minCost);
        }

        return cache.get(step);
    }
}
