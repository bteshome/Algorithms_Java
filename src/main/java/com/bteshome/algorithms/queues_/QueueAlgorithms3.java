package com.bteshome.algorithms.queues_;

import java.util.ArrayList;
import java.util.List;

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
}
