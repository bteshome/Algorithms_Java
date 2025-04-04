package com.bteshome.algorithms.backtracking_;

import java.util.ArrayList;
import java.util.List;

public class BacktrackingAlgorithms10 {
    public static List<List<Integer>> combinationSum(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return List.of();

        List<List<Integer>> result = new ArrayList<>();

        combinationSum(0, nums, target, result, new ArrayList<Integer>());

        return result;
    }

    private static void combinationSum(int pos, int[] nums, int target, List<List<Integer>> result, List<Integer> c) {
        if (target == 0) {
            result.add(c.stream().toList());
            return;
        }

        if (target < 0)
            return;

        if (pos == nums.length)
            return;

        c.add(nums[pos]);
        combinationSum(pos, nums, target - nums[pos], result, c);
        c.removeLast();
        combinationSum(pos + 1, nums, target, result, c);
    }
}
