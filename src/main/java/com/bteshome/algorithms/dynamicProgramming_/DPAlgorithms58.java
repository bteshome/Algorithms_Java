package com.bteshome.algorithms.dynamicProgramming_;

import java.util.*;

public class DPAlgorithms58 {
    private static final int MOD = (int) Math.pow(10, 9) + 7;

    /**
     * https://leetcode.com/problems/number-of-ways-to-wear-different-hats-to-each-other
     * NOTE: this solution is fast, but the next one is much slower.
     */
    public static int numberWaysTopDown(List<List<Integer>> hats) {
        if (hats == null || hats.isEmpty())
            return 0;

        // Additional optimization - use set for each person instead of list
        Set<Integer>[] people = new HashSet[hats.size()];
        for (int i = 0; i < hats.size(); i++)
            people[i] = new HashSet<>(hats.get(i));

        int mask = (1 << hats.size()) - 1;
        Integer[][] dp = new Integer[41][mask + 1];

        return numberWaysTopDown(people, 1, mask, dp);
    }

    private static int numberWaysTopDown(Set<Integer>[] people, int hatNumber, int mask, Integer[][] dp) {
        if (mask == 0)
            return 1;
        if (hatNumber == 41)
            return 0;

        // Additional optimization - early pruning
        int hatsLeft = 41 - hatNumber;
        int peopleLeft = Integer.bitCount(mask);
        if (peopleLeft > hatsLeft)
            return 0;

        if (dp[hatNumber][mask] == null) {
            long ways = numberWaysTopDown(people, hatNumber + 1, mask, dp);

            for (int person = 0; person < people.length; person++) {
                if (people[person].contains(hatNumber)) {
                    int personMask = 1 << person;
                    if ((mask & personMask) != 0)
                        ways = (ways + numberWaysTopDown(people, hatNumber + 1, mask & (~personMask), dp)) % MOD;
                }
            }

            dp[hatNumber][mask] = (int)ways;
        }

        return dp[hatNumber][mask];
    }

    /**
     * NOTE: this solution is correct, but exceeds LeetCode time limit.
     *       Shows when using a bitmask is appropriate.
     */
    public static int numberWaysTopDownSlower(List<List<Integer>> hats) {
        if (hats == null || hats.isEmpty())
            return 0;

        Map<Long, Integer>[] dp = new HashMap[hats.size()];
        for (int i = 0; i < hats.size(); i++)
            dp[i] = new HashMap<>();

        long mask = (1L << 40) - 1;
        return numberWaysTopDownSlower(hats, 0, mask, dp);
    }

    private static int numberWaysTopDownSlower(List<List<Integer>> hats, int peoplePos, long mask, Map<Long, Integer>[] dp) {
        if (peoplePos == hats.size())
            return 1;

        if (!dp[peoplePos].containsKey(mask)) {
            long ways = 0;

            for (int hat : hats.get(peoplePos)) {
                long hatMask = 1L << (hat - 1);
                if ((mask & hatMask) != 0)
                    ways = (ways + numberWaysTopDownSlower(hats, peoplePos + 1, mask & (~hatMask), dp)) % MOD;
            }

            dp[peoplePos].put(mask, (int) ways);
        }

        return dp[peoplePos].get(mask);
    }
}