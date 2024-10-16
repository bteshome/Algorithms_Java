package com.bteshome.algorithms.greedy;

import java.util.Arrays;

public class GreedyAlgorithms1 {
    public static int minimumOperations(int[] nums) {
        if (nums == null) {
            return 0;
        }

        int operations = 0;

        while (true) {
            int min = Integer.MAX_VALUE;

            for (int num : nums) {
                if (num < 0) {
                    return 0;
                }
                if (num > 0 && num < min) {
                    min = num;
                }
            }

            if (min == Integer.MAX_VALUE) {
                break;
            }

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0) {
                    nums[i] = nums[i] - min;
                }
            }

            operations++;
        }

        return operations;
    }

    /**
     * https://leetcode.com/problems/can-place-flowers
     * */
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int available = 0;

        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                available++;
                flowerbed[i] = 1;
            }
            if (available >= n) {
                return true;
            }
        }

        return false;
    }

    /**
     * https://leetcode.com/problems/assign-cookies/
     * */
    public static int assignCookies(int[] g, int[] s) {
        if (g == null || s == null || g.length == 0 || s.length == 0) {
            return 0;
        }

        Arrays.sort(g);
        Arrays.sort(s);

        int contentChildren = 0;
        int cookieIndex = 0;

        while (cookieIndex < s.length && contentChildren < g.length) {
            if (s[cookieIndex] >= g[contentChildren]) {
                contentChildren++;
            }
            cookieIndex++;
        }

        return contentChildren;
    }

    public static int assignCookies_Approach2(int[] g, int[] s) {
        if (g == null || s == null || g.length == 0 || s.length == 0) {
            return 0;
        }

        Arrays.sort(g);
        Arrays.sort(s);

        int contentChildren = 0;
        int cookieIndex = s.length - 1;
        int childIndex = g.length - 1;

        while (cookieIndex >= 0 && childIndex >= 0) {
            if (s[cookieIndex] >= g[childIndex]) {
                contentChildren++;
                cookieIndex--;
            }
            childIndex--;
        }

        return contentChildren;
    }

    /**
     * https://leetcode.com/problems/largest-odd-number-in-string/
     * */
    public static String largestOddNumber(String num) {
        if (num == null || num.isEmpty()) {
            return "";
        }

        for (int i = num.length() - 1; i >= 0; i--) {
            if (Integer.parseInt(num.substring(i, i+1)) % 2 == 1) {
                return num.substring(0, i+1);
            }
        }

        return "";
    }
}
