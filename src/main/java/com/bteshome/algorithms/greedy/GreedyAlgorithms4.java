package com.bteshome.algorithms.greedy;

import java.util.*;

public class GreedyAlgorithms4 {
    /**
     * https://leetcode.com/problems/array-partition/
     * */
    public static int arrayPairSum(int[] nums) {
        if (nums == null || nums.length < 2)
            throw new IllegalArgumentException();

        Arrays.sort(nums);

        int maxSum = 0;

        for (int i = 0; i < nums.length; i=i+2)
            maxSum += nums[i];

        return maxSum;
    }

    /**
     * https://leetcode.com/problems/minimum-amount-of-time-to-fill-cups/description/
     * */
    public static int fillCups(int[] amount) {
        class CupType {
            public int color;
            public int amountNeeded;
            public CupType(int color, int amountNeeded) {
                this.color = color;
                this.amountNeeded = amountNeeded;
            }
        }

        if (amount == null)
            return 0;

        PriorityQueue<CupType> q = new PriorityQueue<>((a, b) -> b.amountNeeded - a.amountNeeded);

        if (amount.length > 0 && amount[0] > 0)
            q.add(new CupType(0, amount[0]));
        if (amount.length > 1 && amount[1] > 0)
            q.add(new CupType(1, amount[1]));
        if (amount.length > 2 && amount[2] > 0)
            q.add(new CupType(2, amount[2]));

        int seconds = 0;

        while (!q.isEmpty()) {
            CupType nextCup = q.poll();
            nextCup.amountNeeded--;
            seconds++;

            if (!q.isEmpty()) {
                CupType nextDifferentCup = q.poll();
                nextDifferentCup.amountNeeded--;
                if (nextDifferentCup.amountNeeded > 0)
                    q.offer(nextDifferentCup);
            }

            if (nextCup.amountNeeded > 0)
                q.offer(nextCup);
        }

        return seconds;
    }

    /**
     * https://leetcode.com/problems/minimum-cost-to-connect-sticks/
     * */
    public static int connectSticks(int[] sticks) {
        if (sticks == null || sticks.length < 2)
            return 0;

        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.addAll(Arrays.stream(sticks).boxed().toList());

        int cost = 0;

        while (q.size() > 1) {
            int first =  q.poll();
            int second = q.poll();
            int currentCost = first + second;
            cost += currentCost;
            q.offer(currentCost);
        }

        return cost;
    }
}
