package com.bteshome.algorithms.backtracking_;

import java.util.NavigableSet;
import java.util.TreeSet;

public class BacktrackingAlgorithms13 {
    /**
     * https://leetcode.com/problems/beautiful-arrangement
     * NOTE: there is also a DP solution for this
     * */
    public int countArrangement(int n) {
        int mask = (1 << n) - 1;
        return countArrangement(n, 0, mask);
    }

    private int countArrangement(int n, int pos, int mask) {
        if (pos == n)
            return 1;

        int count = 0;

        for (int i = 0; i < n; i++) {
            int num = i + 1;
            int numMask = 1 << i;
            if ((mask & numMask) != 0) {
                int posPlus1 = pos + 1;
                if ((num % posPlus1 == 0) || (posPlus1 % num == 0))
                    count += countArrangement(n, pos + 1, mask & ~numMask);
            }
        }

        return count;
    }

    /**
     * https://leetcode.com/problems/beautiful-arrangement-ii
     * NOTE: - this solution is correct, but slow and not accepted
     *       - there ia a way to solve it in linear time, which LeetCode expects
     *       - for every k, there is one answer, hence DP is not suitable
     *         (in fact, it makes it even slower)
     * */
    public static int[] constructArray(int n, int k) {
        if (n < 1 || k < 0)
            return new int[0];

        NavigableSet<Integer> diffs = new TreeSet<>();
        int[] candidate = new int[n];

        for (int i = 0; i < n; i++)
            candidate[i] = i + 1;

        int[] result = constructArray(n, k, 0, candidate, diffs);

        return result == null ? new int[0] : result;
    }

    private static int[] constructArray(int n, int k, int pos, int[] candidate, NavigableSet<Integer> diffs) {
        if (pos == n)
            return diffs.size() == k ? candidate : null;
        // pruning 1
        if (diffs.size() > k)
            return null;
        // pruning 2
        if (diffs.size() + n - pos < k)
            return null;

        for (int i = pos; i < n; i++) {
            swap(candidate, pos, i);

            if (pos == 0) {
                int[] result = constructArray(n, k, pos + 1, candidate, diffs);
                if (result != null)
                    return result;
            } else {
                int diff = Math.abs(candidate[pos] - candidate[pos - 1]);
                boolean newDiff = diffs.add(diff);
                int[] result = constructArray(n, k, pos + 1, candidate, diffs);
                if (result != null)
                    return result;
                if (newDiff)
                    diffs.remove(diff);
            }

            swap(candidate, pos, i);
        }

        return null;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
