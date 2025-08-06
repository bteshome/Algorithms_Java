package com.bteshome.algorithms.dynamicProgramming_;

import java.util.HashMap;
import java.util.Map;

public class DPAlgorithms17 {
    /* https://leetcode.com/problems/cherry-pickup/ */
    public static int cherryPickupTopDown(int[][] grid) {
        if (grid == null)
            return 0;

        int maxPickup = cherryPickupTopDown(grid, 0, 0, 0, 0, new HashMap<>());
        return maxPickup == - 1 ? 0 : maxPickup;
    }

    private static final int[][] cherryPickupMoves = new int[][]{
            new int[]{1, 0},
            new int[]{0, 1}
    };

    private static int cherryPickupTopDown(int[][] grid, int row1, int col1, int row2, int col2, Map<String, Integer> dp) {
        int n = grid.length;
        if (row1 >= n || col1 >= n || row2 >= n || col2 >= n || grid[row1][col1] == -1 || grid[row2][col2] == -1)
            return -1;
        if (row1 == n - 1 && col1 == n - 1 && row2 == n - 1 && col2 == n - 1)
            return grid[row1][col1];

        String key = "%s,%s,%s,%s".formatted(row1, col1, row2, col2);

        if (!dp.containsKey(key)) {
            int maxPickup = -1;

            for (int[] move1 : cherryPickupMoves) {
                for (int[] move2 : cherryPickupMoves) {
                    int nextRow1 = row1 + move1[0];
                    int nextCol1 = col1 + move1[1];
                    int nextRow2 = row2 + move2[0];
                    int nextCol2 = col2 + move2[1];
                    maxPickup = Math.max(maxPickup, cherryPickupTopDown(grid, nextRow1, nextCol1, nextRow2, nextCol2, dp));
                }
            }

            if (maxPickup != -1) {
                maxPickup += grid[row1][col1];
                if (row1 != row2 || col1 != col2)
                    maxPickup += grid[row2][col2];
            }

            dp.put(key, maxPickup);
        }

        return dp.get(key);
    }

    public static int cherryPickupBottomUp(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;

        int n = grid.length;

        if (grid[0][0] == -1 || grid[n - 1][n - 1] == -1)
            return 0;

        int[][][][] dp = new int[n][n][n][n];

        for (int row1 = n - 1; row1 >= 0; row1--) {
            for (int col1 = n - 1; col1 >= 0; col1--) {
                for (int row2 = n - 1; row2 >= 0; row2--) {
                    for (int col2 = n - 1; col2 >= 0; col2--) {
                        if (row1 == n - 1 && col1 == n - 1 && row2 == n - 1 && col2 == n - 1) {
                            dp[row1][col1][row2][col2] = grid[row1][col1];
                            continue;
                        }

                        if (grid[row1][col1] == -1 || grid[row2][col2] == -1) {
                            dp[row1][col1][row2][col2] = -1;
                            continue;
                        }

                        int maxPickup = -1;

                        for (int[] move1 : cherryPickupMoves) {
                            for (int[] move2 : cherryPickupMoves) {
                                int nextRow1 = row1 + move1[0];
                                int nextCol1 = col1 + move1[1];
                                int nextRow2 = row2 + move2[0];
                                int nextCol2 = col2 + move2[1];
                                if (nextRow1 < n && nextCol1 < n && nextRow2 < n && nextCol2 < n)
                                    maxPickup = Math.max(maxPickup, dp[nextRow1][nextCol1][nextRow2][nextCol2]);
                            }
                        }

                        if (maxPickup != -1) {
                            maxPickup += grid[row1][col1];
                            if (row1 != row2 || col1 != col2)
                                maxPickup += grid[row2][col2];
                        }

                        dp[row1][col1][row2][col2] = maxPickup;
                    }
                }
            }
        }

        return Math.max(0, dp[0][0][0][0]);
    }

    /**
     * https://leetcode.com/problems/cherry-pickup-ii/
     * */
    public static int cherryPickupIITopDown(int[][] grid) {
        if (grid == null)
            return 0;

        return cherryPickupIITopDown(grid, 0, 0, grid[0].length - 1, new HashMap<>());
    }

    private static final int[] cherryPickupIIMoves = {-1, 0, 1};

    private static int cherryPickupIITopDown(int[][] grid, int row, int col1, int col2, Map<String, Integer> cache) {
        if (row == grid.length)
            return 0;

        String key = "%s,%s,%s".formatted(row, col1, col2);

        if (!cache.containsKey(key)) {
            int maxPickup = 0;

            if (row < grid.length - 1) {
                for (int move1 : cherryPickupIIMoves) {
                    for (int move2 : cherryPickupIIMoves) {
                        int newCol1 = col1 + move1;
                        int newCol2 = col2 + move2;
                        if (newCol1 >= 0 && newCol1 < grid[0].length && newCol2 >= 0 && newCol2 < grid[0].length)
                            maxPickup = Math.max(maxPickup, cherryPickupIITopDown(grid, row + 1, newCol1, newCol2, cache));
                    }
                }
            }

            maxPickup += grid[row][col1];
            if (col1 != col2)
                maxPickup += grid[row][col2];

            cache.put(key, maxPickup);
        }

        return cache.get(key);
    }

    public static int cherryPickupIIBottomUp(int[][] grid) {
        if (grid == null)
            return 0;

        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m][n][n];

        for (int row = m - 1; row >= 0; row--) {
            for (int col1 = 0; col1 < n; col1++) {
                for (int col2 = 0; col2 < n; col2++) {
                    int maxPickup = 0;
                    if (row < m - 1) {
                        for (int move1 : cherryPickupIIMoves) {
                            for (int move2 : cherryPickupIIMoves) {
                                int newCol1 = col1 + move1;
                                int newCol2 = col2 + move2;
                                if (newCol1 >= 0 && newCol1 < n && newCol2 >= 0 && newCol2 < n)
                                    maxPickup = Math.max(maxPickup, dp[row + 1][newCol1][newCol2]);
                            }
                        }
                    }
                    maxPickup += grid[row][col1];
                    if (col1 != col2)
                        maxPickup += grid[row][col2];
                    dp[row][col1][col2] = maxPickup;
                }
            }
        }

        return dp[0][0][n - 1];
    }
}