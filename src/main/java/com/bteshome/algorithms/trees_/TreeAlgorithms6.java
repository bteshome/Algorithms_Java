package com.bteshome.algorithms.trees_;

public class TreeAlgorithms6 {
    /**
     * https://leetcode.com/problems/binary-tree-coloring-game
     * NOTE:
     *       - one key observation suggests a simple mathematical solution
     *         with linear time
     *       - also check out the DP + backtracking solutions that are correct
     *         but inefficient (for comparison)
     * */
    public static class BtreeGameWinningMoveMathematical {
        private static final int LEFT = 0;
        private static final int RIGHT = 1;
        private static final int PARENT = 2;

        public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
            if (root == null || x < 1 || x > n || n % 2 == 0)
                return false;

            int[][] tree = new int[n + 1][3];
            int[] counts = new int[n + 1];
            buildTree(root, tree);
            buildCounts(root, n, counts);

            int leftCount = counts[tree[x][LEFT]];
            int rightCount = counts[tree[x][RIGHT]];
            int parentCount = n - leftCount - rightCount - 1;
            int majority = (n / 2) + 1;

            return  leftCount >= majority ||
                    rightCount >= majority ||
                    parentCount >= majority;
        }

        private int buildCounts(TreeNode root, int n, int[] counts) {
            if (root == null)
                return 0;
            int leftCount = buildCounts(root.left, n, counts);
            int rightCount = buildCounts(root.right, n, counts);
            int count = leftCount + rightCount + 1;
            counts[root.val] = count;
            return count;
        }

        private void buildTree(TreeNode root, int[][] tree) {
            int val = root.val;
            if (root.left != null) {
                int left = root.left.val;
                tree[val][LEFT] = left;
                tree[left][PARENT] = val;
                buildTree(root.left, tree);
            }
            if (root.right != null) {
                int right = root.right.val;
                tree[val][RIGHT] = right;
                tree[right][PARENT] = val;
                buildTree(root.right, tree);
            }
        }
    }

    /* https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree */
    public static class LongestZigZag {
        private int max = 0;
        private final int LEFT = 0;
        private final int RIGHT = 1;

        public int longestZigZag(TreeNode root) {
            longestZigZag(root, LEFT);
            return max;
        }

        private int longestZigZag(TreeNode root, int direction) {
            if (root == null)
                return 0;

            int left = longestZigZag(root.left, RIGHT);
            int right = longestZigZag(root.right, LEFT);

            int maxRootedHere = Math.max(left, right);
            max = Math.max(max, maxRootedHere);

            return 1 + (direction == LEFT ? left : right);
        }
    }

    /* https://leetcode.com/problems/longest-univalue-path */
    public static class LongestUnivaluePath {
        private int max = 0;

        public int longestUnivaluePath(TreeNode root) {
            if (root == null)
                return 0;
            dfs(root);
            return max;
        }

        private int dfs(TreeNode root) {
            int pathGoingUp = 0;
            int pathRootedHere = 0;

            if (root.left != null) {
                int left = dfs(root.left);
                if (root.left.val == root.val) {
                    pathRootedHere += left;
                    pathGoingUp = left;
                }
            }

            if (root.right != null) {
                int right = dfs(root.right);
                if (root.right.val == root.val) {
                    pathRootedHere += right;
                    pathGoingUp = Math.max(pathGoingUp, right);
                }
            }

            max = Math.max(max, pathRootedHere);

            return pathGoingUp + 1;
        }
    }
}