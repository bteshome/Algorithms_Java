package com.bteshome.algorithms.strings_;

public class StringAlgorithms1 {
    /**
     * https://leetcode.com/problems/valid-palindrome/
     * */
    public static boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }

        int i = 0;
        int j = s.length() - 1;

        while (i <= j) {
            char a = s.charAt(i);
            char b = s.charAt(j);
            if (!(Character.isLetter(a) || Character.isDigit(a))) {
                i++;
            } else if (!(Character.isLetter(b) || Character.isDigit(b))) {
                j--;
            } else {
                if (Character.toLowerCase(a) != Character.toLowerCase(b)) {
                    return false;
                }
                i++;
                j--;
            }
        }

        return true;
    }

    /**
     * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
     * */
    public static int findFirstOccurrence(String haystack, String needle) {
        if (haystack == null || needle == null || needle.length() > haystack.length()) {
            return -1;
        }

        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            if (findFirstOccurrenceMatch(haystack, needle, i)) {
                return i;
            }
        }

        return -1;
    }

    private static boolean findFirstOccurrenceMatch(String haystack, String needle, int i) {
        for (int j = 0; j < needle.length() && i < haystack.length(); j++, i++) {
            if (haystack.charAt(i) != needle.charAt(j)) {
                return false;
            }
        }

        return true;
    }

    /**
     * https://leetcode.com/problems/roman-to-integer/
     * */
    public static int romanToInteger(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("input null or empty.");
        }

        int number = 0;
        int end = s.length() - 1;

        for (int i = 0; i < s.length();) {
            char c = s.charAt(i);

            if (c == 'I') {
                if (i < end && s.charAt(i + 1) == 'V') {
                    number += 4;
                    i += 2;
                } else if (i < end && s.charAt(i + 1) == 'X') {
                    number += 9;
                    i += 2;
                } else {
                    number += 1;
                    i++;
                }
            } else if (c == 'X') {
                if (i < end && s.charAt(i + 1) == 'L') {
                    number += 40;
                    i += 2;
                } else if (i < end && s.charAt(i + 1) == 'C') {
                    number += 90;
                    i += 2;
                } else {
                    number += 10;
                    i++;
                }
            } else if (c == 'C') {
                if (i < end && s.charAt(i + 1) == 'D') {
                    number += 400;
                    i += 2;
                } else if (i < end && s.charAt(i + 1) == 'M') {
                    number += 900;
                    i += 2;
                } else {
                    number += 100;
                    i++;
                }
            } else {
                if (c == 'V') {
                    number += 5;
                } else if (c == 'L') {
                    number += 50;
                } else if (c == 'D') {
                    number += 500;
                } else if (c == 'M') {
                    number += 1000;
                } else {
                    throw new IllegalArgumentException("input is invalid.");
                }
                i++;
            }
        }

        return number;
    }
}
