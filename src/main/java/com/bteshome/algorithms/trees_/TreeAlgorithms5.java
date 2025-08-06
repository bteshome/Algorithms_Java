package com.bteshome.algorithms.trees_;

public class TreeAlgorithms5 {
    /* https://leetcode.com/problems/diameter-of-binary-tree */
    public static class DiameterOfBinaryTree {
        private int max = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            getMaxDepth(root);
            return max;
        }

        public int getMaxDepth(TreeNode root) {
            if (root == null)
                return 0;

            int leftMaxDepth = getMaxDepth(root.left);
            int rightMaxDepth = getMaxDepth(root.right);

            int passThroughMaxLength = leftMaxDepth + rightMaxDepth;
            max = Math.max(max, passThroughMaxLength);

            int maxDepth = Math.max(leftMaxDepth, rightMaxDepth);
            return 1 + maxDepth;
        }
    }

    /* https://leetcode.com/problems/binary-tree-maximum-path-sum */
    public static class BinaryTreeMaxSum {
        private int max = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            getMaxSum(root);
            return max;
        }

        public int getMaxSum(TreeNode root) {
            if (root == null)
                return 0;

            int leftMaxSum = getMaxSum(root.left);
            int rightMaxSum = getMaxSum(root.right);
            leftMaxSum = Math.max(0, leftMaxSum);
            rightMaxSum = Math.max(0, rightMaxSum);

            int passThroughMaxSum = root.val + leftMaxSum + rightMaxSum;
            max = Math.max(max, passThroughMaxSum);

            int maxSum = root.val + Math.max(leftMaxSum, rightMaxSum);
            return maxSum;
        }
    }

    /* https://leetcode.com/problems/distribute-coins-in-binary-tree */
    public static class DistributeCoinsInBinaryTree {
        private int operations = 0;

        public int distributeCoins(TreeNode root) {
            if (root == null)
                return 0;
            getOperations(root);
            return operations;
        }

        private int getOperations(TreeNode root) {
            if (root == null)
                return 0;

            int left = getOperations(root.left);
            int right = getOperations(root.right);

            int currentOperations = Math.abs(left) + Math.abs(right);
            operations += currentOperations;

            return root.val - 1 + left + right;
        }
    }

    /* https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree */
    public static class LowestCommonAncestor {
        private TreeNode lca;

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            helper(root, p, q);
            return lca;
        }

        private int helper(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null)
                return 0;

            int left = helper(root.left, p, q);

            if (lca != null)
                return 0;

            int right = helper(root.right, p, q);

            if (lca != null)
                return 0;

            int result = left | right;

            if (root == p)
                result |= 1;
            else if (root == q)
                result |= 2;

            if (result == 3)
                lca = root;

            return result;
        }
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;

        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);

        if (left != null && right != null)
            return root;
        else if (left != null)
            return left;
        else
            return right;
    }
}