package com.bteshome.algorithms.dynamicProgramming_;

import java.util.Arrays;
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
    public static int minPathSumTopDown(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }

        var cache = new Integer[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            cache[i] = new Integer[grid[0].length];
        }

        return minPathSumTopDown(grid, grid.length - 1, grid[0].length - 1, cache);
    }

    private static int minPathSumTopDown(int[][] grid, int row, int col, Integer[][] cache) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return -1;
        }

        if (cache[row][col] == null) {
            var minSum = Integer.MAX_VALUE;
            var sum1 = minPathSumTopDown(grid, row, col - 1, cache);
            var sum2 = minPathSumTopDown(grid, row - 1, col, cache);
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

    public static int minPathSumBottomUp(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++)
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        for (int j = 1; j < n; j++)
            dp[0][j] = dp[0][j - 1] + grid[0][j];

        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];

        return dp[m - 1][n - 1];
    }

    /* https://leetcode.com/problems/minimum-falling-path-sum */
    public static int minFallingPathSumBottomUp(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int n = matrix.length;
        int[][] dp = new int[n][n];

        for (int j = 0; j < n; j++)
            dp[0][j] = matrix[0][j];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int min = dp[i - 1][j];
                if (j > 0)
                    min = Math.min(min, dp[i - 1][j - 1]);
                if (j < n - 1)
                    min = Math.min(min, dp[i - 1][j + 1]);
                dp[i][j] = min + matrix[i][j];
            }
        }

        int overallMin = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++)
            overallMin = Math.min(overallMin, dp[n - 1][j]);

        return overallMin;
    }
}