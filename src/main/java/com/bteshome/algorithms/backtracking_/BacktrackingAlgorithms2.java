package com.bteshome.algorithms.backtracking_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BacktrackingAlgorithms2 {
    /**
     * leetcode https://leetcode.com/problems/permutations/submissions/720114385/?envType=study-plan-v2&envId=top-interview-150
     * */
    public static List<List<Integer>> permute1(int[] nums) {
        var permutations = new ArrayList<List<Integer>>();

        if ((nums != null) && (nums.length > 0)) {
            permute1(nums, permutations, 0);
        }

        return permutations;
    }

    private static void permute1(int[] nums, ArrayList<List<Integer>> permutations, int pos) {
        if (pos == nums.length) {
            permutations.add(Arrays.stream(nums).boxed().toList());
            return;
        }

        for (int i = pos; i < nums.length; i++) {
            permute1Swap(nums, pos, i);
            permute1(nums, permutations, pos + 1);
            permute1Swap(nums, pos, i);
        }
    }

    private static void permute1Swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static List<List<Integer>> permute2(int[] nums) {
        var permutations = new ArrayList<List<Integer>>();

        if ((nums != null) && (nums.length > 0)) {
            permute2(nums, permutations, 0, new boolean[nums.length], new ArrayList<Integer>(nums.length));
        }

        return permutations;
    }

    private static void permute2(int[] nums, ArrayList<List<Integer>> permutations, int pos, boolean[] selected, ArrayList<Integer> permutation) {
        if (pos == nums.length) {
            permutations.add(permutation.stream().toList());
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!selected[i]) {
                permutation.add(nums[i]);
                selected[i] = true;
                permute2(nums, permutations, pos + 1, selected, permutation);
                permutation.removeLast();
                selected[i] = false;
            }
        }
    }

    /**
     * leetcode https://leetcode.com/problems/combinations/?envType=study-plan-v2&envId=top-interview-150
     * */
    public static List<List<Integer>> combine(int n, int k) {
        var combinations = new ArrayList<List<Integer>>();

        if (n > 0 && k > 0 && k <= n) {
            var combination = new ArrayList<Integer>(k);
            combine(n, k, 1, combinations, combination);
        }

        return combinations;
    }

    private static void combine(int n, int k, int pos, ArrayList<List<Integer>> combinations, ArrayList<Integer> combination) {
        if (combination.size() == k) {
            combinations.add(combination.stream().toList());
            return;
        }

        if (pos > n) {
            return;
        }

        combination.addLast(pos);
        combine(n, k, pos + 1, combinations, combination);
        combination.removeLast();

        combine(n, k, pos + 1, combinations, combination);
    }

    /**
     * leetcode https://leetcode.com/problems/combination-sum/description/?envType=study-plan-v2&envId=top-interview-150
     * */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        var combinations = new ArrayList<List<Integer>>();

        if (candidates != null && candidates.length > 0) {
            combinationSum(candidates, target, combinations, 0, new ArrayList<Integer>());
        }

        return combinations;
    }

    private static void combinationSum(int[] candidates, int target, List<List<Integer>> combinations, int pos, List<Integer> combination) {
        if (target == 0) {
            combinations.add(combination.stream().toList());
            return;
        }

        if (target < 0) {
            return;
        }

        if (pos == candidates.length) {
            return;
        }

        combination.addLast(candidates[pos]);
        combinationSum(candidates, target - candidates[pos], combinations, pos, combination);
        combination.removeLast();
        combinationSum(candidates, target, combinations, pos + 1, combination);
    }
}
