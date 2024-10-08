package com.bteshome.algorithms.graphs_.dfs;

import com.bteshome.algorithms.graphs_.Graph;
import com.bteshome.algorithms.graphs_.State;
import com.bteshome.algorithms.graphs_.Vertex;

import java.util.*;

public class DFSAlgorithms2 {
    /**
     * https://leetcode.com/problems/course-schedule/?envType=problem-list-v2&envId=graph&difficulty=MEDIUM
     * */
    public static boolean courseSchedule(int numCourses, int[][] prerequisites) {
        if (numCourses < 1 || prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        
        var g = new Graph<Integer>();

        for (int i = 0; i < numCourses; i++) {
            Vertex<Integer> v = g.addVertex(i);
        }

        for (int[] prerequisite : prerequisites) {
            g.addEdge(prerequisite[0], prerequisite[1]);
        }

        for (var start : g.getVertices().values()) {
            if (!courseSchedule(start)) {
                return false;
            }
        }

        return true;
    }

    private static boolean courseSchedule(Vertex<Integer> vertex) {
        if (vertex.getState() == State.Visiting) {
            return false;
        }

        if (vertex.getState() == State.Visited) {
            return true;
        }

        vertex.setState(State.Visiting);

        for (Vertex<Integer> neighbor : vertex.getNeighbors().values()) {
            if (!courseSchedule(neighbor)) {
                return false;
            }
        }

        vertex.setState(State.Visited);

        return true;
    }

    /**
     * https://leetcode.com/problems/course-schedule-ii/?envType=problem-list-v2&envId=graph&difficulty=MEDIUM
     * */
    public static int[] courseScheduleII(int numCourses, int[][] prerequisites) {
        var g = new Graph<Integer>();
        var order = new ArrayList<Integer>(numCourses);

        for (int i = 0; i < numCourses; i++) {
            g.addVertex(i);
        }

        if (prerequisites != null) {
            for (int[] prerequisite : prerequisites) {
                g.addEdge(prerequisite[0], prerequisite[1]);
            }
        }

        for (var start : g.getVertices().values()) {
            if (!courseScheduleII(start, order)) {
                return new int[0];
            }
        }

        return order.stream().mapToInt(e -> e).toArray();
    }

    private static boolean courseScheduleII(Vertex<Integer> vertex, List<Integer> order) {
        if (vertex.getState() == State.Visiting) {
            return false;
        }

        if (vertex.getState() == State.Visited) {
            return true;
        }

        vertex.setState(State.Visiting);

        for (var neighbor : vertex.getNeighbors().values()) {
            if (!courseScheduleII(neighbor, order)) {
                return false;
            }
        }

        vertex.setState(State.Visited);
        order.add(vertex.getKey());

        return true;
    }
}
