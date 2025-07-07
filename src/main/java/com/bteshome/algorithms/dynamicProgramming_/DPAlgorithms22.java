package com.bteshome.algorithms.dynamicProgramming_;

import java.util.HashMap;
import java.util.Map;

public class DPAlgorithms22 {
    private static final int MOD = (int)Math.pow(10, 9) + 7;

    /**
     * https://leetcode.com/problems/count-all-possible-routes/
     * */
    public static int countRoutes(int[] locations, int start, int finish, int fuel) {
        if (locations == null || locations.length < 2 || start < 0 || finish < 0)
            return 0;

        Integer[][] dp = new Integer[locations.length][fuel + 1];

        return countRoutes(locations, start, finish, fuel, dp);
    }

    private static int countRoutes(int[] locations, int start, int finish, int fuel, Integer[][] dp) {
        if (dp[start][fuel] == null) {
            long routes = start == finish ? 1 : 0;

            for (int i = 0; i < locations.length; i++) {
                if (i != start) {
                    int fuelNeeded = Math.abs(locations[start] - locations[i]);
                    if (fuelNeeded <= fuel) {
                        int next = countRoutes(locations, i, finish, fuel - fuelNeeded, dp);
                        routes = (routes + next) % MOD;
                    }
                }
            }

            dp[start][fuel] = (int)routes;
        }

        return dp[start][fuel];
    }

    /**
     * https://leetcode.com/problems/number-of-ways-to-form-a-target-string-given-a-dictionary/
     * */
    public static int numWaysTopDown(String[] words, String target) {
        if (words == null || target == null || words.length == 0 || target.isEmpty() || target.length() > words[0].length())
            return 0;

        int[][] charFreq = new int[26][words[0].length()];
        for (String word : words)
            for (int j = 0; j < word.length(); j++)
                charFreq[word.charAt(j) - 'a'][j]++;

        return numWaysTopDown(words, target, 0, 0, new Integer[target.length()][words[0].length()], charFreq);
    }

    private static int numWaysTopDown(String[] words, String target, int targetPos, int wordPos, Integer[][] dp, int[][] charFreq) {
        if (targetPos == target.length())
            return 1;
        if (words[0].length() - wordPos < target.length() - targetPos)
            return 0;

        if (dp[targetPos][wordPos] == null) {
            long ways = numWaysTopDown(words, target, targetPos, wordPos + 1, dp, charFreq);
            int c = target.charAt(targetPos) - 'a';
            if (charFreq[c][wordPos] > 0) {
                long next = numWaysTopDown(words, target, targetPos + 1, wordPos + 1, dp, charFreq);
                ways = (ways + ((charFreq[c][wordPos] * next) % MOD)) % MOD;
            }
            dp[targetPos][wordPos] = (int) ways;
        }

        return dp[targetPos][wordPos];
    }
}