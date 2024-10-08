package com.bteshome.algorithms.hashTable_;

import java.util.HashMap;

public class HashTableAlgorithms1 {
    /**
     * https://leetcode.com/problems/palindrome-permutation/description/
     * */
    public static boolean palindromePermutation(String s) {
        if (s == null) {
            return false;
        }

        if (s.isEmpty()) {
            return true;
        }

        var frequency = new HashMap<Character, Integer>();

        for (int i = 0; i < s.length(); i++) {
            var current = s.charAt(i);
            if (frequency.containsKey(current)) {
                frequency.put(current, frequency.get(current) + 1);
            } else {
                frequency.put(current, 1);
            }
        }

        var oddCount = frequency.values().stream().filter(e -> e % 2 == 1);

        if (s.length() % 2 == 0) {
            return oddCount.findAny().isEmpty();
        } else {
            return oddCount.count() == 1;
        }
    }
}
