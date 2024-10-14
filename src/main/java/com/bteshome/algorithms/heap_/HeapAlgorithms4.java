package com.bteshome.algorithms.heap_;

import java.util.HashMap;
import java.util.PriorityQueue;

public class HeapAlgorithms4 {
    /*public static int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) {
            return 0;
        }

        var frequencies = new HashMap<Character, Integer>();
        var ordering = new PriorityQueue<Task>();
        var schedule = new StringBuilder();

        for (char task : tasks) {
            if (!frequencies.containsKey(task)) {
                frequencies.put(task, 1);
            } else {
                frequencies.put(task, frequencies.get(task) + 1);
            }
        }

        for (Character task : frequencies.keySet()) {
            ordering.add(new Task(task, frequencies.get(task)));
        }

        while (!ordering.isEmpty()) {
            var top = ordering.remove();
            if (canSchedule(schedule, top, n)) {

            }
        }
    }

    private static boolean canSchedule(StringBuilder schedule, Task task, int n) {
        for (int i = 0, j = schedule.length() - 1; i < n && j >= 0; i++,j--) {
            if (task.type.equals(schedule.charAt(j))) {
                return false;
            }
        }
        return true;
    }

    private static class Task implements Comparable<Task> {
        private final Character type;
        private Integer frequency;

        public Task(Character type, Integer frequency) {
            this.type = type;
            this.frequency = frequency;
        }
        public Character getType() {
            return type;
        }
        public Integer getFrequency() {
            return frequency;
        }
        public void setFrequency(Integer frequency) {
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Task o) {
            return o.getFrequency() - getFrequency();
        }
    }*/
}
