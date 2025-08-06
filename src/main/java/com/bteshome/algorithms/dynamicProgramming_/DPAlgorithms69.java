package com.bteshome.algorithms.dynamicProgramming_;

import java.util.HashMap;
import java.util.Map;

public class DPAlgorithms69 {
    /**
     * https://leetcode.com/problems/maximum-score-from-performing-multiplication-operations/
     * NOTE:
     *  - all three are accepted
     *  - MaximumScoreTopDownUsingArray2D and MaximumScoreBottomUp2D are the fatest
     *  - MaximumScoreTopDownUsingMap2D is a bit slower
     *  - MaximumScoreTopDownUsingMap3D is even slower
     * */
    public static class MaximumScoreTopDownUsingMap3D {
        public static int maximumScore(int[] nums, int[] multipliers) {
            if (nums == null || multipliers == null)
                return 0;
            if (nums.length < multipliers.length)
                return 0;

            return maximumScore(nums, multipliers, 0, nums.length - 1, 0, new HashMap<>());
        }

        private static int maximumScore(int[] nums, int[] multipliers, int start, int end, int operation, Map<String, Integer> dp) {
            if (operation == multipliers.length)
                return 0;
            if (start == end)
                return nums[start] * multipliers[operation];

            String key = "%s,%s,%s".formatted(start, end, operation);

            if (!dp.containsKey(key)) {
                int left = nums[start] * multipliers[operation] + maximumScore(nums, multipliers, start + 1, end, operation + 1, dp);
                int right = nums[end] * multipliers[operation] + maximumScore(nums, multipliers, start, end - 1, operation + 1, dp);
                dp.put(key, Math.max(left, right));
            }

            return dp.get(key);
        }
    }

    public static class MaximumScoreTopDownUsingMap2D {
        public static int maximumScore(int[] nums, int[] multipliers) {
            if (nums == null || multipliers == null)
                return 0;
            if (nums.length < multipliers.length)
                return 0;

            return maximumScore(nums, multipliers, 0, nums.length - 1, 0, new HashMap<>());
        }

        private static int maximumScore(int[] nums, int[] multipliers, int start, int end, int operation, Map<String, Integer> dp) {
            if (operation == multipliers.length)
                return 0;
            if (start == end)
                return nums[start] * multipliers[operation];

            String key = "%s,%s".formatted(start, operation);

            if (!dp.containsKey(key)) {
                int left = nums[start] * multipliers[operation] + maximumScore(nums, multipliers, start + 1, end, operation + 1, dp);
                int right = nums[end] * multipliers[operation] + maximumScore(nums, multipliers, start, end - 1, operation + 1, dp);
                dp.put(key, Math.max(left, right));
            }

            return dp.get(key);
        }
    }

    public static class MaximumScoreTopDownUsingArray2D {
        public static int maximumScore(int[] nums, int[] multipliers) {
            if (nums == null || multipliers == null)
                return 0;
            if (nums.length < multipliers.length)
                return 0;

            int n = nums.length;
            int m = multipliers.length;
            Integer[][] dp = new Integer[n][m];

            return maximumScore(nums, multipliers, 0, nums.length - 1, 0, dp);
        }

        private static int maximumScore(int[] nums, int[] multipliers, int start, int end, int operation, Integer[][] dp) {
            if (operation == multipliers.length)
                return 0;
            if (start == end)
                return nums[start] * multipliers[operation];
            if (dp[start][operation] != null)
                return dp[start][operation];

            int left = nums[start] * multipliers[operation] + maximumScore(nums, multipliers, start + 1, end, operation + 1, dp);
            int right = nums[end] * multipliers[operation] + maximumScore(nums, multipliers, start, end - 1, operation + 1, dp);
            dp[start][operation] = Math.max(left, right);

            return dp[start][operation];
        }
    }

    public static class MaximumScoreBottomUp2D {
        public static int maximumScore(int[] nums, int[] multipliers) {
            if (nums == null || multipliers == null)
                return 0;
            if (nums.length < multipliers.length)
                return 0;

            int n = nums.length;
            int m = multipliers.length;
            int[][] dp = new int[n][m + 1];

            for (int operation = m - 1; operation >= 0; operation--) {
                for (int start = 0; start <= operation; start++) {
                    int end = start + (n - operation) - 1;
                    if (start == end) {
                        dp[start][operation] = nums[start] * multipliers[operation];
                    } else {
                        int left = nums[start] * multipliers[operation] + dp[start + 1][operation + 1];
                        int right = nums[end] * multipliers[operation] + dp[start][operation + 1];
                        dp[start][operation] = Math.max(left, right);
                    }
                }
            }

            return dp[0][0];
        }
    }
}