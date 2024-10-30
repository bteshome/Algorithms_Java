package com.bteshome.algorithms.arrays_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Stream;

public class ArrayAlgorithms1 {
    /**
     * leetcode https://leetcode.com/problems/two-sum/?envType=problem-list-v2&envId=array&difficulty=EASY
     * */
    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }

        HashMap<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            int other = target - current;
            if (seen.containsKey(other)) {
                return new int[]{seen.get(other), i};
            }
            seen.put(current, i);
        }

        return null;
    }

    /**
     * leetcode https://leetcode.com/problems/remove-duplicates-from-sorted-array/?envType=problem-list-v2&envId=array&difficulty=EASY
     * */
    public static int removeDuplicatesInPlace(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int lastUnique = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[lastUnique]) {
                nums[++lastUnique] = nums[i];
            }
        }

        return lastUnique + 1;
    }

    /**
     * leetcode https://leetcode.com/problems/remove-element/?envType=problem-list-v2&envId=array&difficulty=EASY
     * */
    public static int removeElementInPlace(int[] nums, int val) {
        if (nums == null)
            return 0;

        int lastOther = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[++lastOther] = nums[i];
            }
        }

        return lastOther + 1;
    }

    /**
     * leetcode https://leetcode.com/problems/search-insert-position/?envType=problem-list-v2&envId=array&difficulty=EASY
     * */
    public static int searchInsert(int[] nums, int target) {
        if (nums == null) {
            return 0;
        }

        return searchInsert(nums, target, 0, nums.length - 1);
    }

    private static int searchInsert(int[] nums, int target, int start, int end) {
        if (start > end) {
            return start;
        }

        int mid = (start + end) / 2;
        int midValue = nums[mid];

        if (target == midValue) {
            return mid;
        } else if (target < midValue) {
            return searchInsert(nums, target, start, mid - 1);
        } else {
            return searchInsert(nums, target, mid + 1, end);
        }
    }

    /**
     * leetcode https://leetcode.com/problems/plus-one/submissions/1404166546/?envType=problem-list-v2&envId=array&difficulty=EASY
     * */
    public static int[] plusOne(int[] digits) {
        if (digits == null) {
            return null;
        }

        for (int i = digits.length - 1; i >= 0 ; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
                if (i == 0) {
                    var digits2 = new int[digits.length + 1];
                    System.arraycopy(digits, 0, digits2, 1, digits2.length - 1);
                    digits2[0] = 1;
                    return digits2;
                }
            } else {
                digits[i] = digits[i] + 1;
                break;
            }
        }

        return digits;
    }
}
