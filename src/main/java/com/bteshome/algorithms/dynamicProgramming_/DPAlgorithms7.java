package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms7 {
    /**
     * TODO
     * https://leetcode.com/problems/interleaving-string/description/?envType=study-plan-v2&envId=top-interview-150
     * */
    public static boolean isInterleaveTODO(String s1, String s2, String s3) {
        return false;
    }

    public static boolean isInterleaveBruteforce(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null || (s1.length() + s2.length()) != s3.length()) {
            return false;
        }

        return isInterleaveBruteforce(s1, s2, s3, 0, 0) || isInterleaveBruteforce(s2, s1, s3, 0, 0);
    }

    private static boolean isInterleaveBruteforce(String s1, String s2, String s3, int s1Parts, int s2Parts) {
        if (s3.isEmpty()) {
            return Math.abs(s1Parts - s2Parts) <= 1;
        }

        for (int i = 1; i <= s1.length(); i++) {
            var s1Part = s1.substring(0, i);
            if (!s3.startsWith(s1Part)) {
                break;
            }
            if (isInterleaveBruteforce(s2, s1.substring(i), s3.substring(i), s2Parts, s1Parts + 1)) {
                return true;
            }
        }

        return false;
    }

    /**
     * https://leetcode.com/problems/edit-distance/?envType=study-plan-v2&envId=top-interview-150
     * */
    public static int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return -1;
        }

        var cache = new Integer[word1.length()][];
        for (int i = 0; i < word1.length(); i++) {
            cache[i] = new Integer[word2.length()];
        }

        return minDistance(word1, word2, 0, 0, cache);
    }

    private static int minDistance(String word1, String word2, int pos1, int pos2, Integer[][] cache) {
        if (pos1 == word1.length()) {
            return word2.length() - pos2;
        }

        if (pos2 == word2.length()) {
            return word1.length() - pos1;
        }

        if (cache[pos1][pos2] == null) {
            int distance = Integer.MAX_VALUE;

            if (word1.charAt(pos1) == word2.charAt(pos2)) {
                distance = minDistance(word1, word2, pos1 + 1, pos2 + 1, cache);
            } else {
                distance = Math.min(distance, 1 + minDistance(word1, word2, pos1 + 1, pos2 + 1, cache));
                distance = Math.min(distance, 1 + minDistance(word1, word2, pos1 + 1, pos2, cache));
                distance = Math.min(distance, 1 + minDistance(word1, word2, pos1, pos2 + 1, cache));
            }

            cache[pos1][pos2] = distance;
        }

        return cache[pos1][pos2];
    }


}
