package com.bteshome.algorithms.tries_;

import java.util.HashSet;
import java.util.List;

public class TrieAlgorithms1 {
    /**
     * https://leetcode.com/problems/word-search-ii/?envType=problem-list-v2&envId=backtracking&difficulty=HARD
     * NOTE: the current implementation is purely backtracking.
     *       And it fails the leetcode time limit test.
     *       It could run faster using Trie.
     * */
    public static List<String> wordSearchII_TODO(char[][] board, String[] words) {
        var wordsFound = new HashSet<String>();

        if (board != null && words != null) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    wordSearchII_TODO(board, words, i, j, wordsFound, "");
                }
            }
        }

        return wordsFound.stream().toList();
    }

    private static void wordSearchII_TODO(char[][] board, String[] words, int row, int col, HashSet<String> wordsFound, String prefix) {
        if (row < 0 || row >= board.length) {
            return;
        }

        if (col < 0 || col >= board[0].length) {
            return;
        }

        if (board[row][col] == '?') {
            return;
        }

        for (String word : words) {
            var c = board[row][col];
            var candidate = prefix + c;
            if (word.startsWith(candidate)) {
                board[row][col] = '?';
                if (word.equals(candidate)) {
                    wordsFound.add(candidate);
                }
                wordSearchII_TODO(board, words, row, col - 1, wordsFound, candidate);
                wordSearchII_TODO(board, words, row, col + 1, wordsFound, candidate);
                wordSearchII_TODO(board, words, row - 1, col, wordsFound, candidate);
                wordSearchII_TODO(board, words, row + 1, col, wordsFound, candidate);
                board[row][col] = c;
            }
        }
    }
}
