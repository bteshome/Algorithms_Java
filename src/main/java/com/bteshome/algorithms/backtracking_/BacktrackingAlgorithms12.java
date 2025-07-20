package com.bteshome.algorithms.backtracking_;

public class BacktrackingAlgorithms12 {
    /* https://leetcode.com/problems/maximum-score-words-formed-by-letters */
    public static class MaxScoreWords {
        private int maxScore = 0;

        public int maxScoreWords(String[] words, char[] letters, int[] score) {
            if (words == null || words.length == 0 || letters == null || letters.length == 0 || score == null || score.length == 0)
                return 0;

            int[] letterFreq = new int[26];
            for (char c : letters)
                letterFreq[c - 'a']++;

            maxScoreWords(words, letterFreq, score, 0, 0);

            return maxScore;
        }

        private void maxScoreWords(String[] words, int[] letterFreq, int[] score, int wordPos, int scoreSoFar) {
            if (wordPos == words.length) {
                maxScore = Math.max(maxScore, scoreSoFar);
                return;
            }

            maxScoreWords(words, letterFreq, score, wordPos + 1, scoreSoFar);

            int i = 0;
            int currentWordScore = 0;

            for (; i < words[wordPos].length(); i++) {
                char c = words[wordPos].charAt(i);
                int letterIndex = c - 'a';
                if (letterFreq[letterIndex] == 0)
                    break;
                currentWordScore += score[letterIndex];
                letterFreq[letterIndex]--;
            }

            if (i == words[wordPos].length())
                maxScoreWords(words, letterFreq, score, wordPos + 1, scoreSoFar + currentWordScore);

            for (int j = 0; j < i; j++) {
                char c = words[wordPos].charAt(j);
                int letterIndex = c - 'a';
                letterFreq[letterIndex]++;
            }
        }
    }
}
