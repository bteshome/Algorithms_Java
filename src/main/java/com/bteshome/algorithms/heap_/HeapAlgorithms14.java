package com.bteshome.algorithms.heap_;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class HeapAlgorithms14 {
    /**
     * https://leetcode.com/problems/the-number-of-the-smallest-unoccupied-chair/
     * */
    public static int smallestChair(int[][] times, int targetFriend) {
        if (times == null || times.length == 0 || targetFriend < 0 || targetFriend >= times.length) {
            return -1;
        }

        var nextAvailableChairs = new PriorityQueue<Integer>();
        nextAvailableChairs.offer(0);

        record Friend(int id, int arrival, int leaving){};
        record Sitting(Friend friend, int chair){}

        var arrivals = new PriorityQueue<Friend>(Comparator.comparingInt(Friend::arrival));
        var sittings = new PriorityQueue<Sitting>(Comparator.comparingInt(a -> a.friend().leaving()));

        int earliestArrivalTime = Integer.MAX_VALUE;
        int latestLeavingTime = Integer.MIN_VALUE;

        for (int i = 0; i < times.length; i++) {
            int arrivalTime = times[i][0];
            int leavingTime = times[i][1];
            arrivals.offer(new Friend(i, arrivalTime, leavingTime));
            earliestArrivalTime = Math.min(earliestArrivalTime, arrivalTime);
            latestLeavingTime = Math.max(latestLeavingTime, leavingTime);
        }

        for (int time = earliestArrivalTime; time <= latestLeavingTime; time++) {
            while (!sittings.isEmpty() && sittings.peek().friend().leaving() == time) {
                nextAvailableChairs.offer(sittings.poll().chair());
            }

            while (!arrivals.isEmpty() && arrivals.peek().arrival() == time) {
                var nextArrival = arrivals.remove();

                if (nextArrival.id == targetFriend) {
                    return nextAvailableChairs.peek();
                }

                if (nextAvailableChairs.size() == 1) {
                    nextAvailableChairs.offer(nextAvailableChairs.peek() + 1);
                }

                sittings.offer(new Sitting(nextArrival, nextAvailableChairs.poll()));
            }
        }

        return 0;
    }

    /**
     * https://leetcode.com/problems/car-pooling/
     * */
    public static boolean carPooling(int[][] trips, int capacity) {
        var tripsStartingAt = new HashMap<Integer, List<int[]>>();
        var tripsEndingAt = new HashMap<Integer, List<int[]>>();
        var stops = new TreeSet<Integer>();

        for (int[] trip : trips) {
            int start = trip[1];
            int end = trip[2];
            stops.add(start);
            stops.add(end);
            if (!tripsStartingAt.containsKey(start)) {
                tripsStartingAt.put(start, new ArrayList<>());
            }
            tripsStartingAt.get(start).add(trip);
            if (!tripsEndingAt.containsKey(end)) {
                tripsEndingAt.put(end, new ArrayList<>());
            }
            tripsEndingAt.get(end).add(trip);
        }

        for (int stop : stops) {
            if (tripsStartingAt.containsKey(stop)) {
                for (int[] trip : tripsStartingAt.get(stop)) {
                    capacity -= trip[0];
                };
            }
            if (tripsEndingAt.containsKey(stop)) {
                for (int[] trip : tripsEndingAt.get(stop)) {
                    capacity += trip[0];
                }
            }

            if (capacity < 0) {
                return false;
            }
        }

        return true;
    }
}