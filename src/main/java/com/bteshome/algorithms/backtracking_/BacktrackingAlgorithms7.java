package com.bteshome.algorithms.backtracking_;

import java.util.ArrayList;
import java.util.List;

public class BacktrackingAlgorithms7 {
    /**
     * https://leetcode.com/problems/word-ladder/description/
     * NOTE - it fails leetcode time limit test. Try graph instead.
     * */
    public static int wordLadder(String beginWord, String endWord, List<String> wordList) {
        if (!(beginWord == null || endWord == null || wordList == null || beginWord.isEmpty() || endWord.isEmpty() || wordList.isEmpty())) {
            var transformation = new ArrayList<String>();
            transformation.add(beginWord);
            var shortest = wordLadder(beginWord, endWord, new ArrayList<String>(wordList), Integer.MAX_VALUE, transformation, 0);
            return shortest == Integer.MAX_VALUE ? 0 : shortest;
        }

        return 0;
    }

    private static int wordLadder(String beginWord, String endWord, List<String> wordList, int shortestSoFar, List<String> transformation, int pos) {
        if (beginWord.equals(endWord)) {
            return Math.min(shortestSoFar, transformation.size());
        }

        if (transformation.size() >= shortestSoFar) {
            return shortestSoFar;
        }

        if (pos == wordList.size()) {
            return shortestSoFar;
        }

        for (int i = pos; i < wordList.size(); i++) {
            String currentWord = wordList.get(i);
            if (wordLadderCanTransform(beginWord, currentWord)) {
                transformation.addLast(currentWord);
                wordLadderSwap(wordList, pos, i);
                shortestSoFar = Math.min(shortestSoFar, wordLadder(currentWord, endWord, wordList, shortestSoFar, transformation, pos + 1));
                transformation.removeLast();
                wordLadderSwap(wordList, pos, i);
            }
        }

        return shortestSoFar;
    }

    private static void wordLadderSwap(List<String> wordList, int i, int j) {
        String temp = wordList.get(i);
        wordList.set(i, wordList.get(j));
        wordList.set(j, temp);
    }

    private static boolean wordLadderCanTransform(String word, String to) {
        if (word.length() != to.length()) {
            return false;
        }

        int difference = 0;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != to.charAt(i)) {
                difference++;
            }
            if (difference > 1) {
                return false;
            }
        }

        return difference == 1;
    }

    /**
     * https://leetcode.com/problems/word-ladder-ii/?envType=problem-list-v2&envId=backtracking
     * NOTE - it fails leetcode time limit test. Try graph instead.
     * */
    public static List<List<String>> wordLadderII(String beginWord, String endWord, List<String> wordList) {
        var transformations = new ArrayList<List<String>>();

        if (!(beginWord == null || endWord == null || wordList == null || beginWord.isEmpty() || endWord.isEmpty() || wordList.isEmpty())) {
            var transformation = new ArrayList<String>();
            transformation.add(beginWord);
            wordLadderII(beginWord, endWord, new ArrayList<String>(wordList), transformations, transformation, 0);
        }

        return transformations;
    }

    private static void wordLadderII(String beginWord, String endWord, List<String> wordList, List<List<String>> transformations, List<String> transformation, int pos) {
        if (beginWord.equals(endWord)) {
            if (transformations.isEmpty() || transformation.size() == transformations.getLast().size()) {
                transformations.add(transformation.stream().toList());
            } else if (transformation.size() < transformations.getLast().size()) {
                transformations.clear();
                transformations.add(transformation.stream().toList());
            }
            return;
        }

        if (!transformations.isEmpty() && transformation.size() >= transformations.getLast().size()) {
            return;
        }

        if (pos == wordList.size()) {
            return;
        }

        for (int i = pos; i < wordList.size(); i++) {
            String currentWord = wordList.get(i);
            if (wordLadderIICanTransform(beginWord, currentWord)) {
                transformation.addLast(currentWord);
                wordLadderIISwap(wordList, pos, i);
                wordLadderII(currentWord, endWord, wordList, transformations, transformation, pos + 1);
                transformation.removeLast();
                wordLadderIISwap(wordList, pos, i);
            }
        }
    }

    private static void wordLadderIISwap(List<String> wordList, int i, int j) {
        String temp = wordList.get(i);
        wordList.set(i, wordList.get(j));
        wordList.set(j, temp);
    }

    private static boolean wordLadderIICanTransform(String word, String to) {
        if (word.length() != to.length()) {
            return false;
        }

        int difference = 0;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != to.charAt(i)) {
                difference++;
            }
            if (difference > 1) {
                return false;
            }
        }

        return difference == 1;
    }
}
