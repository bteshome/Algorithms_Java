package com.bteshome.algorithms.stacks_;

import java.util.HashSet;
import java.util.Stack;

public class StackAlgorithms2 {
    /**
     * https://leetcode.com/problems/remove-outermost-parentheses/
     * */
    public static String removeOuterParentheses(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        if (s.length() % 2 == 1) {
            return s;
        }

        var openings = new Stack<Integer>();
        var outers = new HashSet<Integer>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                openings.push(i);
            } else {
                var top = openings.pop();
                if (openings.isEmpty()) {
                    outers.add(top);
                    outers.add(i);
                }
            }
        }

        var output = new StringBuilder(s.length());

        for (int i = 0; i < s.length(); i++) {
            if (!outers.contains(i)) {
                output.append(s.charAt(i));
            }
        }

        return output.toString();
    }

    /**
     * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
     * */
    public static String minRemoveToMakeValidParentheses(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        var stack = new Stack<Integer>();
        var buffer = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            var c = s.charAt(i);
            if (c == '(') {
                buffer.append(c);
                stack.push(buffer.length()-1);
            } else if (c == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                    buffer.append(c);
                }
            } else {
                buffer.append(c);
            }
        }

        while (!stack.isEmpty()) {
            int i = stack.pop();
            buffer.deleteCharAt(i);
            buffer.insert(i, '?');
        }

        for (int i = 0;;) {
            if (i == buffer.length()) {
                break;
            }
            if (buffer.charAt(i) == '?') {
                buffer.deleteCharAt(i);
            } else {
                i++;
            }
        }

        return buffer.toString();
    }
}
