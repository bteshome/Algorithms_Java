package com.bteshome.algorithms.trees_;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeAlgorithms3 {
    /**
     * https://leetcode.com/problems/kth-smallest-element-in-a-bst/editorial/
     * NOTE - inorder traversal of a BST is literally an array sorted in ascending order.
     * */
    public static int kthSmallestInBST(TreeNode root, int k) {
        if (root == null || k < 1) {
            throw new IllegalArgumentException("input is invalid.");
        }

        var traversal = new ArrayList<Integer>();
        kthSmallestInBST(root, k, traversal);
        return traversal.get(k - 1);
    }

    private static void kthSmallestInBST(TreeNode root, int k, List<Integer> traversal) {
        if (root.left != null) {
            kthSmallestInBST(root.left, k, traversal);
        }

        traversal.add(root.val);

        if (traversal.size() == k) {
            return;
        }

        if (root.right != null) {
            kthSmallestInBST(root.right, k, traversal);
        }
    }

    public static int kthSmallestInBSTIterative(TreeNode root, int k) {
        if (root == null || k < 1) {
            throw new IllegalArgumentException("input is invalid.");
        }

        var stack = new Stack<TreeNode>();
        var current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            if (k == 1) {
                return current.val;
            }
            k--;
            current = current.right;
        }

        return root.val;
    }

    /**
     * https://leetcode.com/problems/inorder-successor-in-bst/
     * */
    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            throw new IllegalArgumentException("input is invalid.");
        }

        if (p.right != null) {
            var successor = p.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            return successor;
        } else {
            var traversal = new ArrayList<Integer>();
            return inorderSuccessor(root, p, traversal);
        }
    }

    private static TreeNode inorderSuccessor(TreeNode root, TreeNode p, ArrayList<Integer> traversal) {
        if (root.left != null) {
            var node = inorderSuccessor(root.left, p, traversal);
            if (node != null) {
                return node;
            }
        }

        if (!traversal.isEmpty() && traversal.getLast() == p.val) {
            return root;
        }

        traversal.add(root.val);

        if (root.right != null) {
            return inorderSuccessor(root.right, p, traversal);
        }

        return null;
    }
}