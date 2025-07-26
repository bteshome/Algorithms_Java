package com.bteshome.algorithms.dynamicProgramming_;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DPAlgorithms18 {
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
