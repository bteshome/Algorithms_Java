package com.bteshome.algorithms.graphs_.dfs;

import com.bteshome.algorithms.graphs_.Graph;
import com.bteshome.algorithms.graphs_.State;
import com.bteshome.algorithms.graphs_.Vertex;

import java.util.LinkedList;

public class DFSAlgorithms5 {
    /**
     * NOTE - there is also a BFS implementation that is not finished yet.
     * https://leetcode.com/problems/parallel-courses/description/?envType=study-plan-v2&envId=premium-algo-100
     * */
    public static int parallelCourses(int n, int[][] relations) {
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
            g.addEdge(relation[1], relation[0]);
        }

        int minSemesters = 1;

        for (var start : g.getVertices().values()) {
            if (start.getState() == State.Unvisited) {
                var semesters = parallelCourses(start);
                if (semesters == -1) {
                    return -1;
                } else {
                    minSemesters = Math.max(minSemesters, semesters);
                }
            }
        }

        return minSemesters;
    }

    private static int parallelCourses(Vertex<Integer> vertex) {
        vertex.setState(State.Visiting);
        int rank = 1;

        for (var neighbor : vertex.getNeighbors().values()) {
            if (neighbor.getState() == State.Visiting) {
                return -1;
            }
            else if (neighbor.getState() == State.Unvisited) {
                var neighborRank = parallelCourses(neighbor);
                if (neighborRank == -1) {
                    return -1;
                }
                else {
                    rank = Math.max(rank, 1 + neighborRank);
                }
            } else {
                rank = Math.max(rank, 1 + neighbor.getRank());
            }
        }

        vertex.setState(State.Visited);
        vertex.setRank(rank);

        return rank;
    }

    /**
     * TODO - the leetcode problem seems to have an issue with its test cases.
     * https://leetcode.com/problems/alien-dictionary/
     * */
    public static String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }

        var g = new Graph<Character>();

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                g.addVertex(word.charAt(i));
            }
            for (int i = 1; i < word.length(); i++) {
                if (word.charAt(i - 1) != word.charAt(i)) {
                    g.addEdge(word.charAt(i - 1), word.charAt(i));
                }
            }
        }

        var dictionary = new StringBuilder();

        for (var start : g.getVertices().values()) {
            if (start.getState() == State.Unvisited) {
                if (!alienOrder(start, dictionary)) {
                    return "";
                }
            }
        }

        return dictionary.toString();
    }

    private static boolean alienOrder(Vertex<Character> vertex, StringBuilder dictionary) {
        vertex.setState(State.Visiting);

        for (var neighbor : vertex.getNeighbors().values()) {
            if (neighbor.getState() == State.Visiting) {
                return false;
            }
            if (neighbor.getState() == State.Unvisited) {
                alienOrder(neighbor, dictionary);
            }
        }

        dictionary.insert(0, vertex.getKey());
        vertex.setState(State.Visited);

        return true;
    }
}
