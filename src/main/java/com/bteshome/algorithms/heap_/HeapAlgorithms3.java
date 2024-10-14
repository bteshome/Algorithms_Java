package com.bteshome.algorithms.heap_;

import java.util.HashMap;
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
        var ordering = new PriorityQueue<Occurrence>();
        var buffer = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!frequencies.containsKey(c)) {
                frequencies.put(c, 1);
            } else {
                frequencies.put(c, frequencies.get(c) + 1);
            }
        }

        for (Character character : frequencies.keySet()) {
            ordering.add(new Occurrence(character, frequencies.get(character)));
        }

        while (!ordering.isEmpty()) {
            var top = ordering.remove();
            if (!buffer.isEmpty() && top.getCharacter().equals(buffer.charAt(buffer.length() - 1))) {
                if (ordering.isEmpty()) {
                    return "";
                } else {
                    var nextTop = ordering.remove();
                    buffer.append(nextTop.getCharacter());
                    if (nextTop.getFrequency() > 1) {
                        ordering.add(new Occurrence(nextTop.getCharacter(), nextTop.getFrequency() - 1));
                    }
                    ordering.add(top);
                }
            } else {
                buffer.append(top.getCharacter());
                if (top.getFrequency() > 1) {
                    ordering.add(new Occurrence(top.getCharacter(), top.getFrequency() - 1));
                }
            }
        }

        return buffer.toString();
    }

    private static class Occurrence implements Comparable<Occurrence> {
        private final Character character;
        private final Integer frequency;

        public Occurrence(Character character, Integer frequency) {
            this.character = character;
            this.frequency = frequency;
        }

        public Integer getFrequency() {
            return frequency;
        }

        public Character getCharacter() {
            return character;
        }

        @Override
        public int compareTo(Occurrence o) {
            return o.getFrequency() - getFrequency();
        }
    }
}
