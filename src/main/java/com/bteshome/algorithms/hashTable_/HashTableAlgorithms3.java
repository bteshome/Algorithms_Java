package com.bteshome.algorithms.hashTable_;

import java.util.*;

public class HashTableAlgorithms3 {
    /**
     * https://leetcode.com/problems/longest-substring-without-repeating-characters/
     * NOTE: there is also a version that uses queue.
     * */
    public static int lengthOfLongestSubstringWithoutRepeatingCharacters(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        record Entry(int index, char value){}

        HashMap<Character, Integer> occurrence = new HashMap<>();
        int maxLength = 1;
        int windowStart = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (occurrence.containsKey(c)) {
                int oldIndex = occurrence.get(c);
                for (int j = windowStart; j <= oldIndex; j++) {
                    occurrence.remove(s.charAt(j));
                }
                windowStart = oldIndex + 1;
            } else {
                maxLength = Math.max(maxLength, i - windowStart + 1);
            }

            occurrence.put(c, i);
        }

        return maxLength;
    }

    /**
     * https://leetcode.com/problems/meeting-rooms-ii/
     * NOTE: - there is also a priority queue based solution, which is slightly faster.
     * */
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null) {
            return 0;
        }

        if (intervals.length < 2) {
            return intervals.length;
        }

        var eventsStartingAt = new HashMap<Integer, List<int[]>>();
        var eventsEndingAt = new HashMap<Integer, List<int[]>>();
        var times = new TreeSet<Integer>();

        int maxRooms = 0;
        int rooms = 0;
        int minTime = Integer.MAX_VALUE;
        int maxTime = Integer.MIN_VALUE;

        for (int[] interval : intervals) {
            times.add(interval[0]);
            times.add(interval[1]);
            minTime = Math.min(minTime, interval[0]);
            maxTime = Math.max(maxTime, interval[1]);

            if (!eventsStartingAt.containsKey(interval[0])) {
                eventsStartingAt.put(interval[0], new ArrayList<>());
            }
            eventsStartingAt.get(interval[0]).add(interval);
            if (!eventsEndingAt.containsKey(interval[1])) {
                eventsEndingAt.put(interval[1], new ArrayList<>());
            }
            eventsEndingAt.get(interval[1]).add(interval);
        }

        for (int time : times) {
            if (eventsStartingAt.containsKey(time)) {
                rooms += eventsStartingAt.get(time).size();
            }
            if (eventsEndingAt.containsKey(time)) {
                rooms -= eventsEndingAt.get(time).size();
            }
            maxRooms = Math.max(maxRooms, rooms);
        }

        return maxRooms;
    }
}