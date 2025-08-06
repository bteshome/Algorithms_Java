package com.bteshome.algorithms.dynamicProgramming_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DPAlgorithms64 {
    /**
     * https://leetcode.com/problems/sum-of-distances-in-tree
     * NOTE: - the re-rooting version is fast and accepted
     *       - for comparison, see also the bruteforce version, which exceeds LC TL
     * */
    public static class SumOfDistancesInTreeWithRerooting {
        public int[] sumOfDistancesInTree(int n, int[][] edges) {
            if (n < 1 || edges == null)
                return new int[0];

            List<Integer>[] tree = buildTree(n, edges);
            int[] counts = new int[n];
            int[] distances = new int[n];
            int[] result = new int[n];

            getCounts(tree, counts, 0, -1);
            result[0] = getDistancesFromFirstRoot(tree, distances, 0, -1, 0);
            reroot(tree, n, result, counts, 0, -1);

            return result;
        }

        private int getDistancesFromFirstRoot(List<Integer>[] tree, int[] distances, int root, int parent, int distance) {
            distances[root] = distance;
            int sum = distance;

            for (int child : tree[root]) {
                if (child == parent)
                    continue;
                sum += getDistancesFromFirstRoot(tree, distances, child, root, distance + 1);
            }

            return sum;
        }

        private void reroot(List<Integer>[] tree, int n, int[] result, int[] counts, int root, int parent) {
            for (int child : tree[root]) {
                if (child == parent)
                    continue;
                result[child] = result[root] - counts[child] + (n - counts[child]);
                reroot(tree, n, result, counts, child, root);
            }
        }

        private List<Integer>[] buildTree(int n, int[][] edges) {
            List<Integer>[] tree = new List[n];

            for (int i = 0; i < n; i++)
                tree[i] = new ArrayList<>();

            for (int[] edge : edges) {
                int from = edge[0];
                int to = edge[1];
                tree[from].add(to);
                tree[to].add(from);
            }

            return tree;
        }

        private void getCounts(List<Integer>[] tree, int[] counts, int root, int parent) {
            counts[root] = 1;
            for (int child : tree[root]) {
                if (child == parent)
                    continue;
                getCounts(tree, counts, child, root);
                counts[root] += counts[child];
            }
        }
    }

    public static class SumOfDistancesInTreeBruteForce {
        public int[] sumOfDistancesInTree(int n, int[][] edges) {
            if (n < 1 || edges == null)
                return new int[0];

            List<Integer>[] tree = buildTree(n, edges);
            int[] distances = new int[n];
            int[] result = new int[n];

            for (int i = 0; i < n; i++) {
                if (i > 0)
                    Arrays.fill(distances, 0);
                int distance = getDistances(tree, distances, i, -1, 0);
                result[i] = distance;
            }

            return result;
        }

        private int getDistances(List<Integer>[] tree, int[] distances, int root, int parent, int distance) {
            distances[root] = distance;
            int sum = distance;

            for (int child : tree[root]) {
                if (child == parent)
                    continue;
                sum += getDistances(tree, distances, child, root, distance + 1);
            }

            return sum;
        }

        private List<Integer>[] buildTree(int n, int[][] edges) {
            List<Integer>[] tree = new List[n];

            for (int i = 0; i < n; i++)
                tree[i] = new ArrayList<>();

            for (int[] edge : edges) {
                int from = edge[0];
                int to = edge[1];
                tree[from].add(to);
                tree[to].add(from);
            }

            return tree;
        }
    }
}