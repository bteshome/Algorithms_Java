package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms8 {
    /**
     * https://leetcode.com/problems/maximum-subarray/description/?envType=problem-list-v2&envId=dynamic-programming&difficulty=MEDIUM
     * */
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        var maxSum = nums[0];
        var currentSubArrayMaxSum = nums[0];

        for (int end = 1; end < nums.length; end++) {
            currentSubArrayMaxSum = Math.max(0, currentSubArrayMaxSum) + nums[end];
            maxSum = Math.max(maxSum, currentSubArrayMaxSum);
        }

        return maxSum;
    }

    public static int maxSubArrayBruteforce(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        var maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                var sum = 0;

                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }

                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }

        return maxSum;
    }

    public static int maxSubArrayBruteforce2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        var maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            var sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }




}
