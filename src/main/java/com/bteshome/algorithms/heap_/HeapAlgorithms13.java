package com.bteshome.algorithms.heap_;

import java.util.*;

public class HeapAlgorithms13 {
    /**
     * https://leetcode.com/problems/single-threaded-cpu/
     * */
    public static int[] getTaskProcessingOrder(int[][] tasks) {
        if (tasks == null || tasks.length == 0) {
            return new int[0];
        }

        record Task(int id, int enqueueTime, int processingTime){}

        var sortedByProcessingTime = new PriorityQueue<Task>((a, b) -> {
            if (a.processingTime() == b.processingTime()) {
                return a.id() - b.id();
            }
            return a.processingTime - b.processingTime;
        });
        var sortedByEnqueueTime = new PriorityQueue<Task>(Comparator.comparingInt(Task::enqueueTime));

        for (int i = 0; i < tasks.length; i++) {
            int enqueueTime = tasks[i][0];
            int processingTime = tasks[i][1];
            sortedByEnqueueTime.offer(new Task(i, enqueueTime, processingTime));
        }

        int time = sortedByEnqueueTime.peek().enqueueTime();
        int[] order = new int[tasks.length];
        int index = 0;

        while (!(sortedByEnqueueTime.isEmpty() && sortedByProcessingTime.isEmpty())) {
            while (!sortedByEnqueueTime.isEmpty() && sortedByEnqueueTime.peek().enqueueTime() <= time) {
                sortedByProcessingTime.offer(sortedByEnqueueTime.poll());
            }
            if (sortedByProcessingTime.isEmpty()) {
                time = sortedByEnqueueTime.peek().enqueueTime();
            } else {
                var nextTask = sortedByProcessingTime.poll();
                order[index] = nextTask.id();
                index++;
                time += nextTask.processingTime();
            }
        }

        return order;
    }

    /**
     * https://leetcode.com/problems/furthest-building-you-can-reach/
     * */
    public static int furthestBuildingYouCanReach(int[] heights, int bricks, int ladders) {
        if (heights == null || heights.length == 0) {
            return -1;
        }

        var buildingsThatUseLadder = new PriorityQueue<Integer[]>(Comparator.comparingInt(a -> a[1]));
        int furthest = 0;

        for (int i = 0; i < heights.length; i++) {
            furthest = i;

            if (i == heights.length - 1) {
                break;
            }

            var heightDifference = heights[i + 1] - heights[i];

            if (heightDifference <= 0) {
                continue;
            }

            if (buildingsThatUseLadder.size() < ladders) {
                buildingsThatUseLadder.offer(new Integer[]{i, heightDifference});
            } else {
                if (!buildingsThatUseLadder.isEmpty() && heightDifference > buildingsThatUseLadder.peek()[1]) {
                    bricks -= buildingsThatUseLadder.poll()[1];
                    buildingsThatUseLadder.offer(new Integer[]{i, heightDifference});
                } else {
                    bricks -= heightDifference;
                }
                if (bricks < 0) {
                    break;
                }
            }
        }

        return furthest;
    }
}
