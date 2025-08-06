package com.bteshome.algorithms.dynamicProgramming_;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DPAlgorithms25 {
    /**
     * https://leetcode.com/problems/tallest-billboard
     * NOTE:
     *  - tallestBillboardUsingDiffAndArrayWithPruning is accepted and the fastest
     *  - tallestBillboardUsingDiffAndArray is accepted and fast
     *  - tallestBillboardUsingDiffAndArray2 is accepted and fast
     *  - tallestBillboardUsingDiffAndArrayPLusMap is accepted but a bit slower
     *  - tallestBillboardUsingDiffAndMap is accepted, but even slower
     *  - tallestBillboardUsingSumsAndMap leads to TLE
     *  - tallestBillboardUsingSumsAndArray leads to MLE
     * */
    public static int tallestBillboardUsingDiffAndArray(int[] rods) {
        if (rods == null || rods.length < 2)
            return 0;

        int sum = Arrays.stream(rods).sum();
        Integer[][] dp = new Integer[rods.length][2 * sum + 1];
        return tallestBillboardUsingDiffAndArray(rods, 0, 0, dp, sum);
    }

    private static int tallestBillboardUsingDiffAndArray(int[] rods, int pos, int diff, Integer[][] dp, int diffOffset) {
        if (pos == rods.length)
            return diff == 0 ? 0 : -1;

        int diffIndex = diff + diffOffset;
        if (dp[pos][diffIndex] != null)
            return dp[pos][diffIndex];

        int rod = rods[pos];
        int tallest = Integer.MIN_VALUE;

        int skip = tallestBillboardUsingDiffAndArray(rods, pos + 1, diff, dp, diffOffset);
        if (skip != -1)
            tallest = skip;

        int addToLeft = tallestBillboardUsingDiffAndArray(rods, pos + 1, diff + rod, dp, diffOffset);
        if (addToLeft != -1)
            tallest = Math.max(tallest, addToLeft);

        int addToRight = tallestBillboardUsingDiffAndArray(rods, pos + 1, diff - rod, dp, diffOffset);
        if (addToRight != -1)
            tallest = Math.max(tallest, rod + addToRight);

        dp[pos][diffIndex] = tallest;

        return tallest;
    }

    public static int tallestBillboardUsingDiffAndArrayWithPruning(int[] rods) {
        if (rods == null || rods.length < 2)
            return 0;

        int n = rods.length;
        int[] suffixSums = new int[n];
        suffixSums[n - 1] = rods[n - 1];

        for (int i = rods.length - 2; i >= 0; i--)
            suffixSums[i] = suffixSums[i + 1] + rods[i];

        int sum = suffixSums[0];
        Integer[][] dp = new Integer[rods.length][2 * sum + 1];
        return tallestBillboardUsingDiffAndArrayWithPruning(rods, 0, 0, dp, sum, suffixSums);
    }

    private static int tallestBillboardUsingDiffAndArrayWithPruning(int[] rods, int pos, int diff, Integer[][] dp, int diffOffset, int[] suffixSums) {
        if (pos == rods.length)
            return diff == 0 ? 0 : -1;

        // pruning
        int remainingSum = suffixSums[pos];
        if (Math.abs(diff) > remainingSum)
            return -1;

        int diffIndex = diff + diffOffset;
        if (dp[pos][diffIndex] != null)
            return dp[pos][diffIndex];

        int rod = rods[pos];
        int tallest = Integer.MIN_VALUE;

        int skip = tallestBillboardUsingDiffAndArrayWithPruning(rods, pos + 1, diff, dp, diffOffset, suffixSums);
        if (skip != -1)
            tallest = skip;

        int addToLeft = tallestBillboardUsingDiffAndArrayWithPruning(rods, pos + 1, diff + rod, dp, diffOffset, suffixSums);
        if (addToLeft != -1)
            tallest = Math.max(tallest, addToLeft);

        int addToRight = tallestBillboardUsingDiffAndArrayWithPruning(rods, pos + 1, diff - rod, dp, diffOffset, suffixSums);
        if (addToRight != -1)
            tallest = Math.max(tallest, rod + addToRight);

        dp[pos][diffIndex] = tallest;

        return tallest;
    }

    public static int tallestBillboardUsingDiffAndArray2(int[] rods) {
        if (rods == null || rods.length < 2)
            return 0;

        Integer[][] dp = new Integer[rods.length][10001];
        return tallestBillboardUsingDiffAndArray2(rods, 0, 0, dp);
    }

    private static int tallestBillboardUsingDiffAndArray2(int[] rods, int pos, int diff, Integer[][] dp) {
        if (pos == rods.length)
            return diff == 0 ? 0 : -1;

        int diffIndex = diff + 5000;
        if (dp[pos][diffIndex] != null)
            return dp[pos][diffIndex];

        int rod = rods[pos];
        int tallest = Integer.MIN_VALUE;

        int skip = tallestBillboardUsingDiffAndArray2(rods, pos + 1, diff, dp);
        if (skip != -1)
            tallest = skip;

        int addToLeft = tallestBillboardUsingDiffAndArray2(rods, pos + 1, diff + rod, dp);
        if (addToLeft != -1)
            tallest = Math.max(tallest, addToLeft);

        int addToRight = tallestBillboardUsingDiffAndArray2(rods, pos + 1, diff - rod, dp);
        if (addToRight != -1)
            tallest = Math.max(tallest, rod + addToRight);

        dp[pos][diffIndex] = tallest;

        return tallest;
    }

    public static int tallestBillboardUsingDiffAndArrayPLusMap(int[] rods) {
        if (rods == null || rods.length < 2)
            return 0;

        Map<Integer, Integer>[] dp = new HashMap[rods.length];
        for (int i = 0; i < rods.length; i++)
            dp[i] = new HashMap<>();

        return tallestBillboardUsingDiffAndArrayPLusMap(rods, 0, 0, dp);
    }

    private static int tallestBillboardUsingDiffAndArrayPLusMap(int[] rods, int pos, int diff, Map<Integer, Integer>[] dp) {
        if (pos == rods.length)
            return diff == 0 ? 0 : -1;

        if (dp[pos].containsKey(diff))
            return dp[pos].get(diff);

        int rod = rods[pos];
        int tallest = Integer.MIN_VALUE;

        int skip = tallestBillboardUsingDiffAndArrayPLusMap(rods, pos + 1, diff, dp);
        if (skip != -1)
            tallest = skip;

        int addToLeft = tallestBillboardUsingDiffAndArrayPLusMap(rods, pos + 1, diff + rod, dp);
        if (addToLeft != -1)
            tallest = Math.max(tallest, addToLeft);

        int addToRight = tallestBillboardUsingDiffAndArrayPLusMap(rods, pos + 1, diff - rod, dp);
        if (addToRight != -1)
            tallest = Math.max(tallest, rod + addToRight);

        dp[pos].put(diff, tallest);

        return tallest;
    }

    public static int tallestBillboardUsingDiffAndMap(int[] rods) {
        if (rods == null || rods.length < 2)
            return 0;

        return tallestBillboardUsingDiffAndMap(rods, 0, 0, new HashMap<>());
    }

    private static int tallestBillboardUsingDiffAndMap(int[] rods, int pos, int diff, Map<String, Integer> dp) {
        if (pos == rods.length)
            return diff == 0 ? 0 : -1;

        String key = "%s,%s".formatted(pos, diff);
        if (dp.containsKey(key))
            return dp.get(key);

        int rod = rods[pos];
        int tallest = Integer.MIN_VALUE;

        int skip = tallestBillboardUsingDiffAndMap(rods, pos + 1, diff, dp);
        if (skip != -1)
            tallest = skip;

        int addToLeft = tallestBillboardUsingDiffAndMap(rods, pos + 1, diff + rod, dp);
        if (addToLeft != -1)
            tallest = Math.max(tallest, addToLeft);

        int addToRight = tallestBillboardUsingDiffAndMap(rods, pos + 1, diff - rod, dp);
        if (addToRight != -1)
            tallest = Math.max(tallest, rod + addToRight);

        dp.put(key, tallest);

        return tallest;
    }

    public static int tallestBillboardUsingSumsAndMap(int[] rods) {
        if (rods == null || rods.length < 2)
            return 0;

        return tallestBillboardUsingSumsAndMap(rods, 0, 0, 0, new HashMap<>());
    }

    private static int tallestBillboardUsingSumsAndMap(int[] rods, int pos, int sum1, int sum2, Map<String, Integer> dp) {
        if (pos == rods.length)
            return sum1 == sum2 ? sum1 : -1;

        String key = "%s,%s,%s".formatted(pos, sum1, sum2);
        if (dp.containsKey(key))
            return dp.get(key);

        int rod = rods[pos];
        int tallest = Integer.MIN_VALUE;

        int skip = tallestBillboardUsingSumsAndMap(rods, pos + 1, sum1, sum2, dp);
        if (skip != -1)
            tallest = skip;

        int addToLeft = tallestBillboardUsingSumsAndMap(rods, pos + 1, sum1 + rod, sum2, dp);
        if (addToLeft != -1)
            tallest = Math.max(tallest, addToLeft);

        int addToRight = tallestBillboardUsingSumsAndMap(rods, pos + 1, sum1, sum2 + rod, dp);
        if (addToRight != -1)
            tallest = Math.max(tallest, addToRight);

        dp.put(key, tallest);

        return tallest;
    }

    public static int tallestBillboardUsingSumsAndArray(int[] rods) {
        if (rods == null || rods.length < 2)
            return 0;

        Integer[][][] dp = new Integer[rods.length][5001][5001];
        return tallestBillboardUsingSumsAndArray(rods, 0, 0, 0, dp);
    }

    private static int tallestBillboardUsingSumsAndArray(int[] rods, int pos, int sum1, int sum2, Integer[][][] dp) {
        if (pos == rods.length)
            return sum1 == sum2 ? sum1 : -1;

        if (dp[pos][sum1][sum2] != null)
            return dp[pos][sum1][sum2];

        int rod = rods[pos];
        int tallest = Integer.MIN_VALUE;

        int skip = tallestBillboardUsingSumsAndArray(rods, pos + 1, sum1, sum2, dp);
        if (skip != -1)
            tallest = skip;

        int addToLeft = tallestBillboardUsingSumsAndArray(rods, pos + 1, sum1 + rod, sum2, dp);
        if (addToLeft != -1)
            tallest = Math.max(tallest, addToLeft);

        int addToRight = tallestBillboardUsingSumsAndArray(rods, pos + 1, sum1, sum2 + rod, dp);
        if (addToRight != -1)
            tallest = Math.max(tallest, addToRight);

        dp[pos][sum1][sum2] = tallest;

        return tallest;
    }
}