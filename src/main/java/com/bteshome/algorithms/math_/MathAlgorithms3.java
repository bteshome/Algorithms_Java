package com.bteshome.algorithms.math_;

import java.util.ArrayList;
import java.util.List;

public class MathAlgorithms3 {
    /**
     * https://leetcode.com/problems/subsets
     * NOTE: checkout the backtracking solution as well, which is more general.
     *       This bitmask solution is good only for a small array size
     * */
    public static List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0)
            return List.of(List.of());

        int n = nums.length;
        int numSubsets = 1 << n;
        List<List<Integer>> allSubsets = new ArrayList<>();

        for (int mask = 0; mask < numSubsets; mask++) {
            List<Integer> subset = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (((1 << i) & mask) != 0)
                    subset.add(nums[i]);
            }
            allSubsets.add(subset);
        }

        return allSubsets;
    }
}
