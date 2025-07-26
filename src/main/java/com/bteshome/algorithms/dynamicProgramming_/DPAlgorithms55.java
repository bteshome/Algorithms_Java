package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms55 {
    /* https://leetcode.com/problems/minimum-score-triangulation-of-polygon */
    public static int minScoreTriangulationTopDown(int[] values) {
        if (values == null)
            return 0;

        int n = values.length;
        Integer[][] dp = new Integer[n][n];
        return minScoreTriangulationTopDown(values, 0, n - 1, dp);
    }

    private static int minScoreTriangulationTopDown(int[] values, int start, int end, Integer[][] dp) {
        if (start > end - 2)
            return 0;
        if (dp[start][end] != null)
            return dp[start][end];

        int min = Integer.MAX_VALUE;

        for (int mid = start + 1; mid < end; mid++) {
            int left = minScoreTriangulationTopDown(values, start, mid, dp);
            int right = minScoreTriangulationTopDown(values, mid, end, dp);
            int weight = values[start] * values[mid] * values[end];
            min = Math.min(min, left + right + weight);
        }

        dp[start][end] = min;

        return dp[start][end];
    }

    public static int minScoreTriangulationBottomUp(int[] values) {
        if (values == null)
            return 0;
        if (values.length < 3)
            return 0;

        int n = values.length;
        int[][] dp = new int[n][n];

        // length 3
        for (int start = 0; start <= n - 3; start++)
            dp[start][start + 2] = values[start] * values[start + 1] * values[start + 2];

        // length >= 3
        for (int len = 4; len <= n; len++) {
            for (int start = 0; start <= n - len; start++) {
                int end = start + len - 1;
                int min = Integer.MAX_VALUE;

                for (int mid = start + 1; mid < end; mid++) {
                    int left = dp[start][mid];
                    int right = dp[mid][end];
                    int weight = values[start] * values[mid] * values[end];
                    min = Math.min(min, left + right + weight);
                }

                dp[start][end] = min;
            }
        }

        return dp[0][n - 1];
    }

    /* https://leetcode.com/problems/burst-balloons */
    public static int burstBalloonsTopDown(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int n = nums.length;
        int[] numsPadded = new int[n + 2];

        for (int i = 0; i < n; i++)
            numsPadded[i + 1] = nums[i];

        n = n + 2;
        numsPadded[0] = 1;
        numsPadded[n - 1] = 1;

        Integer[][] dp = new Integer[n][n];

        return   burstBalloonsTopDown(numsPadded, 0, n - 1, dp);
    }

    private static int burstBalloonsTopDown(int[] nums, int start, int end, Integer[][] dp) {
        if (start > end - 2)
            return 0;
        if (dp[start][end] != null)
            return dp[start][end];

        int max = 0;

        for (int last = start + 1; last < end; last++) {
            int left = burstBalloonsTopDown(nums, start, last, dp);
            int right = burstBalloonsTopDown(nums, last, end, dp);
            int current = nums[start] * nums[last] * nums[end];
            max = Math.max(max, left + current + right);
        }

        dp[start][end] = max;

        return dp[start][end];
    }

    public static int burstBalloonsBottomUp(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int n = nums.length;
        int[] numsPadded = new int[n + 2];

        for (int i = 0; i < n; i++)
            numsPadded[i + 1] = nums[i];

        n = n + 2;
        nums = numsPadded;
        nums[0] = 1;
        nums[n - 1] = 1;

        int[][] dp = new int[n][n];

        for (int len = 3; len <= n; len++) {
            for (int start = 0; start <= n - len; start++) {
                int end = start + len - 1;
                int max = 0;

                for (int last = start + 1; last < end; last++) {
                    int left = dp[start][last];
                    int right = dp[last][end];
                    int current = nums[start] * nums[last] * nums[end];
                    max = Math.max(max, left + current + right);
                }

                dp[start][end] = max;
            }
        }

        return dp[0][n - 1];
    }

    /* https://leetcode.com/problems/minimum-cost-to-merge-stones */
    public static int mergeStonesBottomUp(int[] stones, int k) {
        if (stones == null)
            return -1;

        int n = stones.length;

        if (n < 1)
            return -1;
        if (n == 1)
            return 0;
        if (k < 2 || k > n)
            return -1;
        if ((n - 1) % (k - 1) != 0)
            return -1;

        int[][][] dp = new int[n][n][k + 1];
        int[] prefixSums = new int[n + 1];
        final int INF = Integer.MAX_VALUE / 2;

        for (int i = 0; i < n; i++)
            prefixSums[i + 1] = prefixSums[i] + stones[i];

        for (int i = 0; i < n; i++) {
            dp[i][i][1] = 0;
            for (int targetLen = 2; targetLen <= k; targetLen++)
                dp[i][i][targetLen] = INF;
        }

        for (int intervalLen = 2; intervalLen <= n; intervalLen++) {
            for (int i = 0; i <= n - intervalLen; i++) {
                int j = i + intervalLen - 1;

                for (int targetLen = 2; targetLen <= k; targetLen++) {
                    int min = INF;

                    for (int mid = i; mid < j; mid++) {
                        int left = dp[i][mid][1];
                        int right = dp[mid + 1][j][targetLen - 1];
                        if (left != INF && right != INF)
                            min = Math.min(min, left + right);
                    }

                    dp[i][j][targetLen] = min;
                }

                dp[i][j][1] =
                        dp[i][j][k] == INF ? INF :
                                dp[i][j][k] + prefixSums[j + 1] - prefixSums[i];
            }
        }

        return dp[0][n - 1][1] >= INF ? -1 : dp[0][n - 1][1];
    }
}