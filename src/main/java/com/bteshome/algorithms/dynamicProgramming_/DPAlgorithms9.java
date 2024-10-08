package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms9 {
    /**
     * https://leetcode.com/problems/jump-game/editorial/?envType=problem-list-v2&envId=dynamic-programming&difficulty=MEDIUM
     * */
    public static boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        return canJump(nums, nums.length - 1, new Boolean[nums.length]);
    }

    private static boolean canJump(int[] nums, int pos, Boolean[] cache) {
        if (pos == 0) {
            return true;
        }

        if (cache[pos] == null) {
            boolean can = false;

            for (int i = 0; i < pos; i++) {
                if (nums[i] >= pos - i && canJump(nums, i, cache)) {
                    can = true;
                    break;
                }
            }

            cache[pos] = can;
        }

        return cache[pos];
    }

    /**
     * https://leetcode.com/problems/jump-game-ii/?envType=problem-list-v2&envId=dynamic-programming&difficulty=MEDIUM
     * */
    public static int jumpRecursive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        return jumpRecursive(nums, nums.length - 1, new Integer[nums.length]);
    }

    private static int jumpRecursive(int[] nums, int pos, Integer[] cache) {
        if (pos == 0) {
            return 0;
        }

        if (cache[pos] == null) {
            int minJumps = Integer.MAX_VALUE;

            for (int i = 0; i < pos; i++) {
                if (nums[i] >= pos - i) {
                    var prevJumps = jumpRecursive(nums, i, cache);
                    if (prevJumps != -1) {
                        minJumps = Math.min(minJumps, 1 + prevJumps);
                    }
                }
            }

            cache[pos] = minJumps == Integer.MAX_VALUE ? -1 : minJumps;
        }

        return cache[pos];
    }

    public static int jumpIterative(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        var cache = new int[nums.length];

        for (int pos = 1; pos < nums.length ; pos++) {
            int minJumps = Integer.MAX_VALUE;

            for (int j = 0; j < pos; j++) {
                if (nums[j] >= pos - j) {
                    if (cache[j] != -1) {
                        minJumps = Math.min(minJumps, 1 + cache[j]);
                    }
                }
            }

            cache[pos] = minJumps == Integer.MAX_VALUE ? -1 : minJumps;
        }

        return cache[nums.length - 1];
    }

    //TODO
    public static int jumpBetterTODO(int[] nums) {
        return 0;
    }
}
