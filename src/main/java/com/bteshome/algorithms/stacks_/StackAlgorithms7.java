package com.bteshome.algorithms.stacks_;

import java.util.Stack;

public class StackAlgorithms7 {
    /**
     * NOTE - it uses stacks, and fails leetcode time limit test.
     *        Take a look at the second approach below, which is significantly faster.
     *        This second approach does not use stacks.
     * https://leetcode.com/problems/trapping-rain-water/
     * */
    public static int trappingRainWater(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }

        var biggestOnLeft = getBiggestOnLeft(height);
        var biggestOnRight = getBiggestOnRight(height);
        var trappedVolume = 0;

        for (int i = 0; i < height.length; i++) {
            if (biggestOnLeft[i] != -1 && biggestOnRight[i] != -1) {
                var trappingHeight = Math.min(height[biggestOnLeft[i]], height[biggestOnRight[i]]) - height[i];
                trappedVolume += trappingHeight;
            }
        }

        return trappedVolume;
    }

    private static int[] getBiggestOnLeft(int[] height) {
        var biggest = new int[height.length];
        var stack = new Stack<Integer>();

        for (int i = 0; i < height.length; i++) {
            if (!stack.isEmpty() && height[stack.peek()] > height[i]) {
                biggest[i] = stack.peek();
            } else {
                biggest[i] = -1;
            }

            var temp = new Stack<Integer>();
            while (!stack.isEmpty() && height[stack.peek()] > height[i]) {
                temp.push(stack.pop());
            }
            stack.push(i);
            while (!temp.isEmpty()) {
                stack.push(temp.pop());
            }
        }

        return biggest;
    }

    private static int[] getBiggestOnRight(int[] height) {
        var biggest = new int[height.length];
        var stack = new Stack<Integer>();

        for (int i = height.length - 1; i >= 0; i--) {
            if (!stack.isEmpty() && height[stack.peek()] > height[i]) {
                biggest[i] = stack.peek();
            } else {
                biggest[i] = -1;
            }

            var temp = new Stack<Integer>();
            while (!stack.isEmpty() && height[stack.peek()] > height[i]) {
                temp.push(stack.pop());
            }
            stack.push(i);
            while (!temp.isEmpty()) {
                stack.push(temp.pop());
            }
        }

        return biggest;
    }

    public static int trappingRainWater_Approach2(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }

        var biggestOnLeft = new int[height.length];
        var biggestOnRight = new int[height.length];
        var trappedVolume = 0;

        var max = -1;

        for (int i = 0; i < height.length; i++) {
            biggestOnLeft[i] = max;
            if (max == -1 || height[i] >= height[max]) {
                max = i;
            }
        }

        max = -1;

        for (int i = height.length - 1; i >= 0 ; i--) {
            biggestOnRight[i] = max;
            if (max == -1 || height[i] >= height[max]) {
                max = i;
            }
        }

        for (int i = 0; i < height.length; i++) {
            if (biggestOnLeft[i] != -1 && biggestOnRight[i] != -1) {
                var trappingHeight = Math.min(height[biggestOnLeft[i]], height[biggestOnRight[i]]) - height[i];
                if (trappingHeight > 0) {
                    trappedVolume += trappingHeight;
                }
            }
        }

        return trappedVolume;
    }
}
