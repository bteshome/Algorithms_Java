package com.bteshome.algorithms.hashTable_;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.TreeSet;

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
}
