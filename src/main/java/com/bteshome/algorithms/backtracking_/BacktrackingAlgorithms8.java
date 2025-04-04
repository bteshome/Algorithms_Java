package com.bteshome.algorithms.backtracking_;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BacktrackingAlgorithms8 {
    /**
     * https://leetcode.com/problems/word-break-ii/?envType=problem-list-v2&envId=backtracking&difficulty=HARD
     * */
    public static List<String> wordBreakII(String s, List<String> wordDict) {
        if (s == null || s.isBlank() || wordDict == null || wordDict.isEmpty())
            return List.of();

        List<String> sentences = new ArrayList<>();

        wordBreakII(s, wordDict, new StringBuilder(), sentences);

        return sentences;
    }

    private static void wordBreakII(String s, List<String> dict, StringBuilder candidate, List<String> sentences) {
        if (s.isBlank()) {
            sentences.add(candidate.toString());
            return;
        }

        for (String word : dict) {
            if (s.startsWith(word)) {
                if (!candidate.isEmpty())
                    candidate.append(" ");
                candidate.append(word);
                wordBreakII(s.substring(word.length()), dict, candidate, sentences);
                candidate.delete(candidate.length() - 1 - (word.length() - 1), candidate.length());
                if (!candidate.isEmpty())
                    candidate.deleteCharAt(candidate.length() - 1);
            }
        }
    }

    public static List<String> wordBreakII2(String s, List<String> wordDict) {
        var sentences = new ArrayList<String>();

        if (s != null && wordDict != null) {
            wordBreakII2(s, wordDict, sentences, "");
        }

        return sentences;
    }

    private static void wordBreakII2(String s, List<String> wordDict, List<String> sentences, String sentence) {
        if (s.isEmpty()) {
            sentences.add(sentence);
            return;
        }

        for (String word : wordDict) {
            if ((!word.isEmpty()) && s.startsWith(word)) {
                wordBreakII2(s.substring(word.length()), wordDict, sentences, sentence + (sentence.isEmpty() ? "" : " ") + word);
            }
        }
    }

    public static List<String> wordBreakII3(String s, List<String> wordDict) {
        var sentences = new ArrayList<String>();

        if (s != null && wordDict != null) {
            wordBreakII3(s, new HashSet<String>(wordDict), sentences, "");
        }

        return sentences;
    }

    private static void wordBreakII3(String s, HashSet<String> wordDict, List<String> sentences, String sentence) {
        if (s.isEmpty()) {
            sentences.add(sentence);
            return;
        }

        for (int i = 1; i <= s.length(); i++) {
            String word = s.substring(0, i);
            if (wordDict.contains(word)) {
                wordBreakII3(s.substring(i), wordDict, sentences, sentence + (sentence.isEmpty() ? "" : " ") + word);
            }
        }
    }
}
