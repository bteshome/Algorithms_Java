package com.bteshome.algorithms.heap_;

import java.util.*;

public class HeapAlgorithms11 {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * https://leetcode.com/problems/merge-k-sorted-lists/
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }

        var minHeap = new PriorityQueue<ListNode>(Comparator.comparingInt(a -> a.val));

        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }

        ListNode outputRoot = null;
        ListNode outputCurrent = null;

        while (!minHeap.isEmpty()) {
            var min = minHeap.remove();
            if (outputRoot == null) {
                outputRoot = new ListNode(min.val);
                outputCurrent = outputRoot;
            } else {
                outputCurrent.next = new ListNode(min.val);
                outputCurrent = outputCurrent.next;
            }
            if (min.next != null) {
                minHeap.offer(min.next);
            }
        }

        return outputRoot;
    }

    /**
     * https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
     */
    public static int longestSubarray(int[] nums, int limit) {
        record Pair(int num, int index) {
        }

        var minHeap = new PriorityQueue<Pair>(Comparator.comparingInt(Pair::num));
        var maxHeap = new PriorityQueue<Pair>((a, b) -> Integer.compare(b.num(), a.num()));

        int maxLength = 0;
        int windowStart = 0;

        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            minHeap.offer(new Pair(nums[windowEnd], windowEnd));
            maxHeap.offer(new Pair(nums[windowEnd], windowEnd));

            while (Math.abs(minHeap.peek().num() - maxHeap.peek().num()) > limit) {
                windowStart = Math.min(minHeap.peek().index(), maxHeap.peek().index()) + 1;
                while (minHeap.peek().index() < windowStart) {
                    minHeap.poll();
                }
                while (maxHeap.peek().index() < windowStart) {
                    maxHeap.poll();
                }
            }

            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    /**
     * https://leetcode.com/problems/maximum-total-importance-of-roads/
     * */
    public static long maximumTotalImportanceOfRoads(int n, int[][] roads) {
        if (n < 1) {
            return 0;
        }

        if (roads == null) {
            roads = new int[0][];
        }

        class City {
            public int id;
            public int degree;
            public int value;
            public City(int id) {
                this.id = id;
            }
        }

        var cities = new HashMap<Integer, City>();
        for (int i = 0; i < n; i++) {
            cities.put(i, new City(i));
        }

        for (int[] road : roads) {
            cities.get(road[0]).degree++;
            cities.get(road[1]).degree++;
        }

        var ordering = new PriorityQueue<City>(Comparator.comparingInt(a -> a.degree));
        ordering.addAll(cities.values());

        int rank = 1;
        while (!ordering.isEmpty()) {
            ordering.remove().value = rank;
            rank++;
        }

        long totalImportance = 0L;
        for (int[] road : roads) {
            totalImportance += cities.get(road[0]).value + cities.get(road[1]).value;
        }

        return totalImportance;
    }
}