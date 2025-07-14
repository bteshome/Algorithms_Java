package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms32 {
    /* https://leetcode.com/problems/toss-strange-coins/ */
    public static double probabilityOfHeads(double[] prob, int target) {
        if (prob == null || prob.length == 0)
            return target == 0 ? 1 : 0;
        if (target < 0 || target > prob.length)
            return 0;

        return probabilityOfHeads(prob, 0, target, new Double[prob.length][target + 1]);
    }

    private static double probabilityOfHeads(double[] prob, int coin, int target, Double[][] dp) {
        if (coin == prob.length)
            return target == 0 ? 1 : 0;

        if (dp[coin][target] == null) {
            double p = 0;
            p += (1 - prob[coin]) * probabilityOfHeads(prob, coin + 1, target, dp);
            if (target > 0)
                p += prob[coin] * probabilityOfHeads(prob, coin + 1, target - 1, dp);
            dp[coin][target] = p;
        }

        return dp[coin][target];
    }

    /* https://leetcode.com/problems/knight-probability-in-chessboard */
    public static double knightProbability(int n, int k, int row, int column) {
        if (n < 1 || k < 0)
            return  0;

        return knightProbability(n, k, row, column, new Double[n][n][k + 1]);
    }

    private static final int[][] moves = new int[][]{
            new int[]{ 1, 2},
            new int[]{ 1, -2},
            new int[]{ -1, 2},
            new int[]{ -1, -2},
            new int[]{ 2, 1},
            new int[]{ 2, -1},
            new int[]{ -2, 1},
            new int[]{ -2, -1}
    };

    private static double knightProbability(int n, int k, int row, int column, Double[][][] dp) {
        if (row < 0 || row >= n || column < 0 || column >= n)
            return 0;
        if (k == 0)
            return 1;

        if (dp[row][column][k] == null) {
            double p = 0;
            for (int[] move : moves)
                p += 0.125 * knightProbability(n, k - 1, row + move[0], column + move[1], dp);
            dp[row][column][k] = p;
        }

        return dp[row][column][k];
    }
}