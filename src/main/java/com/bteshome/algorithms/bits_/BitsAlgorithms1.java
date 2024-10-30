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

    /**
     * https://leetcode.com/problems/reverse-integer/
     * NOTE - there is another implementation that does not use decimal math,
     *        which is slower.
     * */
    public static int reverseInteger(int x) {
        int reverse = 0;

        while (x != 0) {
            int mod = (x % 10);

            if (reverse > Integer.MAX_VALUE / 10 || (reverse == Integer.MAX_VALUE / 10 && mod > 7)) {
                return 0;
            }

            if (reverse < Integer.MIN_VALUE / 10 || (reverse == Integer.MAX_VALUE / 10 && mod < -8)) {
                return 0;
            }

            reverse = reverse * 10 + mod;
            x /= 10;
        }

        return reverse;
    }

    /**
     * https://leetcode.com/problems/single-number/
     * */
    public int singleNumber(int[] nums) {
        int xor = 0;

        for (int num : nums) {
            xor = xor ^ num;
        }

        return xor;
    }
}
