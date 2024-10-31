package com.bteshome.algorithms.math_;

public class MathAlgorithms2 {
    /**
     * https://leetcode.com/problems/single-number/
     * */
    public static int singleNumber(int[] nums) {
        int xor = 0;

        for (int num : nums) {
            xor = xor ^ num;
        }

        return xor;
    }

    public static boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }

        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }

    /**
     * https://leetcode.com/problems/number-of-1-bits/
     * */
    public static int hammingWeight(int n) {
        int ones = 0;

        while (n > 0) {
            if ((n & 1) != 0) {
                ones++;
            }
            n = n >> 1;
        }

        return ones;
    }

    /**
     * https://leetcode.com/problems/hamming-distance/
     * */
    public static int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int distance = 0;

        while (xor != 0) {
            if ((xor & 1) != 0) {
                distance++;
            }
            xor = xor >> 1;
        }

        return distance;
    }

    /**
     * https://leetcode.com/problems/reverse-bits/
     * */
    public static int reverseBits(int n) {
        int reverse = 0;

        for (int i = 0; i < 32; i++) {
            reverse = reverse << 1;
            reverse += (n & 1);
            n = n >> 1;
        }

        return reverse;
    }


}
