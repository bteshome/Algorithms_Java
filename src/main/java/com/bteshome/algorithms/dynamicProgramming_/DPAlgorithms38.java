package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms38 {
    /* https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing */
    public static int minSwapTopDown(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length != nums2.length || nums1.length == 0)
            return 0;

        return minSwapTopDown(nums1, nums2, 0, 0, new Integer[nums1.length][2]);
    }

    private static int minSwapTopDown(int[] nums1, int[] nums2, int pos, int prevSwapped, Integer[][] dp) {
        if (pos == nums1.length)
            return 0;

        if (dp[pos][prevSwapped] == null) {
            int swaps = Integer.MAX_VALUE;
            int prev1 = pos == 0 ? Integer.MIN_VALUE : (prevSwapped == 1 ? nums2[pos - 1] : nums1[pos - 1]);
            int prev2 = pos == 0 ? Integer.MIN_VALUE : (prevSwapped == 1 ? nums1[pos - 1] : nums2[pos - 1]);
            int current1 = nums1[pos];
            int current2 = nums2[pos];

            if (current1 > prev1 && current2 > prev2)
                swaps = minSwapTopDown(nums1, nums2, pos + 1, 0, dp);

            if (current2 > prev1 && current1 > prev2) {
                int next = 1 + minSwapTopDown(nums1, nums2, pos + 1, 1, dp);
                swaps = swaps == Integer.MAX_VALUE ? next : Math.min(swaps, next);
            }

            dp[pos][prevSwapped] = swaps;
        }

        return dp[pos][prevSwapped];
    }

    public static int minSwapBottomUp(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length != nums2.length || nums1.length == 0)
            return 0;

        int n = nums1.length;
        int[][] dp = new int[n + 1][2];

        for (int pos = n - 1; pos >= 0; pos--) {
            for (int prevSwapped = 0; prevSwapped < 2; prevSwapped++) {
                int swaps = Integer.MAX_VALUE;
                int prev1 = pos == 0 ? Integer.MIN_VALUE : (prevSwapped == 1 ? nums2[pos - 1] : nums1[pos - 1]);
                int prev2 = pos == 0 ? Integer.MIN_VALUE : (prevSwapped == 1 ? nums1[pos - 1] : nums2[pos - 1]);
                int current1 = nums1[pos];
                int current2 = nums2[pos];

                if (current1 > prev1 && current2 > prev2)
                    swaps = dp[pos + 1][0];

                if (current2 > prev1 && current1 > prev2) {
                    int next = 1 + dp[pos + 1][1];
                    swaps = swaps == Integer.MAX_VALUE ? next : Math.min(swaps, next);
                }

                dp[pos][prevSwapped] = swaps;
            }
        }

        return dp[0][0];
    }

    /**
     * https://leetcode.com/problems/valid-palindrome-iii
     * NOTE - even though the problem might seem to need a 3D array to store state,
     *        it can be optimized by converting it into a different problem:
     *        "what is the minimum number of deletions needed to convert s into a palindrome?"
     *        This only needs a 2D array.
     *        At the bottom is the 3D version, for reference.
     * */
    public static boolean isValidPalindromeTopDown(String s, int k) {
        if (s == null || k < 0)
            return false;
        int n = s.length();
        return isValidPalindromeTopDown(s, 0, n - 1, new Integer[n][n]) <= k;
    }

    private static int isValidPalindromeTopDown(String s, int i, int j, Integer[][] dp) {
        if (i >= j)
            return 0;

        if (dp[i][j] == null) {
            char a = s.charAt(i);
            char b = s.charAt(j);

            int deleteLeft = isValidPalindromeTopDown(s, i + 1, j, dp);
            int deleteRight = isValidPalindromeTopDown(s, i, j - 1, dp);
            int min = 1 + Math.min(deleteLeft, deleteRight);
            if (a == b) {
                int skipDelete = isValidPalindromeTopDown(s, i + 1, j - 1, dp);
                min = Math.min(min, skipDelete);
            }

            dp[i][j] = min;
        }

        return dp[i][j];
    }

    public static boolean isValidPalindromeBottomUp(String s, int k) {
        if (s == null || k < 0)
            return false;

        int n = s.length();
        int[][] dp = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                char a = s.charAt(i);
                char b = s.charAt(j);

                int deleteLeft = dp[i + 1][j];
                int deleteRight = dp[i][j - 1];
                int min = 1 + Math.min(deleteLeft, deleteRight);
                if (a == b) {
                    int skipDelete = len == 2 ? 0 : dp[i + 1][j - 1];
                    min = Math.min(min, skipDelete);
                }

                dp[i][j] = min;
            }
        }

        return dp[0][n - 1] <= k;
    }

    public static boolean isValidPalindrome3DArray(String s, int k) {
        if (s == null || k < 0)
            return false;
        int n = s.length();
        return isValidPalindrome3DArray(s, k, 0, n - 1, new Boolean[n][n][k + 1]);
    }

    private static boolean isValidPalindrome3DArray(String s, int k, int i, int j, Boolean[][][] dp) {
        if (i > j)
            return true;

        if (dp[i][j][k] == null) {
            boolean is = false;
            char a = s.charAt(i);
            char b = s.charAt(j);

            if (k == 0) {
                if (a == b)
                    is = isValidPalindrome3DArray(s, k, i + 1, j - 1, dp);
            }
            else {
                is = isValidPalindrome3DArray(s, k - 1, i + 1, j, dp) ||
                    isValidPalindrome3DArray(s, k - 1, i, j - 1, dp);
                if (a == b)
                    is |= isValidPalindrome3DArray(s, k, i + 1, j - 1, dp);
            }

            dp[i][j][k] = is;
        }

        return dp[i][j][k];
    }
}