package com.bteshome.algorithms.dynamicProgramming_;

import java.util.*;

public class DPAlgorithms9 {
    /**
     * https://leetcode.com/problems/jump-game
     * NOTE: it can be solved more efficiently using a greedy approach
     * */
    public static boolean jumpGameTopDown(int[] nums) {
        if (nums == null || nums.length == 0)
            return false;
        if (nums.length == 1)
            return true;

        return jumpGameTopDown(nums, 0, new Boolean[nums.length]);
    }

    private static boolean jumpGameTopDown(int[] nums, int pos, Boolean[] dp) {
        if (pos == nums.length - 1)
            return true;

        if (dp[pos] != null)
            return dp[pos];

        boolean can = false;
        int maxJump = Math.min(nums[pos], nums.length - 1 - pos);

        for (int jump = 1; jump <= maxJump; jump++) {
            if (jumpGameTopDown(nums, pos + jump, dp)) {
                can = true;
                break;
            }
        }

        dp[pos] = can;

        return dp[pos];
    }

    public static boolean jumpGameBottomUp(int[] nums) {
        if (nums == null || nums.length == 0)
            return false;
        if (nums.length == 1)
            return true;

        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[n - 1] = true;

        for (int pos = n - 2; pos >= 0; pos--) {
            boolean can = false;
            int maxJump = Math.min(nums[pos], nums.length - 1 - pos);

            for (int jump = 1; jump <= maxJump; jump++) {
                if (dp[pos + jump]) {
                    can = true;
                    break;
                }
            }

            dp[pos] = can;
        }

        return dp[0];
    }

    /**
     * https://leetcode.com/problems/jump-game-ii
     * NOTE: it can be solved more efficiently using a greedy approach
     * */
    public static int jumpGameIITopDown(int[] nums) {
        if (nums == null || nums.length == 0)
            return Integer.MAX_VALUE;

        return jumpGameIITopDown(nums, 0, new Integer[nums.length]);
    }

    private static int jumpGameIITopDown(int[] nums, int pos, Integer[] dp) {
        if (pos == nums.length - 1)
            return 0;

        if (dp[pos] != null)
            return dp[pos];

        int maxJump = Math.min(nums[pos], nums.length - 1 - pos);
        int min = Integer.MAX_VALUE;

        for (int jump = 1; jump <= maxJump; jump++)
            min = Math.min(min, jumpGameIITopDown(nums, pos + jump, dp));

        if (min != Integer.MAX_VALUE)
            min = min + 1;

        dp[pos] = min;

        return dp[pos];
    }

    public static int jumpGameIIBottomUp(int[] nums) {
        if (nums == null || nums.length == 0)
            return Integer.MAX_VALUE;

        int n = nums.length;
        int[] dp = new int[n];

        for (int pos = n - 2; pos >= 0; pos--) {
            int maxJump = Math.min(nums[pos], nums.length - 1 - pos);
            int min = Integer.MAX_VALUE;

            for (int jump = 1; jump <= maxJump; jump++)
                min = Math.min(min, dp[pos + jump]);

            if (min != Integer.MAX_VALUE)
                min = min + 1;

            dp[pos] = min;
        }

        return dp[0];
    }

    /**
     * https://leetcode.com/problems/jump-game-iv
     * NOTE: The purpose of this solution is to remind that DP is not a for cyclic, undirected graphs.
     *       Hence, the solution gives incorrect results.
     *       Use BFS instead.
     * */
    public static int jumpGameIVTopDown(int[] arr) {
        if (arr == null)
            return -1;

        int n = arr.length;
        Map<Integer, List<Integer>> valueIndexMap = new HashMap<>();
        Integer[] dp = new Integer[n];
        boolean[] visiting = new boolean[n];

        for (int i = 0; i < n; i++) {
            int value = arr[i];
            if (!valueIndexMap.containsKey(value))
                valueIndexMap.put(value, new ArrayList<>());
            valueIndexMap.get(value).add(i);
        }

        int min = jumpGameIVTopDown(arr, 0, dp, visiting, valueIndexMap);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private static int jumpGameIVTopDown(int[] arr, int pos, Integer[] dp, boolean[] visiting, Map<Integer, List<Integer>> valueIndexMap) {
        if (pos == arr.length - 1)
            return 0;
        if (pos < 0 || pos >= arr.length || visiting[pos])
            return Integer.MAX_VALUE;
        if (dp[pos] != null)
            return dp[pos];

        visiting[pos] = true;

        int min = jumpGameIVTopDown(arr, pos - 1, dp, visiting, valueIndexMap);
        min = Math.min(min, jumpGameIVTopDown(arr, pos + 1, dp, visiting, valueIndexMap));

        for (int nextIndex : valueIndexMap.get(arr[pos]))
            if (nextIndex != pos)
                min = Math.min(min, jumpGameIVTopDown(arr, nextIndex, dp, visiting, valueIndexMap));

        visiting[pos] = false;
        dp[pos] = (min == Integer.MAX_VALUE) ? min : min + 1;

        return dp[pos];
    }

    /**
     * https://leetcode.com/problems/jump-game-v/description
     * NOTE: there is no need for a visiting state, because we always move to lower values.
     *       Hence, there can't be cycles.
     * */
    public static int jumpGameVTopDown(int[] arr, int d) {
        if (arr == null)
            return 0;
        if (arr.length < 2)
            return arr.length;

        int n = arr.length;
        Integer[] dp = new Integer[n];
        int max = 1;

        for (int start = 0; start < n; start++)
            max = Math.max(max, jumpGameVTopDown(arr, d, start, dp));

        return max;
    }

    private static int jumpGameVTopDown(int[] arr, int d, int pos, Integer[] dp) {
        if (dp[pos] != null)
            return dp[pos];

        int max = 0;

        for (int jump = 1; jump <= Math.min(arr.length - 1 - pos, d); jump++) {
            int nextPos = pos + jump;
            if (arr[nextPos] >= arr[pos])
                break;
            max = Math.max(max, jumpGameVTopDown(arr, d, nextPos, dp));
        }

        for (int jump = 1; jump <= Math.min(pos, d); jump++) {
            int nextPos = pos - jump;
            if (arr[nextPos] >= arr[pos])
                break;
            max = Math.max(max, jumpGameVTopDown(arr, d, nextPos, dp));
        }

        dp[pos] = max + 1;

        return dp[pos];
    }

    /**
     * https://leetcode.com/problems/jump-game-vi/
     * NOTE: this has a quadratic worst case performance, and not accepted by LeetCode.
     *       A sliding window approach can give a linear solution.
     * */
    public static int jumpGameVITopDown(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return Integer.MIN_VALUE;

        return jumpGameVITopDown(nums, k, 0, new Integer[nums.length]);
    }

    private static int jumpGameVITopDown(int[] nums, int k, int pos, Integer[] dp) {
        if (pos == nums.length - 1)
            return nums[nums.length - 1];
        if (dp[pos] != null)
            return dp[pos];

        int max = Integer.MIN_VALUE;

        for (int jump = Math.min(k, nums.length - 1 - pos); jump >= 1; jump--)
            max = Math.max(max, jumpGameVITopDown(nums, k, pos + jump, dp));

        dp[pos] = max == Integer.MIN_VALUE ? max : max + nums[pos];

        return dp[pos];
    }

    /**
     * https://leetcode.com/problems/jump-game-vii
     * NOTE: this has a quadratic worst case performance, and not accepted by LeetCode.
     *       A sliding window approach can give a linear solution.
     * */
    public static boolean jumpGameVIITopDown(String s, int minJump, int maxJump) {
        if (s == null || s.isEmpty())
            return false;
        if (s.charAt(s.length() - 1) != '0')
            return false;
        if (minJump > maxJump)
            return false;
        if (minJump == maxJump && maxJump == 0)
            return false;

        minJump = Math.max(minJump, 1);

        return jumpGameVIITopDown(s, minJump, maxJump, 0, new Boolean[s.length()]);
    }

    private static boolean jumpGameVIITopDown(String s, int minJump, int maxJump, int pos, Boolean[] dp) {
        if (pos == s.length() - 1)
            return true;
        if (s.charAt(pos) != '0')
            return false;
        if (dp[pos] != null)
            return dp[pos];

        boolean can = false;

        for (int nextPos = Math.min(pos + maxJump, s.length() - 1); nextPos >= pos + minJump; nextPos--) {
            if (jumpGameVIITopDown(s, minJump, maxJump, nextPos, dp)) {
                can = true;
                break;
            }
        }

        dp[pos] = can;

        return dp[pos];
    }

    public static boolean jumpGameVIIBottomUp(String s, int minJump, int maxJump) {
        if (s == null || s.isEmpty())
            return false;
        if (s.charAt(s.length() - 1) != '0')
            return false;
        if (minJump > maxJump)
            return false;
        if (minJump == maxJump && maxJump == 0)
            return false;

        minJump = Math.max(minJump, 1);

        int n = s.length();
        boolean[] dp = new boolean[n];
        dp[n - 1] = true;

        for (int pos = n - 2; pos >= 0; pos--) {
            if (s.charAt(pos) != '0') {
                dp[pos] = false;
                continue;
            }

            boolean can = false;

            for (int nextPos = Math.min(pos + maxJump, s.length() - 1); nextPos >= pos + minJump; nextPos--) {
                if (dp[nextPos]) {
                    can = true;
                    break;
                }
            }

            dp[pos] = can;
        }

        return dp[0];
    }
}