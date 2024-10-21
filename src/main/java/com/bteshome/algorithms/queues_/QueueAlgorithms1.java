package com.bteshome.algorithms.queues_;

import java.util.*;

public class QueueAlgorithms1 {
    /**
     * https://leetcode.com/problems/implement-stack-using-queues/
     * */
    private static class MyStack {
        private Queue<Integer> q1 = new LinkedList<>();
        private Queue<Integer> q2 = new LinkedList<>();

        public MyStack() {
        }

        public void push(int x) {
            q1.add(x);
        }

        public int pop() {
            if (q1.isEmpty()) {
                throw new RuntimeException("stack empty");
            }

            while (q1.size() > 1) {
                q2.add(q1.remove());
            }

            int val = q1.remove();
            var temp = q1;
            q1 = q2;
            q2 = temp;
            return val;
        }

        public int top() {
            int val = pop();
            q1.add(val);
            return val;
        }

        public boolean empty() {
            return q1.isEmpty();
        }
    }

    /**
     * https://leetcode.com/problems/moving-average-from-data-stream/
     * */
    private static class MovingAverage {
        private int sum = 0;
        private final int size;
        private final Queue<Integer> queue = new LinkedList<Integer>();

        public MovingAverage(int size) {
            this.size = size;
        }

        public double next(int val) {
            if (queue.size() == size) {
                sum -= queue.remove();
            }

            queue.add(val);
            sum += val;

            return ((double)sum) / queue.size();
        }
    }

    /**
     * https://leetcode.com/problems/number-of-recent-calls/
     * */
    private static class RecentCounter {
        private Queue<Integer> q = new LinkedList<>();

        public RecentCounter() {
        }

        public int ping(int t) {
            while (!q.isEmpty() && q.peek() < (t - 3000)) {
                q.remove();
            }
            q.add(t);
            return q.size();
        }
    }

    /**
     * https://leetcode.com/problems/design-hit-counter/
     * */
    static class HitCounter {
        private Deque<Entry> q = new LinkedList<>();
        public HitCounter() {
        }

        static class Entry {
            public int ts;
            public int count;
            public Entry(int ts, int count) {
                this.ts = ts;
                this.count = count;
            }
        }

        public void hit(int timestamp) {
            if (!q.isEmpty() && q.peekLast().ts == timestamp) {
                q.addLast(new Entry(timestamp, 1 +  q.removeLast().count));
            } else {
                q.addLast(new Entry(timestamp, 1));
            }
        }

        public int getHits(int timestamp) {
            Deque<Entry> q2 = new LinkedList<>();

            int count = 0;
            int start = timestamp - 300 + 1;

            while (!q.isEmpty() && q.peek().ts < start) {
                q2.add(q.remove());
            }

            while (!q.isEmpty() && q.peek().ts <= timestamp) {
                var entry = q.remove();
                q2.add(entry);
                count += entry.count;
            }

            while (!q2.isEmpty()) {
                q.addFirst(q2.removeLast());
            }

            return count;
        }
    }
}
