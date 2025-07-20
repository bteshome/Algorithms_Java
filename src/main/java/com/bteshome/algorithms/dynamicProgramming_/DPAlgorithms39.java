package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms39 {
    /**
     * https://leetcode.com/problems/palindromic-substrings
     * NOTE: even though this solution is good, there is also a two-pointer expand around center
     *       approach that uses less space and is faster (though in the same big 0 class)
     */
    public static int countSubstringsBottomUp(String s) {
        if (s == null)
            return 0;
        if (s.length() < 2)
            return s.length();

        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        // length 1
        for (int i = 0; i < n; i++)
            dp[i][i] = true;
        int count = n;

        // length 2
        for (int i = 0; i < n - 1; i++) {
            int j = i + 1;
            if (s.charAt(i) == s.charAt(j)) {
                dp[i][j] = true;
                count++;
            }
        }
        
        // length > 2
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * https://leetcode.com/problems/longest-palindromic-substring
     * NOTE: this solution is correct, but is slower than the two-pointer solution,
     *       which employs the expand around center approach
     * */
    public static String longestPalindromeBottomUp(String s) {
        if (s == null || s.length() < 2)
            return s;

        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int longestStart = 0;
        int longestEnd = 0;

        for (int i = 0; i < n; i++)
            dp[i][i] = true;

        for (int i = 0; i < n - 1; i++) {
            int j = i + 1;
            if (s.charAt(i) == s.charAt(j)) {
                dp[i][j] = true;
                longestStart = i;
                longestEnd = j;
            }
        }

        for (int len = 3; len <= n; len++)
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    longestStart = i;
                    longestEnd = j;
                }
            }

        return s.substring(longestStart, longestEnd + 1);
    }
}