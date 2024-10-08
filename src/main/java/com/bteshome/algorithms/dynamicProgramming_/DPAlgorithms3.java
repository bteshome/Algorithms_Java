package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms3 {
    /**
     * leetcode https://leetcode.com/problems/longest-palindromic-substring/submissions/721472102/?envType=problem-list-v2&envId=two-pointers
     * */
    public static String longestPalindrome(String s) {
        if (s == null) {
            return null;
        }

        String longestPalindrome = "";
        int longestPalindromeLength = 0;
        Boolean[][] cache = new Boolean[s.length()][];
        for (int i = 0; i < s.length(); i++) {
            cache[i] = new Boolean[s.length()];
        }

        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length() - 1; j >= i; j--) {
                if (longestPalindrome(s, i, j, cache)) {
                    var palindromeLength = j - i + 1;
                    if (palindromeLength > longestPalindromeLength) {
                        longestPalindromeLength = palindromeLength;
                        longestPalindrome = s.substring(i, j + 1);
                    }
                }
            }
        }

        return longestPalindrome;
    }

    private static boolean longestPalindrome(String s, int left, int right, Boolean[][] cache) {
        if (left >= right) {
            return true;
        }

        if (cache[left][right] == null) {
            cache[left][right] = (s.charAt(left) == s.charAt(right))
                && longestPalindrome(s, left + 1, right - 1, cache);
        }

        return cache[left][right];
    }

    public static String longestPalindrome_bruteForce1(String s) {
        if (s == null) {
            return null;
        }

        var maxLengthPalindrome = "";

        // even length palindromes
        for (int center = 0; center < s.length(); center++) {
            var palindrome = longestPalindrome_bruteForce1(s, center, "", center, center + 1);
            if (palindrome.length() > maxLengthPalindrome.length()) {
                maxLengthPalindrome = palindrome;
            }
        }

        // odd length palindromes
        for (int center = 0; center < s.length(); center++) {
            var palindrome = longestPalindrome_bruteForce1(s, center, s.substring(center, center + 1), center - 1, center + 1);
            if (palindrome.length() > maxLengthPalindrome.length()) {
                maxLengthPalindrome = palindrome;
            }
        }

        return maxLengthPalindrome;
    }

    private static String longestPalindrome_bruteForce1(String s, int center, String palindrome, int left, int right) {
        if (left < 0 || right >= s.length()) {
            return palindrome;
        }

        if (s.charAt(left) != s.charAt(right)) {
            return palindrome;
        }

        return longestPalindrome_bruteForce1(s, center, s.charAt(left) + palindrome + s.charAt(right), left - 1, right + 1);
    }

    public static String longestPalindrome_bruteForce2(String s) {
        if (s == null) {
            return null;
        }

        String longestPalindrome = "";
        int longestPalindromeLength = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length() - 1; j >= i; j--) {
                if (longestPalindrome_bruteForce2(s, i, j)) {
                    var palindromeLength = j - i + 1;
                    if (palindromeLength > longestPalindromeLength) {
                        longestPalindromeLength = palindromeLength;
                        longestPalindrome = s.substring(i, j + 1);
                    }
                }
            }
        }

        return longestPalindrome;
    }

    private static boolean longestPalindrome_bruteForce2(String s, int left, int right) {
        if (left >= right) {
            return true;
        }

        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }

        return longestPalindrome_bruteForce2(s, left + 1, right - 1);
    }
}
