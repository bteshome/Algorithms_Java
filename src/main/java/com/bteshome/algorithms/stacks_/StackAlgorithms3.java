package com.bteshome.algorithms.stacks_;

import java.util.Stack;

public class StackAlgorithms3 {
    /**
     * https://leetcode.com/problems/daily-temperatures/
     * */
    public static int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
            return temperatures;
        }

        int[] daysWaited = new int[temperatures.length];
        var days = new Stack<Integer>();
        days.add(0);

        for (int i = 1; i < temperatures.length; i++) {
            while (!days.isEmpty() && temperatures[i] > temperatures[days.peek()]) {
                int day = days.pop();
                daysWaited[day] = i - day;
            }
            days.add(i);
        }

        return daysWaited;
    }

    /**
     * https://leetcode.com/problems/decode-string/
     * */
    public static String decodeString(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        var stack = new Stack<String>();

        for (int i = 0; i < s.length();) {
            char c = s.charAt(i);
            if (c == ']') {
                decodeStringEvaluateTop(stack);
                i++;
            } else if (c == '[') {
                i++;
            } else if (decodeStringIsDigit(c)) {
                int j = i + 1;
                while (j < s.length() && decodeStringIsDigit(s.charAt(j))) {
                    j++;
                }
                stack.push(s.substring(i, j));
                i = j;
            } else if (decodeStringIsLetter(c)){
                int j = i + 1;
                while (j < s.length() && decodeStringIsLetter(s.charAt(j))) {
                    j++;
                }
                var text = s.substring(i, j);
                if (!stack.isEmpty() && !decodeStringIsNumber(stack.peek())) {
                    text = stack.pop() + text;
                }
                stack.push(text);
                i = j;
            } else {
                throw new IllegalArgumentException("string contains in an valid character");
            }
        }

        return stack.pop();
    }

    private static boolean decodeStringIsLetter(char c) {
        return c >= 'a' && c <= 'z';
    }

    private static boolean decodeStringIsDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean decodeStringIsNumber(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void decodeStringEvaluateTop(Stack<String> stack) {
        var operand = stack.pop();
        var frequency = Integer.parseInt(stack.pop());
        var text = operand.repeat(frequency);
        if (!stack.isEmpty() && !decodeStringIsNumber(stack.peek())) {
            text = stack.pop() + text;
        }
        stack.push(text);
    }
}
