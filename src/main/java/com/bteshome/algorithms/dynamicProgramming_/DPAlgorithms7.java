package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms7 {
    private static final int MOD = ((int) Math.pow(10, 9)) + 7;

    /**
     * https://leetcode.com/problems/interleaving-string
     * */
    public static boolean isInterleaveTopDown(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null || (s1.length() + s2.length()) != s3.length())
            return false;

        return isInterleaveTopDown(s1, s2, s3, 0, 0, new Boolean[s1.length() + 1][s2.length() + 1]);
    }

    private static boolean isInterleaveTopDown(String s1, String s2, String s3, int pos1, int pos2, Boolean[][] dp) {
        if (pos1 + pos2 == s3.length())
            return true;

        if (dp[pos1][pos2] == null) {
            int pos3 = pos1 + pos2;
            char c = s3.charAt(pos3);
            boolean s1Matches = (pos1 < s1.length()) && (s1.charAt(pos1) == c);
            boolean s2Matches = (pos2 < s2.length()) && (s2.charAt(pos2) == c);
            boolean matches = false;

            if (s1Matches && s2Matches) {
                matches = isInterleaveTopDown(s1, s2, s3, pos1 + 1, pos2, dp) ||
                        isInterleaveTopDown(s1, s2, s3, pos1, pos2 + 1, dp);
            } else if (s1Matches)
                matches = isInterleaveTopDown(s1, s2, s3, pos1 + 1, pos2, dp);
            else if (s2Matches)
                matches = isInterleaveTopDown(s1, s2, s3, pos1, pos2 + 1, dp);

            dp[pos1][pos2] = matches;
        }

        return dp[pos1][pos2];
    }

    /**
     * https://leetcode.com/problems/edit-distance/?envType=study-plan-v2&envId=top-interview-150
     * */
    public static int minDistanceTopDown(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return -1;
        }

        var cache = new Integer[word1.length()][];
        for (int i = 0; i < word1.length(); i++) {
            cache[i] = new Integer[word2.length()];
        }

        return minDistanceTopDown(word1, word2, 0, 0, cache);
    }

    private static int minDistanceTopDown(String word1, String word2, int pos1, int pos2, Integer[][] cache) {
        if (pos1 == word1.length()) {
            return word2.length() - pos2;
        }

        if (pos2 == word2.length()) {
            return word1.length() - pos1;
        }

        if (cache[pos1][pos2] == null) {
            int distance = Integer.MAX_VALUE;

            if (word1.charAt(pos1) == word2.charAt(pos2)) {
                distance = minDistanceTopDown(word1, word2, pos1 + 1, pos2 + 1, cache);
            } else {
                distance = Math.min(distance, 1 + minDistanceTopDown(word1, word2, pos1 + 1, pos2 + 1, cache));
                distance = Math.min(distance, 1 + minDistanceTopDown(word1, word2, pos1 + 1, pos2, cache));
                distance = Math.min(distance, 1 + minDistanceTopDown(word1, word2, pos1, pos2 + 1, cache));
            }

            cache[pos1][pos2] = distance;
        }

        return cache[pos1][pos2];
    }

    /* https://leetcode.com/problems/distinct-subsequences */
    public static int numDistinctTopDown(String s, String t) {
        if (s == null || t == null || s.length() < t.length())
            return 0;

        return numDistinctTopDown(s, t, 0, 0, new Integer[s.length()][t.length()]);
    }

    private static int numDistinctTopDown(String s, String t, int pos1, int pos2, Integer[][] dp) {
        if (pos2 == t.length())
            return 1;
        if (pos1 == s.length())
            return 0;

        if (dp[pos1][pos2] == null) {
            int num = numDistinctTopDown(s, t, pos1 + 1, pos2, dp);
            if (s.charAt(pos1) == t.charAt(pos2))
                num += numDistinctTopDown(s, t, pos1 + 1, pos2 + 1, dp);
            dp[pos1][pos2] = num;
        }

        return dp[pos1][pos2];
    }

    public static int numDistinctBottomUp(String s, String t) {
        if (s == null || t == null || s.length() < t.length())
            return 0;

        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++)
            dp[i][n] = 1;

        for (int i = m - 1; i >= 0 ; i--) {
            for (int j = n - 1; j >= 0 ; j--) {
                int num = dp[i + 1][j];
                if (s.charAt(i) == t.charAt(j))
                    num += dp[i + 1][j + 1];
                dp[i][j] = num;
            }
        }

        return dp[0][0];
    }
}