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

        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++)
                if (nums[j] < nums[i])
                    max = Math.max(max, dp[j]);
            dp[i] = max + 1;
        }

        int overallMax = 0;
        for (int i = 0; i < n; i++)
            overallMax = Math.max(overallMax, dp[i]);

        return overallMax;
    }

    /* https://leetcode.com/problems/number-of-longest-increasing-subsequence */
    public static int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 1;       // length
        dp[0][1] = 1;       // count

        for (int i = 1; i < n; i++) {
            int max = 0;
            int count = 1;
            for (int j = 0; j < i; j++)
                if (nums[j] < nums[i]) {
                    if (dp[j][0] > max) {
                        max = dp[j][0];
                        count = dp[j][1];
                    } else if (dp[j][0] == max) {
                        count += dp[j][1];
                    }
                }
            dp[i][0] = max + 1;
            dp[i][1] = count;
        }

        int overallMax = 0;
        int overallMaxCount = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i][0] > overallMax) {
                overallMax = dp[i][0];
                overallMaxCount = dp[i][1];
            }
            else if (dp[i][0] == overallMax)
                overallMaxCount += dp[i][1];
        }

        return overallMaxCount;
    }

    /**
     * https://leetcode.com/problems/longest-common-subsequence/
     * */
    public static int longestCommonSubsequenceBottomUp(String text1, String text2) {
        if (text1 == null || text2 == null || text1.isBlank() || text2.isBlank())
            return 0;

        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (text1.charAt(i) == text2.charAt(j))
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
            }
        }

        return dp[0][0];
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

    /**
     * https://leetcode.com/problems/uncrossed-lines
     * NOTE: this problem is almost identical to the common subsequence problem
     * */
    public static int maxUncrossedLinesBottomUp(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0)
            return 0;

        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (nums1[i] == nums2[j])
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
            }
        }

        return dp[0][0];
    }
}