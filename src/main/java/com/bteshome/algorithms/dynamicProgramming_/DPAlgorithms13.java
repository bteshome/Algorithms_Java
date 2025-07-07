package com.bteshome.algorithms.dynamicProgramming_;

import java.util.*;

public class DPAlgorithms13 {
    /**
     * https://www.hackerrank.com/challenges/sherlock-and-cost/
     * */
    public static int cost(List<Integer> b) {
        if (b == null || b.size() < 2)
            return 0;

        int[][] dp = new int[b.size()][];
        for (int i = 0; i < dp.length; i++)
            dp[i] = new int[2];

        // unnecessary, just for clarity about the base case
        dp[0][0] = 0;
        dp[0][1] = 0;

        for (int i = 1; i < dp.length; i++) {
            int cost1 = dp[i - 1][0];
            int cost2 = dp[i - 1][1] + Math.abs(1 - b.get(i - 1));
            dp[i][0] = Math.max(cost1, cost2);

            cost1 = dp[i - 1][0] + Math.abs(b.get(i) - 1);
            cost2 = dp[i - 1][1] + Math.abs(b.get(i) - b.get(i - 1));
            dp[i][1] = Math.max(cost1, cost2);
        }

        return Math.max(dp[b.size() - 1][0], dp[b.size() - 1][1]);
    }

    /**
     * https://leetcode.com/problems/longest-string-chain/
     * */
    public static int longestStrChain(String[] words) {
        if (words == null)
            return 0;
        if (words.length < 2)
            return words.length;

        Arrays.sort(words, Comparator.comparingInt(String::length));

        int maxLength = 0;
        Integer[] cache = new Integer[words.length];

        for (int i = 0; i < words.length; i++)
            maxLength = Math.max(maxLength, longestStrChain(words, i, cache));

        return maxLength;
    }

    private static int longestStrChain(String[] words, int pos, Integer[] cache) {
        if (cache[pos] == null) {
            int length = 0;

            for (int i = pos + 1; i < words.length; i++)
                if (isPredecessor(words[pos], words[i]))
                    length = Math.max(length, longestStrChain(words, i, cache));

            cache[pos] = length + 1;
        }

        return cache[pos];
    }

    private static boolean isPredecessor(String word1, String word2) {
        if (word1.length() + 1 != word2.length())
            return false;

        int i = 0;
        int j = 0;

        while (i < word1.length() && j < word2.length()) {
            if (word1.charAt(i) == word2.charAt(j)) {
                i++;
                j++;
            } else {
                if (i != j)
                    return false;
                j++;
            }
        }

        return true;
    }
}