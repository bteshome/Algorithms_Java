package com.bteshome.algorithms.trees_;

import java.util.*;

public class TreeAlgorithms2 {
    /**
     * https://leetcode.com/problems/binary-tree-inorder-traversal/
     * NOTE - this is just an iterative implementation of inorder traversal.
     * */
    public static List<Integer> inorderTraversalIterative(TreeNode root) {
        var path = new ArrayList<Integer>();
        var stack = new Stack<TreeNode>();
        var current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            path.add(current.val);

            current = current.right;
        }

        return path;
    }

    /**
     * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
     * */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) {
            return null;
        }

        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private static TreeNode buildTree(int[] preorder, int[] inorder, int preorderStart, int preorderEnd, int inorderStart, int inorderEnd) {
        int inorderRootIndex = -1;

        for (int i = inorderStart; i <= inorderEnd; i++) {
            if (inorder[i] == preorder[preorderStart]) {
                inorderRootIndex = i;
                break;
            }
        }

        int sizeOfLeft = inorderRootIndex - inorderStart;
        int sizeOfRight = inorderEnd - inorderRootIndex;
        TreeNode root = new TreeNode(preorder[preorderStart]);
        root.left = sizeOfLeft == 0 ? null : buildTree(preorder, inorder, preorderStart + 1,  preorderStart + sizeOfLeft, inorderStart, inorderRootIndex - 1);
        root.right = sizeOfRight == 0 ? null : buildTree(preorder, inorder, preorderStart + 1 + sizeOfLeft,  preorderEnd, inorderRootIndex + 1, inorderEnd);
        return root;
    }

    /**
     * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
     * */
    public static TreeNode populateNextRightPointers(TreeNode root) {
        if (root == null) {
            return null;
        }

        Map<TreeNode, Integer> levels = new HashMap<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        Map<Integer, Deque<TreeNode>> traversal = new HashMap<>();
        q.offer(root);
        levels.put(root, 0);

        while (!q.isEmpty()) {
            var front = q.poll();
            int frontLevel = levels.get(front);
            if (!traversal.containsKey(frontLevel)) {
                traversal.put(frontLevel, new ArrayDeque<>());
            }
            var frontLevelTraversal = traversal.get(frontLevel);
            if (!frontLevelTraversal.isEmpty()) {
                frontLevelTraversal.peekLast().next = front;
            }
            frontLevelTraversal.offer(front);

            if (front.left != null) {
                q.offer(front.left);
                levels.put(front.left, frontLevel + 1);
            }
            if (front.right != null) {
                q.offer(front.right);
                levels.put(front.right, frontLevel + 1);
            }
        }

        return root;
    }
}