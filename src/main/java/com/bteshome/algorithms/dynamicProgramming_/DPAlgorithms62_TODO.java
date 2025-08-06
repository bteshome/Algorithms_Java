package com.bteshome.algorithms.dynamicProgramming_;

import java.util.*;

public class DPAlgorithms62_TODO {
    /**
     * - This is the HackerRank problem: Equal: 'Christy is interning at HackerRank. One day she has
     *   to distribute some chocolates ...'
     *   - It's correct but exceeds the set time limit. Supposed to be solved using math instead of DP.
     * */
    public static int equal(List<Integer> arr) {
        if (arr == null || arr.size() < 2)
            return 0;

        int minValue = Integer.MAX_VALUE;
        for (Integer integer : arr)
            minValue = Math.min(minValue, integer);

        Map<String, Integer> dp = new HashMap<>();
        int min = Integer.MAX_VALUE;
        List<Integer> pieces = new ArrayList<>();

        pieces.add(1);
        pieces.add(2);
        pieces.add(5);

        for (int i = 0; i < 5; i++) {
            int minTarget = minValue - i;
            min = Math.min(min, equal(arr, pieces, minTarget, dp));
        }

        return min;
    }

    private static int equal(List<Integer> arr, List<Integer> pieces, int minTarget, Map<String, Integer> dp) {
        boolean allEqual = true;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) < minTarget)
                return Integer.MAX_VALUE;
            if (arr.get(i) != arr.get(0)) {
                allEqual = false;
                break;
            }
        }
        if (allEqual)
            return 0;

        List<Integer> copy = new ArrayList<>(arr);
        Collections.sort(copy);
        String key = copy.toString();

        if (dp.containsKey(key))
            return dp.get(key);

        int min = Integer.MAX_VALUE;

        for (int piece : pieces) {
            for (int i = 0; i < arr.size(); i++) {
                List<Integer> next = new ArrayList<>(arr);
                next.set(i, next.get(i) - piece);
                min = Math.min(min, equal(next, pieces, minTarget, dp));
            }
        }

        dp.put(key, min == Integer.MAX_VALUE ? min : min + 1);

        return dp.get(key);
    }

    private static final long MOD = 1000000007;

    /**
     * - This is the HackerRank problem: Construct the Array - 'Your goal is to find the number
     *   of ways to construct an array such that consecutive positions contain different values...'.
     * - This solution exceeds HackerRank time limit
     * */
    public static long countArrayTopDown(int n, int k, int x) {
        if (n < 1)
            return 0;
        if (n == 1)
            return x == 1 ? 1 : 0;
        if (n == 2)
            return x != 1 ? 1 : 0;
        if (k <= 1)
            return 0;

        return countArrayTopDown(n, k, x, 1, 1, new Long[n][k + 1]);
    }

    private static long countArrayTopDown(int n, int k, int x, int prev, int pos, Long[][] dp) {
        if (pos == n - 1)
            return prev == x ? 0 : 1;
        if (dp[pos][prev] != null)
            return dp[pos][prev];

        long ways = 0;

        for (int i = 1; i <= k; i++)
            if (i != prev)
                ways = (ways + countArrayTopDown(n, k, x, i, pos + 1, dp)) % MOD;

        dp[pos][prev] = ways;
        return dp[pos][prev];
    }

    /**
     * This solution too exceeds HackerRank time limit.
     * */
    public static long countArrayBottomUp(int n, int k, int x) {
        if (n < 1)
            return 0;
        if (n == 1)
            return x == 1 ? 1 : 0;
        if (n == 2)
            return x != 1 ? 1 : 0;
        if (k <= 1)
            return 0;

        long[][] dp = new long[n][k + 1];

        for (int i = 1; i <= k; i++)
            if (i != x)
                dp[n - 1][i] = 1;

        for (int pos = n - 2; pos >= 1; pos--) {
            for (int prev = 1; prev <= (pos == 1 ? 1 : k); prev++) {
                long ways = 0;

                for (int i = 1; i <= k; i++)
                    if (i != prev)
                        ways = (ways + dp[pos + 1][i]) % MOD;

                dp[pos][prev] = ways;
            }
        }

        return dp[1][1];
    }

    /**
     * This solution too exceeds HackerRank time limit, though better space wise.
     * */
    public static long countArrayBottomUpSpaceOptimized(int n, int k, int x) {
        if (n < 1)
            return 0;
        if (n == 1)
            return x == 1 ? 1 : 0;
        if (n == 2)
            return x != 1 ? 1 : 0;
        if (k <= 1)
            return 0;

        long[] currentRow = new long[k + 1];
        long[] rightRow = new long[k + 1];
        long[] temp = null;

        for (int i = 1; i <= k; i++)
            if (i != x)
                rightRow[i] = 1;

        for (int pos = n - 2; pos >= 1; pos--) {
            Arrays.fill(currentRow, 0);

            for (int prev = 1; prev <= (pos == 1 ? 1 : k); prev++) {
                long ways = 0;

                for (int i = 1; i <= k; i++)
                    if (i != prev)
                        ways = (ways + rightRow[i]) % MOD;

                currentRow[prev] = ways;
            }

            temp = rightRow;
            rightRow = currentRow;
            currentRow = temp;
        }

        return rightRow[1];
    }

    /**
     * This solution makes one more optimization using precomputation of sums.
     * It is still not accepted.
     * */
    public static long countArrayBottomUpSpaceOptimizedFaster(int n, int k, int x) {
        if (n < 1)
            return 0;
        if (n == 1)
            return x == 1 ? 1 : 0;
        if (n == 2)
            return x != 1 ? 1 : 0;
        if (k <= 1)
            return 0;

        long[] currentRow = new long[k + 1];
        long[] rightRow = new long[k + 1];
        long[] temp = null;

        for (int i = 1; i <= k; i++)
            if (i != x)
                rightRow[i] = 1;

        for (int pos = n - 2; pos >= 1; pos--) {
            Arrays.fill(currentRow, 0);

            long totalSum = 0;
            for (int i = 1; i <= k; i++)
                totalSum = (totalSum + rightRow[i]) % MOD;

            for (int prev = 1; prev <= (pos == 1 ? 1 : k); prev++)
                currentRow[prev] = (totalSum - rightRow[prev] + MOD) % MOD;

            temp = rightRow;
            rightRow = currentRow;
            currentRow = temp;
        }

        return rightRow[1];
    }

    public static long countArrayBottomUp2(int n, int k, int x) {
        if (n < 1)
            return 0;
        if (n == 1)
            return x == 1 ? 1 : 0;
        if (n == 2)
            return x != 1 ? 1 : 0;
        if (k <= 1)
            return 0;

        long ways = 1;

        for (int i = 1; i < n - 1 ; i++) {
            ways = (ways * (k - 1)) % MOD;
        }

        return ways;
    }
}