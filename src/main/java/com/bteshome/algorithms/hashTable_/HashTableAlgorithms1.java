package com.bteshome.algorithms.hashTable_;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    /**
     * https://leetcode.com/problems/top-k-frequent-words/?envType=problem-list-v2&envId=trie&difficulty=MEDIUM
     * */
    public static List<String> topKFrequent(String[] words, int k) {
        var top = new ArrayList<String>();

        if (words == null || words.length == 0 || k < 1) {
            return top;
        }

        var freq = new HashMap<String, Integer>();

        for (String word : words) {
            if (!freq.containsKey(word)) {
                freq.put(word, 1);
            } else {
                freq.put(word, freq.get(word) + 1);
            }
        }

        for (int i = 0; i < k; i++) {
            String mostFrequent = null;

            for (String word : freq.keySet()) {
                if (mostFrequent == null) {
                    mostFrequent = word;
                } else {
                    if (freq.get(word) > freq.get(mostFrequent)) {
                        mostFrequent = word;
                    } else if (freq.get(word) == freq.get(mostFrequent)) {
                        if (word.compareTo(mostFrequent) < 0) {
                            mostFrequent = word;
                        }
                    }
                }
            }

            top.add(mostFrequent);
            freq.remove(mostFrequent);
        }

        return top;
    }
}
