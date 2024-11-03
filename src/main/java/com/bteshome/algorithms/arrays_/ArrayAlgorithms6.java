package com.bteshome.algorithms.arrays_;

import java.util.Stack;

public class ArrayAlgorithms6 {
    /**
     * https://leetcode.com/problems/product-of-array-except-self
     * Beautiful!!
     * */
    public static int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        var productsFromFirst = new int[nums.length];
        var productsFromLast = 1;
        int[] output = new int[nums.length];

        productsFromFirst[0] = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            productsFromFirst[i] = productsFromFirst[i-1] * nums[i];
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            output[i] = (i == 0 ? 1 : productsFromFirst[i-1]) * productsFromLast;
            productsFromLast *= nums[i];
        }

        return output;
    }
}