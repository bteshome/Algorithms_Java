package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms54 {
    private static final String INVALID = "0";
    private static final String EMPTY = "";

    /* https://leetcode.com/problems/form-largest-integer-with-digits-that-add-up-to-target */
    public static String largestNumberTopDown(int[] cost, int target) {
        if (cost == null || cost.length == 0 || target < 1)
            return EMPTY;

        return largestNumberTopDown(cost, target, new String[5001]);
    }

    private static String largestNumberTopDown(int[] cost, int target, String[] dp) {
        if (target == 0)
            return EMPTY;
        if (dp[target] != null)
            return dp[target];

        String max = INVALID;

        for (int i = 0; i < cost.length; i++) {
            if (cost[i] <= target) {
                String next = largestNumberTopDown(cost, target - cost[i], dp);
                if (next != INVALID) {
                    next = (i + 1) + next;
                    if (max == INVALID)
                        max = next;
                    else if (compare(next, max) > 0)
                        max = next;
                }
            }
        }

        dp[target] = max;

        return dp[target];
    }

    private static int compare(String num1, String num2) {
        if (num1.length() == num2.length()) {
            for (int i = 0; i < num1.length(); i++) {
                char a = num1.charAt(i);
                char b = num2.charAt(i);
                if (a == b)
                    continue;
                return Character.compare(a, b);
            }
        } else {
            return num1.length() - num2.length();
        }
        return 0;
    }

    /* https://leetcode.com/problems/partition-array-for-maximum-sum */
    public static int maxSumAfterPartitioning(int[] arr, int k) {
        if (arr == null || k < 1)
            return 0;

        return maxSumAfterPartitioning(arr, k, 0, new Integer[arr.length]);
    }

    private static int maxSumAfterPartitioning(int[] arr, int k, int start, Integer[] dp) {
        if (start == arr.length)
            return 0;
        if (dp[start] != null)
            return dp[start];

        int maxInPartition = 0;
        int max = 0;

        for (int end = start; end <= Math.min(start + k - 1, arr.length - 1); end++) {
            maxInPartition = Math.max(maxInPartition, arr[end]);
            int partitionLength = end - start + 1;
            int partitionSum = partitionLength * maxInPartition;
            max = Math.max(max, partitionSum + maxSumAfterPartitioning(arr, k, end + 1, dp));
        }

        dp[start] = max;

        return dp[start];
    }
}