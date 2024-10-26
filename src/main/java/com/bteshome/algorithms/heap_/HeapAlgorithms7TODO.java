package com.bteshome.algorithms.heap_;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HeapAlgorithms7TODO {
    /**
     * TODO - wrong answer.
     * This is another attempt.
     * https://leetcode.com/problems/rearrange-string-k-distance-apart/
     * */
    public static String rearrangeStringKDistanceApart(String s, int k) {
        if (s == null || s.length() < 2) {
            return s;
        }

        var frequencies = new HashMap<Character, Integer>();
        var ordering = new PriorityQueue<Map.Entry<Character, Integer>>((a, b) -> b.getValue() - a.getValue());
        var buffer = new StringBuilder(s.length());

        for (int i = 0; i < s.length(); i++) {
            frequencies.put(s.charAt(i), frequencies.getOrDefault(s.charAt(i), 0) + 1);
        }

        ordering.addAll(frequencies.entrySet());

        while (!ordering.isEmpty()) {
            if (!rearrangeStringKDistanceApartAppend(ordering.remove(), ordering, buffer, k)) {
                return "";
            }
        }

        return buffer.toString();
    }

    private static boolean rearrangeStringKDistanceApartAppend(Map.Entry<Character, Integer> top, PriorityQueue<Map.Entry<Character, Integer>> ordering, StringBuilder buffer, int k) {
        while (true) {
            var lastIndex = rearrangeStringKDistanceApartLastIndex(top.getKey(), buffer, k);
            if (lastIndex == -1) {
                buffer.append(top.getKey());
                if (top.getValue() > 1) {
                    top.setValue(top.getValue() - 1);
                    ordering.add(top);
                }
                break;
            } else if (ordering.isEmpty()) {
                return false;
            } else {
                var nextTop = ordering.remove();
                ordering.add(top);
                if (!rearrangeStringKDistanceApartAppend(nextTop, ordering, buffer, k)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static int rearrangeStringKDistanceApartLastIndex(Character c, StringBuilder buffer, int k) {
        for (int i = buffer.length() - 1; i >= Math.max(0, buffer.length() - k + 1) ; i--) {
            if (buffer.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }
}
