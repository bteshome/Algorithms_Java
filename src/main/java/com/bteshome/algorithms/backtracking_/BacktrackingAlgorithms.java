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

    public static List<String> generateParenthesis2(int n) {
        if (n <= 0)
            return List.of();

        var pairs = new ArrayList<String>();
        generateParenthesis2(n, 0, 0, pairs, new StringBuilder());

        return pairs;
    }

    private static void generateParenthesis2(int n, int openCount, int closedCount, List<String> pairs, StringBuilder candidate) {
        if (closedCount == n) {
            pairs.add(candidate.toString());
            return;
        }

        if (openCount == 0) {
            candidate.append('(');
            generateParenthesis2(n, openCount + 1, closedCount, pairs, candidate);
            candidate.deleteCharAt(candidate.length() - 1);
            return;
        }

        if (openCount + closedCount == n) {
            candidate.append(')');
            generateParenthesis2(n, openCount - 1, closedCount + 1, pairs, candidate);
            candidate.deleteCharAt(candidate.length() - 1);
            return;
        }

        candidate.append('(');
        generateParenthesis2(n, openCount + 1, closedCount, pairs, candidate);
        candidate.deleteCharAt(candidate.length() - 1);

        candidate.append(')');
        generateParenthesis2(n, openCount - 1, closedCount + 1, pairs, candidate);
        candidate.deleteCharAt(candidate.length() - 1);
    }

    /**
     * leetcode https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/?envType=study-plan-v2&envId=top-interview-150
     * */
    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.isBlank())
            return List.of();

        Map<Character, Set<Character>> digitToLetter = new HashMap<>();
        List<String> combinations = new ArrayList<>();
        digitToLetter.put('2', Set.of('a', 'b', 'c'));
        digitToLetter.put('3', Set.of('d', 'e', 'f'));
        digitToLetter.put('4', Set.of('g', 'h', 'i'));
        digitToLetter.put('5', Set.of('j', 'k', 'l'));
        digitToLetter.put('6', Set.of('m', 'n', 'o'));
        digitToLetter.put('7', Set.of('p', 'q', 'r', 's'));
        digitToLetter.put('8', Set.of('t', 'u', 'v'));
        digitToLetter.put('9', Set.of('w', 'x', 'y', 'z'));

        letterCombinations(digits, 0, combinations, new StringBuilder(), digitToLetter);

        return combinations;
    }

    private static void letterCombinations(String digits, int pos, List<String> combinations, StringBuilder candidate, Map<Character, Set<Character>> digitToLetter) {
        if (pos == digits.length()) {
            combinations.add(candidate.toString());
            return;
        }

        char digit = digits.charAt(pos);

        if (!digitToLetter.containsKey(digit))
            throw new IllegalArgumentException("input contains invalid characters.");

        for (Character letter : digitToLetter.get(digit)) {
            candidate.append(letter);
            letterCombinations(digits, pos + 1, combinations, candidate, digitToLetter);
            candidate.deleteCharAt(candidate.length() - 1);
        }
    }
}
