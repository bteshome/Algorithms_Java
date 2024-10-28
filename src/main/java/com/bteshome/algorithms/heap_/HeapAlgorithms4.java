package com.bteshome.algorithms.heap_;

import java.util.HashMap;
import java.util.PriorityQueue;

public class HeapAlgorithms4 {
    /**
     * TODO - it fails leetcode time limit test.
     *      - update. It now passes, but merely.
     * https://leetcode.com/problems/task-scheduler/
     * */
    public static int taskScheduler(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) {
            return 0;
        }

        var frequencies = new int[26];
        var ordering = new PriorityQueue<Task>((a, b) -> b.getFrequency() - a.getFrequency());
        var schedule = new StringBuilder();

        for (char task : tasks) {
            frequencies[task - 'A']++;
        }

        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                ordering.add(new Task(i, frequencies[i]));
            }
        }

        while (!ordering.isEmpty()) {
            doSchedule(schedule, ordering, n);
        }

        System.out.println(schedule);

        return schedule.length();
    }

    private static void doSchedule(StringBuilder schedule, PriorityQueue<Task> ordering, int n) {
        var top = ordering.remove();
        if (canSchedule(schedule, top, n)) {
            schedule.append((char)(top.getType() + 'A'));
            top.decrementFrequency();
        } else if (!ordering.isEmpty()) {
            doSchedule(schedule, ordering, n);
        } else {
            schedule.append(" ");
        }
        if (top.getFrequency() > 0) {
            ordering.add(top);
        }
    }

    private static boolean canSchedule(StringBuilder schedule, Task task, int n) {
        for (int i = 0, j = schedule.length() - 1; i < n && j >= 0; i++,j--) {
            if (task.type == schedule.charAt(j) - 'A') {
                return false;
            }
        }
        return true;
    }

    private static class Task {
        private final int type;
        private Integer frequency;

        public Task(int type, Integer frequency) {
            this.type = type;
            this.frequency = frequency;
        }
        public int getType() {
            return type;
        }
        public Integer getFrequency() {
            return frequency;
        }
        public void decrementFrequency() {
            this.frequency--;
        }
    }
}
