package com.bteshome.algorithms.heap_;

import java.util.Arrays;
import java.util.Comparator;
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


}
