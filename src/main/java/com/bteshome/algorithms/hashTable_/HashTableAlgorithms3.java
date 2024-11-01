package com.bteshome.algorithms.hashTable_;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

public class HashTableAlgorithms3 {
    /**
     * https://leetcode.com/problems/longest-substring-without-repeating-characters/
     * */
    public static int lengthOfLongestSubstringWithoutRepeatingCharacters(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        record Entry(int index, char value){}

        HashMap<Character, Integer> occurrence = new HashMap<>();
        int maxLength = 1;
        int windowStart = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (occurrence.containsKey(c)) {
                int oldIndex = occurrence.get(c);
                for (int j = windowStart; j <= oldIndex; j++) {
                    occurrence.remove(s.charAt(j));
                }
                windowStart = oldIndex + 1;
            } else {
                maxLength = Math.max(maxLength, i - windowStart + 1);
            }

            occurrence.put(c, i);
        }

        return maxLength;
    }


}
