package com.bteshome.algorithms.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class GreedyAlgorithms3 {
    /**
     * https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs-ii/
     * */
    public static int minimumTimeToFinishAllJobsII(int[] jobs, int[] workers) {
        if (jobs == null || workers == null || jobs.length != workers.length || jobs.length == 0) {
            return 0;
        }

        Arrays.sort(jobs);
        Arrays.sort(workers);
        int days = 0;

        for (int i = 0; i < jobs.length; i++) {
            int currDays = (int)(Math.ceil(((double)jobs[i]) / workers[i]));
            days = Math.max(days, currDays);
        }

        return days;
    }

    /**
     * https://leetcode.com/problems/campus-bikes/
     * */
    public static int[] assignBikes_TODO(int[][] workers, int[][] bikes) {
        if (workers == null || bikes == null || workers.length > bikes.length) {
            throw new IllegalArgumentException();
        }

        record Bike(int id, int[] position){};

        Bike[] bikeEntries = new Bike[bikes.length];

        for (int i = 0; i < bikes.length; i++) {
            bikeEntries[i] = new Bike(i, bikes[i]);
        }

        Arrays.sort(workers, Comparator.comparingInt(a -> a[0] + a[1]));
        Arrays.sort(bikeEntries, Comparator.comparingInt(a -> a.position()[0] + a.position()[1]));

        int[] assignments = new int[workers.length];

        for (int i = 0; i < workers.length; i++) {
            assignments[i] = bikeEntries[i].id();
        }

        return assignments;
    }

    /**
     * https://leetcode.com/problems/non-overlapping-intervals/
     * */
    public static int eraseOverlappingIntervals(int[][] intervals) {
        if (intervals == null || intervals.length < 2) {
            return 0;
        }

        var ordering = new PriorityQueue<int[]>(Comparator.comparingInt(a -> a[1]));

        ordering.addAll(Arrays.asList(intervals));

        int removals = 0;

        while (!ordering.isEmpty()) {
            var current = ordering.remove();
            if (!ordering.isEmpty()) {
                var next = ordering.peek();
                if (current[1] > next[0]) {
                    ordering.remove();
                    removals++;
                    if (current[1] < next[1]) {
                        ordering.add(current);
                    } else {
                        ordering.add(next);
                    }
                }
            }
        }

        return removals;
    }

    /**
     * https://leetcode.com/problems/minimum-health-to-beat-game/
     * */
    public static long minimumHealth(int[] damage, int armor) {
        int max = 0;
        long sum = 0;

        for (int d : damage) {
            max = Math.max(max, d);
            sum += d;
        }

        armor = Math.min(armor, max);

        long loss = sum - armor;

        return loss + 1;
    }
}
