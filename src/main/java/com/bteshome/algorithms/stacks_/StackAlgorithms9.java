package com.bteshome.algorithms.stacks_;

import java.util.*;

public class StackAlgorithms9 {
    /*
    * https://leetcode.com/problems/maximum-width-ramp
    * */
    public static int maxWidthRamp(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        Stack<Integer> stack = new Stack<>();
        int maxWidth = 0;

        for (int i = 0; i < nums.length; i++)
            if (stack.isEmpty() || nums[i] < nums[stack.peek()])
                stack.push(i);

        for (int i = nums.length - 1; i >= 0 ; i--) {
            while (!stack.isEmpty() && nums[i] >= nums[stack.peek()])
                maxWidth = Math.max(maxWidth, i - stack.pop());
            if (stack.isEmpty())
                break;
        }

        return maxWidth;
    }

    /*
    * https://leetcode.com/problems/buildings-with-an-ocean-view/
    * */
    public static int[] findBuildingsWithOceanView(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> nextGreaterOrEqual = new HashMap<>();
        List<Integer> havingOceanView = new ArrayList<>();

        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] >= heights[stack.peek()])
                nextGreaterOrEqual.put(stack.pop(), i);
            stack.push(i);
        }

        for (int i = 0; i < heights.length; i++)
            if (!nextGreaterOrEqual.containsKey(i))
                havingOceanView.add(i);

        return havingOceanView.stream().mapToInt(e -> e).toArray();
    }
}
