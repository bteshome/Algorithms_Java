package com.bteshome.algorithms.backtracking_;

import com.bteshome.algorithms.dynamicProgramming_.DPAlgorithms63;

import java.util.*;

public class BacktrackingAlgorithms15 {
    /**
     * https://www.hackerrank.com/challenges/fair-cut/problem?isFullScreen=true
     * NOTE: this bruteforce backtracking solution does not pass the HackerRank
     *       time limit test.
     * */
    public static class FairCutBruteforce {
        public static long fairCut(int k, List<Integer> arr) {
            // Write your code here
            if (arr == null)
                return 0;
            int n = arr.size();
            if (k == 0 || k == n)
                return 0;
            if (k > n / 2)
                k = n - k;

            return fairCut(k, arr, 0, new boolean[arr.size()], 0);
        }

        private static long fairCut(int k, List<Integer> arr, int pos, boolean[] partition1, int partition1Size) {
            if (partition1Size == k) {
                long unfairness = 0;

                for (int i = 0; i < arr.size(); i++) {
                    if (!partition1[i])
                        continue;
                    int a = arr.get(i);
                    for (int j = 0; j < arr.size(); j++) {
                        if (partition1[j])
                            continue;
                        int b = arr.get(j);
                        unfairness += Math.abs(a - b);
                    }
                }

                return unfairness;
            }
            if (pos == arr.size())
                return -1;
            if (partition1Size > k)
                return -1;
            if (partition1Size + arr.size() - pos < k)
                return -1;

            long min = Long.MAX_VALUE;

            partition1[pos] = true;
            long next1 = fairCut(k, arr, pos + 1, partition1, partition1Size + 1);
            partition1[pos] = false;
            if (next1 != -1)
                min = next1;

            long next2 = fairCut(k, arr, pos + 1, partition1, partition1Size);
            if (next2 != -1)
                min = Math.min(min, next2);

            return min == Long.MAX_VALUE ? -1 : min;
        }
    }
}
