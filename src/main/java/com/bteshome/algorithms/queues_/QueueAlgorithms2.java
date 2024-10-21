package com.bteshome.algorithms.queues_;

import java.util.*;

public class QueueAlgorithms2 {
    /**
     * https://leetcode.com/problems/zigzag-iterator//
     * */
    public static class ZigzagIterator {
        private final Queue<Integer> queue1 = new LinkedList<>();
        private final Queue<Integer> queue2 = new LinkedList<>();
        private int lastAccessedQueue = -1;

        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            queue1.addAll(v1);
            queue2.addAll(v2);
        }

        public int next() {
            if (!hasNext()) {
                throw new RuntimeException("iterator has no more elements");
            }

            if (queue1.isEmpty()) {
                lastAccessedQueue = 2;
                return queue2.remove();
            }

            if (queue2.isEmpty()) {
                lastAccessedQueue = 1;
                return queue1.remove();
            }

            if (lastAccessedQueue == -1 || lastAccessedQueue == 2) {
                lastAccessedQueue = 1;
                return queue1.remove();
            }

            lastAccessedQueue = 2;
            return queue2.remove();
        }

        public boolean hasNext() {
            return !queue1.isEmpty() || !queue2.isEmpty();
        }
    }

    /**
     * https://leetcode.com/problems/design-phone-directory/
     * */
    public static class PhoneDirectory {
        private final Queue<Integer> availableNumbers = new LinkedList<>();
        private final HashSet<Integer> assignedNumbers = new HashSet<>();

        public PhoneDirectory(int maxNumbers) {
            for (int i = 0; i < maxNumbers; i++) {
                availableNumbers.add(i);
            }
        }

        public int get() {
            if (availableNumbers.isEmpty()) {
                return -1;
            }
            int num = availableNumbers.remove();
            assignedNumbers.add(num);
            return num;
        }

        public boolean check(int number) {
            return !assignedNumbers.contains(number);
        }

        public void release(int number) {
            if (assignedNumbers.contains(number)) {
                assignedNumbers.remove(number);
                availableNumbers.add(number);
            }
        }
    }
}
