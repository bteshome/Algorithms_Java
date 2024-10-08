package com.bteshome.algorithms.dynamicProgramming_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DPAlgorithms5 {
    /**
     * https://leetcode.com/problems/word-break/description/?envType=study-plan-v2&envId=top-interview-150
     * */
    public static boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null) {
            return false;
        }

        return wordBreak(s, wordDict, new HashMap<String, Boolean>());
    }

    private static boolean wordBreak(String s, List<String> wordDict, HashMap<String, Boolean> cache) {
        if (s.isEmpty()) {
            return true;
        }

        if (!cache.containsKey(s)) {
            var breakable = false;

            for (var word : wordDict) {
                if (s.startsWith(word) && wordBreak(s.substring(word.length()), wordDict, cache)) {
                    breakable = true;
                    break;
                }
            }

            cache.put(s, breakable);
        }

        return cache.get(s);
    }

    /**
     * https://leetcode.com/problems/longest-increasing-subsequence/description/?envType=study-plan-v2&envId=top-interview-150
     * */
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        var cache = new int[nums.length];
        Arrays.fill(cache, 1);
        int lis = 1;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    cache[i] = Math.max(cache[i], 1 + cache[j]);
                    lis = Math.max(lis, cache[i]);
                }
            }
        }

        return lis;
    }
}
