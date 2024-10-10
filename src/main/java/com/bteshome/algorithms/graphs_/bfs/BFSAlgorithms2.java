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

    /**
     * https://leetcode.com/problems/word-ladder/description/
     * NOTE - also take a look at the slower backtracking implementation,
     *        which actually fails leetcode time limit test.
     * */
    public static int wordLadder(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null || beginWord.isEmpty() || endWord.isEmpty() || wordList.isEmpty()) {
            return 0;
        }

        if (beginWord.equals(endWord)) {
            return 0;
        }

        class Transformation {
            public final String value;
            public final int steps;
            public Transformation(String value, int steps) {
                this.value = value;
                this.steps = steps;
            }
        }

        var wordSet = new HashSet<String>(wordList);
        var visited = new HashSet<String>();
        Queue<Transformation> q = new LinkedList<>();

        visited.add(beginWord);
        q.add(new Transformation(beginWord, 1));

        while (!q.isEmpty()) {
            var front = q.remove();

            for (int i = 0; i < front.value.length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    if (front.value.charAt(i) != c) {
                        var transformation = front.value.substring(0, i) + c + front.value.substring(i + 1);
                        if (wordSet.contains(transformation)) {
                            if (transformation.equals(endWord)) {
                                return front.steps + 1;
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

        return 0;
    }
}
