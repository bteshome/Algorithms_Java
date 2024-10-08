package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms4 {
    /**
     * https://leetcode.com/problems/house-robber/?envType=study-plan-v2&envId=top-interview-150
     * */
    public static int robRecursive(int[] nums) {
        if (nums == null) {
            return 0;
        }

        return robRecursive(nums, 0, new Integer[nums.length]);
    }

    private static int robRecursive(int[] nums, int pos, Integer[] cache) {
        if (pos >= nums.length) {
            return 0;
        }

        if (cache[pos] == null) {
            cache[pos] = Math.max(nums[pos] + robRecursive(nums, pos + 2, cache), robRecursive(nums, pos + 1, cache));
        }

        return cache[pos];
    }

    /**
     * https://leetcode.com/problems/house-robber-ii/description/
     * */
    public static int robIIRecursive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        var startFrom1 = nums[0] + robIIRecursive(nums, 2, new Integer[nums.length], true);
        var startFrom2 = robIIRecursive(nums, 1, new Integer[nums.length], false);
        return Math.max(startFrom1, startFrom2);
    }

    private static int robIIRecursive(int[] nums, int pos, Integer[] cache, boolean firstWasRobbed) {
        if (pos >= nums.length) {
            return 0;
        }

        if (cache[pos] == null) {
            if (pos == nums.length - 1) {
                if (firstWasRobbed) {
                    cache[pos] = 0;
                } else {
                    cache[pos] = nums[pos];
                }
            } else {
                cache[pos] = Math.max(nums[pos] + robIIRecursive(nums, pos + 2, cache, firstWasRobbed), robIIRecursive(nums, pos + 1, cache, firstWasRobbed));
            }
        }

        return cache[pos];
    }
}
