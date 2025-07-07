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

    /* https://leetcode.com/problems/maximum-number-of-achievable-transfer-requests */
    public static int maximumRequests(int n, int[][] requests) {
        if (n < 1 || requests == null || requests.length < 1)
            return 0;

        return maximumRequests(requests, 0, new int[n], 0);
    }

    private static int maximumRequests(int[][] requests, int pos, int[] state, int count) {
        if (pos == requests.length) {
            for (int j : state)
                if (j != 0)
                    return 0;
            return count;
        }

        int from = requests[pos][0];
        int to = requests[pos][1];
        state[from]--;
        state[to]++;
        int completed = maximumRequests(requests, pos + 1, state, count + 1);
        state[from]++;
        state[to]--;
        completed = Math.max(completed, maximumRequests(requests, pos + 1, state, count));

        return completed;
    }
}
