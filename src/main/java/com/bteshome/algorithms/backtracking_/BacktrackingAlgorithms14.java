package com.bteshome.algorithms.backtracking_;

import java.util.Arrays;

public class BacktrackingAlgorithms14 {
    /**
     * https://leetcode.com/problems/matchsticks-to-square
     * NOTE: - this problem is https://leetcode.com/problems/partition-to-k-equal-sum-subsets
     *         with k=4 worded differently
     *       - there is also a DP version of this solution, but this version is a bit faster
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

        int n = matchsticks.length;
        int mask = (1 << n) - 1;

        return makesquare(matchsticks, mask, 0, 0, targetSum, 0);
    }

    private static boolean makesquare(int[] matchsticks, int mask, int sidePos, int stickPos, int targetSum, int currentSum) {
        if (sidePos == 4)
            return mask == 0;
        if (currentSum == targetSum)
            return makesquare(matchsticks, mask, sidePos + 1, 0, targetSum, 0);
        if (sidePos == matchsticks.length)
            return false;

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

            if (makesquare(matchsticks, mask & ~stickMask, sidePos, i + 1, targetSum, currentSum + stick))
                return true;

            // pruning 4
            if (currentSum == 0)
                break;
        }

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
