package com.bteshome.algorithms.trees_;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TreeAlgorithms4 {
    static final String NULL = "null";

    // TODO - unfinished.
    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode front = q.poll();

            if (!builder.isEmpty()) {
                builder.append(',');
            }

            if (front == null) {
                builder.append(NULL);
            } else {
                builder.append(front.val);
                q.offer(front.left);
                q.offer(front.right);
            }
        }

        return builder.toString();
    }

    // Decodes your encoded data to tree.
    /*public TreeNode deserialize(String data) {
        data = data.substring(1, data.length() - 1);
        var tokens = data.split(",");
    }*/

    /* https://leetcode.com/problems/unique-binary-search-trees-ii */
    public static List<TreeNode> generateTrees(int n) {
        if (n < 1)
            return List.of();

        return generateTrees(1, n);
    }

    private static List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> trees = new ArrayList<>();

        if (start > end) {
            trees.add(null);
            return trees;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftSubTrees = generateTrees(start, i - 1);
            List<TreeNode> rightSubTrees = generateTrees(start, i - 1);

            for (TreeNode leftSubTree : leftSubTrees)
                for (TreeNode rightSubTree : rightSubTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftSubTree;
                    root.right = rightSubTree;
                    trees.add(root);
                }
        }

        return trees;
    }

    /* https://leetcode.com/problems/binary-tree-maximum-path-sum */
    public static class MaxPathSum {
        private int max = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            getMaxSum(root);
            return max;
        }

        private int getMaxSum(TreeNode root) {
            if (root == null)
                return 0;

            int leftGain = Math.max(getMaxSum(root.left), 0);
            int rightGain = Math.max(getMaxSum(root.right), 0);
            int gain = root.val + leftGain + rightGain;

            max = Math.max(max, gain);

            return root.val + Math.max(leftGain, rightGain);
        }
    }

    /**
     * https://leetcode.com/problems/all-possible-full-binary-trees
     * NOTE: this solution is accepted by LeetCode,
     *       but it can be optimized using DP
     * */
    public static List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> trees = new ArrayList<>();

        // Optimization - early pruning: an even n will never yield a valid tree
        if (n % 2 == 0)
            return trees;

        if (n == 1) {
            trees.add(new TreeNode(0));
            return trees;
        }

        for (int i = 1; i <= n - 2; i = i + 2) {
            List<TreeNode> leftTrees = allPossibleFBT(i);
            List<TreeNode> rightTrees = allPossibleFBT(n - i - 1);
            for (TreeNode leftTree : leftTrees)
                for (TreeNode rightTree : rightTrees) {
                    TreeNode root = new TreeNode(0);
                    root.left = leftTree;
                    root.right = rightTree;
                    trees.add(root);
                }
        }

        return trees;
    }
}
