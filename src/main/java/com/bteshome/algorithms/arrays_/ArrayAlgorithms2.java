package com.bteshome.algorithms.arrays_;

import java.util.HashSet;

public class ArrayAlgorithms2 {
    /**
     * leetcode https://leetcode.com/problems/merge-sorted-array/submissions/1404216812/?envType=problem-list-v2&envId=array&difficulty=EASY
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        var merged = new int[nums1.length];
        for (int i = 0, j = 0, k = 0; k < nums1.length; k++) {
            if (i >= m) {
                merged[k] = nums2[j];
                j++;
            } else if (j >= n) {
                merged[k] = nums1[i];
                i++;
            } else {
                if (nums1[i] > nums2[j]) {
                    merged[k] = nums2[j];
                    j++;
                } else {
                    merged[k] = nums1[i];
                    i++;
                }
            }
        }

        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = merged[i];
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * leetcode https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/submissions/1404245260/?envType=problem-list-v2&envId=array&difficulty=EASY
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) {
            return null;
        }

        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private static TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        var mid = (start + end) / 2;
        var root = new TreeNode(nums[mid]);

        root.left = sortedArrayToBST(nums, start, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, end);

        return root;
    }

    /**
     * https://leetcode.com/problems/rotate-array/
     * NOTE: - this solution works.
     *         but the one that follows - based on cycles - is unfinished.
     * */
    public static void rotateUsingReversing(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }

        k = k % nums.length;

        if (k == 0) {
            return;
        }

        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private static void reverse(int[] a, int start, int end) {
        while (start <= end) {
            int temp = a[start];
            a[start] = a[end];
            a[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * TODO - this solution is unfinished.
     * */
    public static void rotateUsingCycles(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }

        k = k % nums.length;

        if (k == 0) {
            return;
        }

        var visited = new HashSet<Integer>();

        rotateUsingCycles(nums, k, 0, visited);

        /*if (nums.length % k == 0) {
            for (int i = 1; i < (n / k); i++) {
                rotateUsingCycles(nums, k, i, visited);
            }
        }*/
    }

    private static void rotateUsingCycles(int[] nums, int k, int start, HashSet<Integer> visited) {
        int i = start;
        int original = nums[start];
        while (!visited.contains(i)) {
            int next = (i + k) % nums.length;
            int temp = nums[next];
            nums[next] = original;
            original = temp;
            visited.add(i);
            i = next;
        }
    }
}
