package com.bteshome.algorithms.tries_;

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
        var root = trie.getRoot();
        var buffer = new StringBuilder();

        for (String rootWord : dictionary)
            trie.insert(rootWord);

        for (String word : sentence.split(" ")) {
            String rootWord = search2(root, word);
            if (!buffer.isEmpty())
                buffer.append(" ");
            buffer.append(rootWord);
        }

        return buffer.toString();
    }

    private static String search2(Node root, String word) {
        Node current = root;
        StringBuilder rootWord = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            if (current.isWord())
                return rootWord.toString();
            char c = word.charAt(i);
            if (!current.getChildren().containsKey(c))
                return word;
            current = current.getChildren().get(c);
            rootWord.append(c);
        }

        return word;
    }
}
