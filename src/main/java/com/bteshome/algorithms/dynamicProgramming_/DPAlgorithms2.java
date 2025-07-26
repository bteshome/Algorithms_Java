package com.bteshome.algorithms.dynamicProgramming_;

import java.util.HashMap;

public class DPAlgorithms2 {
    /**
     * https://leetcode.com/problems/maximum-repeating-substring
     * */
    public static int maxRepeating(String sequence, String word) {
        if (sequence == null || word == null || word.isEmpty() || sequence.length() < word.length())
            return 0;

        int overallMax = 0;

        boolean[] dpMatches = new boolean[sequence.length() - word.length() + 1];
        for (int i = 0; i < dpMatches.length; i++)
            dpMatches[i] = findMatches(sequence, word, i);

        for (int i = 0; i < dpMatches.length; i++) {
            if (dpMatches[i]) {
                int max = maxRepeating(sequence, word, i, dpMatches);
                overallMax = Math.max(overallMax, max);
            }
        }

        return overallMax;
    }

    private static int maxRepeating(String sequence, String word, int pos, boolean[] matches) {
        int max = 0;

        while (pos < matches.length && matches[pos]) {
            max++;
            pos = pos + word.length();
        }

        return max;
    }

    private static boolean findMatches(String sequence, String word, int pos) {
        int i = 0;

        for (; i < word.length() && pos < sequence.length(); i++, pos++)
            if (sequence.charAt(pos) != word.charAt(i))
                return false;

        return i == word.length();
    }

    public static int maxRepeating2(String sequence, String word) {
        if (sequence == null || word == null || word.isEmpty() || sequence.length() < word.length())
            return 0;

        int overallMax = 0;
        StringBuilder sb = new StringBuilder(word);

        while (sequence.contains(sb)) {
            sb.append(word);
            overallMax++;
        }

        return overallMax;
    }

    /**
     * leetcode https://leetcode.com/problems/count-numbers-with-unique-digits-ii/submissions/1406191275/?envType=problem-list-v2&envId=dynamic-programming&difficulty=EASY
     * */
    public static int numberCount_bruteForce(int a, int b) {
        int count = 0;

        for (int i = a; i <= b; i++) {
            String decimalString = Integer.toString(i);
            var digits = new HashMap<Character, Boolean>();
            var allUnique = true;

            for (char c : decimalString.toCharArray()) {
                if (digits.containsKey(c)) {
                    allUnique = false;
                    break;
                }
                digits.put(c, true);
            }

            if (allUnique) {
                count++;
            }
        }

        return count;
    }
}
