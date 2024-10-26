package com.bteshome.algorithms.heap_;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapAlgorithms9 {
    /**
     * https://leetcode.com/problems/meeting-rooms-ii
     * */
    public static int meetingRoomsII(int[][] intervals) {
        if (intervals == null) {
            return 0;
        }

        if (intervals.length < 2) {
            return intervals.length;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        var maxRoomsNeeded = 0;
        var rooms = new PriorityQueue<Integer>();

        for (int[] interval : intervals) {
            if (!rooms.isEmpty() && interval[0] >= rooms.peek()) {
                rooms.remove();
                rooms.add(interval[1]);
            } else {
                rooms.add(interval[1]);
                maxRoomsNeeded = Math.max(maxRoomsNeeded, rooms.size());
            }
        }

        return maxRoomsNeeded;
    }

    /**
     * https://leetcode.com/problems/k-th-smallest-prime-fraction/
     * */
    public static int[] kthSmallestPrimeFraction(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        record Pair(int nom, int denom) {
            public double getValue() {
                return ((double)nom()) / denom();
            }
        }
        
        var maxHeap = new PriorityQueue<Pair>((a, b) -> Double.compare(b.getValue(), a.getValue()));

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                var pair = new Pair(arr[i], arr[j]);
                if (maxHeap.size() < k) {
                    maxHeap.add(pair);
                } else {
                    if (pair.getValue() < maxHeap.peek().getValue()) {
                        maxHeap.remove();
                        maxHeap.add(pair);
                    }
                }
            }
        }

        return new int[]{maxHeap.peek().nom(), maxHeap.peek().denom()};
    }

    /**
     * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
     * Beautiful!
     * */
    public static int kthSmallestInSortedMatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || k < 1) {
            throw new RuntimeException("matrix empty or k < 1");
        }

        class Entry {
            public int row;
            public int col;
            public Entry(int row, int col) {
                this.row = row;
                this.col = col;
            }
            public int getValue() {
                return matrix[row][col];
            }
        }

        var minHeap = new PriorityQueue<Entry>(Comparator.comparingInt(Entry::getValue));
        int count = 0;

        for (int i = 0; i < matrix.length; i++) {
            minHeap.add(new Entry(i, 0));
        }

        while (!minHeap.isEmpty()) {
            var min = minHeap.poll();
            count++;

            if (count == k) {
                return min.getValue();
            }

            if (min.col < matrix[0].length - 1) {
                minHeap.offer(new Entry(min.row, min.col + 1));
            }
        }

        return 0;
    }
}