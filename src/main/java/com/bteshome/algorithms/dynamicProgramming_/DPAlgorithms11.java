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
     * https://leetcode.com/problems/different-ways-to-add-parentheses
     * */
    public static List<Integer> diffWaysToCompute(String expression) {
        if (expression == null || expression.isEmpty())
            return List.of();

        return diffWaysToCompute(expression, new HashMap<>());
    }

    private static List<Integer> diffWaysToCompute(String expression, Map<String, List<Integer>> dp) {
        if (dp.containsKey(expression))
            return dp.get(expression);

        List<Integer> results = new ArrayList<>();

        if (expression.length() == 1) {
            results.add(Integer.parseInt(expression.substring(0, 1)));
        }
        else {
            for (int i = 1; i < expression.length() - 1; i++) {
                char c = expression.charAt(i);
                if (c == '+' || c == '-' || c == '*') {
                    List<Integer> leftResults = diffWaysToCompute(expression.substring(0, i));
                    List<Integer> rightResults = diffWaysToCompute(expression.substring(i + 1));
                    for (int left : leftResults) {
                        for (int right : rightResults) {
                            results.add(evaluate(left, c, right));
                        }
                    }
                }
            }
        }

        dp.put(expression, results);

        return dp.get(expression);
    }

    private static int evaluate(int left, char operator, int right) {
        return switch (operator) {
            case '+' -> left + right;
            case '-' -> left - right;
            case '*' -> left * right;
            default -> 0;
        };
    }

    /**
     * https://leetcode.com/problems/paint-house/?envType=problem-list-v2&envId=dynamic-programming&difficulty=MEDIUM
     * */
    public static int paintHouseTopDown(int[][] costs) {
        if (costs == null || costs.length == 0)
            return 0;

        Integer[][] cache = new Integer[costs.length][];
        for (int i = 0; i < costs.length; i++)
            cache[i] = new Integer[3];

        int redCost = paintHouseTopDown(costs, costs.length - 1, 0, cache);
        int blueCost = paintHouseTopDown(costs, costs.length - 1, 1, cache);
        int greenCost = paintHouseTopDown(costs, costs.length - 1, 2, cache);

        return Math.min(Math.min(redCost, blueCost), greenCost);
    }

    private static int paintHouseTopDown(int[][] costs, int pos, int color, Integer[][] cache) {
        if (pos == 0)
            return costs[pos][color];

        if (cache[pos][color] == null) {
            int cost = Integer.MAX_VALUE;

            for (int i = 0; i < 3; i++)
                if (i != color)
                    cost = Math.min(cost, paintHouseTopDown(costs, pos - 1, i, cache));

            cache[pos][color] = cost + costs[pos][color];
        }

        return cache[pos][color];
    }

    public static int paintHouseBottomUp(int[][] costs) {
        if (costs == null || costs.length == 0)
            return 0;

        int n = costs.length;
        int[][] dp = new int[n][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];

        for (int i = 1; i < n; i++) {
            dp[i][0] = costs[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = costs[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = costs[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        return Math.min(Math.min(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2]);
    }

    /**
     * https://leetcode.com/problems/paint-house-ii/?envType=study-plan-v2&envId=premium-algo-100
     * */
    public static int paintHouseIITopDown(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }

        var cache = new Integer[costs.length][];
        for (int i = 0; i < costs.length; i++) {
            cache[i] = new Integer[costs[i].length + 1];
        }

        return paintHouseIITopDown(costs, 0, costs[0].length, cache);
    }

    private static int paintHouseIITopDown(int[][] costs, int pos, int prevColor, Integer[][] cache) {
        if (pos == costs.length) {
            return 0;
        }

        if (cache[pos][prevColor] == null) {
            int minCost = Integer.MAX_VALUE;

            for (int color = 0; color < costs[pos].length; color++) {
                if (color != prevColor) {
                    var nextCost = paintHouseIITopDown(costs, pos + 1, color, cache);
                    if (nextCost != -1) {
                        minCost = Math.min(minCost, costs[pos][color] + nextCost);
                    }
                }
            }

            cache[pos][prevColor] = minCost == Integer.MAX_VALUE ? -1 : minCost;
        }

        return cache[pos][prevColor];
    }

    public static int paintHouseIIBottomUp(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0)
            return 0;

        int n = costs.length;
        int k = costs[0].length;

        int[][] dp = new int[n][k];
        for (int j = 0; j < k; j++)
            dp[0][j] = costs[0][j];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                int min = Integer.MAX_VALUE;
                for (int l = 0; l < k; l++)
                    if (l != j)
                        min = Math.min(min, dp[i - 1][l]);
                dp[i][j] = min + costs[i][j];
            }
        }

        int min = Integer.MAX_VALUE;
        for (int j = 0; j < k; j++)
            min = Math.min(min, dp[n - 1][j]);

        return min;
    }

    /**
     * https://leetcode.com/problems/paint-house-iii/
     * NOTE:
     *  - the early pruning makes a significant improvement, at least 5x
     *  - moreover, the array version is around 50x faster than the map version
     *  - the bottom up version is up to 3x slower than the equivalent top down version
     * */
    public static class PaintHouseIIITopDownWithMap {
        private int[] houses = null;
        private int[][] cost = null;
        private int m;
        private int n;
        private int target;

        public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
            if (target < 0)
                return -1;
            if (houses == null || houses.length == 0 || cost == null || cost.length == 0)
                return target == 0 ? 0 : -1;
            if (m != houses.length || m != cost.length || n != cost[0].length)
                return -1;

            this.houses = houses;
            this.cost = cost;
            this.m = m;
            this.n = n;
            this.target = target;

            return minCost(0, 0, 0, new HashMap<>());
        }

        private int minCost(int neighborhoodsCreated, int prevColor, int pos, Map<String, Integer> dp) {
            if (pos == houses.length)
                return neighborhoodsCreated == target ? 0 : -1;
            // early pruning
            if (neighborhoodsCreated > target)
                return -1;
            // early pruning
            if (neighborhoodsCreated + m - pos < target)
                return -1;

            String key = "%s,%s,%s".formatted(pos, neighborhoodsCreated, prevColor);

            if (dp.containsKey(key))
                return dp.get(key);

            int min = Integer.MAX_VALUE;

            if (houses[pos] != 0) {
                int color = houses[pos];
                int currentNeighborhoodsCreated = neighborhoodsCreated + (pos == 0 || prevColor != color ? 1 : 0);
                int next = minCost(currentNeighborhoodsCreated, color, pos + 1, dp);
                if (next != -1)
                    min = next;
            } else {
                for (int color = 1; color <= n; color++) {
                    int currentNeighborhoodsCreated = neighborhoodsCreated + (pos == 0 || prevColor != color ? 1 : 0);
                    int next = minCost(currentNeighborhoodsCreated, color, pos + 1, dp);
                    if (next != -1)
                        min = Math.min(min, cost[pos][color - 1] + next);
                }
            }

            dp.put(key, min == Integer.MAX_VALUE ? -1 : min);

            return dp.get(key);
        }
    }

    public static class PaintHouseIIITopDownWithArray {
        private int[] houses = null;
        private int[][] cost = null;
        private int m;
        private int n;
        private int target;
        private Integer[][][] dp = null;

        public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
            if (target < 0)
                return -1;
            if (houses == null || houses.length == 0 || cost == null || cost.length == 0)
                return target == 0 ? 0 : -1;
            if (m != houses.length || m != cost.length || n != cost[0].length)
                return -1;

            this.houses = houses;
            this.cost = cost;
            this.m = m;
            this.n = n;
            this.target = target;
            this.dp = new Integer[m][target + 1][n + 1];

            return minCost(0, 0, 0);
        }

        private int minCost(int neighborhoodsCreated, int prevColor, int pos) {
            if (pos == houses.length)
                return neighborhoodsCreated == target ? 0 : -1;
            // early pruning
            if (neighborhoodsCreated > target)
                return -1;
            // early pruning
            if (neighborhoodsCreated + m - pos < target)
                return -1;

            if (dp[pos][neighborhoodsCreated][prevColor] != null)
                return dp[pos][neighborhoodsCreated][prevColor];

            int min = Integer.MAX_VALUE;

            if (houses[pos] != 0) {
                int color = houses[pos];
                int currentNeighborhoodsCreated = neighborhoodsCreated + (pos == 0 || prevColor != color ? 1 : 0);
                int next = minCost(currentNeighborhoodsCreated, color, pos + 1);
                if (next != -1)
                    min = next;
            } else {
                for (int color = 1; color <= n; color++) {
                    int currentNeighborhoodsCreated = neighborhoodsCreated + (pos == 0 || prevColor != color ? 1 : 0);
                    int next = minCost(currentNeighborhoodsCreated, color, pos + 1);
                    if (next != -1)
                        min = Math.min(min, cost[pos][color - 1] + next);
                }
            }

            dp[pos][neighborhoodsCreated][prevColor] = min == Integer.MAX_VALUE ? -1 : min;

            return dp[pos][neighborhoodsCreated][prevColor];
        }
    }

    public static int paintHouseIIIBottomUp(int[] houses, int[][] cost, int m, int n, int target) {
        if (target < 0)
            return -1;
        if (houses == null || houses.length == 0 || cost == null || cost.length == 0)
            return target == 0 ? 0 : -1;
        if (m != houses.length || m != cost.length || n != cost[0].length)
            return -1;

        int [][][] dp = new int[m + 1][target + 1][n + 1];

        for (int prevColor = 0; prevColor <= n; prevColor++) {
            dp[m][target][prevColor] = 0;
            for (int neighborhoodsCreated = 0; neighborhoodsCreated < target; neighborhoodsCreated++)
                dp[m][neighborhoodsCreated][prevColor] = -1;
        }

        for (int pos = m - 1; pos >= 0; pos--) {
            for (int prevColor = 0; prevColor <= n; prevColor++) {
                for (int neighborhoodsCreated = 0; neighborhoodsCreated <= target; neighborhoodsCreated++) {
                    if (neighborhoodsCreated + m - pos < target) {
                        dp[pos][neighborhoodsCreated][prevColor] = -1;
                        continue;
                    }

                    if (houses[pos] != 0) {
                        int color = houses[pos];
                        int currentNeighborhoodsCreated = neighborhoodsCreated + (pos == 0 || prevColor != color ? 1 : 0);

                        if (currentNeighborhoodsCreated > target) {
                            dp[pos][neighborhoodsCreated][prevColor] = -1;
                            continue;
                        }

                        dp[pos][neighborhoodsCreated][prevColor] = dp[pos + 1][currentNeighborhoodsCreated][color];;;
                    } else {
                        int min = Integer.MAX_VALUE;

                        for (int color = 1; color <= n; color++) {
                            int currentNeighborhoodsCreated = neighborhoodsCreated + (pos == 0 || prevColor != color ? 1 : 0);

                            if (currentNeighborhoodsCreated > target)
                                continue;

                            int next = dp[pos + 1][currentNeighborhoodsCreated][color];
                            if (next != -1)
                                min = Math.min(min, cost[pos][color - 1] + next);
                        }

                        dp[pos][neighborhoodsCreated][prevColor] = min == Integer.MAX_VALUE ? -1 : min;
                    }
                }
            }
        }

        return dp[0][0][0];
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