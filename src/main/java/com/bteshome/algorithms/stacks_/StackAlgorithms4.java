package com.bteshome.algorithms.stacks_;

import java.util.*;

public class StackAlgorithms4 {
    /**
     * https://leetcode.com/problems/evaluate-reverse-polish-notation/
     * */
    public static int evaluateReversePolishNotation(String[] tokens) {
        if (tokens == null) {
            throw new IllegalArgumentException("tokens is null");
        }

        var stack = new Stack<Integer>();

        for (String token : tokens) {
            if (token.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (token.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (token.equals("/")) {
                Integer denominator = stack.pop();
                stack.push(stack.pop() / denominator);
            } else if (token.equals("-")) {
                Integer subtrahend = stack.pop();
                stack.push(stack.pop() - subtrahend);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    /**
     * https://leetcode.com/problems/asteroid-collision/
     * */
    public static int[] asteroidCollision(int[] asteroids) {
        if (asteroids == null || asteroids.length < 2) {
            return asteroids;
        }

        var state = new Stack<Integer>();

        for (int asteroid : asteroids) {
            asteroidCollisionAdd(asteroid, state);
        }

        var finalState = new int[state.size()];
        for (int i = finalState.length - 1; i >= 0; i--) {
            finalState[i] = state.pop();
        }

        return finalState;
    }

    private static void asteroidCollisionAdd(Integer current, Stack<Integer> state) {
        if (state.isEmpty()) {
            state.push(current);
        } else {
            var last = state.peek();
            if ((last > 0 && current > 0) || (last < 0 && current < 0)) {
                state.push(current);
            } else {
                if (current > 0) {
                    state.push(current);
                } else if (last + current == 0) {
                    state.pop();
                } else if (Math.abs(current) > Math.abs(last)) {
                    state.pop();
                    asteroidCollisionAdd(current, state);
                }
            }
        }
    }
}
