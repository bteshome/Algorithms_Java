package com.bteshome.algorithms.backtracking_;

import java.util.ArrayList;
import java.util.List;

public class BacktrackingAlgorithms5 {
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

    /**
     * https://leetcode.com/problems/path-sum-ii/description/?envType=problem-list-v2&envId=backtracking&difficulty=MEDIUM
     * */
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        var paths = new ArrayList<List<Integer>>();

        if (root != null) {
            pathSum(root, targetSum, paths, new ArrayList<Integer>());
        }

        return paths;
    }

    private static void pathSum(TreeNode root, int targetSum, List<List<Integer>> paths, List<Integer> path) {
        path.addLast(root.val);
        targetSum -= root.val;

        if (root.left == null && root.right == null) {
            if (targetSum == 0) {
                paths.add(path.stream().toList());
            }
        }

        if (root.left != null) {
            pathSum(root.left, targetSum, paths, path);
        }

        if (root.right != null) {
            pathSum(root.right, targetSum, paths, path);
        }

        path.removeLast();
    }

    /**
     * https://leetcode.com/problems/combination-sum-iii/?envType=problem-list-v2&envId=backtracking&difficulty=MEDIUM
     * */
    public static List<List<Integer>> combinationSum3(int k, int n) {
        var combinations = new ArrayList<List<Integer>>();

        if (k > 0 && n > 0) {
            combinationSum3(k, n, 1, new ArrayList<Integer>(k), combinations);
        }

        return combinations;
    }

    private static void combinationSum3(int k, int n, int digit, List<Integer> combination, List<List<Integer>> combinations) {
        if (k == 0 && n == 0) {
            combinations.add(combination.stream().toList());
            return;
        }

        if (k == 0 || n <= 0) {
            return;
        }

        if (digit > 9) {
            return;
        }

        combination.addLast(digit);
        combinationSum3(k - 1, n - digit, digit + 1, combination, combinations);
        combination.removeLast();
        combinationSum3(k, n, digit + 1, combination, combinations);
    }
}
