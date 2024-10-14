package com.bteshome.algorithms.disjointSets_;

import java.util.ArrayList;
import java.util.List;

public class DisjointSetsAlgorithms1 {
    /**
     * https://leetcode.com/problems/find-if-path-exists-in-graph/
     * */
    public static boolean validPath(int n, int[][] edges, int source, int destination) {
        if (source < 0 || source >= n || destination < 0 || destination >= n) {
            return false;
        }

        if (source == destination) {
            return true;
        }

        if (edges == null || edges.length == 0) {
            return  false;
        }

        var sets = new DisjointSetForest<Integer>();
        for (int i = 0; i < n; i++) {
            sets.makeSet(i);
        }

        for (int[] edge : edges) {
            sets.union(edge[0], edge[1]);
        }

        var sourceSet = sets.findSet(source);
        var destinationSet = sets.findSet(destination);
        return sourceSet == destinationSet;
    }

    /**
     * https://leetcode.com/problems/number-of-islands/
     * NOTE - this is dst implementation. There is also a DFS implementation, take a look.
     * */
    public static int numberOfIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        var sets = new DisjointSetForest<Integer>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    var cellId = (i * grid[0].length) + j;
                    sets.makeSet(cellId);
                    if (j > 0 && grid[i][j - 1] == '1') {
                        sets.union(cellId, cellId - 1);
                    }
                    if (i > 0 && grid[i - 1][j] == '1') {
                        sets.union(cellId, cellId - grid[0].length);
                    }
                }
            }
        }

        return sets.getNumberOfDisjointSets();
    }

    /**
     * https://leetcode.com/problems/number-of-islands-ii/?envType=study-plan-v2&envId=premium-algo-100
     * */
    public static List<Integer> numberOfIslandsII(int m, int n, int[][] positions) {
        if (m < 0 || n < 0 || positions == null || positions.length == 0) {
            return List.of();
        }

        var islands = new ArrayList<Integer>();
        var grid = new int[m][];
        var sets = new DisjointSetForest<Integer>();

        for (int i = 0; i < grid.length; i++) {
            grid[i] = new int[n];
        }

        for (int[] position : positions) {
            int i = position[0];
            int j = position[1];
            var cellId = i * n + j;
            sets.makeSet(cellId);
            grid[i][j] = 1;
            if (j > 0 && grid[i][j - 1] == 1) {
                sets.union(cellId, cellId - 1);
            }
            if (j < n - 1 && grid[i][j + 1] == 1) {
                sets.union(cellId, cellId + 1);
            }
            if (i > 0 && grid[i - 1][j] == 1) {
                sets.union(cellId, cellId - n);
            }
            if (i < m - 1 && grid[i + 1][j] == 1) {
                sets.union(cellId, cellId + n);
            }
            islands.add(sets.getNumberOfDisjointSets());
        }

        return islands;
    }
}
