package com.bteshome.algorithms.trees_;

public class TreeAlgorithms2 {
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

    
}