package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms3 {
    /* https://leetcode.com/problems/longest-palindromic-subsequence */
    public static int longestPalindromeSubseqBottomUp(String s) {
        if (s == null)
            return 0;

        int n = s.length();

        if (n < 2)
            return n;

        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++)
            dp[i][i] = 1;

        for (int i = 0; i < n - 1; i++) {
            int j = i + 1;
            dp[i][j] = s.charAt(i) == s.charAt(j) ? 2 : 1;
        }

        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len ; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                else
                    dp[i][j] = Math.max(dp[i + 1][j] , dp[i][j - 1]);
            }
        }

        return dp[0][n - 1];
    }

    /* https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings */
    public static int minimumDeleteSumTopDown(String s1, String s2) {
        if (s1 == null)
            s1 = "";
        if (s2 == null)
            s2 = "";

        Integer[][] dp = new Integer[s1.length() + 1][s2.length() + 1];

        return minimumDeleteSumTopDown(s1, s2, 0, 0, dp);
    }

    private static int minimumDeleteSumTopDown(String s1, String s2, int pos1, int pos2, Integer[][] dp) {
        if (dp[pos1][pos2] == null) {
            int minSum = Integer.MAX_VALUE;

            if (pos1 == s1.length()) {
                int sum = 0;
                for (int j = pos2; j < s2.length(); j++)
                    sum += s2.charAt(j);
                minSum = sum;
            } else if (pos2 == s2.length()) {
                int sum = 0;
                for (int i = pos1; i < s1.length(); i++)
                    sum += s1.charAt(i);
                minSum = sum;
            } else {
                if (s1.charAt(pos1) ==  s2.charAt(pos2))
                    minSum = minimumDeleteSumTopDown(s1, s2, pos1 + 1, pos2 + 1, dp);
                else {
                    int minSum1 = s1.charAt(pos1) + minimumDeleteSumTopDown(s1, s2, pos1 + 1, pos2, dp);
                    int minSum2 = s2.charAt(pos2) + minimumDeleteSumTopDown(s1, s2, pos1, pos2 + 1, dp);
                    minSum = Math.min(minSum1, minSum2);
                }
            }

            dp[pos1][pos2] = minSum;
        }

        return dp[pos1][pos2];
    }
}