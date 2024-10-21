package com.bteshome.algorithms.heap_;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class HeapAlgorithms6 {
    /**
     * https://leetcode.com/problems/meeting-rooms-ii
     * */
    public static int meetingRoomsII(int[][] intervals) {
        if (intervals == null) {
            return 0;
        }

        if (intervals.length < 2) {
            return intervals.length;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        var maxRoomsNeeded = 0;
        var rooms = new PriorityQueue<Integer>();

        for (int[] interval : intervals) {
            if (!rooms.isEmpty() && interval[0] >= rooms.peek()) {
                rooms.remove();
                rooms.add(interval[1]);
            } else {
                rooms.add(interval[1]);
                maxRoomsNeeded = Math.max(maxRoomsNeeded, rooms.size());
            }
        }

        return maxRoomsNeeded;
    }

    /**
     * TODO - wrong answer.
     * https://leetcode.com/problems/rearrange-string-k-distance-apart/
     * */
    public static String rearrangeStringKDistanceApart(String s, int k) {
        if (s == null || s.length() < 2 || k < 1) {
            return s;
        }

        var frequencies = new HashMap<Character, Integer>();
        var ordering = new PriorityQueue<Entry>((a,b) -> Integer.compare(b.frequency, a.frequency));
        var buffer = new StringBuilder(s.length());

        for (int i = 0; i < s.length(); i++) {
            char character = s.charAt(i);
            if (!frequencies.containsKey(character)) {
                frequencies.put(character, 1);
            } else {
                frequencies.put(character, frequencies.get(character) + 1);
            }
        }

        for (var character : frequencies.keySet()) {
            ordering.add(new Entry(character, frequencies.get(character)));
        }

        while (!ordering.isEmpty()) {
            if (!insert(ordering, buffer, k)) {
                return "";
            }
        }

        return buffer.toString();
    }

    private static boolean insert(PriorityQueue<Entry> ordering, StringBuilder buffer, int k) {
        var current = ordering.remove();

        if (!canInsert(current, buffer, k)) {
            if (ordering.isEmpty()) {
                return false;
            }
            if (!insert(ordering, buffer, k)) {
                return false;
            }
            if (!canInsert(current, buffer, k)) {
                return false;
            }
        }

        buffer.append(current.character);

        if (current.frequency > 1) {
            current.frequency--;
            ordering.add(current);
        }

        return true;
    }

    private static boolean canInsert(Entry entry, StringBuilder buffer, int k) {
        for (int i = 0, j = buffer.length() - 1; i < k && j >= 0; i++, j--) {
            if (entry.character.equals(buffer.charAt(j))) {
                return false;
            }
        }

        return true;
    }

    private static class Entry {
        public Character character;
        public Integer frequency;
        public Entry(Character character, Integer frequency) {
            this.character = character;
            this.frequency = frequency;
        }
    }
}
