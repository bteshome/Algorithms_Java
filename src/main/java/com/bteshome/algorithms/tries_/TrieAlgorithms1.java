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
     * https://leetcode.com/problems/replace-words/?envType=problem-list-v2&envId=trie&difficulty=MEDIUM
     * */
    public static String replaceWords(List<String> dictionary, String sentence) {
        if (dictionary == null || sentence == null || dictionary.isEmpty() || sentence.isEmpty()) {
            return sentence;
        }

        var trie = new Trie();
        var root = trie.getRoot();
        for (String word : dictionary) {
            trie.insert(word);
        }

        var startIndex = 0;
        var spaceIndex = -1;
        var replacements = new StringBuilder();

        do {
            spaceIndex = replaceWords(sentence, root, startIndex, replacements);
            startIndex = spaceIndex + 1;
        } while (spaceIndex != -1);

        return replacements.toString();
    }

    private static int replaceWords(String sentence, Node node, int startIndex, StringBuilder replacements) {
        var path = new StringBuilder();
        var spaceIndex = sentence.indexOf(' ', startIndex);
        var word = sentence.substring(startIndex, (spaceIndex != -1 ? spaceIndex : sentence.length()));
        var replacement = word;

        for (int i = startIndex; i < (spaceIndex != -1 ? spaceIndex : sentence.length()); i++) {
            var c = sentence.charAt(i);
            if (!node.getChildren().containsKey(c)) {
                break;
            }
            node = node.getChildren().get(c);
            path.append(c);
            if (node.isWord()) {
                replacement = path.toString();
                break;
            }
        }

        if (startIndex > 0) {
            replacements.append(" ");
        }

        replacements.append(replacement);

        return spaceIndex;
    }
}
