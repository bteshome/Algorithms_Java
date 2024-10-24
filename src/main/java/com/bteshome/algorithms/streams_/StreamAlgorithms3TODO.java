package com.bteshome.algorithms.streams_;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class StreamAlgorithms3TODO {
    /**
     * https://leetcode.com/problems/finding-mk-average/
     * */
    public static class MKAverageTODO {
        private final int m;
        private final int k;
        private final Queue<Integer> queue = new LinkedList<>();
        private final PriorityQueue<Integer> smallest = new PriorityQueue<>((a, b) -> b - a);
        private final PriorityQueue<Integer> largest = new PriorityQueue<>();
        private int sum = 0;
        private int smallestSum = 0;
        private int largestSum = 0;

        public MKAverageTODO(int m, int k) {
            this.m = m;
            this.k = k;
        }

        public void addElement(int num) {
            queue.add(num);
            sum += num;

            if (queue.size() > m) {
                sum -= queue.remove();
            }

            if (smallest.size() < k) {
                smallest.add(num);
                smallestSum += num;
            } else {
                if (num < smallest.peek()) {
                    int val = smallest.remove();
                    smallest.add(num);
                    smallestSum += num;
                    smallestSum -= val;
                }
            }

            if (largest.size() < k) {
                largest.add(num);
                largestSum += num;
            } else {
                if (num > largest.peek()) {
                    int val = largest.remove();
                    largest.add(num);
                    largestSum += num;
                    largestSum -= val;
                }
            }
        }

        public int calculateMKAverage() {
            if (queue.size() < m) {
                return -1;
            }

            return (sum - smallestSum - largestSum) / (m - 2*k);
        }
    }
}
