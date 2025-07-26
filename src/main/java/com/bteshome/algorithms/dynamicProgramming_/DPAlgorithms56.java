package com.bteshome.algorithms.dynamicProgramming_;

import java.util.ArrayList;
import java.util.List;

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


}