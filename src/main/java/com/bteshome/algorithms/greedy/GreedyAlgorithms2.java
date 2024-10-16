package com.bteshome.algorithms.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class GreedyAlgorithms2 {
    /**
     * https://leetcode.com/problems/buy-two-chocolates/
     * */
    public static int buyTwoChocolates(int[] prices, int money) {
        if (prices == null) {
            return money;
        }

        Arrays.sort(prices);

        for (int i = 0; i < prices.length - 1; i++) {
            var sum = prices[i] + prices[i+1];
            if (sum <= money) {
                return money - sum;
            }
        }

        return money;
    }

    /**
     * https://leetcode.com/problems/minimum-number-of-moves-to-seat-everyone/
     * */
    public static int minMovesToSeat2(int[] seats, int[] students) {
        if (seats == null || students == null) {
            return 0;
        }

        Arrays.sort(seats);
        Arrays.sort(students);
        int moves = 0;

        for (int i = 0; i < seats.length; i++) {
            moves += Math.abs(seats[i] - students[i]);
        }

        return moves;
    }

    /**
     * https://leetcode.com/problems/minimum-operations-to-make-the-array-increasing/
     * */
    public static int minOperations(int[] nums) {
        if (nums == null) {
            return 0;
        }

        int operations = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i-1]) {
                operations += (1 + nums[i-1] - nums[i]);
                nums[i] = nums[i-1] + 1;
            }
        }

        return operations;
    }

    /**
     * https://leetcode.com/problems/maximum-units-on-a-truck/
     * */
    public static int maximumUnitsOnATruck(int[][] boxTypes, int truckSize) {
        if (boxTypes == null) {
            return 0;
        }

        int maxUnits = 0;

        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);

        for (int[] boxType : boxTypes) {
            if (truckSize == 0) {
                break;
            }
            var boxes = Math.min(truckSize, boxType[0]);
            truckSize -= boxes;
            maxUnits += boxes * boxType[1];
        }

        return maxUnits;
    }

    /**
     * https://leetcode.com/problems/minimum-adjacent-swaps-to-make-a-valid-array/
     * */
    public static int minimumSwaps(int[] nums) {
        if (nums == null) {
            return 0;
        }

        int swaps = 0;
        int minIndex = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
        }

        swaps += minIndex;

        for (int i = minIndex; i > 0; i--) {
            minimumSwapsSwap(nums, i, i-1);
        }

        int maxIndex = nums.length - 1;
        for (int i = nums.length - 2; i > 0 ; i--) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        swaps += (nums.length - 1 - maxIndex);

        return swaps;
    }

    private static void minimumSwapsSwap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
