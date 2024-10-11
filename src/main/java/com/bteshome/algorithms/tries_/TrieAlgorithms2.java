package com.bteshome.algorithms.tries_;

import java.util.HashSet;
import java.util.List;

public class TrieAlgorithms2 {
    /**
     * https://leetcode.com/problems/word-search-ii/submissions/1419317200/?envType=problem-list-v2&envId=trie
     * NOTE: this solution is Trie + backtracking.
     *       There is another pure backtracking solution,
     *       which fails leetcode time limit test.
     * */
    public static List<String> wordSearchII(char[][] board, String[] words) {
        var wordsFound = new HashSet<String>();

        if (board == null || words == null || board.length == 0 || words.length == 0) {
            return wordsFound.stream().toList();
        }

        var trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                wordSearchII(board, trie.getRoot(), wordsFound, i, j, new StringBuilder());
            }
        }

        return wordsFound.stream().toList();
    }

    private static void wordSearchII(char[][] board, Node node, HashSet<String> wordsFound, int row, int col, StringBuilder path) {
        if (row < 0 || row >= board.length) {
            return;
        }

        if (col < 0 || col >= board[0].length) {
            return;
        }

        char c = board[row][col];

        if (c == '?') {
            return;
        }

        if (!node.getChildren().containsKey(c)) {
            return;
        }

        path.append(c);
        node = node.getChildren().get(c);

        if (node.isWord()) {
            wordsFound.add(path.toString());
        }

        board[row][col] = '?';

        wordSearchII(board, node, wordsFound, row, col - 1, path);
        wordSearchII(board, node, wordsFound, row, col + 1, path);
        wordSearchII(board, node, wordsFound, row - 1, col, path);
        wordSearchII(board, node, wordsFound, row + 1, col, path);

        board[row][col] = c;
        path.deleteCharAt(path.length() - 1);
    }

}
