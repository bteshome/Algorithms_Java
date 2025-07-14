package com.bteshome.algorithms.twoPointers_;

public class TwoPointerAlgorithms5 {
    /* https://leetcode.com/problems/valid-palindrome-iv */
    public static boolean makePalindrome(String s) {
        if (s == null || s.length() <= 2)
            return true;

        return makePalindrome(s, 0, s.length() - 1, 0);
    }

    private static boolean makePalindrome(String s, int i, int j, int operations) {
        if (i >= j)
            return true;

        char a = s.charAt(i);
        char b = s.charAt(j);

        if (a == b)
            return makePalindrome(s, i + 1, j - 1, operations);

        if (operations == 2)
            return false;

        return makePalindrome(s, i + 1, j - 1, operations + 1);
    }

    /**
     * https://leetcode.com/problems/palindromic-substrings
     * NOTE: this can be easily solved using DP as well, but this expand around center
     *       approach uses less space, and is faster (though in the same big 0 class)
     */
    public static int countSubstringsBottomUp(String s) {
        if (s == null)
            return 0;
        if (s.length() < 2)
            return s.length();

        int n = s.length();
        int count = 0;

        for (int i = 0; i < n; i++) {
            // odd length palindromes
            count += countSubstringsBottomUp(s, i, i);
            // even length palindromes
            count += countSubstringsBottomUp(s, i, i + 1);
        }

        return count;
    }

    private static int countSubstringsBottomUp(String s, int i, int j) {
        int count = 0;

        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            count++;
            i--;
            j++;
        }

        return count;
    }

    /**
     * https://leetcode.com/problems/longest-palindromic-substring
     * NOTE: this solution is based on the expand around center approach.
     *       There is also a DP solution, but this one is faster
     * */
    public static class LongestPalindrome {
        private int maxLength = 0;
        private int maxLenStart = 0;
        private int maxLenEnd = -1;

        public String longestPalindrome(String s) {
            if (s == null || s.length() < 2)
                return s;

            for (int i = 0; i < s.length(); i++) {
                longestPalindrome(s, i, i);
                longestPalindrome(s, i, i + 1);
            }

            return s.substring(maxLenStart, maxLenEnd + 1);
        }

        private void longestPalindrome(String s, int i, int j) {
            while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
                int length = j - i + 1;
                if (length > maxLength) {
                    maxLength = length;
                    maxLenStart = i;
                    maxLenEnd = j;
                }
                i--;
                j++;
            }
        }
    }
}
