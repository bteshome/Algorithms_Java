package com.bteshome.algorithms.backtracking_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BacktrackingAlgorithms9 {
    /**
     * leetcode https://leetcode.com/problems/n-queens-ii/description/?envType=study-plan-v2&envId=top-interview-150
     * */
    public static int nQueensII(int n) {
        var rowPlacements = new ArrayList<List<Integer>>();

        if (n > 0) {
            nQueensII(n, 0, new Integer[n], rowPlacements);
        }

        return rowPlacements.size();
    }

    private static void nQueensII(int n, int queen, Integer[] rowPlacement, List<List<Integer>> rowPlacements) {
        if (queen == n) {
            rowPlacements.add(Arrays.stream(rowPlacement).toList());
            return;
        }

        for (int row = 0; row < n; row++) {
            if (nQueensIICanPlace(row, queen, rowPlacement)) {
                rowPlacement[queen] = row;
                nQueensII(n, queen + 1, rowPlacement, rowPlacements);
                rowPlacement[queen] = null;
            }
        }
    }

    private static boolean nQueensIICanPlace(int row, int queen, Integer[] rowPlacement) {
        for (int otherQueen = 0; otherQueen < queen; otherQueen++) {
            if (row == rowPlacement[otherQueen]) {
                return false;
            }
            if (queen + row == otherQueen + rowPlacement[otherQueen]) {
                return false;
            }
            if (queen - row == otherQueen - rowPlacement[otherQueen]) {
                return false;
            }
        }
        return true;
    }

    /**
     * https://leetcode.com/problems/all-paths-from-source-to-target/
     * */
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        var paths = new ArrayList<List<Integer>>();

        if (graph == null || graph.length == 0) {
            return paths;
        }

        allPathsSourceTarget(graph, 0, paths, new ArrayList<>());

        return paths;
    }

    private static void allPathsSourceTarget(int[][] graph, int node, ArrayList<List<Integer>> paths, List<Integer> path) {
        path.addLast(node);

        if (node == graph.length - 1) {
            paths.add(path.stream().toList());
        } else {
            for (int neighbor : graph[node]) {
                allPathsSourceTarget(graph, neighbor, paths, path);
            }
        }

        path.removeLast();
    }
}
