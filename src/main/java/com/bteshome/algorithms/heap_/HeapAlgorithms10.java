package com.bteshome.algorithms.heap_;

import java.util.*;

public class HeapAlgorithms10 {
    /**
     * https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
     * Beautiful!
     * */
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        var pairs = new ArrayList<List<Integer>>();

        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return pairs;
        }

        record Entry(int sum, int i, int j){}

        var minHeap = new PriorityQueue<Entry>(Comparator.comparingInt(a -> a.sum));
        var visited = new HashSet<Entry>();
        minHeap.offer(new Entry(nums1[0] + nums2[0], 0, 0));

        while (pairs.size() < k && !minHeap.isEmpty()) {
            var min = minHeap.poll();
            int i = min.i();
            int j = min.j();
            pairs.add(List.of(nums1[i], nums2[j]));

            if (i < nums1.length - 1) {
                var entry = new Entry(nums1[i + 1] + nums2[j], i + 1, j);
                if (!visited.contains(entry)) {
                    minHeap.offer(entry);
                    visited.add(entry);
                }
            }

            if (j < nums2.length - 1) {
                var entry = new Entry(nums1[i] + nums2[j + 1], i, j + 1);
                if (!visited.contains(entry)) {
                    minHeap.offer(entry);
                    visited.add(entry);
                }
            }
        }

        return pairs;
    }

    /**
     * https://leetcode.com/problems/top-k-frequent-elements/
     * */
    public static int[] topKFrequent(int[] nums, int k) {
        if (nums == null) {
            return nums;
        }

        var frequencies = new HashMap<Integer, Integer>();
        var ordering = new PriorityQueue<Map.Entry<Integer, Integer>>((a, b) -> Integer.compare(b.getValue(), a.getValue()));
        k = Math.min(k, nums.length);
        var output = new int[k];

        for (int num : nums) {
            frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);
        }

        ordering.addAll(frequencies.entrySet());

        for (int i = 0; i < k; i++) {
            output[i] = ordering.poll().getKey();
        }

        return output;
    }

    /**
     * https://leetcode.com/problems/ugly-number-ii/
     * */
    public static int nthUglyNumber(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n is less than 1");
        }

        var minHeap = new PriorityQueue<Long>();
        var visited = new HashSet<Long>();

        minHeap.add(1L);
        visited.add(1L);

        while (true) {
            var min = minHeap.remove();

            n--;

            if (n == 0) {
                return (int)(long)min;
            }

            if (!visited.contains(min * 2L)) {
                minHeap.offer(min * 2L);
                visited.add(min * 2L);
            }
            if (!visited.contains(min * 3L)) {
                minHeap.offer(min * 3L);
                visited.add(min * 3L);
            }
            if (!visited.contains(min * 5L)) {
                minHeap.offer(min * 5L);
                visited.add(min * 5L);
            }
        }
    }
}
