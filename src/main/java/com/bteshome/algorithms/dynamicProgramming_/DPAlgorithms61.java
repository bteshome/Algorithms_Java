package com.bteshome.algorithms.dynamicProgramming_;

import java.util.HashMap;
import java.util.Map;

public class DPAlgorithms61 {
    /**
     * https://leetcode.com/problems/maximize-score-after-n-operations
     * NOTE: the difference between the 1D solutions and 2D solutions is that
     *       in the 1D solutions, operation is removed from the state key,
     *       because it can be extracted from the mask.
     * */
    public static int maxScore1DWithArray(int[] nums) {
        if (nums == null || nums.length < 2 || nums.length % 2 != 0)
            return 0;

        int mask = (1 << nums.length) - 1;
        int n = nums.length / 2;
        Integer[] dp = new Integer[mask + 1];
        GCDCalculator gcdCalculator = new GCDCalculator();

        return maxScore1DWithArray(nums, n, 1, mask, dp, gcdCalculator);
    }

    private static int maxScore1DWithArray(int[] nums, int n, int operation, int mask, Integer[] dp, GCDCalculator gcdCalculator) {
        if (operation == n + 1)
            return 0;
        if (dp[mask] != null)
            return dp[mask];

        int max = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            int firstNumberMask = 1 << i;
            if ((mask & firstNumberMask) != 0) {
                for (int j = i + 1; j < nums.length; j++) {
                    int secondNumberMask = 1 << j;
                    if ((mask & secondNumberMask) != 0) {
                        int firstNumber = nums[i];
                        int secondNumber = nums[j];
                        int gcd = gcdCalculator.get(firstNumber, secondNumber);
                        int newMask = (mask & ~firstNumberMask) & ~secondNumberMask;
                        int next = maxScore1DWithArray(nums, n, operation + 1, newMask, dp, gcdCalculator);
                        max = Math.max(max, operation * gcd + next);
                    }
                }
            }
        }

        dp[mask] = max;

        return max;
    }

    private static class GCDCalculator {
        private record Pair(int a, int b){}
        private Map<Pair, Integer> dp = new HashMap<>();

        public int get(int a, int b) {
            if (a < b) {
                int temp = a;
                a = b;
                b = temp;
            }

            if (b == 0)
                return a;

            Pair key = new Pair(a, b);

            if (dp.containsKey(key))
                return dp.get(key);

            int gcd = get(a % b, b);
            dp.put(key, gcd);

            return gcd;
        }
    }

    public static int maxScore1DWithMap(int[] nums) {
        if (nums == null || nums.length < 2 || nums.length % 2 != 0)
            return 0;

        int mask = (1 << nums.length) - 1;
        int n = nums.length / 2;
        Map<Integer, Integer> dp = new HashMap<>();
        GCDCalculator gcdCalculator = new GCDCalculator();

        return maxScore1DWithMap(nums, n, 1, mask, dp, gcdCalculator);
    }

    private static int maxScore1DWithMap(int[] nums, int n, int operation, int mask, Map<Integer, Integer> dp, GCDCalculator gcdCalculator) {
        if (operation == n + 1)
            return 0;
        if (dp.containsKey(mask))
            return dp.get(mask);

        int max = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            int firstNumberMask = 1 << i;
            if ((mask & firstNumberMask) != 0) {
                for (int j = i + 1; j < nums.length; j++) {
                    int secondNumberMask = 1 << j;
                    if ((mask & secondNumberMask) != 0) {
                        int firstNumber = nums[i];
                        int secondNumber = nums[j];
                        int gcd = gcdCalculator.get(firstNumber, secondNumber);
                        int newMask = (mask & ~firstNumberMask) & ~secondNumberMask;
                        int next = maxScore1DWithMap(nums, n, operation + 1, newMask, dp, gcdCalculator);
                        max = Math.max(max, operation * gcd + next);
                    }
                }
            }
        }

        dp.put(mask, max);

        return max;
    }

    public static int maxScore2DWithMap(int[] nums) {
        if (nums == null || nums.length < 2 || nums.length % 2 != 0)
            return 0;

        int mask = (1 << nums.length) - 1;
        int n = nums.length / 2;
        Map<Integer, Map<Integer, Integer>> dp = new HashMap<>();
        GCDCalculator gcdCalculator = new GCDCalculator();

        return maxScore2DWithMap(nums, n, 1, mask, dp, gcdCalculator);
    }

    private static int maxScore2DWithMap(int[] nums, int n, int operation, int mask, Map<Integer, Map<Integer, Integer>> dp, GCDCalculator gcdCalculator) {
        if (operation == n + 1)
            return 0;

        if (!dp.containsKey(operation))
            dp.put(operation, new HashMap<>());
        if (dp.get(operation).containsKey(mask))
            return dp.get(operation).get(mask);

        int max = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            int firstNumberMask = 1 << i;
            if ((mask & firstNumberMask) != 0) {
                for (int j = i + 1; j < nums.length; j++) {
                    int secondNumberMask = 1 << j;
                    if ((mask & secondNumberMask) != 0) {
                        int firstNumber = nums[i];
                        int secondNumber = nums[j];
                        int gcd = gcdCalculator.get(firstNumber, secondNumber);
                        int newMask = (mask & ~firstNumberMask) & ~secondNumberMask;
                        int next = maxScore2DWithMap(nums, n, operation + 1, newMask, dp, gcdCalculator);
                        max = Math.max(max, operation * gcd + next);
                    }
                }
            }
        }

        dp.get(operation).put(mask, max);

        return max;
    }

    public static int maxScore2DWithArray(int[] nums) {
        if (nums == null || nums.length < 2 || nums.length % 2 != 0)
            return 0;

        int mask = (1 << nums.length) - 1;
        int n = nums.length / 2;
        Integer[][] dp = new Integer[n + 1][mask + 1];
        GCDCalculator gcdCalculator = new GCDCalculator();

        return maxScore2DWithArray(nums, n, 1, mask, dp, gcdCalculator);
    }

    private static int maxScore2DWithArray(int[] nums, int n, int operation, int mask, Integer[][] dp, GCDCalculator gcdCalculator) {
        if (operation == n + 1)
            return 0;
        if (dp[operation][mask] != null)
            return dp[operation][mask];

        int max = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            int firstNumberMask = 1 << i;
            if ((mask & firstNumberMask) != 0) {
                for (int j = i + 1; j < nums.length; j++) {
                    int secondNumberMask = 1 << j;
                    if ((mask & secondNumberMask) != 0) {
                        int firstNumber = nums[i];
                        int secondNumber = nums[j];
                        int gcd = gcdCalculator.get(firstNumber, secondNumber);
                        int newMask = (mask & ~firstNumberMask) & ~secondNumberMask;
                        int next = maxScore2DWithArray(nums, n, operation + 1, newMask, dp, gcdCalculator);
                        max = Math.max(max, operation * gcd + next);
                    }
                }
            }
        }

        dp[operation][mask] = max;

        return max;
    }
}