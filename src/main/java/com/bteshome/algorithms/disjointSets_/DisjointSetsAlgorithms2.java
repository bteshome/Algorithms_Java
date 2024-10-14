package com.bteshome.algorithms.disjointSets_;

public class DisjointSetsAlgorithms2 {
    /**
     * https://leetcode.com/problems/redundant-connection
     * */
    public static int[] findRedundantConnection(int[][] edges) {
        if (edges == null || edges.length == 0) {
            return new int[0];
        }

        var sets = new DisjointSetForest<Integer>();
        for (int i = 1; i <= edges.length; i++) {
            sets.makeSet(i);
        }

        for (int i = 0; i < edges.length; i++) {
            var edge = edges[i];
            var set1 = sets.findSet(edge[0]);
            var set2 = sets.findSet(edge[1]);
            if (set1 == set2) {
                return edge;
            }
            sets.union(edge[0], edge[1]);
        }

        return new int[0];
    }
}
