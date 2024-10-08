package com.bteshome.algorithms.backtracking_;

import java.util.*;

public class BacktrackingAlgorithms {
    /**
     * leetcode https://leetcode.com/problems/generate-parentheses/editorial/?envType=problem-list-v2&envId=dynamic-programming&difficulty=MEDIUM
     */
    public static List<String> generateParenthesis(int n) {
        var combinations = new ArrayList<String>();
        generateParenthesis(n, 0, 0, combinations, "");
        return combinations;
    }

    private static void generateParenthesis(int n, int openCount, int closedCount, List<String> combinations, String pairs) {
        if (pairs.length() == n * 2) {
            combinations.add(pairs);
            return;
        }

        if (openCount == 0) {
            generateParenthesis(n, openCount + 1, closedCount, combinations, pairs + "(");
        } else if ((openCount + closedCount) == n) {
            generateParenthesis(n, openCount - 1, closedCount + 1, combinations, pairs + ")");
        } else {
            generateParenthesis(n, openCount + 1, closedCount, combinations, pairs + "(");
            generateParenthesis(n, openCount - 1, closedCount + 1, combinations, pairs + ")");
        }
    }

    /**
     * leetcode https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/?envType=study-plan-v2&envId=top-interview-150
     * */
    public static List<String> letterCombinations(String digits) {
        Map<Character, char[]> digitToLetter = Map.of(
                '2', new char[]{'a', 'b', 'c'},
                '3', new char[]{'d', 'e', 'f'},
                '4', new char[]{'g', 'h', 'i'},
                '5', new char[]{'j', 'k', 'l'},
                '6', new char[]{'m', 'n', 'o'},
                '7', new char[]{'p', 'q', 'r', 's'},
                '8', new char[]{'t', 'u', 'v'},
                '9', new char[]{'w', 'x', 'y', 'z'}
        );

        var combinations = new ArrayList<String>();

        if (digits != null && !digits.isEmpty()) {
            letterCombinations(digits, 0, "", combinations, digitToLetter);
        }

        return combinations;
    }

    private static void letterCombinations(String digits, int pos, String combination, List<String> combinations, Map<Character, char[]> digitToLetter) {
        if (pos == digits.length()) {
            combinations.add(combination);
            return;
        }

        for (var letter : digitToLetter.get(digits.charAt(pos))) {
            letterCombinations(digits, pos + 1, combination + letter, combinations, digitToLetter);
        }
    }

}
