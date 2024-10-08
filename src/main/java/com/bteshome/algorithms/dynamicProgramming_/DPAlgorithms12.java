package com.bteshome.algorithms.dynamicProgramming_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DPAlgorithms12 {
    /**
     * https://leetcode.com/problems/coin-change/?envType=study-plan-v2&envId=top-interview-150
     * */
    public static int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }

        return coinChange(coins, amount, new HashMap<Integer, Integer>());
    }

    private static int coinChange(int[] coins, int amount, HashMap<Integer, Integer> cache) {
        if (amount == 0) {
            return 0;
        }

        if (amount < 0) {
            return -1;
        }

        if (!cache.containsKey(amount)) {
            int minNumCoins = Integer.MAX_VALUE;

            for (int coin : coins) {
                var count = coinChange(coins, amount - coin, cache);
                if (count != -1) {
                    minNumCoins = Math.min(minNumCoins, 1 + count);
                }
            }

            cache.put(amount, minNumCoins == Integer.MAX_VALUE ? -1 : minNumCoins);
        }

        return cache.get(amount);
    }

    /**
     * https://leetcode.com/problems/coin-change-ii/
     * */
    public static int coinChangeII(int amount, int[] coins) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return 0;
        }

        var cache = new Integer[coins.length][];
        for (int i = 0; i < coins.length; i++) {
            cache[i] = new Integer[amount + 1];
        }

        return coinChangeII(amount, coins, cache, 0);
    }

    private static int coinChangeII(int amount, int[] coins, Integer[][] cache, int coinPos) {
        if (amount == 0) {
            return 1;
        }

        if (coinPos == coins.length) {
            return 0;
        }

        if (cache[coinPos][amount] == null) {
            int combinations = 0;

            combinations += coinChangeII(amount, coins, cache, coinPos + 1);

            var coin = coins[coinPos];
            if (coin <= amount) {
                combinations += coinChangeII(amount - coin, coins, cache, coinPos);
            }

            cache[coinPos][amount] = combinations;
        }

        return cache[coinPos][amount];
    }


}
