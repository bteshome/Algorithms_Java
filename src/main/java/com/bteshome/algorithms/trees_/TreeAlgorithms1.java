package com.bteshome.algorithms.trees_;

import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;

public class TreeAlgorithms1 {
    /**
     * https://leetcode.com/problems/validate-binary-search-tree/
     */
    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private static boolean isValidBST(TreeNode root, Integer lowerBound, Integer upperBound) {
        if (root == null) {
            return true;
        }
        if (lowerBound != null && root.val <= lowerBound) {
            return false;
        }
        if (upperBound != null && root.val >= upperBound) {
            return false;
        }
        return isValidBST(root.left, lowerBound, root.val) && isValidBST(root.right, root.val, upperBound);
    }

    /**
     * https://leetcode.com/problems/symmetric-tree/
     */
    public static boolean isSymmetric(TreeNode root) {
        return root == null || isSymmetric(root.left, root.right);
    }

    private static boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null) {
            return right == null;
        } else if (right == null) {
            return false;
        } else if (right.val != left.val) {
            return false;
        } else {
            return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
        }
    }

    /**
     * https://leetcode.com/problems/binary-tree-level-order-traversal/
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        var traversal = new ArrayList<List<Integer>>();

        if (root == null) {
            return traversal;
        }

        record Entry(int level, TreeNode node) {
        }

        Queue<Entry> q = new ArrayDeque<>();
        q.offer(new Entry(0, root));

        while (!q.isEmpty()) {
            Entry front = q.remove();
            int parentLevel = front.level();
            int childLevel = parentLevel + 1;

            if (traversal.size() == front.level()) {
                traversal.add(new ArrayList<>());
            }
            traversal.get(parentLevel).add(front.node.val);

            if (front.node.left != null) {
                q.offer(new Entry(childLevel, front.node.left));
            }

            if (front.node.right != null) {
                q.offer(new Entry(childLevel, front.node.right));
            }
        }

        return traversal;
    }


}