package com.bteshome.algorithms.bits_;

public class BitsAlgorithms1 {
    /**
     * leetcode https://leetcode.com/problems/counting-bits/submissions/1405080874/?envType=problem-list-v2&envId=dynamic-programming&difficulty=EASY
     * */
    public static int[] countBits(int n) {
        int msbPosition = Integer.toBinaryString(n).length();
        int[] counts = new int[n+1];

        for (int i = 0; i <= n; i++) {
            counts[i] = countBits(i, msbPosition);
        }

        return counts;
    }

    private static int countBits(int n, int msbPosition) {
        int count = 0;

        for (int i = 0; i < msbPosition; i++) {
            int mask = 1 << i;
            if ((n & mask) != 0) {
                count++;
            }
        }
        return count;
    }
}
