package com.bteshome.algorithms.dynamicProgramming_;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class DPAlgorithms24 {
    /* https://leetcode.com/problems/scramble-string/ */
    public static boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length())
            return false;
        if (s1.isEmpty())
            return true;

        Boolean[][][] dp = new Boolean[s1.length()][s1.length()][s1.length() + 1];

        return isScramble(s1, s2, 0, 0, s1.length(), dp);
    }

    private static boolean isScramble(String s1, String s2, int pos1, int pos2, int length, Boolean[][][] dp) {
        if (length == 1)
            return s1.charAt(pos1) == s2.charAt(pos2);

        if (dp[pos1][pos2][length] == null) {
            boolean is = false;

            for (int splitPos = pos1 + 1; splitPos < pos1 + length; splitPos++) {
                int splitLength = splitPos - pos1;
                is =
                        (isScramble(s1, s2, pos1, pos2, splitLength, dp) &&
                                isScramble(s1, s2, pos1 + splitLength, pos2 + splitLength, length - splitLength, dp)) ||
                                (isScramble(s1, s2, pos1 + splitLength, pos2, length - splitLength, dp) &&
                                        isScramble(s1, s2, pos1, pos2 + length - splitLength, splitLength, dp));
                if (is)
                    break;
            }

            dp[pos1][pos2][length] = is;
        }

        return dp[pos1][pos2][length];
    }

    /* https://leetcode.com/problems/best-team-with-no-conflicts */
    public static int bestTeamScore(int[] scores, int[] ages) {
        if (scores == null || ages == null || scores.length != ages.length || scores.length < 1)
            return 0;

        Player[] players = new Player[ages.length];
        for (int i = 0; i < ages.length; i++)
            players[i] = new Player(ages[i], scores[i]);

        Arrays.sort(players, (a, b) -> {
            if (a.age == b.age)
                return Integer.compare(a.score, b.score);
            return Integer.compare(a.age, b.age);
        });

        int[] dp = new int[players.length];

        for (int i = 0; i < players.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++)
                if (players[j].score <= players[i].score)
                    max = Math.max(max, dp[j]);
            dp[i] = max + players[i].score;
        }

        int overallMax = 0;
        for (int i = 0; i < players.length; i++)
            overallMax = Math.max(overallMax, dp[i]);

        return overallMax;
    }

    record Player(int age, int score) { }
}