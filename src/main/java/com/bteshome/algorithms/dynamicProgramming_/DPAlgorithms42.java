package com.bteshome.algorithms.dynamicProgramming_;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class DPAlgorithms42 {
    /* https://leetcode.com/problems/longest-arithmetic-subsequence */
    public static int longestArithSeqLength(int[] nums) {
        if (nums == null)
            return 0;
        if (nums.length <= 2)
            return nums.length;

        int n = nums.length;
        int maxLength = 2;
        Map<Integer, Integer>[] dp = new HashMap[n];
        for (int i = 0; i < n; i++)
            dp[i] = new HashMap<>();

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int diff = nums[i] - nums[j];
                int newLength = dp[j].getOrDefault(diff, 1) + 1;
                int existingLength = dp[i].getOrDefault(diff, 0);
                dp[i].put(diff, Math.max(existingLength, newLength));
                maxLength = Math.max(maxLength, newLength);
            }
        }

        return maxLength;
    }

    /**
     * TODO
     * https://leetcode.com/problems/russian-doll-envelopes
     * NOTE: this solution is correct, but not accepted by LeetCode as it's quadratic.
     *       There is a way to optimize it using binary search.
     * */
    public static int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null)
            return 0;
        if (envelopes.length <= 1)
            return envelopes.length;

        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0])
                return b[1] - a[1];
            return a[0] - b[0];
        });

        int n = envelopes.length;
        int maxLength = 1;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++)
                if (envelopes[j][0] < envelopes[i][0] &&
                    envelopes[j][1] < envelopes[i][1])
                    max = Math.max(max, dp[j]);
            dp[i] = max + 1;
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }

    /**
     * TODO
     * https://leetcode.com/problems/find-the-longest-valid-obstacle-course-at-each-position
     * NOTE: this solution is correct, but not accepted by LeetCode as it's quadratic.
     *       There is a way to optimize it using binary search.
     * */
    public static int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        if (obstacles == null)
            return null;
        if (obstacles.length <= 1)
            return new int[obstacles.length];

        int n = obstacles.length;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++)
                if (obstacles[j] <= obstacles[i])
                    max = Math.max(max, dp[j]);
            dp[i] = max + 1;
        }

        return dp;
    }
}