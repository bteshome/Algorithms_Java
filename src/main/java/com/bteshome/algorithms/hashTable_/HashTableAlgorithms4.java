package com.bteshome.algorithms.hashTable_;

import java.util.*;

public class HashTableAlgorithms4 {
    /**
     * https://leetcode.com/problems/insert-delete-getrandom-o1/
     * */
    public static class RandomizedSet {
        private final HashMap<Integer, Integer> valueToIndex = new HashMap<>();
        private final HashMap<Integer, Integer> indexToValue = new HashMap<>();
        private final Random random = new Random();

        public RandomizedSet() {
        }

        public boolean insert(int val) {
            if (valueToIndex.containsKey(val)) {
                return false;
            }

            int index = valueToIndex.size();
            valueToIndex.put(val, index);
            indexToValue.put(index, val);

            return true;
        }

        public boolean remove(int val) {
            if (!valueToIndex.containsKey(val)) {
                return false;
            }

            int indexToRemove = valueToIndex.get(val);
            int lastIndex = valueToIndex.size() - 1;
            int lastIndexValue = indexToValue.get(lastIndex);

            if (indexToRemove == lastIndex) {
                valueToIndex.remove(val);
                indexToValue.remove(indexToRemove);
            } else {
                indexToValue.put(indexToRemove, lastIndexValue);
                valueToIndex.put(lastIndexValue, indexToRemove);
                indexToValue.remove(lastIndex);
                valueToIndex.remove(val);
            }

            return true;
        }

        public int getRandom() {
            int randomIndex = random.nextInt(0, indexToValue.size());
            return indexToValue.get(randomIndex);
        }
    }

    /**
     * https://leetcode.com/problems/index-pairs-of-a-string/
     * */
    public static int[][] indexPairs(String text, String[] words) {
        List<int[]> pairs = new ArrayList<>();
        Set<String> wordsSet = new HashSet<>(Arrays.asList(words));

        for (int i = 0; i < text.length(); i++)
            for (int j = i; j < text.length(); j++)
                if (wordsSet.contains(text.substring(i, j + 1)))
                    pairs.add(new int[]{i, j});

        return pairs.toArray(new int[0][]);
    }

    /**
     * https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference
     * NOTE: this solution is runs in linear time.
     *       For comparison, see the DP solution that runs in quadratic time.
     * */
    public static int longestSubsequenceLinear(int[] arr, int difference) {
        if (arr == null || arr.length == 0)
            return 0;

        Map<Integer, Integer> sequenceEndingIn = new HashMap<>();
        int maxLength = 0;

        for (int num : arr) {
            int prevNum = num - difference;
            int length = 1 + sequenceEndingIn.getOrDefault(prevNum, 0);
            sequenceEndingIn.put(num, length);
            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }
}
