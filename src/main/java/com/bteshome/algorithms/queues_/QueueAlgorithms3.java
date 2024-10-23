package com.bteshome.algorithms.queues_;

import java.util.*;

public class QueueAlgorithms3 {
    /**
     * NOTE: in this solution, list is being used as a queue.
     *       however, the concept is still relevant to queues.
     * https://leetcode.com/problems/find-the-winner-of-the-circular-game/submissions/1429593908/
     * */
    public static int findTheWinnerOfACircularGame(int n, int k) {
        if (n < 1) {
            return -1;
        }

        List<Integer> l = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            l.add(i);
        }

        int start = 0;

        while (l.size() > 1) {
            int k2 = (start + k) % l.size();
            if (k2 == 0) {
                k2 = l.size();
            }
            l.remove(k2 - 1);
            start = k2 - 1;
        }

        return l.get(0);
    }

    /**
     * https://leetcode.com/problems/sliding-window-maximum/
     * */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length) {
            return new int[0];
        }

        Deque<Integer> q = new LinkedList<>();
        var maximums = new ArrayList<Integer>(nums.length - k + 1);

        for (int i = 0; i < nums.length; i++) {
            while (!q.isEmpty() && (q.peekFirst() + k - 1) < i) {
                q.removeFirst();
            }

            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                q.removeLast();
            }

            q.addLast(i);

            if (i >= k - 1) {
                maximums.add(nums[q.peekFirst()]);
            }
        }

        return maximums.stream().mapToInt(e -> e).toArray();
    }


}