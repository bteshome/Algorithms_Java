package com.bteshome.algorithms.heap_;

import com.bteshome.algorithms.greedy.GreedyAlgorithms4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HeapAlgorithms3 {
    /**
     * https://leetcode.com/problems/reorganize-string/
     * */
    public static String reorganizeString(String s) {
        if (s == null || s.length() < 2)
            return s;

        char[] frequencies = new char[26];
        PriorityQueue<CharGroup> q = new PriorityQueue<>(Comparator.comparingInt((CharGroup t) -> t.frequency).reversed());
        StringBuilder arrangement = new StringBuilder();

        for (int i = 0; i < s.length(); i++)
            frequencies[s.charAt(i) - 'a']++;

        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0)
                q.offer(new CharGroup((char)('a' + i), frequencies[i]));
        }

        while (!q.isEmpty()) {
            if (!tryAddNext(q, arrangement))
                return "";
        }

        return arrangement.toString();
    }

    private static boolean tryAddNext(PriorityQueue<CharGroup> q, StringBuilder arrangement) {
        CharGroup next = q.poll();

        if (arrangement.isEmpty() || arrangement.charAt(arrangement.length() - 1) != next.type) {
            arrangement.append(next.type);
            next.frequency--;
        } else {
            if (q.isEmpty())
                return false;
            if (!tryAddNext(q, arrangement))
                return false;
        }

        if (next.frequency > 0)
            q.offer(next);

        return true;
    }

    private static class CharGroup {
        public final char type;
        public int frequency;
        public CharGroup(char type, int frequency) {
            this.type = type;
            this.frequency = frequency;
        }
    }
}

