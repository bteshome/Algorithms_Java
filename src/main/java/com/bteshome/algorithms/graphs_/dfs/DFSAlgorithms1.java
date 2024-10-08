package com.bteshome.algorithms.graphs_.dfs;

import com.bteshome.algorithms.graphs_.Graph;
import com.bteshome.algorithms.graphs_.State;
import com.bteshome.algorithms.graphs_.Vertex;

public class DFSAlgorithms1 {
    /**
     * https://leetcode.com/problems/find-the-town-judge/?envType=problem-list-v2&envId=graph&difficulty=EASY
     * */
    public static int findJudge(int n, int[][] trust) {
        if (n == 0 || trust == null) {
            return -1;
        }

        Graph<Integer> g = new Graph<>();

        for (int i = 1; i <= n; i++) {
            g.addVertex(i);
        }

        for (int i = 0; i < trust.length; i++) {
            int from = trust[i][1];
            int to = trust[i][0];
            g.addEdge(from, to);
        }

        int judge = -1;

        for (Vertex<Integer> source : g.getVertices().values()) {
            if (findJudgeIsTheJudge(n, source)) {
                if (judge == -1) {
                    judge = source.getKey();
                } else {
                    return -1;
                }
            }
        }

        return judge;
    }

    private static boolean findJudgeIsTheJudge(int n, Vertex<Integer> vertex) {
        if (vertex.getOutgoingEdges().size() != n - 1) {
            return false;
        }

        if (!vertex.getIncomingEdges().isEmpty()) {
            return false;
        }

        return true;
    }

    /**
     * https://leetcode.com/problems/find-center-of-star-graph/?envType=problem-list-v2&envId=graph&difficulty=EASY
     * */
    public static int findCenterOfStarGraph(int[][] edges) {
        if (edges == null) {
            return -1;
        }

        var g = new Graph<Integer>(false);
        for (int i = 0; i < edges.length; i++) {
            g.addEdge(edges[i][0], edges[i][1]);
        }

        int center = -1;

        for (var v : g.getVertices().values()) {
            if (v.getOutgoingEdges().size() == g.getVertices().size() - 1) {
                if (center == -1) {
                    center = v.getKey();
                } else {
                    return -1;
                }
            }
        }

        return center;
    }

    /**
     * https://leetcode.com/problems/find-if-path-exists-in-graph/description/?envType=problem-list-v2&envId=graph&difficulty=EASY
     * */
    public static boolean validPath(int n, int[][] edges, int source, int destination) {
        if (edges == null || n < 1 || source >= n || destination >= n) {
            return false;
        }

        if (source == destination) {
            return true;
        }

        var g = new Graph<Integer>(false);

        for (int i = 0; i < n; i++) {
            g.addVertex(i);
        }

        for (int[] edge : edges) {
            g.addEdge(edge[0], edge[1]);
        }

        return validPathDFS(g.getVertices().get(source), g.getVertices().get(destination));
    }

    private static boolean validPathDFS(Vertex<Integer> source, Vertex<Integer> destination) {
        if (source.getKey().equals(destination.getKey())) {
            return true;
        }

        for (var neighbor : source.getNeighbors().values()) {
            if (neighbor.getState() == State.Unvisited) {
                neighbor.setState(State.Visiting);
                if (validPathDFS(neighbor, destination)) {
                    return true;
                }
            }
        }

        return false;
    }
}
