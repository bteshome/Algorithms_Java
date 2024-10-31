package com.bteshome.algorithms.binarySearch_;

import com.bteshome.algorithms.trees_.TreeNode;

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

    /**
     * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
     * */
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) {
            return null;
        }

        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private static TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBST(nums, left, mid - 1);
        node.right = sortedArrayToBST(nums, mid + 1, right);

        return node;
    }

    /**
     * https://leetcode.com/problems/first-bad-version/
     * NOTE - there seems to be a problem with the leetcode test running.
     * */
    public static int firstBadVersion(int n) {
        int low = 1;
        int high = n;

        while (low < high) {
            int mid = (low + high) / 2;
            if (isBadVersion(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }

        }

        return low;
    }

    private static boolean isBadVersion(int version) {
        return version == 1;
    }
}
