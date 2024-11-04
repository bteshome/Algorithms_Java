package com.bteshome.algorithms.binarySearch_;

import java.util.Arrays;

public class BinarySearchAlgorithms3 {
    /**
     * https://leetcode.com/problems/sqrtx/
     * */
    public static int mySqrt(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("x cannot be negative.");
        }

        if (x < 2) {
            return x;
        }

        int low = 1;
        int high = x / 2;

        while (low <= high) {
            int mid = (low + high) / 2;
            long power = (long)mid * mid;
            if (power == x) {
                return mid;
            } else if (power > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return high;
    }
}