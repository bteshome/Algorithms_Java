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
    public static class LongestPalindrome {
        private int maxLength = 0;
        private int maxLenStart = 0;
        private int maxLenEnd = -1;
        boolean[][] dp = null;
        int n = 0;
        String s = null;

        public String longestPalindrome(String s) {
            if (s == null || s.length() < 2)
                return s;

            this.s = s;
            n = s.length();
            dp = new boolean[n][n];

            // length 1
            for (int i = 0; i < n; i++)
                dp[i][i] = true;
            maxLength = 1;
            maxLenStart = 0;
            maxLenEnd = 0;

            // length 2
            for (int i = 0; i < n - 1; i++) {
                int j = i + 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    maxLength = 2;
                    maxLenStart = i;
                    maxLenEnd = j;
                }
            }

            // length > 2
            for (int len = 3; len <= n; len++) {
                for (int i = 0; i <= n - len; i++) {
                    int j = i + len - 1;
                    if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        if (len > maxLength) {
                            maxLength = len;
                            maxLenStart = i;
                            maxLenEnd = j;
                        }
                    }
                }
            }

            return s.substring(maxLenStart, maxLenEnd + 1);
        }

        private void longestPalindrome(String s, int i, int j) {
            while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
                int length = j - i + 1;
                if (length > maxLength) {
                    maxLength = length;
                    maxLenStart = i;
                    maxLenEnd = j;
                }
                i--;
                j++;
            }
        }
    }
}
