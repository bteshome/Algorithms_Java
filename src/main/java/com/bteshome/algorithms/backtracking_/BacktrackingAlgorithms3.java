package com.bteshome.algorithms.backtracking_;

import java.util.HashSet;
import java.util.List;

public class BacktrackingAlgorithms3 {
    /**
     * leetcode https://leetcode.com/problems/word-search/description/?envType=study-plan-v2&envId=top-interview-150
     * */
    public static boolean wordSearch(char[][] board, String word) {
        if (board == null || word == null) {
            return false;
        }

        if (board.length == 0 || word.isEmpty()) {
            return false;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (wordSearch(board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean wordSearch(char[][] board, String word, int row, int col, int pos) {
        if (pos == word.length()) {
            return true;
        }

        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }

        if (board[row][col] == '?') {
            return false;
        }

        if (board[row][col] != word.charAt(pos)) {
            return false;
        }

        board[row][col] = '?';

        if (wordSearch(board, word, row, col - 1, pos + 1)) {
            return true;
        }

        if (wordSearch(board, word, row, col + 1, pos + 1)) {
            return true;
        }

        if (wordSearch(board, word, row - 1, col, pos + 1)) {
            return true;
        }

        if (wordSearch(board, word, row + 1, col, pos + 1)) {
            return true;
        }

        board[row][col] = word.charAt(pos);

        return false;
    }

    /**
     * https://leetcode.com/problems/word-search-ii/?envType=problem-list-v2&envId=backtracking&difficulty=HARD
     * NOTE: this implementation is purely backtracking.
     *       And it fails the leetcode time limit test.
     *       Take a look at the faster Trie implementation.
     * */
    public static List<String> wordSearchII(char[][] board, String[] words) {
        var wordsFound = new HashSet<String>();

        if (board != null && words != null) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    wordSearchII(board, words, i, j, wordsFound, "");
                }
            }
        }

        return wordsFound.stream().toList();
    }

    private static void wordSearchII(char[][] board, String[] words, int row, int col, HashSet<String> wordsFound, String prefix) {
        if (row < 0 || row >= board.length) {
            return;
        }

        if (col < 0 || col >= board[0].length) {
            return;
        }

        if (board[row][col] == '?') {
            return;
        }

        var c = board[row][col];
        var candidate = prefix + c;
        var isAWordPrefix = false;
        board[row][col] = '?';

        for (String word : words) {
            if (word.equals(candidate)) {
                wordsFound.add(candidate);
            } else if (word.startsWith(candidate)) {
                isAWordPrefix = true;
            }
        }

        if (isAWordPrefix) {
            wordSearchII(board, words, row, col - 1, wordsFound, candidate);
            wordSearchII(board, words, row, col + 1, wordsFound, candidate);
            wordSearchII(board, words, row - 1, col, wordsFound, candidate);
            wordSearchII(board, words, row + 1, col, wordsFound, candidate);
        }

        board[row][col] = c;
    }
}

