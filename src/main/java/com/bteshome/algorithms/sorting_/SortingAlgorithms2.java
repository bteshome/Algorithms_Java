package com.bteshome.algorithms.sorting_;

public class SortingAlgorithms2 {
    /*
    * https://leetcode.com/problems/median-of-two-sorted-arrays/
    * NOTE - this is a simpler variant of the sort-merged-lists problem,
    *        in which there are more than two lists and the use of
    *        a min heap becomes more convenient.
    * */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null)
            throw new IllegalArgumentException("null.");

        int[] merged = new int[nums1.length + nums2.length];

        for (int i = 0, j = 0, k = 0; k < merged.length; k++) {
            if (i == nums1.length) {
                merged[k] = nums2[j];
                j++;
            } else if (j == nums2.length) {
                merged[k] = nums1[i];
                i++;
            } else if (nums1[i] <= nums2[j]) {
                merged[k] = nums1[i];
                i++;
            } else {
                merged[k] = nums2[j];
                j++;
            }
        };

        double median;

        if (merged.length % 2 == 1) {
            median = merged[merged.length / 2];
        } else {
            median = (merged[merged.length / 2 - 1] + merged[merged.length / 2]) / 2.0;
        }

        return median;
    }
}
