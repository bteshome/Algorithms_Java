package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms10 {
    /**
     * https://leetcode.com/problems/unique-paths/?envType=problem-list-v2&envId=dynamic-programming&difficulty=MEDIUM
     * */
    public static int uniquePathsRecursive(int m, int n) {
        if (m < 0 || n < 0) {
            return 0;
        }

        var cache = new Integer[m][];
        for (int i = 0; i < m; i++) {
            cache[i] = new Integer[n];
        }

        return uniquePathsRecursive(m, n, m - 1, n - 1, cache);
    }

    private static int uniquePathsRecursive(int m, int n, int row, int col, Integer[][] cache) {
        if (row == 0 && col == 0) {
            return 1;
        }

        if (row < 0 || col < 0) {
            return 0;
        }

        if (cache[row][col] == null) {
            cache[row][col] =
                    uniquePathsRecursive(m, n, row, col - 1, cache) +
                    uniquePathsRecursive(m, n, row - 1, col, cache);
        }

        return cache[row][col];
    }

    public static int uniquePathsIterative(int m, int n) {
        if (m < 1 || n < 1)
            return 0;

        int[][] dp = new int[m][n];

        for (int j = 0; j < n; j++)
            dp[0][j] = 1;
        for (int i = 0; i < m; i++)
            dp[i][0] = 1;

        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];

        return dp[m - 1][n - 1];
    }

    /**
     * https://leetcode.com/problems/unique-paths-ii/?envType=study-plan-v2&envId=top-interview-150
     * */
    public static int uniquePathsWithObstaclesBottomUp(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0] == 1 ? 0 : 1;

        for (int j = 1; j < n; j++)
            dp[0][j] = grid[0][j] == 1 ? 0 : dp[0][j - 1];
        for (int i = 1; i < m; i++)
            dp[i][0] = grid[i][0] == 1 ? 0 : dp[i - 1][0];

        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                dp[i][j] = grid[i][j] == 1 ? 0 : dp[i][j - 1] + dp[i - 1][j];

        return dp[m - 1][n - 1];
    }

    public static int uniquePathsWithObstaclesTopDown(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }

        var cache = new Integer[obstacleGrid.length][];
        for (int i = 0; i < obstacleGrid.length; i++) {
            cache[i] = new Integer[obstacleGrid[0].length];
        }

        return uniquePathsWithObstaclesTopDown(obstacleGrid, obstacleGrid.length - 1, obstacleGrid[0].length - 1, cache);
    }

    private static int uniquePathsWithObstaclesTopDown(int[][] obstacleGrid, int row, int col, Integer[][] cache) {
        if (row < 0 || row >= obstacleGrid.length || col < 0 || col >= obstacleGrid[0].length) {
            return 0;
        }

        if (obstacleGrid[row][col] == 1) {
            return 0;
        }

        if (row == 0 && col == 0) {
            return 1;
        }

        if (cache[row][col] == null) {
            cache[row][col] =
                    uniquePathsWithObstaclesTopDown(obstacleGrid, row, col - 1, cache) +
                            uniquePathsWithObstaclesTopDown(obstacleGrid, row - 1, col, cache);
        }

        return cache[row][col];
    }
}
