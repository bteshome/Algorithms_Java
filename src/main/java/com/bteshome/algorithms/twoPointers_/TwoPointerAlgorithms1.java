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

    /*
     * https://leetcode.com/problems/valid-palindrome/
     * */
    public static boolean isPalindrome(String s) {
        if (s == null)
            return false;

        int i = 0;
        int j = s.length() - 1;
        char A = 'A';
        char Z = 'Z';

        while (i < j) {
            char a = s.charAt(i);
            char b = s.charAt(j);
            if (!Character.isLetterOrDigit(a))
                i++;
            else if (!Character.isLetterOrDigit(b))
                j--;
            else {
                if (a >= A && a <= Z)
                    a = Character.toLowerCase(a);
                if (b >= A && b <= Z)
                    b = Character.toLowerCase(b);
                if (a != b)
                    return false;
                i++;
                j--;
            }
        }

        return true;
    }

    /**
     * https://leetcode.com/problems/valid-palindrome-ii/
     */
    public static class ValidPalindromeII {
        private int deletions = 0;

        public boolean validPalindrome(String s) {
            if (s == null)
                return false;
            return validPalindrome(s, 0, s.length() - 1);
        }

        private boolean validPalindrome(String s, int left, int right) {
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    if (deletions != 0)
                        return false;
                    deletions++;
                    return validPalindrome(s, left + 1, right) || validPalindrome(s, left, right - 1);
                }
                left++;
                right--;
            }

            return true;
        }
    }
}