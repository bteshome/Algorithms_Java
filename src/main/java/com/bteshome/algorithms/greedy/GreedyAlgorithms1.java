package com.bteshome.algorithms.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class GreedyAlgorithms1 {
    /**
     * https://leetcode.com/problems/make-array-zero-by-subtracting-equal-amounts/
     * */
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

    // NOTE - the first solution is faster
    public static int minimumOperations_UsingPriorityQueue(int[] nums) {
        if (nums == null)
            return 0;

        PriorityQueue<Integer> q1 = new PriorityQueue<>();
        PriorityQueue<Integer> q2 = new PriorityQueue<>();

        int operations = 0;

        for (int num : nums) {
            if (num > 0)
                q1.offer(num);
        }

        while (!q1.isEmpty()) {
            int decrement = q1.peek();
            while (!q1.isEmpty()) {
                int next = q1.poll();
                next -= decrement;
                if (next > 0)
                    q2.offer(next);
            }
            PriorityQueue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
            operations++;
        }

        return operations;
    }

    /**
     * https://leetcode.com/problems/can-place-flowers
     * */
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed == null || flowerbed.length < 1 || n < 0)
            return false;

        for (int i = 0; i < flowerbed.length; i++) {
            if (n == 0)
                break;

            if (flowerbed[i] == 1)
                continue;

            if (i > 0 && flowerbed[i - 1] == 1)
                continue;

            if (i < flowerbed.length - 1 && flowerbed[i + 1] == 1)
                continue;

            flowerbed[i] = 1;
            n--;
        }

        return n == 0;
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
