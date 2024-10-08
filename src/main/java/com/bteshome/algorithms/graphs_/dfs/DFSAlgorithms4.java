package com.bteshome.algorithms.graphs_.dfs;

import java.util.ArrayList;
import java.util.List;

public class DFSAlgorithms4 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode() {}
        public TreeNode(int val) { this.val = val; }
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        var path = new ArrayList<Integer>();
        inorderTraversal(root, path);
        return path;
    }

    private static void inorderTraversal(TreeNode root, List<Integer> path) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, path);
        path.add(root.val);
        inorderTraversal(root.right, path);
    }


}

