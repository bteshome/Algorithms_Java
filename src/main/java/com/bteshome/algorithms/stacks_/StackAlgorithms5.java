package com.bteshome.algorithms.stacks_;

import java.util.*;

public class StackAlgorithms5 {
    /**
     * https://leetcode.com/problems/next-greater-element-i/description/
     * */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length > nums2.length || nums1.length == 0) {
            return new int[0];
        }

        var stack = new Stack<Integer>();
        var nextGreater = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums2.length; i++) {
            int currentValue = nums2[i];
            while (!stack.isEmpty() && currentValue > nums2[stack.peek()]) {
                var prevValue = nums2[stack.pop()];
                nextGreater.put(prevValue, currentValue);
            }
            stack.push(i);
        }

        var output = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            output[i] = nextGreater.getOrDefault(nums1[i], -1);
        }

        return output;
    }

    /**
     * https://leetcode.com/problems/next-greater-element-ii/
     * */
    public static int[] nextGreaterElementII(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        var stack = new Stack<Integer>();
        var nextGreater = new int[nums.length];
        Arrays.fill(nextGreater, -1);

        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                nextGreater[stack.pop()] = nums[i];
            }
            stack.push(i);
        }

        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                nextGreater[stack.pop()] = nums[i];
            }
        }

        return nextGreater;
    }

    /*
    * https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/
    * */
    public static int[] finalPrices(int[] prices) {
        if (prices == null || prices.length < 2)
            return prices;

        Stack<Integer> stack = new Stack<>();
        int[] finalPrices = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            int price = prices[i];
            while (!stack.isEmpty() && price <= prices[stack.peek()]) {
                int prevIndex = stack.pop();
                finalPrices[prevIndex] = prices[prevIndex] - price;
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int prevIndex = stack.pop();
            finalPrices[prevIndex] = prices[prevIndex];
        }

        return finalPrices;
    }
}