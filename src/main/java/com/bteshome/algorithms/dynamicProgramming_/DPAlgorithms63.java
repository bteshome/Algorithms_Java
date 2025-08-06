package com.bteshome.algorithms.dynamicProgramming_;

import java.util.*;

public class DPAlgorithms63 {
    /**
     * https://leetcode.com/problems/binary-tree-coloring-game
     * NOTE:
     *       - both DP + backtracking solutions are correct
     *         but inefficient (not accepted)
     *       - also checkout the simple mathematical solution
     *         with linear time (for comparison)
     * */
    public static class BtreeGameWinningMoveUsingSetAsKey {
        private static final int LEFT = 0;
        private static final int RIGHT = 1;
        private static final int PARENT = 2;
        private static final int RED = 0;
        private static final int BLUE = 1;

        public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
            if (root == null || x < 1 || x > n || n % 2 == 0)
                return false;

            int[][] tree = new int[n + 1][3];
            buildTree(root, tree);

            // prunning
            int[] neighborsOfX = {tree[x][LEFT], tree[x][RIGHT], tree[x][PARENT]};
            for (int y : neighborsOfX) {
                if (y != 0) {
                    NavigableSet<Integer> reds = new TreeSet<>();
                    NavigableSet<Integer> blues = new TreeSet<>();
                    reds.add(x);
                    blues.add(y);
                    if (!move(tree, n, reds, blues, RED, new HashMap<>()))
                        return true;
                }
            }

            return false;
        }

        private boolean move(int[][] tree, int n, NavigableSet<Integer> reds, NavigableSet<Integer> blues, int player, Map<String, Boolean> dp) {
            if (reds.size() + blues.size() == n) {
                boolean blueWins = blues.size() > reds.size();
                return player == BLUE ? blueWins : !blueWins;
            }
            // pruning
            if (reds.size() > n / 2)
                return player == RED;
            if (blues.size() > n / 2)
                return player == BLUE;
            int remaining = n - reds.size() - blues.size();
            if (reds.size() + remaining <= n / 2)
                return player == BLUE;
            if (blues.size() + remaining <= n / 2)
                return player == RED;

            String key = "%s,%s,%s".formatted(reds, blues, player);

            if (dp.containsKey(key))
                return dp.get(key);

            Set<Integer> myNextNodes = getNextNodes(tree, reds, blues, player);
            Set<Integer> opponentNextNodes = getNextNodes(tree, reds, blues, 1 - player);
            Set<Integer> blockingMoves = new HashSet<>();
            Set<Integer> otherMoves = new HashSet<>();
            Set<Integer> set = player == RED ? reds : blues;

            for (int node : myNextNodes) {
                if (opponentNextNodes.contains(node))
                    blockingMoves.add(node);
                else
                    otherMoves.add(node);
            }

            for (int nextNode : blockingMoves) {
                set.add(nextNode);
                boolean opponentLoses = !move(tree, n, reds, blues, 1 - player, dp);
                set.remove(nextNode);
                if (opponentLoses) {
                    dp.put(key, true);
                    return true;
                }
            }

            for (int nextNode : otherMoves) {
                set.add(nextNode);
                boolean opponentLoses = !move(tree, n, reds, blues, 1 - player, dp);
                set.remove(nextNode);
                if (opponentLoses) {
                    dp.put(key, true);
                    return true;
                }
            }

            dp.put(key, false);
            return false;
        }

        private Set<Integer> getNextNodes(int[][] tree, Set<Integer> reds, Set<Integer> blues, int color) {
            Set<Integer> candidates = new HashSet<>();

            for (int nextNode : color == RED ? reds : blues) {
                int[] neighbors = {tree[nextNode][LEFT], tree[nextNode][RIGHT], tree[nextNode][PARENT]};
                for (int neighbor : neighbors)
                    if (neighbor > 0 && !reds.contains(neighbor) && !blues.contains(neighbor))
                        candidates.add(neighbor);
            }

            return candidates;
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

        public static class TreeNode {
            public int val;
            public TreeNode left;
            public TreeNode right;
            public TreeNode next;

            public TreeNode() {
            }

            public TreeNode(int val) {
                this.val = val;
            }

            public TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }
    }

    public static class BtreeGameWinningMoveUsingCustomKey {
        private static final int LEFT = 0;
        private static final int RIGHT = 1;
        private static final int PARENT = 2;
        private static final int RED = 0;
        private static final int BLUE = 1;

        public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
            if (root == null || x < 1 || x > n || n % 2 == 0)
                return false;

            int[][] tree = new int[n + 1][3];
            buildTree(root, tree);

            // prunning
            int[] neighborsOfX = {tree[x][LEFT], tree[x][RIGHT], tree[x][PARENT]};
            for (int y : neighborsOfX) {
                if (y != 0) {
                    Set<Integer> reds = new HashSet<>();
                    Set<Integer> blues = new HashSet<>();
                    reds.add(x);
                    blues.add(y);
                    if (!move(tree, n, reds, blues, RED, new HashMap<>()))
                        return true;
                }
            }

            return false;
        }

        private boolean move(int[][] tree, int n, Set<Integer> reds, Set<Integer> blues, int player, Map<GameState, Boolean> dp) {
            if (reds.size() + blues.size() == n) {
                boolean blueWins = blues.size() > reds.size();
                return player == BLUE ? blueWins : !blueWins;
            }
            // pruning
            if (reds.size() > n / 2)
                return player == RED;
            if (blues.size() > n / 2)
                return player == BLUE;
            int remaining = n - reds.size() - blues.size();
            if (reds.size() + remaining <= n / 2)
                return player == BLUE;
            if (blues.size() + remaining <= n / 2)
                return player == RED;

            GameState key = getKey(reds, blues, player);

            if (dp.containsKey(key))
                return dp.get(key);

            Set<Integer> myNextNodes = getNextNodes(tree, reds, blues, player);
            Set<Integer> opponentNextNodes = getNextNodes(tree, reds, blues, 1 - player);
            Set<Integer> blockingMoves = new HashSet<>();
            Set<Integer> otherMoves = new HashSet<>();
            Set<Integer> set = player == RED ? reds : blues;

            for (int node : myNextNodes) {
                if (opponentNextNodes.contains(node))
                    blockingMoves.add(node);
                else
                    otherMoves.add(node);
            }

            for (int nextNode : blockingMoves) {
                set.add(nextNode);
                boolean opponentLoses = !move(tree, n, reds, blues, 1 - player, dp);
                set.remove(nextNode);
                if (opponentLoses) {
                    dp.put(key, true);
                    return true;
                }
            }

            for (int nextNode : otherMoves) {
                set.add(nextNode);
                boolean opponentLoses = !move(tree, n, reds, blues, 1 - player, dp);
                set.remove(nextNode);
                if (opponentLoses) {
                    dp.put(key, true);
                    return true;
                }
            }

            dp.put(key, false);
            return false;
        }

        private Set<Integer> getNextNodes(int[][] tree, Set<Integer> reds, Set<Integer> blues, int color) {
            Set<Integer> candidates = new HashSet<>();

            for (int nextNode : color == RED ? reds : blues) {
                int[] neighbors = {tree[nextNode][LEFT], tree[nextNode][RIGHT], tree[nextNode][PARENT]};
                for (int neighbor : neighbors)
                    if (neighbor > 0 && !reds.contains(neighbor) && !blues.contains(neighbor))
                        candidates.add(neighbor);
            }

            return candidates;
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

        private record GameState(long redLow, long redHigh, long blueLow, long blueHigh, int player) {}

        private GameState getKey(Set<Integer> reds, Set<Integer> blues, int player) {
            long redLow = 0;
            long redHigh = 0;
            long blueLow = 0;
            long blueHigh = 0;
            int hash;

            for (int i : reds) {
                if (i < 64)
                    redLow |= 1L << i;
                else
                    redHigh |= 1L << (i - 64);
            }
            for (int i : blues) {
                if (i < 64)
                    blueLow |= 1L << i;
                else
                    blueHigh |= 1L << (i - 64);
            }

            return new GameState(redLow, redHigh, blueLow, blueHigh, player);
        }

        public static class TreeNode {
            public int val;
            public TreeNode left;
            public TreeNode right;
            public TreeNode next;

            public TreeNode() {
            }

            public TreeNode(int val) {
                this.val = val;
            }

            public TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }
    }
}