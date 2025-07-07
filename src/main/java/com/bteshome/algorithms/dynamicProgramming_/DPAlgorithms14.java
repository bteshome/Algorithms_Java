package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms14 {
    /**
     * https://leetcode.com/problems/wiggle-subsequence/
     * */
    public static int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        Integer[][] cache = new Integer[nums.length][];
        for (int i = 0; i < nums.length; i++)
            cache[i] = new Integer[2];

        int overallMaxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            overallMaxLength = Math.max(overallMaxLength, wiggleMaxLength(nums, i, 0, cache));
            overallMaxLength = Math.max(overallMaxLength, wiggleMaxLength(nums, i, 1, cache));
        }

        return overallMaxLength;
    }

    private static int wiggleMaxLength(int[] nums, int endPos, int endDiffPositive, Integer[][] cache) {
        if (endPos == 0)
            return 1;

        if (cache[endPos][endDiffPositive] == null) {
            int maxLength = 0;
            for (int i = 0; i < endPos; i++) {
                int diff = nums[endPos] - nums[i];
                if ((endDiffPositive == 1 && diff > 0) || (endDiffPositive == 0 && diff < 0))
                    maxLength = Math.max(maxLength, wiggleMaxLength(nums, i, 1 - endDiffPositive, cache));
            }
            cache[endPos][endDiffPositive] = 1 + maxLength;
        }

        return cache[endPos][endDiffPositive];
    }

    /**
     * https://leetcode.com/problems/4-keys-keyboard/
     * */
    public static int maxA(int n) {
        if (n <= 3)
            return n;

        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++)
            dp[i] = i;

        for (int i = 4; i <= n; i++)
            for (int j = 1; j <= i - 3; j++)
                dp[i] = Math.max(dp[i], dp[j] * (i - j - 1));

        return dp[n];
    }

    /**
     * https://leetcode.com/problems/maximal-square/description/
     * */
    public static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int[][] dp = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++)
            dp[i][matrix[0].length - 1] = matrix[i][matrix[0].length - 1] == '1' ? 1 : 0;
        for (int j = 0; j < matrix[0].length; j++)
            dp[matrix.length - 1][j] = matrix[matrix.length - 1][j] == '1' ? 1 : 0;

        for (int i = matrix.length - 2; i >= 0; i--) {
            for (int j = matrix[0].length - 2; j >= 0 ; j--) {
                if (matrix[i][j] == '0')
                    dp[i][j] = 0;
                else {
                    int min = Math.min(dp[i][j + 1], dp[i + 1][j]);
                    min = Math.min(min, dp[i + 1][j + 1]);
                    dp[i][j] = 1 + min;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                max = Math.max(max, dp[i][j]);

        return max * max;
    }
}