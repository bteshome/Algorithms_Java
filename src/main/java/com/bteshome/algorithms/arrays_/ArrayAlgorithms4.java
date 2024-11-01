package com.bteshome.algorithms.arrays_;

import java.util.*;
import java.util.stream.IntStream;

public class ArrayAlgorithms4 {
    /**
     * https://leetcode.com/problems/move-zeroes/
     * */
    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        int nextNonZeroIndex = -1;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0) {
                for (int j = (nextNonZeroIndex == -1 ? i : nextNonZeroIndex) + 1; j < nums.length; j++) {
                    if (nums[j] != 0) {
                        nextNonZeroIndex = j;
                        break;
                    }
                }

                if (nextNonZeroIndex == -1) {
                    break;
                }

                nums[i] = nums[nextNonZeroIndex];
                nums[nextNonZeroIndex] = 0;
            }
        }
    }

    /**
     * https://leetcode.com/problems/reverse-integer/
     * NOTE - there is another implementation that uses decimal math,
     *        which is faster.
     * */
    public static int reverseInteger(int x) {
        if (x == 0) {
            return x;
        }

        boolean isNegative = x < 0;

        if (isNegative) {
            x = -x;
        }

        char[] digits = Integer.toString(x).toCharArray();

        int i = 0;
        int j = digits.length - 1;

        while (i <= j) {
            char temp = digits[i];
            digits[i] = digits[j];
            digits[j] = temp;
            i++;
            j--;
        }

        String s = null;

        if (!isNegative) {
            s = String.valueOf(digits);
        } else {
            var b = new StringBuilder(digits.length + 1);
            b.append('-');
            b.append(digits);
            s = b.toString();
        }

        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * https://leetcode.com/problems/increasing-triplet-subsequence/
     * */
    public static boolean increasingTriplet(int[] nums) {
        if (nums == null) {
            return false;
        }

        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= smallest) {
                smallest = num;
            } else if (num <= secondSmallest) {
                secondSmallest = num;
            } else {
                return true;
            }
        }

        return false;
    }
}