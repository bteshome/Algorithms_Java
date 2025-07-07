package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms19 {
    /**
     * https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/
     * */
    public static int minDifficulty(int[] jobDifficulty, int d) {
        if (jobDifficulty == null || jobDifficulty.length == 0)
            return d == 0 ? 0 : -1;
        if (d == 0)
            return -1;
        if (d > jobDifficulty.length)
            return -1;

        Integer[][] cache = new Integer[d][];
        for (int i = 0; i < d; i++)
            cache[i] = new Integer[jobDifficulty.length];

        return minDifficulty(jobDifficulty, d, 0, 0, cache);
    }

    private static int minDifficulty(int[] jobDifficulty, int d, int day, int jobStart, Integer[][] cache) {
        if (day == d && jobStart == jobDifficulty.length)
            return 0;
        if (day == d)
            return -1;
        if (jobStart == jobDifficulty.length)
            return -1;

        if (cache[day][jobStart] == null) {
            int overallDifficulty = Integer.MAX_VALUE;
            int dayDifficulty = 0;

            for (int jobEnd = jobStart; jobEnd < jobDifficulty.length; jobEnd++) {
                dayDifficulty = Math.max(dayDifficulty, jobDifficulty[jobEnd]);
                int next = minDifficulty(jobDifficulty, d, day + 1, jobEnd + 1, cache);
                if (next != -1)
                    overallDifficulty = Math.min(overallDifficulty, dayDifficulty + next);
            }

            cache[day][jobStart] = overallDifficulty == Integer.MAX_VALUE ? -1 : overallDifficulty;
        }

        return cache[day][jobStart];
    }

    /**
     * https://leetcode.com/problems/strange-printer/
     * */
    public static int strangePrinter(String s) {
        if (s == null)
            return 0;

        Integer[][] cache = new Integer[s.length()][s.length()];

        return strangePrinter(s, 0, s.length() - 1, cache);
    }

    private static int strangePrinter(String s, int start, int end, Integer[][] cache) {
        if (start > end)
            return 0;
        if (start == end)
            return 1;

        if (cache[start][end] == null) {
            int min = Integer.MAX_VALUE;

            if (s.charAt(start) == s.charAt(end)) {
                min = strangePrinter(s, start, end - 1, cache);
            } else {
                for (int splitPoint = start; splitPoint < end; splitPoint++) {
                    int left = strangePrinter(s, start, splitPoint, cache);
                    int right = strangePrinter(s, splitPoint + 1, end, cache);
                    min = Math.min(min, left + right);
                }
            }

            cache[start][end] = min;
        }

        return cache[start][end];
    }

    public static int strangePrinterOptimizedV1(String s) {
        if (s == null || s.isEmpty())
            return 0;

        s = removeConsecutiveDuplicates(s);
        Integer[][] cache = new Integer[s.length()][s.length()];

        return strangePrinterOptimizedV1(s, 0, s.length() - 1, cache);
    }

    private static int strangePrinterOptimizedV1(String s, int start, int end, Integer[][] cache) {
        if (start > end)
            return 0;
        if (start == end)
            return 1;

        if (cache[start][end] == null) {
            int min = Integer.MAX_VALUE;

            if (s.charAt(start) == s.charAt(end)) {
                min = strangePrinterOptimizedV1(s, start, end - 1, cache);
            } else {
                for (int splitPoint = start; splitPoint < end; splitPoint++) {
                    int left = strangePrinterOptimizedV1(s, start, splitPoint, cache);
                    int right = strangePrinterOptimizedV1(s, splitPoint + 1, end, cache);
                    min = Math.min(min, left + right);
                }
            }

            cache[start][end] = min;
        }

        return cache[start][end];
    }

    private static String removeConsecutiveDuplicates(String s) {
        StringBuilder builder = new StringBuilder();
        char prev = s.charAt(0);
        builder.append(prev);

        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != prev) {
                prev = c;
                builder.append(prev);
            }
        }

        return builder.toString();
    }
}