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

    /**
     * https://leetcode.com/problems/valid-palindrome-ii/
     * */
    public static boolean validPalindromeII_Recursive(String s) {
        if (s == null || s.length() < 3) {
            return true;
        }

        return validPalindromeII_Recursive(s, 0, s.length() - 1, 1);
    }

    private static boolean validPalindromeII_Recursive(String s, int i, int j, int numDeletes) {
        if (i >= j) {
            return true;
        }

        if (s.charAt(i) == s.charAt(j)) {
            return validPalindromeII_Recursive(s, i+1, j-1, numDeletes);
        }

        if (numDeletes == 0) {
            return false;
        }

        return validPalindromeII_Recursive(s, i+1, j, numDeletes - 1) || validPalindromeII_Recursive(s, i, j-1, numDeletes - 1);
    }

    public static boolean validPalindromeII_Iterative(String s) {
        if (s == null || s.length() < 3) {
            return true;
        }

        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return validPalindromeII_Iterative(s, i, j-1) || validPalindromeII_Iterative(s, i+1, j);
            }
            i++;
            j--;
        }

        return true;
    }

    private static boolean validPalindromeII_Iterative(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
