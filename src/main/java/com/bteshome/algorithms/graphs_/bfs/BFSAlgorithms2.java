package com.bteshome.algorithms.graphs_.bfs;

import java.util.*;

public class BFSAlgorithms2 {
    /**
     * https://leetcode.com/problems/minimum-genetic-mutation/description/?envType=study-plan-v2&envId=top-interview-150
     * */
    public static int minMutation(String startGene, String endGene, String[] bank) {
        if (startGene == null || endGene == null || startGene.length() != 8 || endGene.length() != 8 || bank == null || bank.length == 0) {
            return -1;
        }

        if (startGene.equals(endGene)) {
            return 0;
        }

        return minMutation(startGene, endGene, Arrays.stream(bank).toList());
    }

    static class Mutation {
        public String gene;
        public int steps;
        public Mutation(String gene, int steps) {
            this.gene = gene;
            this.steps = steps;
        }
    }

    private static int minMutation(String startGene, String endGene, List<String> bank) {
        var visited = new HashSet<String>();
        Queue<Mutation> q = new LinkedList<>();
        q.add(new Mutation(startGene, 0));
        visited.add(startGene);
        var geneChars = new char[]{'A', 'C', 'G', 'T'};

        while (!q.isEmpty()) {
            var front = q.remove();
            for (int i = 0; i < 8; i++) {
                for (char c : geneChars) {
                    if (front.gene.charAt(i) != c) {
                        var mutation = front.gene.substring(0, i) + c + front.gene.substring(i + 1);
                        if (bank.contains(mutation) && !visited.contains(mutation)) {
                            if (mutation.equals(endGene)) {
                                return front.steps + 1;
                            }
                            q.add(new Mutation(mutation, front.steps + 1));
                            visited.add(mutation);
                        }
                    }
                }
            }
        }

        return -1;
    }
}
