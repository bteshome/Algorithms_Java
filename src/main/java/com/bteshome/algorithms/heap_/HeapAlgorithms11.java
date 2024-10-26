package com.bteshome.algorithms.heap_;

import java.util.*;

public class HeapAlgorithms11 {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * https://leetcode.com/problems/merge-k-sorted-lists/
     * */
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
     * Not finished yet - exceeds time limit.
     * https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
     * */
    public static int longestSubarray(int[] nums, int limit) {
        var mins = new PriorityQueue<Integer>();
        var maxes = new PriorityQueue<Integer>((a, b) -> Integer.compare(b, a));

        int i = 0;
        int windowStart = 0;
        int maxLength = 0;

        for (; i < nums.length; i++) {
            mins.add(nums[i]);
            maxes.add(nums[i]);

            if (Math.abs(mins.peek() - maxes.peek()) > limit) {
                maxLength = Math.max(maxLength, i - windowStart);

                while (Math.abs(mins.peek() - maxes.peek()) > limit) {
                    mins.remove(nums[windowStart]);
                    maxes.remove(nums[windowStart]);
                    windowStart++;
                }
            }
        }

        maxLength = Math.max(maxLength, i - windowStart);

        return maxLength;
    }
}
