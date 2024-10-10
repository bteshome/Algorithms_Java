package com.bteshome.algorithms.graphs_.dfs;

import com.bteshome.algorithms.graphs_.Graph;
import com.bteshome.algorithms.graphs_.State;
import com.bteshome.algorithms.graphs_.Vertex;

import java.util.ArrayList;
import java.util.List;

public class DFSAlgorithms4 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode() {}
        public TreeNode(int val) { this.val = val; }
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        var path = new ArrayList<Integer>();
        inorderTraversal(root, path);
        return path;
    }

    private static void inorderTraversal(TreeNode root, List<Integer> path) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, path);
        path.add(root.val);
        inorderTraversal(root.right, path);
    }

    /**
     * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/?envType=study-plan-v2&envId=premium-algo-100
     * */
    public static int countConnectedComponents(int n, int[][] edges) {
        if (n < 1) {
            return 0;
        }

        if (edges == null || edges.length == 0) {
            return n;
        }

        var g = new Graph<Integer>(false);
        for (int i = 0; i < n; i++) {
            g.addVertex(i);
        }

        for (int[] edge : edges) {
            g.addEdge(edge[0], edge[1]);
        }

        int connectedComponents = 0;

        for (var start : g.getVertices().values()) {
            if (start.getState() == State.Unvisited) {
                connectedComponents++;
                countConnectedComponentsDfs(start);
            }
        }

        return connectedComponents;
    }

    private static void countConnectedComponentsDfs(Vertex<Integer> vertex) {
        vertex.setState(State.Visiting);
        for (var neighbor : vertex.getNeighbors().values()) {
            if (neighbor.getState() == State.Unvisited) {
                countConnectedComponentsDfs(neighbor);
            }
        }
    }

    /**
     * https://leetcode.com/problems/number-of-provinces/
     * */
    public static int numberOfProvinces(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0) {
            return 0;
        }
        
        var g = new Graph<Integer>(false);

        for (int i = 0; i < isConnected.length; i++) {
            g.addVertex(i);
        }

        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected.length; j++) {
                if (i != j && isConnected[i][j] == 1) {
                    g.addEdge(i, j);
                }
            }
        }

        int provinces = 0;

        for (var start : g.getVertices().values()) {
            if (start.getState() == State.Unvisited) {
                provinces++;
                numberOfProvincesDfs(start);
            }
        }

        return provinces;
    }

    private static void numberOfProvincesDfs(Vertex<Integer> vertex) {
        vertex.setState(State.Visiting);
        for (var neighbor : vertex.getNeighbors().values()) {
            if (neighbor.getState() == State.Unvisited) {
                numberOfProvincesDfs(neighbor);
            }
        }
    }
}

