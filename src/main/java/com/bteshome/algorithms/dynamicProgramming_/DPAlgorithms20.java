package com.bteshome.algorithms.dynamicProgramming_;

import java.util.HashMap;
import java.util.Map;

public class DPAlgorithms20 {
    /**
     * leetcode https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
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
    public static int maxProfitII(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;

        int overallProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - prices[i-1];
            if (profit > 0)
                overallProfit += profit;
        }

        return overallProfit;
    }

    /**
     * TODO - exceeds leetcode time limit
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
     * */
    public static int maxProfitIIITODO(int[] prices) {
        if (prices == null)
            return 0;

        return maxProfitIIITODO(prices, 0, -1, 2, new HashMap<>());
    }

    private static int maxProfitIIITODO(int[] prices, int day, int lastPurchaseAmount, int transactionsRemaining, Map<String, Integer> cache) {
        if (day == prices.length)
            return 0;

        String key = "%s,%s,%s".formatted(day, lastPurchaseAmount, transactionsRemaining);

        if (!cache.containsKey(key)) {
            int maxProfit = 0;

            if (lastPurchaseAmount == -1) {
                int skip = maxProfitIIITODO(prices, day + 1, lastPurchaseAmount, transactionsRemaining, cache);
                int buy = 0;
                if (transactionsRemaining > 0)
                    buy = maxProfitIIITODO(prices, day + 1, prices[day], transactionsRemaining - 1, cache);
                maxProfit = Math.max(skip, buy);
            }
            else {
                int skip = maxProfitIIITODO(prices, day + 1, lastPurchaseAmount, transactionsRemaining, cache);
                int sell = 0;
                if (prices[day] > lastPurchaseAmount)
                    sell = prices[day] - lastPurchaseAmount + maxProfitIIITODO(prices, day + 1, -1, transactionsRemaining, cache);
                maxProfit = Math.max(skip, sell);
            }

            cache.put(key, maxProfit);
        }

        return cache.get(key);
    }

    /**
     * TODO - exceeds leetcode time limit
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
     * */
    public static int maxProfitIVTODO(int k, int[] prices) {
        if (k < 1 || prices == null)
            return 0;

        return maxProfitIVTODO(k, prices, 0, -1, new HashMap<>());
    }

    private static int maxProfitIVTODO(int k, int[] prices, int day, int lastPurchaseAmount, Map<String, Integer> cache) {
        if (day == prices.length)
            return 0;

        String key = "%s,%s,%s".formatted(day, lastPurchaseAmount, k);

        if (!cache.containsKey(key)) {
            int maxProfit = 0;

            if (lastPurchaseAmount == -1) {
                int skip = maxProfitIVTODO(k, prices, day + 1, lastPurchaseAmount, cache);
                int buy = 0;
                if (k > 0)
                    buy = maxProfitIVTODO(k - 1, prices, day + 1, prices[day], cache);
                maxProfit = Math.max(skip, buy);
            }
            else {
                int skip = maxProfitIVTODO(k, prices, day + 1, lastPurchaseAmount, cache);
                int sell = 0;
                if (prices[day] > lastPurchaseAmount)
                    sell = prices[day] - lastPurchaseAmount + maxProfitIVTODO(k, prices, day + 1, -1, cache);
                maxProfit = Math.max(skip, sell);
            }

            cache.put(key, maxProfit);
        }

        return cache.get(key);
    }
}
