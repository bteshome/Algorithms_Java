package com.bteshome.algorithms.twoPointers_;

public class TwoPointerAlgorithms3 {
    /*
    * https://leetcode.com/problems/container-with-most-water/
    * */
    public static int maxArea(int[] height) {
        if (height == null || height.length < 2)
            return 0;

        int left = 0;
        int right = height.length - 1;
        int maxAreaSoFar = 0;

        while (left < right) {
            int leftHeight = height[left];
            int rightHeight = height[right];
            int area = (right - left) * Math.min(leftHeight, rightHeight);
            maxAreaSoFar = Math.max(maxAreaSoFar, area);
            if (leftHeight <= rightHeight)
                left++;
            else
                right--;
        }

        return maxAreaSoFar;
    }

    /*
    * https://leetcode.com/problems/valid-palindrome/
    * */
    public static boolean isPalindrome(String s) {
        if (s == null)
            return true;

        s = isPalindrome_cleanse(s);

        if (s.length() < 2)
            return true;

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right))
                return false;
            left++;
            right--;
        }

        return true;
    }

    private static String isPalindrome_cleanse(String s) {
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                int diffWithA = c - 'A';
                int lowercasedAsInt = 'a' + diffWithA;
                buffer.append((char)lowercasedAsInt);
            }
            else if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9'))
                buffer.append(c);
        }

        return buffer.toString();
    }
}
