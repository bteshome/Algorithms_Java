package com.bteshome.algorithms.graphs_.bfs;

import com.bteshome.algorithms.graphs_.Graph;
import com.bteshome.algorithms.graphs_.State;
import com.bteshome.algorithms.graphs_.Vertex;

import java.util.LinkedList;
import java.util.Queue;

public class BFSAlgorithms1 {
    /**
     * https://leetcode.com/problems/find-if-path-exists-in-graph/description/?envType=problem-list-v2&envId=graph&difficulty=EASY
     * */
    public static boolean validPath(int n, int[][] edges, int source, int destination) {
        if (edges == null || n < 1 || source >= n || destination >= n) {
            return false;
        }

        if (source == destination) {
            return true;
        }

        var g = new Graph<Integer>(false);

        for (int i = 0; i < n; i++) {
            g.addVertex(i);
        }

        for (int[] edge : edges) {
            g.addEdge(edge[0], edge[1]);
        }

        Queue<Vertex<Integer>> q = new LinkedList<Vertex<Integer>>();
        var start = g.getVertices().get(source);
        q.add(start);
        start.setState(State.Visiting);

        while (!q.isEmpty()) {
            var front = q.remove();
            for (var neighbor : front.getNeighbors().values()) {
                if (neighbor.getKey() == destination) {
                    return true;
                }
                if (neighbor.getState() == State.Unvisited) {
                    neighbor.setState(State.Visiting);
                    q.add(neighbor);
                }
            }
        }

        return false;
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
                if (grid[i][j] == '1') {
                    islands++;
                    numIslands(grid, new int[]{i, j});
                }
            }
        }

        return islands;
    }

    private static void numIslands(char[][] grid, int[] start) {
        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        grid[start[0]][start[1]] = '0';

        var moves = new int[][]{
                new int[]{0, -1},
                new int[]{0, 1},
                new int[]{-1, 0},
                new int[]{1, 0}
        };

        while (!q.isEmpty()) {
            var front = q.remove();
            for (var move : moves) {
                int nextRow = front[0] + move[0];
                int nextCol = front[1] + move[1];
                if (nextRow >= 0 && nextRow < grid.length && nextCol >= 0 && nextCol < grid[0].length && grid[nextRow][nextCol] == '1') {
                    q.add(new int[]{nextRow, nextCol});
                    grid[nextRow][nextCol] = '0';
                }
            }
        }
    }
}
