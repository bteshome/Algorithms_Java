package com.bteshome.algorithms.binarySearch_;

public class BinarySearchAlgorithms1 {
    /**
     * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days
     * */
    public static int shipWithinDays(int[] weights, int days) {
        if (weights == null || weights.length == 0 || days == 0) {
            return 0;
        }

        int maxWeight = weights[0];
        int sumOfWeights = weights[0];

        for (int i = 1; i < weights.length; i++) {
            maxWeight = Math.max(maxWeight, weights[i]);
            sumOfWeights += weights[i];
        }

        int minCapacityNeeded = maxWeight;
        int maxCapacityNeeded = sumOfWeights;

        if (ship(weights, days, 0, minCapacityNeeded)) {
            return minCapacityNeeded;
        }

        while (true) {
            if (minCapacityNeeded == maxCapacityNeeded) {
                return minCapacityNeeded;
            }

            int mid = (minCapacityNeeded + maxCapacityNeeded) / 2;

            if (ship(weights, days, 0, mid)) {
                maxCapacityNeeded = mid;
            } else {
                minCapacityNeeded = mid + 1;
            }
        }
    }

    private static boolean ship(int[] weights, int days, int start, int capacity) {
        if (start == weights.length) {
            return true;
        }

        if (days == 0) {
            return false;
        }

        int sum = 0;

        for (int i = start; i < weights.length; i++) {
            sum += weights[i];
            if (sum == capacity) {
                return ship(weights, days - 1, i + 1, capacity);
            }
            if (sum > capacity) {
                return ship(weights, days - 1, i, capacity);
            }
        }

        return ship(weights, days - 1, weights.length, capacity);
    }
}
