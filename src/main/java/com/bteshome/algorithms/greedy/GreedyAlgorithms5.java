package com.bteshome.algorithms.greedy;

import java.util.*;

public class GreedyAlgorithms5 {
    /*
     * https://leetcode.com/problems/reduce-array-size-to-the-half/
     * */
    public static int minSetSize(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;

        class Element {
            public final int value;
            public int frequency;

            public Element(int value, int frequency) {
                this.value = value;
                this.frequency = frequency;
            }
        }

        Map<Integer, Element> frequencies = new HashMap<>();
        PriorityQueue<Element> q = new PriorityQueue<>(Comparator.comparingInt((Element a) -> a.frequency).reversed());
        int minSize = 0;
        int removedSize = 0;
        int half = (int)Math.ceil(arr.length / 2.0);

        for (int value : arr) {
            if (frequencies.containsKey(value))
                frequencies.get(value).frequency++;
            else
                frequencies.put(value, new Element(value, 1));
        }

        q.addAll(frequencies.values());

        while (!q.isEmpty() && removedSize < half) {
            removedSize += q.poll().frequency;
            minSize++;
        }

        return minSize;
    }

    /*
    * https://leetcode.com/problems/maximal-score-after-applying-k-operations/
    * */
    public static long maxKElements(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1)
            return 0;

        class Num {
            public int value;
            public Num(int value) {
                this.value = value;
            }
        }

        PriorityQueue<Num> q = new PriorityQueue<>((Num a, Num b) -> b.value - a.value);
        long score = 0;

        for (int i = 0; i < nums.length; i++)
            q.add(new Num(nums[i]));

        while (k > 0 && !q.isEmpty()) {
            Num next = q.poll();
            int ceil = (int)Math.ceil(next.value / 3.0);
            score += next.value;
            next.value = ceil;
            if (ceil > 0)
                q.offer(next);
            k--;
        }

        return score;
    }


}
