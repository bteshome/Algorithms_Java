package com.bteshome.algorithms.dynamicProgramming_;

import java.util.Arrays;

public class DPAlgorithms29 {
    /* https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii */
    public static int maxValueTopDown(int[][] events, int k) {
        if (events == null)
            return 0;

        Arrays.sort(events, (a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            return a[0] - b[0];
        });

        return maxValueTopDown(events, k, 0, new Integer[events.length][k + 1]);
    }

    private static int maxValueTopDown(int[][] events, int k, int pos, Integer[][] dp) {
        if (k <= 0 || pos == events.length)
            return 0;

        if (dp[pos][k] == null) {
            int nextEvent = findNextEvent(events, pos);
            int skip = maxValueTopDown(events, k, pos + 1, dp);
            int take = events[pos][2] + maxValueTopDown(events, k - 1, nextEvent, dp);
            dp[pos][k] = Math.max(take, skip);
        }

        return dp[pos][k];
    }

    public static int maxValueBottomUp(int[][] events, int k) {
        if (events == null)
            return 0;

        Arrays.sort(events, (a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            return a[0] - b[0];
        });

        int n = events.length;
        int[][] dp = new int[n + 1][k + 1];

        for (int i = n - 1; i >= 0; i--) {
            int nextEvent = findNextEvent(events, i);
            for (int j = 1; j <= k; j++) {
                int skip = dp[i + 1][j];
                int take = events[i][2] + dp[nextEvent][j - 1];
                dp[i][j] = Math.max(take, skip);
            }
        }

        return dp[0][k];
    }

    private static int findNextEvent(int[][] events, int pos) {
        int left = pos + 1;
        int right = events.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (events[mid][0] > events[pos][1])
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }
}