package com.bteshome.algorithms.dynamicProgramming_;

import java.util.HashMap;

public class DPAlgorithms2 {
    /**
     * TODO
     * leetcode https://leetcode.com/problems/maximum-repeating-substring/?envType=problem-list-v2&envId=dynamic-programming&difficulty=EASY
     * */
    public static int maxRepeating_TODO(String sequence, String word) {
        if (sequence == null || word == null) {
            return 0;
        }

        int maxCount = 0;

        for (int i = 0; i < word.length(); i++) {
            maxCount = Math.max(maxCount, maxRepeating(sequence, word, i));
        }

        return maxCount;
    }

    private static int maxRepeating(String sequence, String word, int sequenceStart) {
        int maxCount = 0;
        int count = 0;

        for (int i = sequenceStart; i < sequence.length() - word.length() + 1;) {
            if (maxRepeatingIsSubstring(sequence, word, i)) {
                count++;
                maxCount = Math.max(count, maxCount);
                i = i + word.length();
            } else {
                i++;
                count = 0;
            }
        }

        return maxCount;
    }

    private static boolean maxRepeatingIsSubstring(String sequence, String word, int sequencePos) {
        for (int i = 0, j = sequencePos; i < word.length(); i++, j++) {
            if (word.charAt(i) != sequence.charAt(j)) {
                return false;
            }
        }
        return true;
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
