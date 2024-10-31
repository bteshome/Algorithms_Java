package com.bteshome.algorithms.binarySearch_;

public class BinarySearchAlgorithms2 {
    /**
     * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
     * NOTE - this binary search based solution is relatively inefficient.
     *        For a faster solution, take a look at the one in TwoPointers.
     * */
    public static int[] twoSumII(int[] numbers, int target) {
        if (numbers == null) {
            return null;
        }

        for (int i = 0; i < numbers.length; i++) {
            int current = numbers[i];
            int other = target - current;
            if (other < current) {
                return null;
            }

            int low = i + 1;
            int high = numbers.length - 1;

            while (low <= high) {
                int mid = (low + high) / 2;
                if (numbers[mid] == other) {
                    return new int[]{i+1, mid+1};
                } else if (other < numbers[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }

        return null;
    }
}
