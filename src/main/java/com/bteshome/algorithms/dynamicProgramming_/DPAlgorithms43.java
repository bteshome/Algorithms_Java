package com.bteshome.algorithms.dynamicProgramming_;

import java.util.List;

public class DPAlgorithms43 {
    /* https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome */
    public static int minInsertionsTopDown(String s) {
        if (s == null)
            return 0;

        int n = s.length();

        return minInsertionsTopDown(s, 0, n - 1, new Integer[n][n]);
    }

    private static int minInsertionsTopDown(String s, int left, int right, Integer[][] dp) {
        if (left >= right)
            return 0;

        if (dp[left][right] == null) {
            if (s.charAt(left) == s.charAt(right))
                dp[left][right] = minInsertionsTopDown(s, left + 1, right - 1, dp);
            else {
                int min1 = minInsertionsTopDown(s, left, right - 1, dp);
                int min2 = minInsertionsTopDown(s, left + 1, right, dp);
                dp[left][right] = 1 + Math.min(min1, min2);
            }
        }

        return dp[left][right];
    }

    /* https://leetcode.com/problems/perfect-squares */
    public static int numSquaresTopDown(int n) {
        if (n < 0)
            return 0;

        return numSquaresTopDown(n, new Integer[n + 1]);
    }

    private static int numSquaresTopDown(int n, Integer[] dp) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;

        if (dp[n] == null) {
            int min = Integer.MAX_VALUE;

            for (int i = 1; i <= Math.sqrt(n); i++)
                min = Math.min(min, numSquaresTopDown(n - i * i, dp));

            dp[n] = min + 1;
        }

        return dp[n];
    }

    /**
     * https://leetcode.com/problems/combination-sum-iv
     * NOTE: the dp array is 1D instead of 2d - a num boundary is not used.
     *       Because order matters, unlike with Coin Change II.
     * */
    public static int combinationSum4(int[] nums, int target) {
        if (target == 0)
            return 1;

        int n = nums.length;

        return combinationSum4(nums, target, new Integer[target + 1]);
    }

    private static int combinationSum4(int[] nums, int target, Integer[] dp) {
        if (target == 0)
            return 1;

        if (dp[target] == null) {
            int ways = combinationSum4(nums, target, dp);

            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                if (target >= num)
                    ways += combinationSum4(nums, target - num, dp);
            }

            dp[target] = ways;
        }

        return dp[target];
    }
}