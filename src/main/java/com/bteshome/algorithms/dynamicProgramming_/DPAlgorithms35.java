package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms35 {
    private static final int MOD = 1000000007;

    /* https://leetcode.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps */
    public static int numWaysTopDown(int steps, int arrLen) {
        if (steps < 0 || arrLen < 0)
            return 0;

        // NOTE - one minor but important optimization: limit the dp table first dim length
        return numWaysTopDown(steps, arrLen, 0, new Integer[Math.min(steps, arrLen)][steps + 1]);
    }

    private static int numWaysTopDown(int steps, int arrLen, int pos, Integer[][] dp) {
        if (steps == 0)
            return pos == 0 ? 1 : 0;
        if (pos > steps)
            return 0;

        if (dp[pos][steps] == null) {
            long ways = numWaysTopDown(steps - 1, arrLen, pos, dp);
            if (pos < arrLen - 1)
                ways = (ways + numWaysTopDown(steps - 1, arrLen, pos + 1, dp)) % MOD;
            if (pos > 0)
                ways = (ways + numWaysTopDown(steps - 1, arrLen, pos - 1, dp)) % MOD;
            dp[pos][steps] = (int)ways;
        }

        return dp[pos][steps];
    }

    /* https://leetcode.com/problems/number-of-ways-to-earn-points */
    public static int waysToReachTargetTopDown(int target, int[][] types) {
        if (target < 0 || types == null || types.length == 0)
            return 0;

        return waysToReachTargetTopDown(target, types, 0, new Integer[types.length][target + 1]);
    }

    private static int waysToReachTargetTopDown(int target, int[][] types, int pos, Integer[][] dp) {
        if (target == 0)
            return 1;
        if (pos == types.length)
            return 0;

        if (dp[pos][target] == null) {
            long ways = waysToReachTargetTopDown(target, types, pos + 1, dp);
            int numQuestions = types[pos][0];
            int pointsPerQuestion = types[pos][1];

            for (int i = 1; i <= numQuestions; i++) {
                int points = i * pointsPerQuestion;
                if (points > target)
                    break;
                ways = (ways + waysToReachTargetTopDown(target - points, types, pos + 1, dp)) % MOD;
            }

            dp[pos][target] = (int)ways;
        }

        return dp[pos][target];
    }

    /* https://leetcode.com/problems/student-attendance-record-ii */
    //private static final int MOD = 1000000007;

    public static int checkRecordTopDown(int n) {
        if (n < 1)
            return 0;
        return checkRecordTopDown(n, 0, 0, new Integer[n + 1][2][3]);
    }

    private static int checkRecordTopDown(int n, int absentDays, int consecutiveLateDays, Integer[][][] dp) {
        if (n == 0)
            return 1;

        if (dp[n][absentDays][consecutiveLateDays] == null) {
            long ways = checkRecordTopDown(n - 1, absentDays, 0, dp);
            if (absentDays < 1)
                ways = (ways + checkRecordTopDown(n - 1, absentDays + 1, 0, dp)) % MOD;
            if (consecutiveLateDays < 2)
                ways = (ways + checkRecordTopDown(n - 1, absentDays, consecutiveLateDays + 1, dp)) % MOD;
            dp[n][absentDays][consecutiveLateDays] = (int) ways;
        }

        return dp[n][absentDays][consecutiveLateDays];
    }
}
