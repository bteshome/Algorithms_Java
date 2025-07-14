package com.bteshome.algorithms.dynamicProgramming_;

import java.util.Arrays;

public class DPAlgorithms30 {
    /* https://leetcode.com/problems/maximum-profit-in-job-scheduling */
    public static int jobSchedulingTopDown(int[] startTime, int[] endTime, int[] profit) {
        if (startTime == null || endTime == null || profit == null)
            return 0;
        if (startTime.length != endTime.length || startTime.length != profit.length)
            return 0;

        int[][] jobs = new int[startTime.length][3];
        for (int i = 0; i < jobs.length; i++)
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};

        Arrays.sort(jobs, (a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            return a[0] - b[0];
        });

        return jobSchedulingTopDown(jobs, 0, new Integer[jobs.length]);
    }

    private static int jobSchedulingTopDown(int[][] jobs, int pos, Integer[] dp) {
        if (pos == jobs.length)
            return 0;

        if (dp[pos] == null) {
            int nextEvent = findNextJob(jobs, pos);
            int skip = jobSchedulingTopDown(jobs, pos + 1, dp);
            int take = jobs[pos][2] + jobSchedulingTopDown(jobs, nextEvent, dp);
            dp[pos] = Math.max(skip, take);
        }

        return dp[pos];
    }

    public static int jobSchedulingBottomUp(int[] startTime, int[] endTime, int[] profit) {
        if (startTime == null || endTime == null || profit == null)
            return 0;
        if (startTime.length != endTime.length || startTime.length != profit.length)
            return 0;

        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++)
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};

        Arrays.sort(jobs, (a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            return a[0] - b[0];
        });

        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            int nextEvent = findNextJob(jobs, i);
            int skip = dp[i + 1];
            int take = jobs[i][2] + dp[nextEvent];
            dp[i] = Math.max(skip, take);
        }

        return dp[0];
    }

    private static int findNextJob(int[][] events, int pos) {
        int left = pos + 1;
        int right = events.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (events[mid][0] >= events[pos][1])
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }
}
