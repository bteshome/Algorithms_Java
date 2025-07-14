package com.bteshome.algorithms.backtracking_;

import java.util.Arrays;

public class BacktrackingAlgorithms11 {
    /**
     * https://leetcode.com/problems/fair-distribution-of-cookies
     * */
    public static class CookieDistribution {
        private int minOverall = Integer.MAX_VALUE;

        public int distributeCookies(int[] cookies, int k) {
            if (cookies == null || cookies.length == 0 || k < 1)
                return 0;

            distributeCookies(cookies, 0, new int[k], 0);

            return minOverall;
        }

        private void distributeCookies(int[] cookies, int cookiePos, int[] distribution, int maxInDistribution) {
            if (cookiePos == cookies.length) {
                minOverall = Math.min(minOverall, maxInDistribution);
                return;
            }

            if (maxInDistribution > minOverall)
                return;

            int cookie = cookies[cookiePos];
            for (int i = 0; i < distribution.length; i++) {
                distribution[i] += cookie;
                distributeCookies(cookies, cookiePos + 1, distribution, Math.max(maxInDistribution, distribution[i]));
                distribution[i] -= cookie;
            }
        }
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null)
            return false;

        int n = nums.length;

        if (n == 0 || k < 1)
            return false;
        if (k == 1)
            return true;
        if (k > n)
            return false;

        int[] sums = new int[k];
        long overallSum = 0;

        for (int num : nums)
            overallSum += num;

        // Early pruning 1
        if (overallSum % k != 0)
            return false;
        long targetSum = overallSum / k;

        // Early pruning 2
        Arrays.sort(nums);

        // Early pruning 3
        if (nums[n - 1] > targetSum)
            return false;

        return canPartitionKSubsets(nums, n - 1, sums, overallSum / k);
    }

    private static boolean canPartitionKSubsets(int[] nums, int numPos, int[] sums, long targetSum) {
        if (numPos == -1) {
            for (int sum : sums)
                if (sum != targetSum)
                    return false;
            return true;
        }

        int num = nums[numPos];

        for (int i = 0; i < sums.length; i++) {
            if (sums[i] + num > targetSum)
                continue;
            sums[i] += num;
            if (canPartitionKSubsets(nums, numPos - 1, sums, targetSum))
                return true;
            sums[i] -= num;
        }

        return false;
    }
}
