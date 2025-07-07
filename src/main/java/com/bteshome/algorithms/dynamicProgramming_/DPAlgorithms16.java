package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms16 {
    static final int MOD = (int)Math.pow(10, 9) + 7;

    /**
     * https://leetcode.com/problems/decode-ways-ii/
     * */
    public static int numDecodings(String s) {
        if (s == null)
            return 1;

        return numDecodings(s, 0, new Integer[s.length()]);
    }

    private static long getMultiplierSingle(String s, int pos) {
        char c = s.charAt(pos);
        if (c == '*')
            return 9;
        else if (c >= '1' && c <= '9')
            return 1;
        else
            return 0;
    }

    private static long getMultiplierDouble(String s, int pos) {
        char c1 = s.charAt(pos);
        char c2 = s.charAt(pos + 1);
        String c1AndC2 = s.substring(pos, pos + 2);
        if (c1AndC2.equals("**"))
            return 15;
        else if (c1 == '*') {
            if (c2 >= '0' && c2 <= '6')
                return 2;
            else if (c2 >= '7' && c2 <= '9')
                return 1;
            return 0;
        }
        else if (c2 == '*') {
            if (c1 == '1')
                return 9;
            else if (c1 == '2')
                return 6;
            else
                return 0;
        }
        else if (c1AndC2.compareTo("10") >= 0 && c1AndC2.compareTo("26") <= 0)
            return 1;
        else
            return 0;
    }

    private static int numDecodings(String s, int pos, Integer[] cache) {
        if (pos == s.length())
            return 1;

        if (cache[pos] == null) {
            long ways = 0;

            long multiplier = getMultiplierSingle(s, pos);
            if (multiplier != 0)
                ways = (ways + ((multiplier * numDecodings(s, pos + 1, cache)) % MOD)) % MOD;

            if (pos < s.length() - 1) {
                multiplier = getMultiplierDouble(s, pos);
                if (multiplier != 0)
                    ways = (ways + ((multiplier * numDecodings(s, pos + 2, cache)) % MOD)) % MOD;
            }

            cache[pos] = (int)ways;
        }

        return cache[pos];
    }
}
