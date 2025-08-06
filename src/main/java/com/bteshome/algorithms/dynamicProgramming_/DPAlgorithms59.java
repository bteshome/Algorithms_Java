package com.bteshome.algorithms.dynamicProgramming_;

import java.util.*;

public class DPAlgorithms59 {
    /**
     * https://leetcode.com/problems/beautiful-arrangement
     * NOTE: - there is also a pure backtracking solution for this which has been accepted.
     *       - the memoization key is just mask, instead of mask + pos,
     *         because pos can be derived from the mask
     */
    public int countArrangement(int n) {
        int mask = (1 << n) - 1;
        return countArrangement(n, 0, mask, new HashMap<>());
    }

    private int countArrangement(int n, int pos, int mask, Map<Integer, Integer> dp) {
        if (pos == n)
            return 1;
        if (dp.containsKey(mask))
            return dp.get(mask);

        int count = 0;

        for (int i = 0; i < n; i++) {
            int num = i + 1;
            int numMask = 1 << i;
            if ((mask & numMask) != 0) {
                int posPlus1 = pos + 1;
                if ((num % posPlus1 == 0) || (posPlus1 % num == 0))
                    count += countArrangement(n, pos + 1, mask & ~numMask, dp);
            }
        }

        dp.put(mask, count);

        return count;
    }

    /**
     * https://leetcode.com/problems/matchsticks-to-square
     * NOTE: - this problem is https://leetcode.com/problems/partition-to-k-equal-sum-subsets
     *         with k=4 worded differently
     *       - there is also a pure backtracking solution, which actually runs a bit faster
     *       - the memoization key is only the mask instead of mask + sidePos + currentSum.
     *         Why? because the partial sum and which side we are currently
     *         build can be deduced implicitly from the mask
     * */
    public static boolean makesquare(int[] matchsticks) {
        if (matchsticks == null || matchsticks.length < 4)
            return false;

        // pruning 1
        int totalSum = Arrays.stream(matchsticks).sum();
        if (totalSum % 4 != 0)
            return false;

        // optimization
        Arrays.sort(matchsticks);
        reverse(matchsticks);

        // pruning 2
        int targetSum = totalSum / 4;
        if (matchsticks[0] > targetSum)
            return false;

        int mask = (1 << matchsticks.length) - 1;
        Map<Integer, Boolean> dp = new HashMap<>();

        return makesquare(matchsticks, mask, 0, 0, targetSum, 0, dp);
    }

    private static boolean makesquare(int[] matchsticks, int mask, int sidePos, int stickPos, int targetSum, int currentSum, Map<Integer, Boolean> dp) {
        if (sidePos == 4)
            return mask == 0;
        if (currentSum == targetSum)
            return makesquare(matchsticks, mask, sidePos + 1, 0, targetSum, 0, dp);
        if (sidePos == matchsticks.length)
            return false;
        if (currentSum == 0 && dp.containsKey(mask))
            return dp.get(mask);

        for (int i = stickPos; i < matchsticks.length; i++) {
            int stick = matchsticks[i];
            int stickMask = 1 << i;

            if ((mask & stickMask) == 0)
                continue;
            // pruning 3
            if (currentSum + stick > targetSum)
                continue;
            // pruning 4
            if (i > 0 && matchsticks[i] == matchsticks[i - 1] && (mask & (1 << (i - 1))) != 0)
                continue;

            if (makesquare(matchsticks, mask & ~stickMask, sidePos, i + 1, targetSum, currentSum + stick, dp)) {
                if (currentSum == 0)
                    dp.put(mask, true);
                return true;
            }

            // pruning 4
            if (currentSum == 0)
                break;
        }

        if (currentSum == 0)
            dp.put(mask, false);

        return false;
    }

    private static void reverse(int[] arr) {
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}