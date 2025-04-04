package com.bteshome.algorithms.disjointSets_;

import java.util.*;

public class DisjointSetsAlgorithms3 {
    /*
    * https://leetcode.com/problems/the-earliest-moment-when-everyone-become-friends/
    * NOTE - this problem is an example of how choosing the right
    *        data structure for the problem makes the biggest difference.
    * */
    public static int earliestAcq(int[][] logs, int n) {
        if (logs == null || logs.length == 0 || n < 1)
            return -1;

        DisjointSetForest<Integer> forest = new DisjointSetForest<>();

        for (int i = 0; i < n; i++)
            forest.makeSet(i);

        Arrays.sort(logs, Comparator.comparingInt(a -> a[0]));

        for (int[] log : logs) {
            forest.union(log[1], log[2]);
            if (forest.getNumberOfDisjointSets() == 1)
                return log[0];
        }

        return -1;
    }

    /*
    * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
    * */
    public static int countComponents(int n, int[][] edges) {
        if (n < 1)
            return 0;

        DisjointSetForest<Integer> forest = new DisjointSetForest<>();

        for (int i = 0; i < n; i++)
            forest.makeSet(i);

        for (int[] edge : edges)
            forest.union(edge[0], edge[1]);

        return forest.getNumberOfDisjointSets();
    }

    /*
    * https://leetcode.com/problems/number-of-provinces/
    * */
    public static int findCircleNum(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0)
            return 0;

        DisjointSetForest<Integer> forest = new DisjointSetForest<>();

        for (int i = 0; i < isConnected.length; i++)
            forest.makeSet(i);

        for (int i = 0; i < isConnected.length; i++)
            for (int j = 0; j < isConnected[i].length; j++)
                if (i != j && isConnected[i][j] == 1)
                    forest.union(i, j);

        return forest.getNumberOfDisjointSets();
    }

    public static HashMap<String, Set<String>> getSetElements(List<List<String>> synonyms) {
        DisjointSetForest<String> forest = new DisjointSetForest<>();
        HashMap<String, Set<String>> elements = new HashMap<>();

        for (List<String> synGroup : synonyms) {
            for (int i = 0; i < synGroup.size(); i++) {
                forest.makeSet(synGroup.get(i));
                if (i > 0)
                    forest.union(synGroup.getFirst(), synGroup.get(i));
            }
        }

        for (List<String> synGroup : synonyms) {
            for (String word : synGroup) {
                String setId = forest.findSet(word).getKey();
                if (!elements.containsKey(setId))
                    elements.put(setId, new HashSet<>());
                elements.get(setId).add(word);
            }
        }

        return elements;
    }

    public static List<String> generateSentences(List<List<String>> synonyms, String text) {
        if (text == null)
            return null;

        if (text.isBlank() || synonyms == null || synonyms.isEmpty())
            return List.of(text);

        String[] textWords = text.split(" ");
        DisjointSetForest<String> forest = new DisjointSetForest<>();
        Map<String, Set<String>> setElements = new HashMap<>();
        List<String> sentences = new ArrayList<>();

        if (textWords.length == 1)
            return List.of(text);

        for (List<String> synGroup : synonyms) {
            for (int i = 0; i < synGroup.size(); i++) {
                forest.makeSet(synGroup.get(i));
                if (i > 0)
                    forest.union(synGroup.getFirst(), synGroup.get(i));
            }
        }

        for (List<String> synGroup : synonyms) {
            for (String word : synGroup) {
                Node<String> set = forest.findSet(word);
                String setId = set.getKey();
                if (!setElements.containsKey(setId))
                    setElements.put(setId, new TreeSet<>());
                setElements.get(setId).add(word);
            }
        }

        for (int i = 0; i < textWords.length; i++) {
            String word = textWords[i];
            Node<String> set = forest.findSet(word);
            if (set != null)
                textWords[i] = set.getKey();
        }

        generateSentences(textWords, 0, new StringBuilder(), sentences, setElements);

        return sentences;
    }

    private static void generateSentences(String[] textWords, int pos, StringBuilder candidate, List<String> sentences, Map<String, Set<String>> setElements) {
        if (pos == textWords.length) {
            sentences.add(candidate.toString());
            return;
        }

        if (!candidate.isEmpty())
            candidate.append(" ");

        String word = textWords[pos];

        if (!setElements.containsKey(word)) {
            candidate.append(word);
            generateSentences(textWords, pos + 1, candidate, sentences, setElements);
            candidate.delete(candidate.length() - 1 - (word.length() - 1), candidate.length());
        } else {
            for (String synonym : setElements.get(word)) {
                candidate.append(synonym);
                generateSentences(textWords, pos + 1, candidate, sentences, setElements);
                candidate.delete(candidate.length() - 1 - (synonym.length() - 1), candidate.length());
            }
        }

        if (!candidate.isEmpty())
            candidate.deleteCharAt(candidate.length() - 1);
    }
}
