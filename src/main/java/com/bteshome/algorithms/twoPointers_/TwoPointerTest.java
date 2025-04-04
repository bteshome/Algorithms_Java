package com.bteshome.algorithms.twoPointers_;

import javax.swing.*;
import java.util.*;

public class TwoPointerTest {
    public static void test() {
        //System.out.println(TwoPointerAlgorithms1.isSubsequence("axc", "ahbgdc"));

        //System.out.println(TwoPointerAlgorithms1.validPalindromeII_Recursive("abc"));
        //System.out.println(TwoPointerAlgorithms1.validPalindromeII_Iterative("abc"));

        //System.out.println(TwoPointerAlgorithms2.threeSum(new int[]{-2,0,0,2,2}));

        //System.out.println(TwoPointerAlgorithms2.longestPalindromicSubstring("babad"));

        //System.out.println(TwoPointerAlgorithms3.maxArea(new int[]{8,7,2,1}));

        //System.out.println(TwoPointerAlgorithms3.isPalindrome("A man, a plan, a canal: Panama"));

        //System.out.println(TwoPointerAlgorithms4.isOneEditDistance("ab", "acb"));
        //System.out.println(TwoPointerAlgorithms4.isOneEditDistance("a", "ac"));

        /*var pairs = twoSum(new int[]{-1,0,1,2,-1,-4}, 1);
        for (var pair : pairs)
            System.out.println(pair);*/

        //System.out.println(TwoPointerAlgorithms4.compareVersion("1.2", "1.10"));

    }


    public static int[] finalPrices(int[] prices) {
        if (prices == null || prices.length < 2)
            return prices;

        Stack<Integer> stack = new Stack<>();
        int[] finalPrices = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            int price = prices[i];
            while (!stack.isEmpty() && price <= prices[stack.peek()]) {
                int prevIndex = stack.pop();
                finalPrices[prevIndex] = prices[prevIndex] - price;
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int prevIndex = stack.pop();
            finalPrices[prevIndex] = prices[prevIndex];
        }

        return finalPrices;
    }
}
