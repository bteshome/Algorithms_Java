package com.bteshome.algorithms.hashTable_;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HashTableAlgorithms1 {
    /**
     * https://leetcode.com/problems/palindrome-permutation/description/
     * */
    public static boolean palindromePermutation(String s) {
        if (s == null) {
            return false;
        }

        if (s.isEmpty()) {
            return true;
        }

        var frequency = new HashMap<Character, Integer>();

        for (int i = 0; i < s.length(); i++) {
            var current = s.charAt(i);
            if (frequency.containsKey(current)) {
                frequency.put(current, frequency.get(current) + 1);
            } else {
                frequency.put(current, 1);
            }
        }

        var oddCount = frequency.values().stream().filter(e -> e % 2 == 1);

        if (s.length() % 2 == 0) {
            return oddCount.findAny().isEmpty();
        } else {
            return oddCount.count() == 1;
        }
    }

    /**
     * https://leetcode.com/problems/task-scheduler-ii/
     * */
    public static long taskSchedulerII(int[] tasks, int space) {
        if (tasks == null || tasks.length == 0 || space < 0) {
            return 0;
        }

        long day = 0;
        int taskIndex = 0;
        var lastScheduled = new HashMap<Integer, Long>();
        lastScheduled.put(tasks[taskIndex], day);

        while (taskIndex < tasks.length - 1) {
            taskIndex++;
            day++;

            int taskType = tasks[taskIndex];

            if (lastScheduled.containsKey(taskType) && day < lastScheduled.get(taskType) + space + 1) {
                day = lastScheduled.get(taskType) + space + 1;
            }

            lastScheduled.put(taskType, day);
        }

        return day + 1;
    }
}
