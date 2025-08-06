package com.bteshome.algorithms.dynamicProgramming_;

import java.util.HashMap;
import java.util.Map;

public class DPAlgorithms68 {
    /**
     * https://leetcode.com/problems/minimum-difference-in-sums-after-removal-of-elements
     * NOTE:
     *  - both solutions are here are too slow and not accepted
     *  - for this problem, Greedy would be better than DP
     * */
    public static class MinimumDifferenceBacktrackingUsingStringKey {
        public static long minimumDifference(int[] nums) {
            if (nums == null || nums.length == 0 || nums.length % 3 != 0)
                return 0;

            return minimumDifference(nums, nums.length / 3, 0, 0, 0, 0, 0, new HashMap<>());
        }

        private static long minimumDifference(int[] nums, int n, int pos, int removedSize, int leftSize, int rightSize, long diff, Map<String, Long> dp) {
            if (pos == nums.length)
                return diff;

            String key = "%s,%s,%s,%s".formatted(pos, removedSize, leftSize, diff);

            if (dp.containsKey(key))
                return dp.get(key);

            long min = Long.MAX_VALUE;

            if (removedSize < n)
                min = Math.min(min, minimumDifference(nums, n, pos + 1, removedSize + 1, leftSize, rightSize, diff, dp));
            if (leftSize < n)
                min = Math.min(min, minimumDifference(nums, n, pos + 1, removedSize, leftSize + 1, rightSize, diff + nums[pos], dp));
            if (leftSize == n && rightSize < n)
                min = Math.min(min, minimumDifference(nums, n, pos + 1, removedSize, leftSize, rightSize + 1, diff - nums[pos], dp));

            dp.put(key, min);

            return dp.get(key);
        }
    }

    public static class MinimumDifferenceBacktrackingUsingEncodedKey {
        private static final int DIFF_OFFSET = 1_000_000_000;

        public static long minimumDifference(int[] nums) {
            if (nums == null || nums.length == 0 || nums.length % 3 != 0)
                return 0;

            return minimumDifference(nums, nums.length / 3, 0, 0, 0, 0, 0, new HashMap<>());
        }

        private static long minimumDifference(int[] nums, int n, int pos, int removedSize, int leftSize, int rightSize, long diff, Map<Long, Long> dp) {
            if (pos == nums.length)
                return diff;

            long key = encodeState(pos, removedSize, leftSize, diff);

            if (dp.containsKey(key))
                return dp.get(key);

            long min = Long.MAX_VALUE;

            if (removedSize < n)
                min = Math.min(min, minimumDifference(nums, n, pos + 1, removedSize + 1, leftSize, rightSize, diff, dp));
            if (leftSize < n)
                min = Math.min(min, minimumDifference(nums, n, pos + 1, removedSize, leftSize + 1, rightSize, diff + nums[pos], dp));
            if (leftSize == n && rightSize < n)
                min = Math.min(min, minimumDifference(nums, n, pos + 1, removedSize, leftSize, rightSize + 1, diff - nums[pos], dp));

            dp.put(key, min);

            return dp.get(key);
        }

        private static long encodeState(int pos, int removed, int left, long diff) {
            long shiftedDiff = diff + DIFF_OFFSET;
            return (((long) pos * 40 + removed) * 40 + left) * 2_000_000_001L + shiftedDiff;
        }
    }
}