package com.bteshome.algorithms.queues_;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class QueueAlgorithms5 {
    public static int lengthOfLongestSubstring(String s) {
        if (s == null)
            return 0;

        Map<Character, Integer> seenPreviously = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!seenPreviously.containsKey(c)) {
                seenPreviously.put(c, i);
                queue.offer(i);
                maxLength = Math.max(maxLength, queue.size());
            } else {
                int prevIndex = seenPreviously.get(c);
                while (!queue.isEmpty() && queue.peek() <= prevIndex)
                    seenPreviously.remove(s.charAt(queue.poll()));
                seenPreviously.put(c, i);
                queue.offer(i);
            }
        }

        return maxLength;
    }
}
