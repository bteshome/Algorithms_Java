package com.bteshome.algorithms.heap_;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapAlgorithms1 {
    /**
     * https://leetcode.com/problems/kth-largest-element-in-a-stream/
     * */
    static class KthLargest {
        private final PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        private final int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            if (nums != null) {
                for (int num : nums) {
                    add(num);
                }
            }
        }

        public int add(int val) {
            if (minHeap.size() < k) {
                minHeap.add(val);
            } else if (val > minHeap.peek()) {
                minHeap.remove();
                minHeap.add(val);
            }
            return minHeap.peek();
        }
    }

    /**
     * https://leetcode.com/problems/last-stone-weight/
     * */
    public static int lastStoneWeight(int[] stones) {
        if (stones == null || stones.length == 0) {
            return -1;
        }

        var maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        for (int stone : stones) {
            maxHeap.add(stone);
        }

        while (maxHeap.size() > 1) {
            var max = maxHeap.remove();
            var secondMax = maxHeap.remove();

            if (secondMax < max) {
                maxHeap.add(max - secondMax);
            }
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }

    /**
     * https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/
     * */
    public static int[] kWeakestRows(int[][] mat, int k) {
        if (mat == null) {
            return new int[0];
        }

        var minHeap = new PriorityQueue<Row>();

        for (int i = 0; i < mat.length; i++) {
            minHeap.add(new Row(i, mat[i]));
        }

        int[] weakest = new int[Math.min(k, mat.length)];
        for (int i = 0; i < weakest.length; i++) {
            weakest[i] = minHeap.remove().getNumber();
        }

        return weakest;
    }

    private static class Row implements Comparable<Row>{
        private final int number;
        private int soldierCount;

        public Row(int number, int[] row) {
            this.number = number;
            int i = 0;
            while (i < row.length && row[i++] == 1) {
                soldierCount++;
            }
        }

        public int getNumber() {
            return number;
        }

        public int getSoldierCount() {
            return soldierCount;
        }

        @Override
        public int compareTo(Row o) {
            return soldierCount != o.soldierCount ?
                    soldierCount - o.soldierCount :
                    number - o.number;
        }
    }
}
