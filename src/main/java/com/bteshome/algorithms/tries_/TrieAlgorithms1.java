package com.bteshome.algorithms.tries_;

import com.bteshome.T;

import java.util.ArrayList;
import java.util.List;

public class TrieAlgorithms1 {
    /**
     * https://leetcode.com/problems/index-pairs-of-a-string/?envType=problem-list-v2&envId=trie&difficulty=EASY
     * */
    public static int[][] indexPairs(String text, String[] words) {
        if (text == null || words == null) {
            return new int[0][];
        }

        var matches = new ArrayList<int[]>();
        var trie = new Trie();
        var root = trie.getRoot();

        for (String word : words) {
            trie.insert(word);
        }

        for (int i = 0; i < text.length(); i++) {
            indexPairs(text, i, i, matches, root);
        }

        return matches.toArray(new int[matches.size()][]);
    }

    private static void indexPairs(String text, int start, int pos, List<int[]> matches, Node node) {
        if (pos == text.length()) {
            return;
        }

        char c = text.charAt(pos);

        if (!node.getChildren().containsKey(c)) {
            return;
        }

        Node child = node.getChildren().get(c);

        if (child.isWord()) {
            matches.add(new int[]{start, pos});
        }

        indexPairs(text, start, pos + 1, matches, child);
    }

    /**
     * https://leetcode.com/problems/replace-words
     * */
    public static String replaceWords(List<String> dictionary, String sentence) {
        if (dictionary == null || sentence == null || dictionary.isEmpty() || sentence.isEmpty()) {
            return sentence;
        }

        var trie = new Trie();
        String[] sentenceParts = sentence.split(" ");

        for (String word : dictionary)
            trie.insert(word);

        for (int i = 0; i < sentenceParts.length; i++) {
            String part = sentenceParts[i];
            Node current = trie.getRoot();

            for (int j = 0; j < part.length(); j++) {
                char c = part.charAt(j);
                if (!current.getChildren().containsKey(c))
                    break;
                current = current.getChildren().get(c);
                if (current.isWord()) {
                    sentenceParts[i] = part.substring(0, j + 1);
                    break;
                }
            }
        }

        return String.join(" ", sentenceParts);
    }
}
