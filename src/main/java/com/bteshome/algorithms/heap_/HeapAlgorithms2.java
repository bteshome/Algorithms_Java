package com.bteshome.algorithms.heap_;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class HeapAlgorithms2 {
    /**
     * https://leetcode.com/problems/high-five/description/
     * */
    public static int[][] highFive(int[][] items) {
        if (items == null) {
            return new int[0][];
        }

        var scores = new TreeMap<Integer, Student>();

        for (int[] item : items) {
            if (!scores.containsKey(item[0])) {
                scores.put(item[0], new Student(item[0]));
            }
            scores.get(item[0]).addScore(item[1]);
        }

        var averages = new int[scores.size()][];

        int i = 0;
        for (Student student : scores.values()) {
            averages[i] = new int[]{student.getId(), student.getAverage()};
            i++;
        }

        return averages;
    }

    private static class Student implements Comparable<Student> {
        private final int id;
        private final PriorityQueue<Integer> topScores = new PriorityQueue<>();

        public Student(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public int getAverage() {
            int sum = 0;
            int count = topScores.size();
            while (!topScores.isEmpty()) {
                System.out.print(" " + topScores.peek());
                sum += topScores.remove();
            }
            return sum / count;
        }

        public int compareTo(Student other) {
            return id - other.id;
        }

        public void addScore(int score) {
            if (topScores.size() < 5) {
                topScores.add(score);
            } else if (score > topScores.peek()) {
                topScores.remove();
                topScores.add(score);
            }
        }
    }
}
