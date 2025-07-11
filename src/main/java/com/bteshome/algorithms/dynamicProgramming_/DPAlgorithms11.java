package com.bteshome.algorithms.dynamicProgramming_;

import java.util.*;

public class DPAlgorithms11 {
    /**
     * https://leetcode.com/problems/decode-ways/?envType=problem-list-v2&envId=dynamic-programming&difficulty=MEDIUM
     * */
    public int numDecodings(String s) {
        if (s == null || s.isEmpty())
            return 0;

        Map<String, Character> map = new HashMap<>();
        for (int i = 1; i <= 26; i++)
            map.put(Integer.toString(i), (char)('A' + i - 1));

        return numDecodings(s, 0, map, new HashMap<>());
    }

    private int numDecodings(String s, int pos, Map<String, Character> map, Map<Integer, Integer> cache) {
        if (pos == s.length())
            return 1;

        if (!cache.containsKey(pos)) {
            int ways = 0;
            if (map.containsKey(s.substring(pos, pos + 1)))
                ways += numDecodings(s, pos + 1, map, cache);
            if (pos < s.length() - 1 && map.containsKey(s.substring(pos, pos + 2)))
                ways += numDecodings(s, pos + 2, map, cache);
            cache.put(pos, ways);
        }

        return cache.get(pos);
    }

    /**
     * https://leetcode.com/problems/different-ways-to-add-parentheses/?envType=problem-list-v2&envId=dynamic-programming&difficulty=MEDIUM
     * */
    public static List<Integer> diffWaysToComputeTODO(String expression) {
        var ways = new ArrayList<Integer>();

        if (expression != null) {
            //diffWaysToComputeTODO(expression, 0, ways);
        }

        return ways;
    }

    private static void diffWaysToComputeTODO(String expression, int pos, List<Integer> ways) {
        char operator = ' ';
        int left = -1;

        for (int i = pos; i < expression.length(); i++) {
            var c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                operator = c;
                left = Integer.parseInt(expression.substring(pos, c));
                break;
            }
        }
    }

    /**
     * https://leetcode.com/problems/paint-house/?envType=problem-list-v2&envId=dynamic-programming&difficulty=MEDIUM
     * */
    public static int paintHouse(int[][] costs) {
        if (costs == null || costs.length == 0)
            return 0;

        Integer[][] cache = new Integer[costs.length][];
        for (int i = 0; i < costs.length; i++)
            cache[i] = new Integer[3];

        int redCost = minCost(costs, costs.length - 1, 0, cache);
        int blueCost = minCost(costs, costs.length - 1, 1, cache);
        int greenCost = minCost(costs, costs.length - 1, 2, cache);

        return Math.min(Math.min(redCost, blueCost), greenCost);
    }

    private static int minCost(int[][] costs, int pos, int color, Integer[][] cache) {
        if (pos == 0)
            return costs[pos][color];

        if (cache[pos][color] == null) {
            int cost = Integer.MAX_VALUE;

            for (int i = 0; i < 3; i++)
                if (i != color)
                    cost = Math.min(cost, minCost(costs, pos - 1, i, cache));

            cache[pos][color] = cost + costs[pos][color];
        }

        return cache[pos][color];
    }

    /**
     * https://leetcode.com/problems/paint-house-ii/?envType=study-plan-v2&envId=premium-algo-100
     * */
    public static int paintHouseII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }

        var cache = new Integer[costs.length][];
        for (int i = 0; i < costs.length; i++) {
            cache[i] = new Integer[costs[i].length + 1];
        }

        return paintHouseII(costs, 0, costs[0].length, cache);
    }

    private static int paintHouseII(int[][] costs, int pos, int prevColor, Integer[][] cache) {
        if (pos == costs.length) {
            return 0;
        }

        if (cache[pos][prevColor] == null) {
            int minCost = Integer.MAX_VALUE;

            for (int color = 0; color < costs[pos].length; color++) {
                if (color != prevColor) {
                    var nextCost = paintHouseII(costs, pos + 1, color, cache);
                    if (nextCost != -1) {
                        minCost = Math.min(minCost, costs[pos][color] + nextCost);
                    }
                }
            }

            cache[pos][prevColor] = minCost == Integer.MAX_VALUE ? -1 : minCost;
        }

        return cache[pos][prevColor];
    }

    /**
     * https://leetcode.com/problems/paint-fence/?envType=problem-list-v2&envId=dynamic-programming&difficulty=MEDIUM
     * */
    public static int paintFence(int n, int k) {
        if (n < 1 || k < 1)
            return 0;
        if (n == 1)
            return k;
        if (n == 2)
            return k * k;

        int[][] dp = new int[n][];
        for (int i = 0; i < n; i++)
            dp[i] = new int[k];

        for (int j = 0; j < k; j++) {
            dp[0][j] = 1;
            dp[1][j] = k;
        }

        for (int i = 2; i < n; i++)
            for (int j = 0; j < k; j++) {
                for (int c = 0; c < k; c++)
                    if (c != j)
                        dp[i][j] += dp[i - 1][c];

                dp[i][j] += dp[i - 2][j] * (k - 1);
            }

        return Arrays.stream(dp[n - 1]).sum();
    }
}
