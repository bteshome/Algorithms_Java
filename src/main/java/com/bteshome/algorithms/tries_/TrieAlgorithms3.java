package com.bteshome.algorithms.tries_;

import java.util.ArrayList;
import java.util.List;

public class TrieAlgorithms3 {
    /**
     * https://leetcode.com/problems/search-suggestions-system/submissions/1420249880/
     */
    public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        if (products == null || products.length == 0 || searchWord == null || searchWord.isEmpty()) {
            return List.of();
        }

        var suggestions = new ArrayList<List<String>>();
        var trie = new Trie();
        for (String product : products) {
            trie.insert(product);
        }

        Node node = trie.getRoot();
        int i = 0;

        for (; i < searchWord.length(); i++) {
            char c = searchWord.charAt(i);
            if (!node.getChildren().containsKey(c)) {
                break;
            }

            var currentSuggestions = new ArrayList<String>();
            node = node.getChildren().get(c);
            StringBuilder path = new StringBuilder();
            path.append(searchWord, 0, i + 1);
            suggestedProductsCollect(node, path, currentSuggestions, 3);
            suggestions.add(currentSuggestions);
        }

        for (; i < searchWord.length(); i++) {
            suggestions.add(List.of());
        }

        return suggestions;
    }

    private static int suggestedProductsCollect(Node node, StringBuilder path, List<String> suggestions, int k) {
        if (node.isWord()) {
            suggestions.add(path.toString());
            k--;
        }

        if (k == 0) {
            return 0;
        }

        for (var childKey : node.getChildren().keySet()) {
            path.append(childKey);
            k = suggestedProductsCollect(node.getChildren().get(childKey), path, suggestions, k);
            if (k == 0) {
                return 0;
            }
            path.deleteCharAt(path.length() - 1);
        }

        return k;
    }

    /**
     * https://leetcode.com/problems/lexicographical-numbers/
     */
    public static List<Integer> lexicalOrder(int n) {
        var nums = new ArrayList<Integer>();

        var trie = new Trie();
        for (int i = 1; i <= n; i++) {
            trie.insert(Integer.toString(i));
        }

        lexicalOrder(trie.getRoot(), new StringBuilder(), nums);

        return nums;
    }

    private static void lexicalOrder(Node node, StringBuilder path, List<Integer> nums) {
        if (node.isWord()) {
            nums.add(Integer.parseInt(path.toString()));
        }

        for (var childKey : node.getChildren().keySet()) {
            path.append(childKey);
            lexicalOrder(node.getChildren().get(childKey), path, nums);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
