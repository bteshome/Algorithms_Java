package com.bteshome.algorithms.streams_;

import java.util.*;

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

    /**
     * https://leetcode.com/problems/find-median-from-data-stream/
     * Beautiful!
     * */
    public static class MedianFinder {
        private PriorityQueue<Integer> smallerHalf = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        private PriorityQueue<Integer> largerHalf = new PriorityQueue<>();

        public MedianFinder() {
        }

        public void addNum(int num) {
            if (smallerHalf.isEmpty()) {
                smallerHalf.add(num);
            } else {
                if (num > smallerHalf.peek()) {
                    largerHalf.add(num);
                } else {
                    smallerHalf.add(num);
                }
                rebalance();
            }
        }

        private void rebalance() {
            while (smallerHalf.size() > largerHalf.size() + 1) {
                largerHalf.add(smallerHalf.poll());
            }

            while (largerHalf.size() > smallerHalf.size()) {
                smallerHalf.add(largerHalf.poll());
            }
        }

        public double findMedian() {
            if (smallerHalf.isEmpty()) {
                throw new RuntimeException("stream empty");
            }

            if (smallerHalf.size() == largerHalf.size()) {
                return (((double)smallerHalf.peek()) + largerHalf.peek()) / 2;
            } else {
                return smallerHalf.peek();
            }
        }
    }

}
