package com.bteshome.algorithms.graphs_.dfs;

import com.bteshome.algorithms.graphs_.Graph;
import com.bteshome.algorithms.graphs_.Vertex;

import java.util.ArrayList;
import java.util.List;

public class DFSAlgorithms3 {
    /**
     * TODO - being N^2, it fails leetcode time limit test
     * https://leetcode.com/problems/minimum-height-trees/editorial/?envType=problem-list-v2&envId=graph&difficulty=MEDIUM
     * */
    public static List<Integer> findMinHeightTreesTODO(int n, int[][] edges) {
        var g = new Graph<Integer>(false);

        for (int i = 0; i < n; i++) {
            g.addVertex(i);
        }

        if (edges != null) {
            for (int[] edge : edges) {
                g.addEdge(edge[0], edge[1]);
            }
        }

        int minHeight = Integer.MAX_VALUE;
        ArrayList<Integer> minHeightNodes = new ArrayList<Integer>();

        for (var start : g.getVertices().values()) {
            var height = findMinHeightTreesTODO(start, new int[n]);
            if (height < minHeight) {
                minHeight = height;
                minHeightNodes = new ArrayList<Integer>();
                minHeightNodes.add(start.getKey());
            } else if (height == minHeight) {
                minHeightNodes.add(start.getKey());
            }
        }

        return minHeightNodes;
    }

    private static int findMinHeightTreesTODO(Vertex<Integer> vertex, int[] state) {
        if (state[vertex.getKey()] == 1) {
            return -1;
        }

        state[vertex.getKey()] = 1;

        int height = 0;

        for (var neighbor : vertex.getNeighbors().values()) {
            var neighborHeight = findMinHeightTreesTODO(neighbor, state);
            if (neighborHeight != -1) {
                height = Math.max(height, 1 + neighborHeight);
            }
        }

        return height;
    }

    /**
     * https://leetcode.com/problems/number-of-islands/
     * */
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int islands = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int num = numIslands(grid, i, j);
                islands += num;
            }
        }

        return islands;
    }

    private static int numIslands(char[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length) {
            return 0;
        }

        if (col < 0 || col >= grid[0].length) {
            return 0;
        }

        if (grid[row][col] == '0') {
            return 0;
        }

        grid[row][col] = '0';

        numIslands(grid, row - 1, col);
        numIslands(grid, row + 1, col);
        numIslands(grid, row, col - 1);
        numIslands(grid, row, col + 1);

        return 1;
    }
}
