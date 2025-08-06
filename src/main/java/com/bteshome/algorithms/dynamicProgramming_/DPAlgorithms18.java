package com.bteshome.algorithms.dynamicProgramming_;

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

    /**
     * https://leetcode.com/problems/can-i-win
     * NOTE: also take a look at the slower solution below (not accepted)
     * */
    public static boolean canIWinTopDown(int maxChoosableInteger, int desiredTotal) {
        int sum = (maxChoosableInteger * (maxChoosableInteger + 1)) / 2;
        if (sum < desiredTotal) return false;
        if (desiredTotal <= 0) return true;

        int availableMask = (1 << maxChoosableInteger) - 1;
        Map<Integer, Boolean> dp = new HashMap<>();

        return canIWinTopDown(maxChoosableInteger, desiredTotal, availableMask, dp);
    }

    private static boolean canIWinTopDown(int maxChoosableInteger, int desiredTotal, int availableMask, Map<Integer, Boolean> dp) {
        if (dp.containsKey(availableMask))
            return dp.get(availableMask);

        boolean can = false;

        for (int i = 1; i <= maxChoosableInteger; i++) {
            int mask = 1 << (i - 1);
            if ((availableMask & mask) != 0) {
                if (i >= desiredTotal) {
                    can = true;
                    break;
                }

                if(!canIWinTopDown(maxChoosableInteger, desiredTotal - i, availableMask & ~mask, dp)) {
                    can = true;
                    break;
                }
            }
        }

        dp.put(availableMask, can);

        return dp.get(availableMask);
    }

    /**
     * NOTE: this version is present just to show that including desiredTotal in the state
     *       is both unnecessary and inefficient. Why?
     *          - the entire state can be defined by which numbers are still available
     *          - two states with the same selected numbers but different desiredTotal
     *            may be equivalent.
     * */
    /* https://leetcode.com/problems/can-i-win/ */
    public static boolean canIWinTopDownSlow(int maxChoosableInteger, int desiredTotal) {
        int sum = (maxChoosableInteger * (maxChoosableInteger + 1)) / 2;
        if (sum < desiredTotal) return false;
        if (desiredTotal <= 0) return true;

        int availableMask = (1 << maxChoosableInteger) - 1;
        Map<String, Boolean> dp = new HashMap<>();

        return canIWinTopDownSlow(maxChoosableInteger, desiredTotal, availableMask, dp);
    }

    private static boolean canIWinTopDownSlow(int maxChoosableInteger, int desiredTotal, int availableMask, Map<String, Boolean> dp) {
        String key = "%s,%s".formatted(desiredTotal, availableMask);

        if (dp.containsKey(key))
            return dp.get(key);

        boolean can = false;

        for (int i = 1; i <= maxChoosableInteger; i++) {
            int mask = 1 << (i - 1);
            if ((availableMask & mask) != 0) {
                if (i >= desiredTotal) {
                    can = true;
                    break;
                }

                if(!canIWinTopDownSlow(maxChoosableInteger, desiredTotal - i, availableMask & ~mask, dp)) {
                    can = true;
                    break;
                }
            }
        }

        dp.put(key, can);

        return dp.get(key);
    }
}