package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms15 {
    static final int MOD = (int)Math.pow(10, 9) + 7;

    /**
     * https://leetcode.com/problems/restore-the-array/
     * */
    public static int numberOfArrays(String s, int k) {
        if (s == null)
            return 0;
        if (s.isEmpty())
            return 0;

        return numberOfArrays(s, k, 0, new Integer[s.length()]);
    }

    private static int numberOfArrays(String s, int k, int pos, Integer[] cache) {
        if (pos == s.length())
            return 1;
        if (s.charAt(pos) == '0')
            return 0;

        if (cache[pos] == null) {
            long numArrays = 0;

            for (int i = pos + 1; i <= Math.min(pos + 10, s.length()); i++) {
                try {
                    long firstNum = Long.parseLong(s.substring(pos, i));
                    if (firstNum > k)
                        break;
                    numArrays += numberOfArrays(s, k, i, cache);
                    numArrays %= MOD;
                } catch (Exception ignore) {
                    break;
                }
            }

            cache[pos] = (int)numArrays;
        }

        return cache[pos];
    }

    /**
     * https://leetcode.com/problems/handshakes-that-dont-cross/
     * */
    public static int numberOfWays(int numPeople) {
        if (numPeople < 0)
            return 0;

        return numberOfWays(numPeople, new Integer[numPeople + 1]);
    }

    private static int numberOfWays(int numPeople, Integer[] cache) {
        if (numPeople == 0)
            return 1;
        if (numPeople % 2 == 1)
            return 0;

        if (cache[numPeople] == null) {
            long ways = 0;

            for (int i = 2; i <= numPeople; i++) {
                long left = numberOfWays(i - 2, cache);
                long right = numberOfWays(numPeople - i, cache);
                ways = (ways + ((left * right) % MOD)) % MOD;
            }

            cache[numPeople] = (int) ways;
        }

        return cache[numPeople];
    }
}