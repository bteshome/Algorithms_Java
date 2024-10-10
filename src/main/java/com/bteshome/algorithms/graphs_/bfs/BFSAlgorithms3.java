package com.bteshome.algorithms.graphs_.bfs;

import com.bteshome.algorithms.graphs_.Graph;
import com.bteshome.algorithms.graphs_.State;
import com.bteshome.algorithms.graphs_.Vertex;

import java.util.*;

public class BFSAlgorithms3 {
    /**
     * TODO - fails the leetcode time limit test.
     * https://leetcode.com/problems/words-within-two-edits-of-dictionary/
     * */
    public static List<String> twoEditWordsTODO(String[] queries, String[] dictionary) {
        var matches = new ArrayList<String>();

        if (queries != null && dictionary != null && dictionary.length > 0) {
            var dictionaryAsSet = new HashSet<String>(Arrays.stream(dictionary).toList());
            for (var query : queries) {
                twoEditWordsTODO(query, dictionaryAsSet, matches);
            }
        }

        return matches;
    }

    private static class Transformation {
        public final String value;
        public final int steps;
        public Transformation(String value, int steps) {
            this.value = value;
            this.steps = steps;
        }
    }

    private static void twoEditWordsTODO(String beginWord, HashSet<String> dictionary, List<String> matches) {
        var visited = new HashSet<String>();
        Queue<Transformation> q = new LinkedList<>();

        visited.add(beginWord);
        q.add(new Transformation(beginWord, 0));

        while (!q.isEmpty()) {
            var front = q.remove();

            if (dictionary.contains(front.value)) {
                matches.add(beginWord);
                return;
            }

            if (front.steps == 2) {
                continue;
            }

            for (int i = 0; i < front.value.length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    if (front.value.charAt(i) != c) {
                        var transformation = front.value.substring(0, i) + c + front.value.substring(i + 1);
                        if (dictionary.contains(transformation)) {
                            matches.add(beginWord);
                            return;
                        }
                        if (!visited.contains(transformation)) {
                            q.add(new Transformation(transformation, front.steps + 1));
                            visited.add(transformation);
                        }
                    }
                }
            }
        }
    }

    /**
     * TODO - there is a DFS implementation as well. Check it out.
     * https://leetcode.com/problems/parallel-courses/description/?envType=study-plan-v2&envId=premium-algo-100
     * */
    public static int parallelCoursesTODO(int n, int[][] relations) {
        if (n < 1) {
            return 0;
        }

        if (relations == null || relations.length == 0) {
            return 1;
        }

        var g = new Graph<Integer>();

        for (int i = 1; i <= n; i++) {
            g.addVertex(i);
        }

        for (int[] relation : relations) {
            g.addEdge(relation[0], relation[1]);
        }

        Queue<Vertex<Integer>> q = new LinkedList<>();
        int maxSemesters = -1;

        for (var start : g.getVertices().values()) {
            if (start.getIncomingEdges().isEmpty()) {
                q.add(start);
                start.setState(State.Visiting);
                start.setRank(1);
            }
        }

        while (!q.isEmpty()) {
            var front = q.remove();
            maxSemesters = Math.max(maxSemesters, front.getRank());

            for (var neighbor : front.getNeighbors().values()) {
                if (neighbor.getState() == State.Unvisited) {
                    q.add(neighbor);
                    neighbor.setState(State.Visiting);
                    neighbor.setRank(front.getRank() + 1);
                }
            }
        }

        return maxSemesters;
    }
}
