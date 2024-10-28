package com.bteshome.algorithms.heap_;

import java.util.*;

public class HeapAlgorithms12 {
    /**
     * https://leetcode.com/problems/find-k-closest-elements/editorial/
     * NOTE: this passes leetcode time limit test.
     *       However, it is not the most efficient solution.
     *       Since the array is already sorted, try binary search instead.
     * */
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        if (arr == null) {
            return List.of();
        }

        var ordering = new PriorityQueue<Integer>((a, b) -> {
            var diff1 = Math.abs(a - x);
            var diff2 = Math.abs(b - x);
            if (diff1 == diff2) {
                return a - b;
            } else {
                return diff1 - diff2;
            }
        });

        for (int num : arr) {
            ordering.offer(num);
        }

        var heap2 = new PriorityQueue<Integer>();

        List<Integer> output = new ArrayList<>(k);

        while (!ordering.isEmpty() && k > 0) {
            heap2.add(ordering.remove());
            k--;
        }

        while (!heap2.isEmpty()) {
            output.add(heap2.remove());
        }

        return output;
    }

    /**
     * https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/
     * */
    public static int maxEventsAttended(int[][] events) {
        if (events == null || events.length == 0) {
            return 0;
        }

        record Event(int id, int start, int end){}
        record Attendance(Event event, int day){}

        var ordering = new PriorityQueue<Event>((a, b) -> {
            if (a.end == b.end) {
                return a.start - b.start;
            }
            return a.end - b.end;
        });

        var eventsStartingOnDay = new HashMap<Integer, List<Event>>();
        var attendance = new ArrayList<Attendance>();
        int minStartDay = Integer.MAX_VALUE;
        int maxEndDay = Integer.MIN_VALUE;

        for (int i = 0; i < events.length; i++) {
            int start = events[i][0];
            int end = events[i][1];
            minStartDay = Math.min(minStartDay, start);
            maxEndDay = Math.max(maxEndDay, end);
            if (!eventsStartingOnDay.containsKey(start)) {
                eventsStartingOnDay.put(start, new ArrayList<>());
            }
            eventsStartingOnDay.get(start).add(new Event(i, start, end));
        }

        for (int day = minStartDay; day <= maxEndDay ; day++) {
            while (!ordering.isEmpty() && ordering.peek().end() < day) {
                ordering.poll();
            }

            if (eventsStartingOnDay.containsKey(day)) {
                ordering.addAll(eventsStartingOnDay.get(day));
            }

            if (!ordering.isEmpty()) {
                 attendance.add(new Attendance(ordering.poll(), day));
            }
        }

        return attendance.size();
    }
}
