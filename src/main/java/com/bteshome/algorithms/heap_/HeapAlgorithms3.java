package com.bteshome.algorithms.heap_;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HeapAlgorithms3 {
    /**
     * https://leetcode.com/problems/reorganize-string/
     * */
    public static String reorganizeString(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        var frequencies = new HashMap<Character, Integer>();
        var ordering = new PriorityQueue<Map.Entry<Character, Integer>>((a, b) -> Integer.compare(b.getValue(), a.getValue()));
        var buffer = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            frequencies.put(s.charAt(i), frequencies.getOrDefault(s.charAt(i), 0) + 1);
        }

        ordering.addAll(frequencies.entrySet());

        while (!ordering.isEmpty()) {
            var top = ordering.remove();

            if (buffer.isEmpty() || buffer.charAt(buffer.length() - 1) != top.getKey()) {
                buffer.append(top.getKey());
            } else if (ordering.isEmpty()) {
                return "";
            } else {
                var nextTop = ordering.remove();
                buffer.append(nextTop.getKey());
                if (nextTop.getValue() > 1) {
                    nextTop.setValue(nextTop.getValue() - 1);
                    ordering.add(nextTop);
                }
                buffer.append(top.getKey());
            }

            if (top.getValue() > 1) {
                top.setValue(top.getValue() - 1);
                ordering.add(top);
            }
        }

        return buffer.toString();
    }
}
