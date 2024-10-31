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

    /**
     * https://leetcode.com/problems/missing-number/
     * NOTE - an array is used instead of a hashtable because the value range is known.
     * */
    public static int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("input null or empty.");
        }

        var freq = new int[nums.length + 1];

        for (int num : nums) {
            freq[num] = 1;
        }

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] == 0) {
                return i;
            }
        }

        return -1;
    }

    /**
     * leetcode https://leetcode.com/problems/two-sum/?envType=problem-list-v2&envId=array&difficulty=EASY
     * */
    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }

        HashMap<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            int other = target - current;
            if (seen.containsKey(other)) {
                return new int[]{seen.get(other), i};
            }
            seen.put(current, i);
        }

        return null;
    }
}
