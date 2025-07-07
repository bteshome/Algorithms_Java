package com.bteshome.algorithms.dynamicProgramming_;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DPAlgorithms18 {
    /**
     * https://leetcode.com/problems/paint-house-iii/
     * */
    public static int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        if (target < 0)
            return -1;
        if (houses == null || houses.length == 0 || cost == null || cost.length == 0)
            return target == 0 ? 0 : -1;
        if (m != houses.length || n != cost[0].length)
            return -1;

        return minCost(houses, cost, m, n, target, 0, -1, 0, new HashMap<>());
    }

    private static int minCost(int[] houses, int[][] cost, int m, int n, int target, int house, int prevColor, int neighborhoodsCreated, Map<String, Integer> cache) {
        if (house == m)
            return neighborhoodsCreated == target ? 0 : -1;
        if (neighborhoodsCreated > target)
            return -1;

        String key = "%s,%s,%s".formatted(house, prevColor, neighborhoodsCreated);

        if (!cache.containsKey(key)) {
            int min = Integer.MAX_VALUE;

            if (houses[house] != 0) {
                int color = houses[house] - 1;
                int newNeighborhoods = neighborhoodsCreated + (prevColor != color ? 1 : 0);
                int next = minCost(houses, cost, m, n, target, house + 1, color, newNeighborhoods, cache);
                if (next != -1)
                    min = next;
            }
            else {
                for (int color = 0; color < n; color++) {
                    int newNeighborhoods = neighborhoodsCreated + (prevColor != color ? 1 : 0);
                    int next = minCost(houses, cost, m, n, target, house + 1, color, newNeighborhoods, cache);
                    if (next != -1)
                        min = Math.min(min, next + cost[house][color]);
                }
            }

            if (min == Integer.MAX_VALUE)
                min = -1;

            cache.put(key, min);
        }

        return cache.get(key);
    }

    /**
     * https://leetcode.com/problems/frog-jump/
     * */
    public static boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0)
            return false;
        if (stones.length == 1)
            return true;
        if (stones[1] - stones[0] != 1)
            return false;

        return canCross(stones, 1, 1, new HashMap<>());
    }

    private static int[] jumps = new int[]{-1, 0, 1};

    private static boolean canCross(int[] stones, int stone, int prevUnits, Map<String, Boolean> cache) {
        if (stone == stones.length - 1)
            return true;

        String key = "%s,%s".formatted(stone, prevUnits);

        if (!cache.containsKey(key)) {
            boolean can = false;

            for (int jump : jumps) {
                int unit = jump + prevUnits;
                if (unit > 0) {
                    // TODO - this can be improved using binary search since the input is sorted
                    for (int i = stone + 1; i < stones.length; i++) {
                        int diff = stones[i] - stones[stone];
                        if (diff > unit)
                            break;
                        if (diff == unit) {
                            can = canCross(stones, i, unit, cache);
                            break;
                        }
                    }
                }
                if (can)
                    break;
            }

            cache.put(key, can);
        }

        return cache.get(key);
    }
}
