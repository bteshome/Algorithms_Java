package com.bteshome.algorithms.hashTable_;

import java.util.HashMap;

public class HashTableAlgorithms2 {
    /**
     * https://leetcode.com/problems/first-unique-character-in-a-string/
     * */
    public static int firstUniqueCharacter(String s) {
        var frequencies = new HashMap<Character, Integer>();

        for (int i = 0; i < s.length(); i++) {
            var c = s.charAt(i);
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            if (frequencies.get(s.charAt(i)) == 1) {
                return i;
            }
        }

        return -1;
    }
}
