package com.bteshome.algorithms.twoPointers_;

public class TwoPointerAlgorithms3 {
    /*
    * https://leetcode.com/problems/container-with-most-water/
    * */
    public static int maxArea(int[] height) {
        if (height == null || height.length < 2)
            return 0;

        int left = 0;
        int right = height.length - 1;
        int maxAreaSoFar = 0;

        while (left < right) {
            int leftHeight = height[left];
            int rightHeight = height[right];
            int area = (right - left) * Math.min(leftHeight, rightHeight);
            maxAreaSoFar = Math.max(maxAreaSoFar, area);
            if (leftHeight <= rightHeight)
                left++;
            else
                right--;
        }

        return maxAreaSoFar;
    }
}
