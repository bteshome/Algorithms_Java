package com.bteshome.algorithms.math_;

public class MathAlgorithms1 {
    /**
     * leetcode https://leetcode.com/problems/counting-bits/submissions/1405080874/?envType=problem-list-v2&envId=dynamic-programming&difficulty=EASY
     * */
    public static int[] countBits(int n) {
        int msbPosition = Integer.toBinaryString(n).length();
        int[] counts = new int[n+1];

        for (int i = 0; i <= n; i++) {
            counts[i] = countBits(i, msbPosition);
        }

        return counts;
    }

    private static int countBits(int n, int msbPosition) {
        int count = 0;

        for (int i = 0; i < msbPosition; i++) {
            int mask = 1 << i;
            if ((n & mask) != 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * https://leetcode.com/problems/reverse-integer/
     * NOTE - there is another implementation that does not use decimal math,
     *        which is slower.
     * */
    public static int reverseInteger(int x) {
        int reverse = 0;

        while (x != 0) {
            int mod = (x % 10);

            if (reverse > Integer.MAX_VALUE / 10 || (reverse == Integer.MAX_VALUE / 10 && mod > 7)) {
                return 0;
            }

            if (reverse < Integer.MIN_VALUE / 10 || (reverse == Integer.MAX_VALUE / 10 && mod < -8)) {
                return 0;
            }

            reverse = reverse * 10 + mod;
            x /= 10;
        }

        return reverse;
    }

    /**
     * https://leetcode.com/problems/string-to-integer-atoi/
     * */
    public static int stringToInteger(String s) {
        if (s == null) {
            return 0;
        }

        s = s.strip();

        if (s.isEmpty()) {
            return 0;
        }

        int i = 0;
        char c = s.charAt(i);
        boolean isNegative = false;
        int number = 0;

        if (c == '-') {
            isNegative = true;
            i++;
        } else if (c == '+') {
            i++;
        }

        while (i < s.length() && s.charAt(i) == '0') {
            i++;
        }

        while (i < s.length()) {
            c = s.charAt(i);

            if (!Character.isDigit(c)) {
                break;
            }

            int digit = c - '0';

            if (isNegative) {
                if (number > 0) {
                    number = -number;
                }

                if (number < Integer.MIN_VALUE / 10 || (number == Integer.MIN_VALUE / 10 && digit > 8)) {
                    number = Integer.MIN_VALUE;
                    break;
                }

                number = number * 10 - digit;
            } else {
                if (number > Integer.MAX_VALUE / 10 || (number == Integer.MAX_VALUE / 10 && digit > 7)) {
                    number = Integer.MAX_VALUE;
                    break;
                }

                number = number * 10 + digit;
            }

            i++;
        }

        return number;
    }
}
