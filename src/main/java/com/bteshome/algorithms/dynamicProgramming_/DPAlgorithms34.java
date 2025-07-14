package com.bteshome.algorithms.dynamicProgramming_;

import java.util.*;

public class DPAlgorithms34 {
    private static final int MOD = 1000000007;

    /* https://leetcode.com/problems/make-array-strictly-increasing */
    public static int makeArrayIncreasingTopDown(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length < 2)
            return 0;
        if (arr2 == null || arr2.length == 0)
            return -1;

        arr2 = removeDuplicatesAndSort(arr2);

        return makeArrayIncreasingTopDown(arr1, arr2, 0, Integer.MIN_VALUE, new HashMap<>());
    }

    private static int makeArrayIncreasingTopDown(int[] arr1, int[] arr2, int pos1, int prevValue, Map<String, Integer> dp) {
        if (pos1 == arr1.length)
            return 0;

        String key = "%s,%s".formatted(pos1, prevValue);

        if (!dp.containsKey(key)) {
            int operations = Integer.MAX_VALUE;
            int value = arr1[pos1];

            if (value > prevValue) {
                int next = makeArrayIncreasingTopDown(arr1, arr2, pos1 + 1, value, dp);
                if (next != -1)
                    operations = next;
            }

            if (arr2[arr2.length - 1] > prevValue) {
                int smallestGreater = getSmallestGreater(prevValue, arr2);
                int next = makeArrayIncreasingTopDown(arr1, arr2, pos1 + 1, smallestGreater, dp);
                if (next != -1)
                    operations = Math.min(operations, 1 + next);
            }

            dp.put(key, operations == Integer.MAX_VALUE ? -1 : operations);
        }

        return dp.get(key);
    }

    private static int getSmallestGreater(int value, int[] arr) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= value) low = mid + 1;
            else high = mid;
        }
        return arr[low];
    }

    private static int[] removeDuplicatesAndSort(int[] array) {
        TreeSet<Integer> sorted = new TreeSet<Integer>();

        for (int value : array)
            sorted.add(value);

        array = new int[sorted.size()];
        int i = 0;

        for (int value : sorted) {
            array[i] = value;
            i++;
        }

        return array;
    }

    /* https://leetcode.com/problems/knight-dialer */
    public static int knightDialerTopDown(int n) {
        if (n < 1)
            return 0;
        if (n == 1)
            return 10;

        Map<Integer, List<Integer>> moves = new HashMap<>();
        moves.put(1, List.of(6, 8));
        moves.put(2, List.of(7, 9));
        moves.put(3, List.of(4, 8));
        moves.put(4, List.of(3, 9, 0));
        moves.put(6, List.of(1, 7, 0));
        moves.put(7, List.of(2, 6));
        moves.put(8, List.of(1, 3));
        moves.put(9, List.of(2, 4));
        moves.put(0, List.of(4, 6));

        Integer[][] dp = new Integer[10][n + 1];

        long nums = 0;
        for (int start : moves.keySet())
            nums = (nums + knightDialerTopDown(n, start, moves, dp)) % MOD;

        return (int)nums;
    }

    private static int knightDialerTopDown(int n, int digit, Map<Integer, List<Integer>> moves, Integer[][] dp) {
        if (n == 1)
            return 1;

        if (dp[digit][n] == null) {
            long nums = 0;
            for (int next : moves.get(digit))
                nums = (nums + knightDialerTopDown(n - 1, next, moves, dp)) % MOD;
            dp[digit][n] = (int)nums;
        }

        return dp[digit][n];
    }
}