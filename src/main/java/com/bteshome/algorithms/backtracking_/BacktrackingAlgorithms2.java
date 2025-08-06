package com.bteshome.algorithms.backtracking_;

import java.util.*;

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
            swap(nums, pos, i);
            permute1(nums, permutations, pos + 1);
            swap(nums, pos, i);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static List<List<Integer>> permute1WithBitmask(int[] nums) {
        int n = nums.length;
        int mask = (1 << n) - 1;
        List<List<Integer>> permutations = new ArrayList<>();
        Integer[] permutation = new Integer[n];

        permute1WithBitmask(nums, 0, mask, permutations, permutation);

        return permutations;
    }

    private static void permute1WithBitmask(int[] nums, int pos, int mask, List<List<Integer>> permutations, Integer[] permutation) {
        if (pos == nums.length) {
            permutations.add(Arrays.stream(permutation).toList());
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int numMask = 1 << i;
            if ((mask & numMask) != 0) {
                permutation[pos] = num;
                permute1WithBitmask(nums, pos + 1, mask & ~numMask, permutations, permutation);
            }
        }
    }

    /* https://leetcode.com/problems/permutations-ii */
    public static List<List<Integer>> permute2(int[] nums) {
        if (nums == null || nums.length == 0)
            return List.of(List.of());

        List<List<Integer>> permutations = new ArrayList<>();

        permute2(nums, 0, permutations);

        return permutations;
    }

    private static void permute2(int[] nums, int pos, List<List<Integer>> permutations) {
        if (pos == nums.length) {
            permutations.add(Arrays.stream(nums).boxed().toList());
            return;
        }

        Set<Integer> selected = new HashSet<>();

        for (int i = pos; i < nums.length; i++) {
            if (selected.contains(nums[i]))
                continue;

            selected.add(nums[i]);
            swap(nums, pos, i);
            permute2(nums, pos + 1, permutations);
            swap(nums, pos, i);
        }
    }

    /* https://leetcode.com/problems/number-of-squareful-arrays */
    public static int numSquarefulPerms(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;

        return numSquarefulPerms(nums, 0);
    }

    private static int numSquarefulPerms(int[] nums, int pos) {
        if (pos == nums.length)
            return 1;

        int numPermutations = 0;
        Set<Integer> selectedValues = new HashSet<>();

        for (int i = pos; i < nums.length; i++) {
            if (selectedValues.contains(nums[i]))
                continue;

            if (pos > 0) {
                int sum = nums[i] + nums[pos - 1];
                int sqrt = (int) Math.sqrt(sum);
                if (sqrt * sqrt != sum)
                    continue;
            }

            selectedValues.add(nums[i]);
            swap(nums, pos, i);
            numPermutations += numSquarefulPerms(nums, pos + 1);
            swap(nums, pos, i);
        }

        return numPermutations;
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

        if (pos == candidates.length) {
            return;
        }

        if (target >= candidates[pos]) {
            combination.addLast(candidates[pos]);
            combinationSum(candidates, target - candidates[pos], combinations, pos, combination);
            combination.removeLast();
        }

        combinationSum(candidates, target, combinations, pos + 1, combination);
    }
}
