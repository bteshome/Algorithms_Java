package com.bteshome.algorithms.heap_;

import java.util.*;

public class HeapAlgorithms8TODO {
    /**
     * TODO - it exceeds leetcode time limit.
     * https://leetcode.com/problems/number-of-people-aware-of-a-secret/
     * */
    public static int numberOfPeopleAwareOfSecret(int n, int delay, int forget) {
        if (n < 1 || forget < 1) {
            return 0;
        }

        class Person {
            public final int id;
            public final int dayOfKnowing;
            public int nextShareDay;

            public Person(int id, int dayOfKnowing, int delay) {
                this.id = id;
                this.dayOfKnowing = dayOfKnowing;
                this.nextShareDay = dayOfKnowing + delay;
            }
        }

        var ordering = new PriorityQueue<Person>(Comparator.comparingInt(a -> a.nextShareDay));
        var numberOfPeopleAware = 1;
        ordering.add(new Person(1, 1, delay));

        while (!ordering.isEmpty()) {
            var top = ordering.remove();

            if (top.nextShareDay > n) {
                return numberOfPeopleAware;
            }

            if (top.nextShareDay >= top.dayOfKnowing + forget) {
                numberOfPeopleAware--;
                continue;
            }

            numberOfPeopleAware++;
            System.out.printf("Day %s - %s shared with %s \n", top.nextShareDay, top.id, numberOfPeopleAware);
            ordering.add(new Person(numberOfPeopleAware, top.nextShareDay, delay));
            ordering.add(top);
            top.nextShareDay++;
        }

        return numberOfPeopleAware;
    }

    /**
     * TODO - it yeilds a wrong answer on some of the leetcode test cases.
     *        not sure if the test case is valid.
     * https://leetcode.com/problems/maximum-number-of-tasks-you-can-assign/
     * */
    public static int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        var taskList = new ArrayList<Integer>(tasks.length);
        for (int task : tasks) {
            taskList.add(task);
        }
        var workersWithPillsQueue = new PriorityQueue<Integer>();

        Collections.sort(taskList);
        Arrays.sort(workers);

        int tasksAassigned = 0;

        for (int worker : workers) {
            int taskIndex = findMatchingTask(taskList, worker, 0, taskList.size() - 1);
            if (taskIndex != -1) {
                tasksAassigned++;
                taskList.remove(taskIndex);
            } else if (pills > 0) {
                workersWithPillsQueue.add(worker);
            }
        }

        while (!workersWithPillsQueue.isEmpty() && !taskList.isEmpty() && pills > 0) {
            var worker = workersWithPillsQueue.remove();
            if (worker + strength >= taskList.get(0)) {
                taskList.remove(0);
                tasksAassigned++;
                pills--;
            }
        }

        return tasksAassigned;
    }

    private static int findMatchingTask(List<Integer> tasks, int worker, int start, int end) {
        if (start > end) {
            return -1;
        }
        if (worker >= tasks.get(end)) {
            return end;
        }
        if (worker < tasks.get(start)) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (worker >= tasks.get(mid)) {
            return findMatchingTask(tasks, worker, mid, end - 1);
        } else {
            return findMatchingTask(tasks, worker, start, mid - 1);
        }
    }
}
