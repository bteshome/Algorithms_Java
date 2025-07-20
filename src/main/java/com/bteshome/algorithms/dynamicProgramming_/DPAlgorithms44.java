package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms44 {
    private static final int MOD = ((int)Math.pow(10, 9)) + 7;

    /* https://leetcode.com/problems/ones-and-zeroes */
    public static int findMaxFormTopDown(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0)
            return (m == 0 && n == 0) ? 1 : 0;
        if (m < 0 || n < 0)
            return 0;

        int[][] counts = new int[strs.length][2];

        for (int i = 0; i < strs.length; i++) {
            String s = strs[i];
            int zeroCount = 0;
            int oneCount = 0;

            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (c == '0')
                    zeroCount++;
                else if (c == '1')
                    oneCount++;
            }

            counts[i] = new int[]{zeroCount, oneCount};
        }

        return findMaxFormTopDown(strs, m, n, 0, counts, new Integer[strs.length][m + 1][n + 1]);
    }

    private static int findMaxFormTopDown(String[] strs, int m, int n, int pos, int[][] counts, Integer[][][] dp) {
        if (pos == strs.length)
            return 0;

        if (dp[pos][m][n] == null) {
            int maxSize = findMaxFormTopDown(strs, m, n, pos + 1, counts, dp);

            int[] count = counts[pos];
            if (count[0] <= m && count[1] <= n)
                maxSize = Math.max(maxSize, 1 + findMaxFormTopDown(strs, m - count[0], n - count[1], pos + 1, counts, dp));

            dp[pos][m][n] = maxSize;
        }

        return dp[pos][m][n];
    }

    /* https://leetcode.com/problems/solving-questions-with-brainpower */
    public long mostPointsTopDown(int[][] questions) {
        if (questions == null)
            return 0;

        return mostPointsTopDown(questions, 0, new Long[questions.length]);
    }

    private long mostPointsTopDown(int[][] questions, int pos, Long[] dp) {
        if (pos >= questions.length)
            return 0;

        if (dp[pos] == null) {
            long max = mostPointsTopDown(questions, pos + 1, dp);

            int points = questions[pos][0];
            int questionsToSkip = questions[pos][1];
            max = Math.max(max, points + mostPointsTopDown(questions, pos + 1 + questionsToSkip, dp));

            dp[pos] = max;
        }

        return dp[pos];
    }

    /* https://leetcode.com/problems/count-ways-to-build-good-strings */
    public static int countGoodStringsTopDown(int low, int high, int zero, int one) {
        if (low > high || low < 0 || zero < 1 || one < 1)
            return 0;

        return countGoodStringsTopDown(low, high, zero, one, 0, new Integer[high + 1]);
    }

    private static int countGoodStringsTopDown(int low, int high, int zero, int one, int length, Integer[] dp) {
        if (length == high)
            return 1;

        if (dp[length] == null) {
            long count = 0;

            if (length >= low && length < high)
                count = 1;

            if (length + zero <= high)
                count = (count + countGoodStringsTopDown(low, high, zero, one, length + zero, dp)) % MOD;

            if (length + one <= high)
                count = (count + countGoodStringsTopDown(low, high, zero, one, length + one, dp)) % MOD;

            dp[length] = (int)count;
        }

        return dp[length];
    }
}