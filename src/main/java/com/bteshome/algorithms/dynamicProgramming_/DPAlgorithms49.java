package com.bteshome.algorithms.dynamicProgramming_;

import java.util.HashMap;
import java.util.Map;

public class DPAlgorithms49 {
    private static final int MOD = ((int) Math.pow(10, 9)) + 7;

    /* https://leetcode.com/problems/number-of-dice-rolls-with-target-sum */
    public static int numRollsToTargetTopDown(int n, int k, int target) {
        if (n < 1 || k < 1 || target < 1)
            return 0;

        return numRollsToTargetTopDown(n, k, 0, target, new Integer[n][target + 1]);
    }

    private static int numRollsToTargetTopDown(int n, int k, int pos, int target, Integer[][] dp) {
        if (pos == n)
            return target == 0 ? 1 : 0;
        if (target <= 0)
            return 0;

        if (dp[pos][target] != null)
            return dp[pos][target];

        long ways = 0;

        for (int i = 1; i <= Math.min(k, target); i++)
            ways = (ways + numRollsToTargetTopDown(n, k, pos + 1, target - i, dp)) % MOD;

        dp[pos][target] = (int) ways;

        return dp[pos][target];
    }

    public static int numRollsToTargetBottomUp(int n, int k, int target) {
        if (n < 1 || k < 1 || target < 1)
            return 0;

        int[][] dp = new int[n + 1][target + 1];
        dp[n][0] = 1;

        for (int pos = n - 1; pos >= 0; pos--) {
            for (int localTarget = 1; localTarget <= target; localTarget++) {
                long ways = 0;

                for (int i = 1; i <= Math.min(k, localTarget); i++)
                    ways = (ways + dp[pos + 1][localTarget - i]) % MOD;

                dp[pos][localTarget] = (int) ways;
            }
        }

        return dp[0][target];
    }

    /**
     * https://leetcode.com/problems/find-all-good-strings
     * NOTE: this solution used the KMP algorithm.
     * The KMP code (getNewEvilMatchLength, getFailureFunction) is copied pasted (TLDR).
     */
    public static class GoodStringsTopDown {
        private final int MOD = ((int) Math.pow(10, 9)) + 7;
        private int[] cachedFailureFunction = null;
        private String cachedEvilString = null;

        public int findGoodStringsTopDown(int n, String s1, String s2, String evil) {
            if (s1 == null || s2 == null || s1.length() != s2.length() || s1.length() != n)
                return 0;
            if (evil == null)
                evil = "";

            Integer[][][][] dp = new Integer[n][2][2][evil.length() + 1];
            return findGoodStringsTopDown(n, s1, s2, evil, 0, false, false, 0, dp);
        }

        private int getNewEvilMatchLength(String evil, char c, int evilMatchLength) {
            if (evil.isEmpty())
                return 0;

            while (evilMatchLength > 0 && evil.charAt(evilMatchLength) != c)
                evilMatchLength = getFailureFunction(evil)[evilMatchLength - 1];

            if (evil.charAt(evilMatchLength) == c)
                evilMatchLength++;

            return evilMatchLength;
        }

        private int[] getFailureFunction(String evil) {
            if (cachedEvilString != null && cachedEvilString.equals(evil))
                return cachedFailureFunction;

            int m = evil.length();
            int[] lps = new int[m];
            int len = 0;
            int i = 1;

            while (i < m) {
                if (evil.charAt(i) == evil.charAt(len)) {
                    len++;
                    lps[i] = len;
                    i++;
                } else {
                    if (len != 0) {
                        len = lps[len - 1];
                    } else {
                        lps[i] = 0;
                        i++;
                    }
                }
            }

            cachedEvilString = evil;
            cachedFailureFunction = lps;

            return lps;
        }

        private int findGoodStringsTopDown(int n, String s1, String s2, String evil, int pos, boolean greaterThanS1, boolean smallerThanS2,
                                           int evilMatchLength, Integer[][][][] dp) {
            if (!evil.isEmpty() && evilMatchLength == evil.length())
                return 0;
            if (pos == n)
                return 1;

            int greaterThanS1Index = greaterThanS1 ? 1 : 0;
            int smallerThanS2Index = smallerThanS2 ? 1 : 0;

            if (dp[pos][greaterThanS1Index][smallerThanS2Index][evilMatchLength] != null)
                return dp[pos][greaterThanS1Index][smallerThanS2Index][evilMatchLength];

            long ways = 0;

            if (greaterThanS1 && smallerThanS2) {
                for (int i = 0; i < 26; i++) {
                    char c = (char) ('a' + i);
                    int newEvilMatchLength = getNewEvilMatchLength(evil, c, evilMatchLength);
                    ways = (ways + findGoodStringsTopDown(n, s1, s2, evil, pos + 1, greaterThanS1, smallerThanS2, newEvilMatchLength, dp)) % MOD;
                }
            } else if (greaterThanS1) {
                char c = s2.charAt(pos);
                int newEvilMatchLength = getNewEvilMatchLength(evil, s2.charAt(pos), evilMatchLength);
                ways = (ways + findGoodStringsTopDown(n, s1, s2, evil, pos + 1, greaterThanS1, smallerThanS2, newEvilMatchLength, dp)) % MOD;

                for (int i = 0; i < (c - 'a'); i++) {
                    newEvilMatchLength = getNewEvilMatchLength(evil, (char) ('a' + i), evilMatchLength);
                    ways = (ways + findGoodStringsTopDown(n, s1, s2, evil, pos + 1, greaterThanS1, true, newEvilMatchLength, dp)) % MOD;
                }
            } else if (smallerThanS2) {
                char c = s1.charAt(pos);
                int newEvilMatchLength = getNewEvilMatchLength(evil, c, evilMatchLength);
                ways = (ways + findGoodStringsTopDown(n, s1, s2, evil, pos + 1, greaterThanS1, smallerThanS2, newEvilMatchLength, dp)) % MOD;

                for (int i = (c - 'a') + 1; i < 26; i++) {
                    newEvilMatchLength = getNewEvilMatchLength(evil, (char) ('a' + i), evilMatchLength);
                    ways = (ways + findGoodStringsTopDown(n, s1, s2, evil, pos + 1, true, smallerThanS2, newEvilMatchLength, dp)) % MOD;
                }
            } else {
                char c1 = s1.charAt(pos);
                char c2 = s2.charAt(pos);
                if (c1 == c2) {
                    int newEvilMatchLength = getNewEvilMatchLength(evil, c1, evilMatchLength);
                    ways = (ways + findGoodStringsTopDown(n, s1, s2, evil, pos + 1, greaterThanS1, smallerThanS2, newEvilMatchLength, dp)) % MOD;
                } else if (c1 < c2) {
                    int newEvilMatchLength = getNewEvilMatchLength(evil, c1, evilMatchLength);
                    ways = (ways + findGoodStringsTopDown(n, s1, s2, evil, pos + 1, greaterThanS1, true, newEvilMatchLength, dp)) % MOD;

                    newEvilMatchLength = getNewEvilMatchLength(evil, c2, evilMatchLength);
                    ways = (ways + findGoodStringsTopDown(n, s1, s2, evil, pos + 1, true, smallerThanS2, newEvilMatchLength, dp)) % MOD;

                    for (int i = (c1 - 'a') + 1; i <= (c2 - 'a') - 1; i++) {
                        newEvilMatchLength = getNewEvilMatchLength(evil, (char) ('a' + i), evilMatchLength);
                        ways = (ways + findGoodStringsTopDown(n, s1, s2, evil, pos + 1, true, true, newEvilMatchLength, dp)) % MOD;
                    }
                }
            }

            dp[pos][greaterThanS1Index][smallerThanS2Index][evilMatchLength] = (int) ways;

            return dp[pos][greaterThanS1Index][smallerThanS2Index][evilMatchLength];
        }
    }
}