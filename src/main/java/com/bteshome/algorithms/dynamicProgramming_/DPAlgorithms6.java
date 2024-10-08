package com.bteshome.algorithms.dynamicProgramming_;

import java.util.List;

public class DPAlgorithms6 {
    /**
     * https://leetcode.com/problems/triangle/description/?envType=study-plan-v2&envId=top-interview-150
     * */
    public static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.isEmpty()) {
            return 0;
        }

        var cache = new Integer[triangle.size()][];
        for (int i = 0; i < triangle.size(); i++) {
            cache[i] = new Integer[triangle.size()];
        }

        return minimumTotal(triangle, 0, 0, cache);
    }

    private static int minimumTotal(List<List<Integer>> triangle, int row, int col, Integer[][] cache) {
        if (row == triangle.size() - 1) {
            return triangle.get(row).get(col);
        }

        if (cache[row][col] == null) {
            var total1 = minimumTotal(triangle, row + 1, col, cache);
            var total2 = minimumTotal(triangle, row + 1, col + 1, cache);
            cache[row][col] = triangle.get(row).get(col) + Math.min(total1, total2);
        }

        return cache[row][col];
    }

    /**
     * https://leetcode.com/problems/minimum-path-sum/description/?envType=study-plan-v2&envId=top-interview-150
     * */
    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }

        var cache = new Integer[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            cache[i] = new Integer[grid[0].length];
        }

        return minPathSum(grid, grid.length - 1, grid[0].length - 1, cache);
    }

    private static int minPathSum(int[][] grid, int row, int col, Integer[][] cache) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return -1;
        }

        if (cache[row][col] == null) {
            var minSum = Integer.MAX_VALUE;
            var sum1 = minPathSum(grid, row, col - 1, cache);
            var sum2 = minPathSum(grid, row - 1, col, cache);
            if (sum1 != - 1) {
                minSum = sum1;
            }
            if (sum2 != -1) {
                minSum = Math.min(minSum, sum2);
            }

            cache[row][col] = grid[row][col] + (minSum == Integer.MAX_VALUE ? 0 : minSum);
        }

        return cache[row][col];
    }


}
