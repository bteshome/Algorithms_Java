package com.bteshome.algorithms.arrays_;

import java.util.*;

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

    public static int[] productExceptSelf2(int[] nums) {
        if (nums == null || nums.length == 0)
            return new int[0];

        int product = 1;
        int numZeros = 0;
        int[] products = new int[nums.length];

        for (int num : nums) {
            if (num == 0)
                numZeros++;
            else
                product *= num;
        }

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (numZeros > 1)
                products[i] = 0;
            else if (numZeros == 1)
                products[i] = num == 0 ? product : 0;
            else
                products[i] = product / num;
        }

        return products;
    }

    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        System.out.println(threeSum(nums1, nums2, nums3, 0));

        return 0;
    }

    private static List<List<Integer>> threeSum(int[] nums1, int[] nums2, int[] nums3, int target) {
        var triplets = new ArrayList<List<Integer>>();

        for (int i = 0; i < nums1.length; i++) {
            int _i = i;
            int num = nums1[i];
            final int other = target - num;
            List<List<Integer>> twoSumList = twoSum(nums2, nums3, other);
            triplets.addAll(twoSumList.stream().map(l -> List.of(_i, l.get(0), l.get(1))).toList());
        }

        return triplets;
    }

    private static List<List<Integer>> twoSum(int[] nums1, int[] nums2, int target) {
        var seen = new HashMap<Integer, List<Integer>>();
        var pairs = new ArrayList<List<Integer>>();

        for (int i = 0; i < nums1.length; i++) {
            int num = nums1[i];
            if (!seen.containsKey(num)) {
                seen.put(num, new ArrayList<>());
            }
            seen.get(num).add(i);
        }

        for (int i = 0; i < nums2.length; i++) {
            int num = nums2[i];
            int other = target - num;
            if (seen.containsKey(other)) {
                final int _i = i;
                pairs.addAll(seen.get(other).stream().map(e -> List.of(e, _i)).toList());
            }
        }

        return pairs;
    }

    /**
     * https://leetcode.com/problems/special-array-with-x-elements-greater-than-or-equal-x/
     * */
    public static int specialArray(int[] nums) {
        if (nums == null) {
            return 0;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int count = nums.length - i;
            int val = nums[i];
            if (val >= count && (i == 0 || nums[i-1] < count)) {
                return count;
            }
        }

        return -1;
    }
}