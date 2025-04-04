package com.bteshome.algorithms.graphs_.dfs;

import com.bteshome.algorithms.graphs_.Graph;
import com.bteshome.algorithms.graphs_.State;
import com.bteshome.algorithms.graphs_.Vertex;

import java.util.ArrayList;
import java.util.List;

public class DFSAlgorithms7 {
    /*
    * https://leetcode.com/problems/graph-valid-tree/
    * */
    public static boolean validTree(int n, int[][] edges) {
        if (n < 1)
            return false;

        if (edges == null)
            return n == 1;

        if (edges.length != n - 1)
            return false;

        Graph<Integer> g = new Graph<>(false);

        for (int i = 0; i < n; i++)
            g.addVertex(i);

        for (int[] edge : edges)
            g.addEdge(edge[0], edge[1]);

        validTree_dfs(g.getVertices().get(0));

        for (Vertex<Integer> v : g.getVertices().values())
            if (v.getState() == State.Unvisited)
                return false;

        return true;
    }

    private static void validTree_dfs(Vertex<Integer> source) {
        source.setState(State.Visiting);
        for (Vertex<Integer> n : source.getNeighbors().values()) {
            if (n.getState() == State.Unvisited)
                validTree_dfs(n);
        }
    }

    /*
    * https://leetcode.com/problems/all-paths-from-source-to-target/
    * NOTE - there is an almost identical, but not Graph DS based, version too.
    * */
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        if (graph == null || graph.length == 0)
            return List.of();

        List<List<Integer>> paths = new ArrayList<>();
        Graph<Integer> g = new Graph<>();

        for (int i = 0; i < graph.length; i++)
            g.addVertex(i);

        for (int i = 0; i < graph.length; i++)
            for (int j = 0; j < graph[i].length; j++)
                g.addEdge(i, graph[i][j]);

        allPathsSourceTarget_dfs(g.getVertices().get(0), graph.length - 1, paths, new ArrayList<>());

        return paths;
    }

    private static void allPathsSourceTarget_dfs(Vertex<Integer> v, int target, List<List<Integer>> paths, List<Integer> path) {
        path.add(v.getKey());

        if (v.getKey() == target) {
            paths.add(path.stream().toList());
        } else {
            for (Vertex<Integer> n : v.getNeighbors().values())
                allPathsSourceTarget_dfs(n, target, paths, path);
        }

        path.removeLast();
    }
}

