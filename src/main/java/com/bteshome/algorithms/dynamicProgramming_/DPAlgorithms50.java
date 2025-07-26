package com.bteshome.algorithms.dynamicProgramming_;

import java.util.HashMap;
import java.util.Map;

public class DPAlgorithms50 {
    private static final int MOD = ((int) Math.pow(10, 9)) + 7;

    /**
     * https://leetcode.com/problems/number-of-ways-to-reach-a-position-after-exactly-k-steps
     * NOTE: early pruning is crucial to avoid unnecessary recursive calls
     *       and to ensure the search space remains within bounds.
     * */
    public static int numberOfWaysTopDown(int startPos, int endPos, int k) {
        if (k < 0)
            return 0;

        return numberOfWaysTopDown(startPos, endPos, k, new HashMap<>());
    }

    private static int numberOfWaysTopDown(int startPos, int endPos, int k, Map<String, Integer> dp) {
        if (k == 0)
            return startPos == endPos ? 1 : 0;
        // early pruning
        if (Math.abs(startPos - endPos) > k)
            return 0;
        if ((k - Math.abs(startPos - endPos)) % 2 != 0)
            return 0;

        String key = "%s,%s".formatted(startPos, k);

        if (dp.containsKey(key))
            return dp.get(key);

        long ways = numberOfWaysTopDown(startPos + 1, endPos, k - 1, dp);
        ways = (ways + numberOfWaysTopDown(startPos - 1, endPos, k - 1, dp)) % MOD;
        dp.put(key, (int)ways);

        return dp.get(key);
    }

    /**
     * NOTE: since k is small (per LeetCode), using record instead of a custom class
     *       hurts performance. Why? because the default hash function of records
     *       leads to lots of collisions.
     * */
    public static int numberOfWaysTopDownWithPair(int startPos, int endPos, int k) {
        if (k < 0)
            return 0;

        return numberOfWaysTopDownWithPair(startPos, endPos, k, new HashMap<>());
    }

    private static int numberOfWaysTopDownWithPair(int startPos, int endPos, int k, Map<Pair, Integer> dp) {
        if (k == 0)
            return startPos == endPos ? 1 : 0;
        // early pruning
        if (Math.abs(startPos - endPos) > k)
            return 0;
        if ((k - Math.abs(startPos - endPos)) % 2 != 0)
            return 0;

        Pair key = new Pair(startPos, k);

        if (dp.containsKey(key))
            return dp.get(key);

        long ways = numberOfWaysTopDownWithPair(startPos + 1, endPos, k - 1, dp);
        ways = (ways + numberOfWaysTopDownWithPair(startPos - 1, endPos, k - 1, dp)) % MOD;
        dp.put(key, (int)ways);

        return dp.get(key);
    }

    private static class Pair {
        int pos;
        int k;

        public Pair(int pos, int k) {
            this.pos = pos;
            this.k = k;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pair)) return false;
            Pair other = (Pair) o;
            return this.pos == other.pos && this.k == other.k;
        }

        @Override
        public int hashCode() {
            return (pos + 10000) * 10007 + k;
        }
    }
}