package com.bteshome.algorithms.dynamicProgramming_;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DPAlgorithms51 {
    /* https://leetcode.com/problems/last-stone-weight-ii */
    public static int lastStoneWeightIITopDown_ModelledAsPartitioning_UsingMap(int[] stones) {
        if (stones == null)
            return 0;

        int n = stones.length;
        Map<Integer, Integer>[] dp = new Map[n];

        for (int i = 0; i < n; i++)
            dp[i] = new HashMap<>();

        return lastStoneWeightIITopDown_ModelledAsPartitioning_UsingMap(stones, 0, 0, dp);
    }

    private static int lastStoneWeightIITopDown_ModelledAsPartitioning_UsingMap(int[] stones, int pos, int diff, Map<Integer, Integer>[] dp) {
        if (pos == stones.length)
            return Math.abs(diff);

        if (dp[pos].containsKey(diff))
            return dp[pos].get(diff);

        int addToGroup1 = lastStoneWeightIITopDown_ModelledAsPartitioning_UsingMap(stones, pos + 1, diff + stones[pos], dp);
        int addToGroup2 = lastStoneWeightIITopDown_ModelledAsPartitioning_UsingMap(stones, pos + 1, diff - stones[pos], dp);
        dp[pos].put(diff, Math.min(addToGroup1, addToGroup2));

        return dp[pos].get(diff);
    }

    public static int lastStoneWeightIITopDown_ModelledAsPartitioning_UsingArray(int[] stones) {
        if (stones == null)
            return 0;

        int n = stones.length;
        int totalSum = Arrays.stream(stones).sum();
        Integer[][] dp = new Integer[n][2 * totalSum + 1];

        return lastStoneWeightIITopDown_ModelledAsPartitioning_UsingArray(stones, 0, 0, dp, totalSum);
    }

    private static int lastStoneWeightIITopDown_ModelledAsPartitioning_UsingArray(int[] stones, int pos, int diff, Integer[][] dp, int offset) {
        if (pos == stones.length)
            return Math.abs(diff);

        int diffIndex = diff + offset;

        if (dp[pos][diffIndex] != null)
            return dp[pos][diffIndex];

        int addToGroup1 = lastStoneWeightIITopDown_ModelledAsPartitioning_UsingArray(stones, pos + 1, diff + stones[pos], dp, offset);
        int addToGroup2 = lastStoneWeightIITopDown_ModelledAsPartitioning_UsingArray(stones, pos + 1, diff - stones[pos], dp, offset);
        dp[pos][diffIndex] = Math.min(addToGroup1, addToGroup2);

        return dp[pos][diffIndex];
    }

    public static int lastStoneWeightIITopDown_ModelledAs01Knapsack(int[] stones) {
        if (stones == null)
            return 0;

        int n = stones.length;
        int totalSum = Arrays.stream(stones).sum();
        int target = totalSum / 2;
        Integer[][] dp = new Integer[n + 1][target + 1];

        int maxSum = lastStoneWeightIITopDown_ModelledAs01Knapsack(stones, target, n, dp);
        return Math.abs(2 * maxSum - totalSum);
    }

    private static int lastStoneWeightIITopDown_ModelledAs01Knapsack(int[] stones, int targetAmount, int numStonesAvailable, Integer[][] dp ) {
        if (numStonesAvailable == 0)
            return 0;

        if (dp[numStonesAvailable][targetAmount] != null)
            return dp[numStonesAvailable][targetAmount];

        int newStoneIndex = numStonesAvailable - 1;
        int newStone = stones[newStoneIndex];

        int max = lastStoneWeightIITopDown_ModelledAs01Knapsack(stones, targetAmount, numStonesAvailable - 1, dp);
        if (newStone <= targetAmount)
            max = Math.max(max, newStone + lastStoneWeightIITopDown_ModelledAs01Knapsack(stones, targetAmount - newStone, numStonesAvailable - 1, dp));

        dp[numStonesAvailable][targetAmount] = max;

        return dp[numStonesAvailable][targetAmount];
    }
}