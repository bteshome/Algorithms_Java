package com.bteshome.algorithms.backtracking_;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs
     * NOTE: - there is also a DP solution that is actually slower
     *       - it can be improved using DP + bitmask
     *       - sorting the jobs array in descending order and starting from the
     *         biggest was supposed to be faster, but it is not for some reason
     * */
    public static class MinimumTimeRequired {
        private int globalMin = Integer.MAX_VALUE;

        public int minimumTimeRequired(int[] jobs, int k) {
            if (jobs == null || jobs.length == 0 || k < 1)
                return 0;

            //Arrays.sort(jobs);
            //reverse(jobs);

            minimumTimeRequired(jobs, k, new int[k], 0);

            return globalMin;
        }

        private void minimumTimeRequired(int[] jobs, int k, int[] assignments, int jobPos) {
            if (jobPos == jobs.length) {
                int max = Arrays.stream(assignments).max().getAsInt();
                globalMin = Math.min(globalMin, max);
                return;
            }

            for (int i = 0; i < k; i++) {
                int jobTime = jobs[jobPos];

                // pruning1
                if (assignments[i] + jobTime >= globalMin)
                    continue;

                // pruning 2
                if (i > 0 && assignments[i] == assignments[i- 1])
                    continue;

                assignments[i] += jobTime;
                minimumTimeRequired(jobs, k, assignments, jobPos + 1);
                assignments[i] -= jobTime;

                // pruning 3
                if (assignments[i] == 0)
                    break;
            }
        }

        private void reverse(int[] arr) {
            for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    }
}