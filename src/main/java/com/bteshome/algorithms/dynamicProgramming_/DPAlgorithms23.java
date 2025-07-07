package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms23 {
    private static final int MOD = (int)Math.pow(10, 9) + 7;
    private static final int[][] directions = new int[][] {
            new int[]{0, 1},
            new int[]{0, -1},
            new int[]{1, 0},
            new int[]{-1, 0}
    };

    /* https://leetcode.com/problems/number-of-increasing-paths-in-a-grid/ */
    public static int countPaths(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        Integer[][] dp = new Integer[grid.length][grid[0].length];
        long paths = 0;

        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                paths = (paths + countPaths(grid, i, j, dp)) % MOD;

        return (int)paths;
    }

    private static int countPaths(int[][] grid, int row, int col, Integer[][] dp) {
        if (dp[row][col] == null) {
            long paths = 1;

            for (int[] direction : directions) {
                int nextRow = row + direction[0];
                int nextCol = col + direction[1];
                if (nextRow >= 0 && nextRow < grid.length && nextCol >= 0 && nextCol < grid[0].length)
                    if (grid[nextRow][nextCol] > grid[row][col])
                        paths = (paths + countPaths(grid, nextRow, nextCol, dp)) % MOD;
            }

            dp[row][col] = (int) paths;
        }

        return dp[row][col];
    }

    /* https://leetcode.com/problems/number-of-ways-of-cutting-a-pizza/ */
    public static int ways(String[] pizza, int k) {
        if (pizza == null || pizza.length == 0 || pizza[0].isEmpty() || k < 1)
            return 0;

        int m = pizza.length;
        int n = pizza[0].length();
        int[][] appleCounts = new int[m][n];

        for (int i = m - 1; i >= 0 ; i--) {
            for (int j = n - 1; j >= 0; j--) {
                appleCounts[i][j] = pizza[i].charAt(j) == 'A' ? 1 : 0;
                if (i + 1 < m)
                    appleCounts[i][j] += appleCounts[i + 1][j];
                if (j + 1 < n)
                    appleCounts[i][j] += appleCounts[i][j + 1];
                if (i + 1 < m && j + 1 < n)
                    appleCounts[i][j] -= appleCounts[i + 1][j + 1];
            }
        }

        Integer[][][] dp = new Integer[m][n][k + 1];

        return ways(m, n, k, 0, 0, appleCounts, dp);
    }

    private static int ways(int m, int n, int k, int row, int col, int[][] appleCounts, Integer[][][] dp) {
        if (k == 1)
            return appleCounts[row][col] == 0 ? 0 : 1;

        if (dp[row][col][k] == null) {
            long count = 0;

            for (int i = row + 1; i < m; i++)
                if (appleCounts[i][col] > 0 && appleCounts[i][col] < appleCounts[row][col])
                    count = (count + ways(m, n, k - 1, i, col, appleCounts, dp)) % MOD;

            for (int j = col + 1; j < n; j++)
                if (appleCounts[row][j] > 0 && appleCounts[row][j] < appleCounts[row][col])
                    count = (count + ways(m, n, k - 1, row, j, appleCounts, dp)) % MOD;

            dp[row][col][k] = (int) count;
        }

        return dp[row][col][k];
    }
}
