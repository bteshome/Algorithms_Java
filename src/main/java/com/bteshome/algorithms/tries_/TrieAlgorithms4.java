package com.bteshome.algorithms.tries_;

import java.util.*;

public class TrieAlgorithms4 {
    public boolean phonePrefix(String[] numbers) {
        if (numbers == null || numbers.length == 0)
            return true;

        class Trie2 {
            private final Node root = new Node();

            public Node getRoot() {
                return root;
            }

            public boolean insert2(String word) {
                Node current = root;

                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if (!current.getChildren().containsKey(c)) {
                        current.getChildren().put(c, new Node());
                    }
                    current = current.getChildren().get(c);
                }

                if (current.isWord())
                    return false;
                current.setWord(true);
                return true;
            }

            public Node search2(String word) {
                Node current = root;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if (!current.getChildren().containsKey(c)) {
                        return null;
                    }
                    current = current.getChildren().get(c);
                }
                return current;
            }
        }

        Trie2 t = new Trie2();

        for (String number : numbers)
            if (!t.insert2(number))
                return false;

        for (String number : numbers) {
            Node node = t.search2(number);
            if (!node.getChildren().isEmpty())
                return false;
        }

        return true;
    }

    public static String longestWord(String[] words) {
        if (words == null || words.length == 0)
            return null;

        Trie trie = new Trie();
        Map<Integer, Map.Entry<Integer, String>> longest = new HashMap<>();

        for (String word : words)
            trie.insert(word);

        dfs(trie.getRoot(), new StringBuilder(), 0, longest);

        System.out.println(longest);
        return longest.get(0).getValue();
    }

    private static void dfs(Node node, StringBuilder word, int depth, Map<Integer, Map.Entry<Integer, String>> longest) {
        if (node.getChildren().isEmpty()) {
            if (longest.isEmpty() || depth > longest.get(0).getKey())
                longest.put(0, Map.entry(depth, word.toString()));
            return;
        }

        for (Map.Entry<Character, Node> child : node.getChildren().entrySet()) {
            word.append(child.getKey());
            dfs(child.getValue(), word, depth + 1, longest);
            word.deleteCharAt(word.length() - 1);
        }
    }
}
