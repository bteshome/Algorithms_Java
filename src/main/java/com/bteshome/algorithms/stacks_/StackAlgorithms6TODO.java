package com.bteshome.algorithms.stacks_;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class StackAlgorithms6TODO {
    /**
     * TODO - fails leetcode time limit test.
     *        There could be a better approach using a segment tree.
     * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
     * */
    public static List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) {
            return List.of();
        }

        var stack = new Stack<Integer>();
        var smaller = new Integer[nums.length];
        var buffer = new Stack<Integer>();

        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            while (!stack.isEmpty() && stack.peek() >= num) {
                buffer.push(stack.pop());
            }

            smaller[i] = stack.size();
            stack.push(num);

            while (!buffer.isEmpty()) {
                stack.add(buffer.pop());
            }
        }

        return Arrays.stream(smaller).toList();
    }

    /**
     * TODO - not finished.
     * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
     * */
    public static int findShortestUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        var leftBoundary = new Stack<Integer>();
        var righBoundary = new Stack<Integer>();

        for (int i = 0; i < nums.length; i++) {
            if (!leftBoundary.isEmpty() && nums[i] < nums[leftBoundary.peek()]) {
                while (!leftBoundary.isEmpty() && nums[i] < nums[leftBoundary.peek()]) {
                    leftBoundary.pop();
                }
                System.out.println("left: " + (leftBoundary.isEmpty() ? 0 : leftBoundary.peek()));
            }
            leftBoundary.push(i);
        }

        for (int i = nums.length - 1; i >= 0 ; i--) {
            if (!righBoundary.isEmpty() && nums[i] > nums[righBoundary.peek()]) {
                while (!righBoundary.isEmpty() && nums[i] > nums[righBoundary.peek()]) {
                    righBoundary.pop();
                }
                System.out.println("right: " + (righBoundary.isEmpty() ? nums.length - 1 : righBoundary.peek()));
                break;
            }

            righBoundary.push(nums[i]);
        }

        /*int left = 0;
        int right = -1;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                left = i;
                break;
            }
        }

        for (int i = nums.length - 1; i > 0 ; i--) {
            if (nums[i] < nums[i - 1]) {
                right = i;
                break;
            }
        }

        return right - left + 1;*/

        return 0;
    }

    // TODO
    public static int findLengthOfShortestSubarray(int[] a) {
        if (a == null || a.length < 2)
            return 0;

        int fromLeft = 0;
        int fromRight = 0;

        for (int i = 0; i < a.length; i++) {
            if (i > 0 && a[i] < a[i-1])
                break;
            fromLeft = i;
        }

        for (int i = a.length - 1; i >= 0; i--) {
            if (i < a.length - 1 && a[i] > a[i+1])
                break;
            fromRight = i;
        }

        return 0;
    }
}
