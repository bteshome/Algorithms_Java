package com.bteshome.algorithms.dynamicProgramming_;

import java.util.*;

public class DPAlgorithms56 {
    /* https://leetcode.com/problems/maximum-earnings-from-taxi */
    public static long maxTaxiEarningsTopDown(int n, int[][] rides) {
        if (n < 2 || rides == null || rides.length == 0)
            return 0;

        Long[] dp = new Long[n + 1];
        List<int[]>[] ridesStartingAtPoint = new List[n + 1];

        for (int point = 1; point <= n; point++)
            ridesStartingAtPoint[point] = new ArrayList<>();
        for (int[] ride : rides)
            ridesStartingAtPoint[ride[0]].add(ride);

        return maxTaxiEarningsTopDown(n, rides, 1, ridesStartingAtPoint, dp);
    }

    private static long maxTaxiEarningsTopDown(int n, int[][] rides, int point, List<int[]>[] ridesStartingAtPoint, Long[] dp) {
        if (point >= n + 1)
            return 0;
        if (dp[point] != null)
            return dp[point];

        long max = maxTaxiEarningsTopDown(n, rides, point + 1, ridesStartingAtPoint, dp);
        for (int[] ride : ridesStartingAtPoint[point]) {
            long pickup = ride[1] - point + ride[2] + maxTaxiEarningsTopDown(n, rides, ride[1], ridesStartingAtPoint, dp);
            max = Math.max(max, pickup);
        }

        dp[point] = max;

        return dp[point];
    }

    /**
     * TODO
     * https://leetcode.com/problems/maximize-grid-happiness
     * NOTE: - this solution exceeds LeetCode timme limit.
     *         It can be optimized by packing all the required state into a numeric key
     *         using bit manipulation.
     * */
    public static class GetMaxGridHappiness {
        private int m;
        private int n;
        private int introvertsCount;
        private int extrovertsCount;
        private int[][] grid;
        private int[][] happinessChanges;

        public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
            if (m <= 0 || n <= 0 || introvertsCount < 0 || extrovertsCount < 0)
                return 0;
            if (introvertsCount + extrovertsCount == 0)
                return 0;

            this.m = m;
            this.n = n;
            this.introvertsCount = introvertsCount;
            this.extrovertsCount = extrovertsCount;
            this.grid = new int[m + 1][n + 1];
            this.happinessChanges = new int[][] {
                    new int[] {0, 0, 0},
                    new int[] {0, -60, -10},
                    new int[] {0, -10, +40}
            };

            return getMaxGridHappiness(1, 1, 0, 0, new HashMap<>());
        }

        private int getMaxGridHappiness(int row, int col, int introverts, int extroverts, Map<String, Integer> dp) {
            if (row == m + 1)
                return 0;
            if (introverts == introvertsCount && extroverts == extrovertsCount)
                return 0;

            String previousRowState = Arrays.toString(grid[row - 1]);
            String currentRowState = Arrays.toString(grid[row]);
            String key = "%s,%s,%s,%s,%s,%s".formatted(row, col, introverts, extroverts, previousRowState, currentRowState);

            if (dp.containsKey(key))
                return dp.get(key);

            int upperNeighbor = grid[row - 1][col];
            int leftNeighbor = grid[row][col - 1];
            int nextRow = col == n ? row + 1 : row;
            int nextCol = col == n ? 1 : col + 1;
            int happiness = 0;
            int overallHappiness = 0;

            // skip
            overallHappiness = getMaxGridHappiness(nextRow, nextCol, introverts, extroverts, dp);

            // place an introvert
            if (introverts < introvertsCount) {
                happiness = 120;
                grid[row][col] = 1;
                happiness += happinessChanges[1][upperNeighbor];
                happiness += happinessChanges[1][leftNeighbor];

                int next = getMaxGridHappiness(nextRow, nextCol, introverts + 1, extroverts, dp);
                grid[row][col] = 0;

                overallHappiness = Math.max(overallHappiness, happiness + next);
            }

            // place an extrovert
            if (extroverts < extrovertsCount) {
                happiness = 40;
                grid[row][col] = 2;
                happiness += happinessChanges[2][upperNeighbor];
                happiness += happinessChanges[2][leftNeighbor];

                int next = getMaxGridHappiness(nextRow, nextCol, introverts, extroverts + 1, dp);
                grid[row][col] = 0;

                overallHappiness = Math.max(overallHappiness, happiness + next);
            }

            dp.put(key, overallHappiness);

            return dp.get(key);
        }
    }
}