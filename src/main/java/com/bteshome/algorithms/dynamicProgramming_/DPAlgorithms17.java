package com.bteshome.algorithms.dynamicProgramming_;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DPAlgorithms17 {
    /**
     * https://leetcode.com/problems/maximum-score-from-performing-multiplication-operations/
     * */
    public static int maximumScore(int[] nums, int[] multipliers) {
        if (nums == null || multipliers == null)
            return 0;
        if (nums.length < multipliers.length)
            return 0;

        return maximumScore(nums, multipliers, 0, nums.length - 1, 0, new HashMap<>());
    }

    private static int maximumScore(int[] nums, int[] multipliers, int start, int end, int operation, Map<String, Integer> cache) {
        if (operation == multipliers.length)
            return 0;
        if (start == end)
            return nums[start] * multipliers[operation];

        String key = "%s,%s,%s".formatted(start, end, operation);

        if (!cache.containsKey(key)) {
            int left = nums[start] * multipliers[operation] + maximumScore(nums, multipliers, start + 1, end, operation + 1, cache);
            int right = nums[end] * multipliers[operation] + maximumScore(nums, multipliers, start, end - 1, operation + 1, cache);
            cache.put(key, Math.max(left, right));
        }

        return cache.get(key);
    }

    /**
     * https://leetcode.com/problems/cherry-pickup-ii/
     * */
    public static int cherryPickup(int[][] grid) {
        if (grid == null)
            return 0;

        return cherryPickup(grid, 0, 0, grid[0].length - 1, new HashMap<>());
    }

    private static int cherryPickup(int[][] grid, int row, int col1, int col2, Map<String, Integer> cache) {
        if (row == grid.length)
            return 0;

        String key = "%s,%s,%s".formatted(row, col1, col2);

        if (!cache.containsKey(key)) {
            int maxPickup = 0;

            List<int[]> nextColumns = getNextColumns(grid, col1, col2);

            for (int[] nextColumnPair : nextColumns)
                maxPickup = Math.max(maxPickup, cherryPickup(grid, row + 1, nextColumnPair[0], nextColumnPair[1], cache));

            maxPickup += grid[row][col1];
            if (col1 != col2)
                maxPickup += grid[row][col2];

            cache.put(key, maxPickup);
        }

        return cache.get(key);
    }

    private static List<int[]> getNextColumns(int[][] grid, int robot1Col, int robot2Col) {
        List<int[]> combinations = new ArrayList<>();
        int numCols = grid[0].length;
        int[] moves = {-1, 0, 1};

        for (int move1 : moves) {
            for (int move2 : moves) {
                int newCol1 = robot1Col + move1;
                int newCol2 = robot2Col + move2;
                if (newCol1 >= 0 && newCol1 < numCols && newCol2 >= 0 && newCol2 < numCols)
                    combinations.add(new int[]{newCol1, newCol2});
            }
        }

        return combinations;
    }
}
