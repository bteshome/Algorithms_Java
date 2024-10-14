package com.bteshome.algorithms.graphs_.dfs;

import com.bteshome.algorithms.graphs_.Graph;
import com.bteshome.algorithms.graphs_.State;
import com.bteshome.algorithms.graphs_.Vertex;

import java.util.List;

public class DFSAlgorithms6 {
    /**
     * https://leetcode.com/problems/keys-and-rooms/?envType=study-plan-v2&envId=leetcode-75
     * */
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms == null || rooms.size() < 2) {
            return true;
        }
        
        var g = new Graph<Integer>();
        for (int i = 0; i < rooms.size(); i++) {
            g.addVertex(i);
        }

        for (int i = 0; i < rooms.size(); i++) {
            for (var key : rooms.get(i)) {
                g.addEdge(i, key);
            }
        }

        canVisitAllRooms(g.getVertices().get(0));

        for (var vertex : g.getVertices().values()) {
            if (vertex.getState() == State.Unvisited) {
                return false;
            }
        }

        return true;
    }

    private static void canVisitAllRooms(Vertex<Integer> vertex) {
        vertex.setState(State.Visiting);
        for (var neighbor : vertex.getNeighbors().values()) {
            if (neighbor.getState() == State.Unvisited) {
                canVisitAllRooms(neighbor);
            }
        }
    }

    /**
     * https://leetcode.com/problems/max-area-of-island/
     * */
    public static int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int maxArea = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                maxArea = Math.max(maxArea, maxAreaOfIsland(grid, i, j));
            }
        }

        return maxArea;
    }

    private static int maxAreaOfIsland(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length) {
            return 0;
        }

        if (j < 0 || j >= grid[0].length) {
            return 0;
        }

        if (grid[i][j] == 0) {
            return 0;
        }

        grid[i][j] = 0;

        int area = 1;

        area += maxAreaOfIsland(grid, i, j-1);
        area += maxAreaOfIsland(grid, i, j+1);
        area += maxAreaOfIsland(grid, i-1, j);
        area += maxAreaOfIsland(grid, i+1, j);

        return area;
    }
}
