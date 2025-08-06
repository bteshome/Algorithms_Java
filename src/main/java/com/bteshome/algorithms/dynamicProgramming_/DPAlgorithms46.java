package com.bteshome.algorithms.dynamicProgramming_;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DPAlgorithms46 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * https://leetcode.com/problems/maximum-product-of-splitted-binary-tree
     * NOTE: the DP part here is only the memoization of the sums
     * */
    public static class MaxTreeProduct {
        private static final long MOD = 1000000007;
        private long max = 0;

        public int maxProduct(TreeNode root) {
            if (root == null)
                return 0;

            Map<TreeNode, Integer> sums = new HashMap<>();
            int totalSum = getSum(root, sums);
            maxProduct(root, sums, totalSum);

            return (int) (max * MOD);
        }

        public void maxProduct(TreeNode root, Map<TreeNode, Integer> sums, int totalSum) {
            if (root.left != null) {
                long leftSum = sums.get(root.left);
                long rightSum = totalSum - leftSum;
                max = Math.max(max, leftSum * rightSum);
                maxProduct(root.left, sums, totalSum);
            }
            if (root.right != null) {
                long rightSum = sums.get(root.right);
                long leftSum = totalSum - rightSum;
                max = Math.max(max, leftSum * rightSum);
                maxProduct(root.right, sums, totalSum);
            }
        }

        private int getSum(TreeNode node, Map<TreeNode, Integer> sums) {
            if (node == null)
                return 0;
            int sum = getSum(node.left, sums) + getSum(node.right, sums) + node.val;
            sums.put(node, sum);
            return sum;
        }
    }

    /**
     * https://leetcode.com/problems/binary-tree-cameras
     * This solution is accepted, but can be improved.
     * */
    public static int minCameraCover(TreeNode root) {
        // nodeMonitorType state definition: 0: not monitored, 1: monitored by a child, 2: monitored by self
        Map<TreeNode, Integer[]> dp =  new HashMap<>();
        int rootMonitoredByChild = minCameraCover(root, 1, dp);
        int rootMonitoredBySelf = minCameraCover(root, 2, dp);
        return Math.min(rootMonitoredByChild, rootMonitoredBySelf);
    }

    private static int minCameraCover(TreeNode root, int monitorType, Map<TreeNode, Integer[]> dp) {
        if (root == null)
            return 0;

        if (!dp.containsKey(root))
            dp.put(root, new Integer[3]);

        if (dp.get(root)[monitorType] != null)
            return dp.get(root)[monitorType];

        int min = Integer.MAX_VALUE;

        if (monitorType == 0) {
            if (root.left != null && root.right != null) {
                int leftMonitoredByChild = minCameraCover(root.left, 1, dp);
                int rightMonitoredByChild = minCameraCover(root.right, 1, dp);
                if (leftMonitoredByChild != Integer.MAX_VALUE && rightMonitoredByChild != Integer.MAX_VALUE)
                    min = leftMonitoredByChild + rightMonitoredByChild;
            } else if (root.left != null)
                min = minCameraCover(root.left, 1, dp);
            else if (root.right != null)
                min = minCameraCover(root.right, 1, dp);
            else
                min = 0;
        } else if (monitorType == 1) {
            if (root.left != null && root.right != null) {
                int leftMonitoredBySelf = minCameraCover(root.left, 2, dp);
                int leftMonitoredByChild = minCameraCover(root.left, 1, dp);
                int rightMonitoredBySelf = minCameraCover(root.right, 2, dp);
                int rightMonitoredByChild = minCameraCover(root.right, 1, dp);

                if (leftMonitoredBySelf != Integer.MAX_VALUE && rightMonitoredBySelf != Integer.MAX_VALUE)
                    min = leftMonitoredBySelf + rightMonitoredBySelf;
                if (leftMonitoredBySelf != Integer.MAX_VALUE && rightMonitoredByChild != Integer.MAX_VALUE)
                    min = Math.min(min, leftMonitoredBySelf + rightMonitoredByChild);
                if (leftMonitoredByChild != Integer.MAX_VALUE && rightMonitoredBySelf != Integer.MAX_VALUE)
                    min = Math.min(min, leftMonitoredByChild + rightMonitoredBySelf);
            } else if (root.left != null)
                min = minCameraCover(root.left, 2, dp);
            else if (root.right != null)
                min = minCameraCover(root.right, 2, dp);
        } else {
            int leftNotMonitored = minCameraCover(root.left, 0, dp);
            int leftMonitoredBySelf = minCameraCover(root.left, 2, dp);
            int leftMonitoredByChild = minCameraCover(root.left, 1, dp);
            int rightNotMonitored = minCameraCover(root.right, 0, dp);
            int rightMonitoredBySelf = minCameraCover(root.right, 2, dp);
            int rightMonitoredByChild = minCameraCover(root.right, 1, dp);

            if (leftNotMonitored != Integer.MAX_VALUE) {
                if (rightNotMonitored != Integer.MAX_VALUE)
                    min = leftNotMonitored + rightNotMonitored;
                if (rightMonitoredByChild != Integer.MAX_VALUE)
                    min = Math.min(min, leftNotMonitored + rightMonitoredByChild);
                if (rightMonitoredBySelf != Integer.MAX_VALUE)
                    min = Math.min(min, leftNotMonitored + rightMonitoredBySelf);
            }

            if (leftMonitoredBySelf != Integer.MAX_VALUE) {
                if (rightNotMonitored != Integer.MAX_VALUE)
                    min = Math.min(min, leftMonitoredBySelf + rightNotMonitored);
                if (rightMonitoredByChild != Integer.MAX_VALUE)
                    min = Math.min(min, leftMonitoredBySelf + rightMonitoredByChild);
                if (rightMonitoredBySelf != Integer.MAX_VALUE)
                    min = Math.min(min, leftMonitoredBySelf + rightMonitoredBySelf);
            }

            if (leftMonitoredByChild != Integer.MAX_VALUE) {
                if (rightNotMonitored != Integer.MAX_VALUE)
                    min = Math.min(min, leftMonitoredByChild + rightNotMonitored);
                if (rightMonitoredByChild != Integer.MAX_VALUE)
                    min = Math.min(min, leftMonitoredByChild + rightMonitoredByChild);
                if (rightMonitoredBySelf != Integer.MAX_VALUE)
                    min = Math.min(min, leftMonitoredByChild + rightMonitoredBySelf);
            }

            if (min != Integer.MAX_VALUE)
                min = min + 1;
        }

        dp.get(root)[monitorType] = min;

        return dp.get(root)[monitorType];
    }
}