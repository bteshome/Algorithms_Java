package com.bteshome.algorithms.dynamicProgramming_;

import java.util.Arrays;

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
        if (m < 0 || n < 0) {
            return 0;
        }

        var prevRow = new int[n];
        var currentRow = new int[n];
        Arrays.fill(prevRow, 1);
        currentRow[0] = 1;

        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                currentRow[col] = currentRow[col - 1] + prevRow[col];
            }
            var temp = prevRow;
            prevRow = currentRow;
            currentRow = temp;
        }

        return prevRow[n - 1];
    }

    /**
     * https://leetcode.com/problems/unique-paths-ii/?envType=study-plan-v2&envId=top-interview-150
     * */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }

        var cache = new Integer[obstacleGrid.length][];
        for (int i = 0; i < obstacleGrid.length; i++) {
            cache[i] = new Integer[obstacleGrid[0].length];
        }

        return uniquePathsWithObstacles(obstacleGrid, obstacleGrid.length - 1, obstacleGrid[0].length - 1, cache);
    }

    private static int uniquePathsWithObstacles(int[][] obstacleGrid, int row, int col, Integer[][] cache) {
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
                    uniquePathsWithObstacles(obstacleGrid, row, col - 1, cache) +
                            uniquePathsWithObstacles(obstacleGrid, row - 1, col, cache);
        }

        return cache[row][col];
    }
}
