package com.bteshome.algorithms.heap_;

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

}
