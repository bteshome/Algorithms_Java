package com.bteshome.algorithms.dynamicProgramming_;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DPAlgorithms21 {
    /**
     * https://leetcode.com/problems/minimum-cost-to-cut-a-stick/
     * */
    public static int minCost(int n, int[] cuts) {
        if (n < 2)
            return 0;
        if (cuts == null)
            return 0;

        Arrays.sort(cuts);

        return minCost(cuts, 0, n, 0, cuts.length - 1, new HashMap<>());
    }

    private static int minCost(int[] cuts, int stickStart, int stickEnd, int cutStart, int cutEnd, Map<String, Integer> cache) {
        if (stickStart == stickEnd - 1)
            return 0;
        if (cutStart > cutEnd)
            return 0;

        String key = "%s,%s".formatted(stickStart, stickEnd);

        if (!cache.containsKey(key)) {
            int min = Integer.MAX_VALUE;

            for (int i = cutStart; i <= cutEnd; i++) {
                int left = minCost(cuts, stickStart, cuts[i], cutStart, i - 1, cache);
                int right = minCost(cuts, cuts[i], stickEnd, i + 1, cutEnd, cache);
                min = Math.min(min, left + right);
            }

            if (min == Integer.MAX_VALUE)
                min = 0;
            else
                min = stickEnd - stickStart + min;

            cache.put(key, min);
        }

        return cache.get(key);
    }

    /**
     * https://leetcode.com/problems/delete-and-earn/description/
     * */
    public static int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        Map<Integer, Integer> counts = new HashMap<>();

        for (int num : nums)
            counts.put(num, counts.getOrDefault(num, 0) + 1);

        nums = toArray(counts);

        if (nums.length == 1)
            return nums[0] * counts.get(nums[0]);

        int[] dp = new int[nums.length];
        dp[0] = nums[0] * counts.get(nums[0]);
        dp[1] = nums[1] == nums[0] + 1 ?
                Math.max(dp[0], nums[1] * counts.get(nums[1])) :
                dp[0] + nums[1] * counts.get(nums[1]);

        for (int i = 2; i < nums.length; i++) {
            int max = 0;
            int points = nums[i] * counts.get(nums[i]);
            dp[i] = nums[i] == nums[i - 1] + 1 ?
                    Math.max(dp[i - 1], dp[i - 2] + points) :
                    dp[i - 1] + points;
        }

        return dp[nums.length - 1];
    }

    private static int[] toArray(Map<Integer, Integer> counts) {
        int[] nums = new int[counts.size()];
        int i = 0;

        for (int num : counts.keySet()) {
            nums[i] = num;
            i++;
        }

        Arrays.sort(nums);

        return nums;
    }
}