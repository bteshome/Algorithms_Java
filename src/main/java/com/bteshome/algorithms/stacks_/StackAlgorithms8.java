package com.bteshome.algorithms.stacks_;

import java.util.Arrays;
import java.util.Stack;

public class StackAlgorithms8 {
    /**
     * https://leetcode.com/problems/largest-rectangle-in-histogram/
     * */
    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int[] nextSmaller = largestRectangleAreaGetNextSmaller(heights);
        int[] previousSmaller = largestRectangleAreaGetPreviousSmaller(heights);
        int largestRectangle = 0;

        for (int i = 0; i < heights.length; i++) {
            var width = nextSmaller[i] - previousSmaller[i] - 1;
            var area = width * heights[i];
            largestRectangle = Math.max(largestRectangle, area);
        }

        return largestRectangle;
    }

    private static int[] largestRectangleAreaGetNextSmaller(int[] heights) {
        var stack = new Stack<Integer>();
        var nextSmaller = new int[heights.length];
        Arrays.fill(nextSmaller, heights.length);

        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                nextSmaller[stack.pop()] = i;
            }
            stack.push(i);
        }

        return nextSmaller;
    }

    private static int[] largestRectangleAreaGetPreviousSmaller(int[] heights) {
        var stack = new Stack<Integer>();
        var previousSmaller = new int[heights.length];
        Arrays.fill(previousSmaller, -1);

        for (int i = heights.length - 1; i >= 0 ; i--) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                previousSmaller[stack.pop()] = i;
            }
            stack.push(i);
        }

        return previousSmaller;
    }
}
