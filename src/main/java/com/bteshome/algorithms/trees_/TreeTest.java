package com.bteshome.algorithms.trees_;

public class TreeTest {
    public static void test() {
        var root = new TreeNode(1);
        var two = new TreeNode(2, new TreeNode(3), new TreeNode(4));
        var two2 = new TreeNode(2, new TreeNode(4), new TreeNode(3));
        root.left = two;
        root.right = two2;
        System.out.println(TreeAlgorithms1.isSymmetric(root));

    }
}
