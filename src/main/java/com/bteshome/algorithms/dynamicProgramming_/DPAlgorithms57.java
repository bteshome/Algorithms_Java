package com.bteshome.algorithms.dynamicProgramming_;

import java.util.*;

public class DPAlgorithms57 {
    /**
     * https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs
     * NOTE: - there is also a pure backtracking solution  that is actually faster
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

            minimumTimeRequired(jobs, k, new int[k], 0, new HashMap<>());

            return globalMin;
        }

        private int minimumTimeRequired(int[] jobs, int k, int[] assignments, int jobPos, Map<Long, Integer> dp) {
            if (jobPos == jobs.length) {
                int max = Arrays.stream(assignments).max().getAsInt();
                globalMin = Math.min(globalMin, max);
                return max;
            }

            long key = encodeState(jobPos, assignments);

            if (dp.containsKey(key))
                return dp.get(key);

            int min = Integer.MAX_VALUE;

            for (int i = 0; i < k; i++) {
                int jobTime = jobs[jobPos];

                // pruning1
                if (assignments[i] + jobTime >= globalMin)
                    continue;

                // pruning 2
                if (i > 0 && assignments[i] == assignments[i- 1])
                    continue;

                assignments[i] += jobTime;
                min = Math.min(min, minimumTimeRequired(jobs, k, assignments, jobPos + 1, dp));
                assignments[i] -= jobTime;

                // pruning 3
                if (assignments[i] == 0)
                    break;
            }

            dp.put(key, min);

            return dp.get(key);
        }

        private long encodeState(int jobPos, int[] assignments) {
            long hash = jobPos;
            long base = 120_000_007L; // Larger than any possible worker load
            for (int load : assignments)
                hash = hash * base + load;
            return hash;
        }

        private void reverse(int[] arr) {
            for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    }

    /**
     * TODO - solve with BFS
     * https://leetcode.com/problems/cat-and-mouse
     * NOTE: this solution is currently not fully correct.
     *       BFS is probably better.
     * */
    public static class CatMouseGameDFS {
        private static final int DRAW = 0;
        private static final int MOUSE_WINS = 1;
        private static final int CAT_WINS = 2;
        private static final int MOUSE_TURN = 0;
        private static final int CAT_TURN = 1;

        public int catMouseGame(int[][] graph) {
            int n = graph.length;
            Integer[][][] dp = new Integer[n][n][3];
            return catMouseGame(graph, 1, 2, MOUSE_TURN, dp, 0);
        }

        private int catMouseGame(int[][] graph, int mouse, int cat, int turn, Integer[][][] dp, int steps) {
            if (mouse == 0)
                return 1;
            if (mouse == cat)
                return 2;
            if (dp[mouse][cat][turn] != null)
                return dp[mouse][cat][turn];

            int n = graph.length;
            if (steps > 2 * n * n) return DRAW;

            int bestResult;

            if (turn == MOUSE_TURN) {
                bestResult = CAT_WINS;

                for (int nextNode : graph[mouse]) {
                    int nextResult = catMouseGame(graph, nextNode, cat, CAT_TURN, dp, steps + 1);
                    if (nextResult == MOUSE_WINS) {
                        bestResult = nextResult;
                        break;
                    }
                    if (nextResult == DRAW)
                        bestResult = nextResult;
                }
            } else {
                bestResult = MOUSE_WINS;

                for (int nextNode : graph[cat]) {
                    if (nextNode == 0)
                        continue;
                    int nextResult = catMouseGame(graph, mouse, nextNode, MOUSE_TURN, dp, steps + 1);
                    if (nextResult == CAT_WINS) {
                        bestResult = nextResult;
                        break;
                    }
                    if (nextResult == DRAW)
                        bestResult = nextResult;
                }
            }

            dp[mouse][cat][turn] = bestResult;

            return dp[mouse][cat][turn];
        }
    }
}