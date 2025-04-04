package com.bteshome.algorithms.heap_;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class HeapAlgorithms15 {
    /**
     * https://leetcode.com/problems/maximal-score-after-applying-k-operations/
     * */
    public static long maximalScoreAfterKOperations(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        var maxHeap = new PriorityQueue<Integer>((a, b) -> Integer.compare(b, a));
        var maxScore = 0L;

        for (int num : nums) {
            maxHeap.offer(num);
        }

        while (k > 0) {
            var max = maxHeap.poll();
            maxScore += max;
            maxHeap.offer((int)Math.ceil(max / 3.0));
            k--;
        }

        return maxScore;
    }

    /*
    * https://leetcode.com/problems/merge-intervals/
    * */
    public static int[][] merge(int[][] intervals) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            return a[0] - b[0];
        });

        for (int[] interval : intervals)
            q.offer(interval);

        List<int[]> merged = new ArrayList<>();

        while (!q.isEmpty()) {
            int[] front = q.poll();
            if (!q.isEmpty() && overlap(front, q.peek())) {
                q.offer(new int[]{front[0], Math.max(front[1], q.poll()[1])});
            } else {
                merged.add(front);
            }
        }

        int[][] mergedArray = new int[merged.size()][];
        for (int i = 0; i < mergedArray.length; i++)
            mergedArray[i] = merged.get(i);

        return mergedArray;
    }

    private static boolean overlap(int[] interval1, int[] interval2) {
        return interval1[1] >= interval2[0];
    }

    /*
    * https://leetcode.com/problems/k-closest-points-to-origin
    * */
    public static int[][] kClosest(int[][] points, int k) {
        if (points == null || points.length == 0 || k < 1)
            return new int[0][];

        record Entry(int x, int y, double distance) { }

        PriorityQueue<Entry> ordering = new PriorityQueue<>(Comparator.comparingDouble(Entry::distance).reversed());

        for (int[] point : points) {
            double distance = getDistance(point);

            if (ordering.size() < k)
                ordering.offer(new Entry(point[0], point[1], distance));
            else if (distance < ordering.peek().distance()) {
                ordering.poll();
                ordering.offer(new Entry(point[0], point[1], distance));
            }
        }

        var result = new int[Math.min(k, ordering.size())][];

        for (int i = 0; i < result.length; i++) {
            Entry entry = ordering.poll();
            result[i] = new int[]{entry.x(), entry.y()};
        }

        return result;
    }

    private static double getDistance(int[] point) {
        return Math.sqrt(point[0] * point[0] + point[1] * point[1]);
    }
}
