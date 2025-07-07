package com.bteshome.algorithms.dynamicProgramming_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DPAlgorithms5 {
    /**
     * https://leetcode.com/problems/word-break/description/?envType=study-plan-v2&envId=top-interview-150
     * */
    public static boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null) {
            return false;
        }

        return wordBreak(s, wordDict, new HashMap<String, Boolean>());
    }

    private static boolean wordBreak(String s, List<String> wordDict, HashMap<String, Boolean> cache) {
        if (s.isEmpty()) {
            return true;
        }

        if (!cache.containsKey(s)) {
            var breakable = false;

            for (var word : wordDict) {
                if (s.startsWith(word) && wordBreak(s.substring(word.length()), wordDict, cache)) {
                    breakable = true;
                    break;
                }
            }

            cache.put(s, breakable);
        }

        return cache.get(s);
    }

    /**
     * https://leetcode.com/problems/longest-increasing-subsequence/description/?envType=study-plan-v2&envId=top-interview-150
     * */
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int[] dp = new int[nums.length];
        dp[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i])
                    max = Math.max(max, dp[j]);
            }
            dp[i] = max + 1;
        }

        int overAllMax = 0;

        for (int i = 0; i < nums.length; i++)
            overAllMax = Math.max(overAllMax, dp[i]);

        return overAllMax;
    }

    /**
     * https://leetcode.com/problems/longest-common-subsequence/
     * */
    public static int longestCommonSubsequenceBottomUp(String text1, String text2) {
        if (text1 == null || text2 == null || text1.isBlank() || text2.isBlank())
            return 0;

        int[][] dp = new int[text1.length()][text2.length()];

        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = 1 + (i == 0 || j == 0 ? 0 : dp[i - 1][j - 1]);
                } else {
                    int lcs1 = i == 0 ? 0 : dp[i - 1][j];
                    int lcs2 = j == 0 ? 0 : dp[i][j - 1];
                    dp[i][j] = Math.max(lcs1, lcs2);
                }
            }
        }

        return dp[text1.length() - 1][text2.length() - 1];
    }

    public static int longestCommonSubsequenceTopDown(String text1, String text2) {
        if (text1 == null || text2 == null)
            return 0;

        Integer[][] dp = new Integer[text1.length()][];
        for (int i = 0; i < text1.length(); i++)
            dp[i] = new Integer[text2.length()];

        return longestCommonSubsequenceTopDown(text1, text2, 0, 0, dp);
    }

    private static int longestCommonSubsequenceTopDown(String text1, String text2, int pos1, int pos2, Integer[][] dp) {
        if (pos1 == text1.length() || pos2 == text2.length())
            return 0;

        if (dp[pos1][pos2] == null) {
            if (text1.charAt(pos1) == text2.charAt(pos2))
                dp[pos1][pos2] = 1 + longestCommonSubsequenceTopDown(text1, text2, pos1 + 1, pos2 + 1, dp);
            else
                dp[pos1][pos2] = Math.max(
                        longestCommonSubsequenceTopDown(text1, text2, pos1, pos2 + 1, dp),
                        longestCommonSubsequenceTopDown(text1, text2, pos1 + 1, pos2, dp));
        }

        return dp[pos1][pos2];
    }
}