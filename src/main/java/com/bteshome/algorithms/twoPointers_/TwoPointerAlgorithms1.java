package com.bteshome.algorithms.twoPointers_;

public class TwoPointerAlgorithms1 {
    /**
     * leetcode https://leetcode.com/problems/is-subsequence/submissions/1405232748/?envType=problem-list-v2&envId=dynamic-programming&difficulty=EASY
     * */
    public static boolean isSubsequence(String s, String t) {
        if (s == null || t == null) {
            return false;
        }

        return isSubsequence(s, t, 0, 0);
    }

    private static boolean isSubsequence(String s, String t, int sPos, int tPos) {
        if (sPos == s.length()) {
            return true;
        }

        if ((s.length() - sPos) > (t.length() - tPos)) {
            return false;
        }

        boolean isSebseq = false;

        if (s.charAt(sPos) == t.charAt(tPos)) {
            isSebseq = isSubsequence(s, t, sPos + 1, tPos + 1);
        } else {
            isSebseq = isSubsequence(s, t, sPos, tPos + 1);
        }

        return isSebseq;
    }
}
