package com.bteshome.algorithms.dynamicProgramming_;

import java.util.ArrayList;
import java.util.List;

public class DPAlgorithms11 {
    /**
     * https://leetcode.com/problems/decode-ways/?envType=problem-list-v2&envId=dynamic-programming&difficulty=MEDIUM
     * */
    public static int numDecodings(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        return numDecodings(s, 0, new Integer[s.length()]);
    }

    public static int numDecodings(String s, int pos, Integer[] cache) {
        if (pos == s.length()) {
            return 1;
        }

        if(cache[pos] == null) {
            var decodeWays = 0;

            if (s.charAt(pos) >= '1' && s.charAt(pos) <= '9') {
                decodeWays += numDecodings(s, pos + 1, cache);
            }

            if (pos < s.length() - 1) {
                var cc = s.substring(pos, pos + 2);
                if (cc.compareTo("10") >= 0 && cc.compareTo("26") <= 0) {
                    decodeWays += numDecodings(s, pos + 2, cache);
                }
            }

            cache[pos] = decodeWays;
        }

        return cache[pos];
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
        if (costs == null) {
            return 0;
        }

        var cache = new Integer[costs.length][];
        for (int i = 0; i < cache.length; i++) {
            cache[i] = new Integer[4];
        }

        return paintHouse(costs, 0, 3, cache);
    }

    private static int paintHouse(int[][] costs, int pos, int prevColorCode, Integer[][] cache) {
        if (pos == costs.length) {
            return 0;
        }

        if (cache[pos][prevColorCode] == null) {
            int cost = Integer.MAX_VALUE;

            for (int colorCode = 0; colorCode < 3; colorCode++) {
                if (colorCode != prevColorCode) {
                    cost = Math.min(cost, costs[pos][colorCode] + paintHouse(costs, pos + 1, colorCode, cache));
                }
            }

            cache[pos][prevColorCode] = cost;
        }

        return cache[pos][prevColorCode];
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
     * TODO - threw 'Memory Limit Exceeded'
     * https://leetcode.com/problems/paint-fence/?envType=problem-list-v2&envId=dynamic-programming&difficulty=MEDIUM
     * */
    public static int paintFenceRecursiveTODO(int n, int k) {
        if (n < 0 || k < 0) {
            return 0;
        }

        var cache = new Integer[n][][];
        for (int i = 0; i < n; i++) {
            cache[i] = new Integer[k + 1][];
            for (int j = 0; j <= k; j++) {
                cache[i][j] = new Integer[k + 1];
            }
        }

        return paintFenceRecursiveTODO(n, k, 0, k, k, cache);
    }

    public static int paintFenceRecursiveTODO(int n, int k, int pos, int prevPrevColor, int prevColor, Integer[][][] cache) {
        if (pos == n) {
            return 1;
        }

        if (cache[pos][prevPrevColor][prevColor] == null) {
            int ways = 0;

            for (int color = 0; color < k; color++) {
                if (!((prevPrevColor == prevColor) && (prevColor == color))) {
                    ways += paintFenceRecursiveTODO(n, k, pos + 1, prevColor, color, cache);
                }
            }

            cache[pos][prevPrevColor][prevColor] = ways;
        }

        return cache[pos][prevPrevColor][prevColor];
    }


}
