package com.bteshome.algorithms.dynamicProgramming_;

import java.util.ArrayList;
import java.util.List;

public class DPAlgorithms40 {
    /**
     * https://leetcode.com/problems/palindrome-partitioning
     * This a DP plus backtracking solution.
     * And it gives the best performance possible.
     * */
    public static List<List<String>> partition(String s) {
        int n = s.length();
        boolean[][] isPalindromeDp = new boolean[n][n];

        for (int i = 0; i < n; i++)
            isPalindromeDp[i][i] = true;

        for (int i = 0; i < n - 1; i++)
            if (s.charAt(i) == s.charAt(i + 1))
                isPalindromeDp[i][i + 1] = true;

        for (int len = 3; len <= n ; len++)
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                isPalindromeDp[i][j] = s.charAt(i) == s.charAt(j) && isPalindromeDp[i + 1][j - 1];
            }

        List<List<String>> partitions = new ArrayList<>();
        partition(s, partitions, new ArrayList<>(), 0, isPalindromeDp);

        return partitions;
    }

    private static void partition(String s, List<List<String>> partitions, List<String> candidate, int pos, boolean[][] isPalindromeDp) {
        if (pos == s.length()) {
            partitions.add(new ArrayList<>(candidate));
            return;
        }

        for (int j = pos; j < s.length(); j++) {
            if (isPalindromeDp[pos][j]) {
                candidate.add(s.substring(pos, j + 1));
                partition(s, partitions, candidate, j + 1, isPalindromeDp);
                candidate.removeLast();
            }
        }
    }

    /* https://leetcode.com/problems/palindrome-partitioning-ii */
    public static int minCutTopDown(String s) {
        if (s == null || s.length() < 2)
            return 0;

        int n = s.length();
        boolean[][] isPalindromeDp = new boolean[n][n];
        Integer[] dp = new Integer[n];

        for (int i = 0; i < n; i++)
            isPalindromeDp[i][i] = true;

        for (int i = 0; i < n - 1; i++)
            isPalindromeDp[i][i + 1] = s.charAt(i) == s.charAt(i + 1);

        for (int len = 3; len <= n; len++)
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                isPalindromeDp[i][j] = s.charAt(i) == s.charAt(j) && isPalindromeDp[i + 1][j - 1];
            }

        return minCutTopDown(s, 0, isPalindromeDp, dp);
    }

    private static int minCutTopDown(String s, int pos, boolean[][] isPalindromeDpTable, Integer[] dp) {
        if (pos == s.length())
            return 0;

        if (isPalindromeDpTable[pos][s.length() - 1])
            return 0;

        if (dp[pos] == null) {
            int min = Integer.MAX_VALUE;
            for (int j = pos; j < s.length() - 1; j++)
                if (isPalindromeDpTable[pos][j])
                    min = Math.min(min, 1 + minCutTopDown(s, j + 1, isPalindromeDpTable, dp));
            dp[pos] = min;
        }

        return dp[pos];
    }

    /**
     * https://leetcode.com/problems/shortest-palindrome
     * NOTE: this solution is correct, but not accepted by LeetCode
     *       with 'Memory Limit Exceeded', from the use of a 2D array.
     *       There is a better solution - the Knuth–Morris–Pratt algorithm.
     * */
    public static String shortestPalindrome(String s) {
        if (s == null || s.length() < 2)
            return s;

        int n = s.length();
        boolean[][] isPalindromeDp = new boolean[n][n];

        for (int i = 0; i < n; i++)
            isPalindromeDp[i][i] = true;

        for (int i = 0; i < n - 1; i++)
            isPalindromeDp[i][i + 1] = s.charAt(i) == s.charAt(i + 1);

        for (int len = 3; len <= n; len++)
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                isPalindromeDp[i][j] = s.charAt(i) == s.charAt(j) && isPalindromeDp[i + 1][j - 1];
            }

        StringBuilder builder = new StringBuilder();

        for (int j = n - 1; j >= 1; j--) {
            if (isPalindromeDp[0][j])
                break;
            builder.append(s.charAt(j));
        }

        return builder + s;
    }
}