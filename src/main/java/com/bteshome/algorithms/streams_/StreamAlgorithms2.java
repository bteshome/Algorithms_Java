package com.bteshome.algorithms.streams_;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StreamAlgorithms2 {
    /**
     * https://leetcode.com/problems/first-unique-number/
     * */
    public static class FirstUnique {
        private List<Integer> list = new ArrayList<>();
        private HashMap<Integer, Integer> frequencies = new HashMap<>();
        private int current = 0;

        public FirstUnique(int[] nums) {
            for (int num : nums) {
                add(num);
            }
        }

        public int showFirstUnique() {
            if (current < list.size()) {
                return list.get(current);
            }
            return -1;
        }

        public void add(int value) {
            list.add(value);
            frequencies.put(value, frequencies.getOrDefault(value, 0) + 1);
            while (current < list.size() && frequencies.get(list.get(current)) > 1) {
                current++;
            }
        }
    }
}
