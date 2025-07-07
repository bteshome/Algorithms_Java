package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms26 {
    /**
     * https://leetcode.com/problems/stone-game
     * key observations:
     * - since it's a zero-sum game, no need to store the player in the DP state
     * */
    public static boolean stoneGameTopDown(int[] piles) {
        if (piles == null || piles.length < 2 || piles.length % 2 == 1)
            return false;

        Integer[][] dp = new Integer[piles.length][piles.length];
        return stoneGameTopDown(piles, 0, piles.length - 1, dp) > 0;
    }

    public static int stoneGameTopDown(int[] piles, int start, int end, Integer[][] dp) {
        if (start == end)
            return piles[start];

        if (dp[start][end] == null) {
            int left = piles[start] - stoneGameTopDown(piles, start + 1, end, dp);
            int right = piles[end] - stoneGameTopDown(piles, start, end - 1, dp);
            dp[start][end] = Math.max(left, right);
        }

        return dp[start][end];
    }

    public static boolean stoneGameBottomUp(int[] piles) {
        if (piles == null || piles.length < 2 || piles.length % 2 == 1)
            return false;

        int n = piles.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++)
            dp[i][i] = piles[i];

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                int left = piles[i] - dp[i + 1][j];
                int right = piles[j] - dp[i][j - 1];
                dp[i][j] = Math.max(left, right);
            }
        }
        
        return dp[0][n - 1] > 0;
    }

    /**
     * https://leetcode.com/problems/stone-game-ii/
     * key observations:
     * - since it's a zero-sum game, no need to store the player in the DP state
     * - extra optimization can be used by precalculating prefix and suffix sums
     *   (though LeetCode accepts the solution without it)
     *   NOTE: the solution next to this one does not have this extra prefix sum optimization
     * */
    public static class StoneGameIITopDownOptimized {
        private int[] piles = null;
        private int n = 0;
        private int[] prefixSums = null;
        private Integer[][] dp = null;

        public int stoneGameII(int[] piles) {
            if (piles == null || piles.length == 0)
                return 0;

            this.piles = piles;
            this.n = piles.length;
            this.dp = new Integer[n][n * 2 + 1];
            this.constructPrefixSums();

            int sum = 0;
            for (int i = 0; i < n; i++)
                sum += piles[i];

            int maxDiff = stoneGameII(0, 1);
            return (sum + maxDiff) / 2;
        }

        private int stoneGameII(int pos, int m) {
            if (pos == piles.length)
                return 0;

            if (dp[pos][m] == null) {
                int len = piles.length - pos;
                int xMax = Math.min(len, 2 * m);
                int maxDiff = Integer.MIN_VALUE;

                for (int x = 1; x <= xMax; x++) {
                    int sum = prefixSums[pos + x - 1] - (pos == 0 ? 0 : prefixSums[pos - 1]);
                    maxDiff = Math.max(maxDiff, sum - stoneGameII(pos + x, Math.max(m, x)));
                }

                dp[pos][m] = maxDiff;
            }

            return dp[pos][m];
        }

        private void constructPrefixSums() {
            prefixSums = new int[n];
            prefixSums[0] = piles[0];
            for (int i = 1; i < n; i++)
                prefixSums[i] = prefixSums[i - 1] + piles[i];
        }
    }

    public static int stoneGameIITopDown(int[] piles) {
        if (piles == null || piles.length == 0)
            return 0;
        int sum = 0;
        for (int i = 0; i < piles.length; i++)
            sum += piles[i];

        Integer[][] dp = new Integer[piles.length][piles.length * 2 + 1];
        int maxDiff = stoneGameIITopDown(piles, 0, 1, dp);
        return (sum + maxDiff) / 2;
    }

    private static int stoneGameIITopDown(int[] piles, int pos, int m, Integer[][] dp) {
        if (pos == piles.length)
            return 0;

        if (dp[pos][m] == null) {
            int len = piles.length - pos;
            int xMax = Math.min(len, 2 * m);
            int maxDiff = Integer.MIN_VALUE;

            for (int x = 1; x <= xMax; x++) {
                int sum = 0;
                for (int i = pos; i < pos + x; i++)
                    sum += piles[i];
                maxDiff = Math.max(maxDiff, sum - stoneGameIITopDown(piles, pos + x, Math.max(m, x),dp));
            }

            dp[pos][m] = maxDiff;
        }

        return dp[pos][m];
    }
}
