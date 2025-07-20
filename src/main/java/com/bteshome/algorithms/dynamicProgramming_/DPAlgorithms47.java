package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms47 {
    private static final int MOD = ((int) Math.pow(10, 9)) + 7;

    /* https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid */
    public static int numOfWays(int n) {
        if (n < 1)
            return 0;

        int[][][][] dp = new int[n][3][3][3];

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                if (j != k) {
                    for (int l = 0; l < 3; l++) {
                        if (k != l) {
                            dp[0][j][k][l] = 1;
                        }
                    }
                }
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (j != k) {
                        for (int l = 0; l < 3; l++) {
                            if (k != l) {
                                long ways = 0;

                                for (int m = 0; m < 3; m++) {
                                    if (j != m) {
                                        for (int o = 0; o < 3; o++) {
                                            if (k != o) {
                                                for (int p = 0; p < 3; p++) {
                                                    if (l != p) {
                                                        ways = (ways + dp[i - 1][m][o][p]) % MOD;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                                dp[i][j][k][l] = (int)ways;
                            }
                        }
                    }
                }
            }
        }

        long overallWays = 0;

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                for (int l = 0; l < 3; l++) {
                    overallWays = (overallWays + dp[n - 1][j][k][l]) % MOD;
                }
            }
        }

        return (int)overallWays;
    }

    /* https://leetcode.com/problems/pizza-with-3n-slices */
    public static int maxSizeSlicesTopDown(int[] slices) {
        if (slices == null || slices.length == 0)
            return 0;
        if (slices.length % 3 != 0)
            return 0;

        Integer[][][][] dp = new Integer[slices.length][2][2][slices.length / 3 + 1];
        return maxSizeSlicesTopDown(slices, 0, false, false, 0, dp);
    }

    private static int maxSizeSlicesTopDown(int[] slices, int pos, boolean previousPicked, boolean firstWasPicked, int numberPicked, Integer[][][][] dp) {
        if (numberPicked > slices.length / 3)
            return -1;
        if (pos + (slices.length / 3 - numberPicked) > slices.length)
            return -1;
        if (pos == slices.length)
            return numberPicked == slices.length / 3 ? 0 : -1;

        int previousPickedIndex = previousPicked ? 1 : 0;
        int firstWasPickedIndex = firstWasPicked ? 1 : 0;

        if (dp[pos][previousPickedIndex][firstWasPickedIndex][numberPicked] != null)
            return dp[pos][previousPickedIndex][firstWasPickedIndex][numberPicked];

        int max = Integer.MIN_VALUE;

        if (pos == 0) {
            int skip = maxSizeSlicesTopDown(slices, pos + 1, false, false, numberPicked, dp);
            int pick = maxSizeSlicesTopDown(slices, pos + 1, true, true, numberPicked + 1, dp);
            if (skip != -1)
                max = skip;
            if (pick != -1)
                max = Math.max(max, slices[pos] + pick);
        } else {
            int skip = maxSizeSlicesTopDown(slices, pos + 1, false, firstWasPicked, numberPicked, dp);
            if (skip != -1)
                max = skip;
            if (!previousPicked) {
                if (!(pos == slices.length - 1 && firstWasPicked)) {
                    int pick = maxSizeSlicesTopDown(slices, pos + 1, true, firstWasPicked, numberPicked + 1, dp);
                    if (pick != -1)
                        max = Math.max(max, slices[pos] + pick);
                }
            }
        }

        dp[pos][previousPickedIndex][firstWasPickedIndex][numberPicked] = max == Integer.MIN_VALUE ? -1 : max;

        return dp[pos][previousPickedIndex][firstWasPickedIndex][numberPicked];
    }

    public static int maxSizeSlicesTopDownOneLessDimension(int[] slices) {
        if (slices == null || slices.length == 0)
            return 0;
        if (slices.length % 3 != 0)
            return 0;

        int n = slices.length;
        Integer[][][] dp1 = new Integer[slices.length][2][n / 3 + 1];
        Integer[][][] dp2 = new Integer[slices.length][2][n / 3 + 1];

        int excludeFirst = maxSizeSlicesTopDownOneLessDimension(slices, 1, n - 1, 1, false, 0, dp1);
        int excludeLast = maxSizeSlicesTopDownOneLessDimension(slices, 0, n - 2, 0, false, 0, dp2);
        int max = Math.max(excludeFirst, excludeLast);

        return max == - 1 ? 0 : max;
    }

    private static int maxSizeSlicesTopDownOneLessDimension(int[] slices, int globalLeft, int globalRight, int pos, boolean previousPicked, int numberPicked, Integer[][][] dp) {
        if (numberPicked > slices.length / 3)
            return -1;
        if (pos + (slices.length / 3 - numberPicked) > (globalRight + 1))
            return -1;
        if (pos > globalRight)
            return numberPicked == slices.length / 3 ? 0 : -1;

        int previousPickedIndex = previousPicked ? 1 : 0;

        if (dp[pos][previousPickedIndex][numberPicked] != null)
            return dp[pos][previousPickedIndex][numberPicked];

        int max = Integer.MIN_VALUE;

        int skip = maxSizeSlicesTopDownOneLessDimension(slices, globalLeft, globalRight, pos + 1, false, numberPicked, dp);
        if (skip != -1)
            max = skip;
        if (!previousPicked) {
            int pick = maxSizeSlicesTopDownOneLessDimension(slices, globalLeft, globalRight, pos + 1, true, numberPicked + 1, dp);
            if (pick != -1)
                max = Math.max(max, slices[pos] + pick);
        }

        dp[pos][previousPickedIndex][numberPicked] = max == Integer.MIN_VALUE ? -1 : max;

        return dp[pos][previousPickedIndex][numberPicked];
    }

    public static int maxSizeSlicesBottomUp(int[] slices) {
        if (slices == null || slices.length == 0)
            return 0;
        if (slices.length % 3 != 0)
            return 0;

        int n = slices.length;
        int maxPicks = n / 3;
        int[][][][] dp = new int[n + 1][2][2][maxPicks + 1];

        for (int pos = n - 1; pos >= 0; pos--) {
            for (int previousPicked = 0; previousPicked < 2; previousPicked++) {
                for (int firstWasPicked = 0; firstWasPicked < 2; firstWasPicked++) {
                    for (int numberPicked = 0; numberPicked <= maxPicks; numberPicked++) {
                        int max = Integer.MIN_VALUE;

                        if (pos == 0) {
                            int skip = dp[pos + 1][0][0][numberPicked];
                            if (numberPicked < maxPicks) {
                                int pick = dp[pos + 1][1][1][numberPicked + 1];
                                if (skip != -1)
                                    max = skip;
                                if (pick != -1)
                                    max = Math.max(max, slices[pos] + pick);
                            }
                        } else {
                            int skip = dp[pos + 1][0][firstWasPicked][numberPicked];
                            if (skip != -1)
                                max = skip;
                            if (numberPicked < maxPicks) {
                                if (previousPicked == 0) {
                                    if (!(pos == slices.length - 1 && firstWasPicked == 1)) {
                                        int pick = dp[pos + 1][1][firstWasPicked][numberPicked + 1];
                                        if (pick != -1)
                                            max = Math.max(max, slices[pos] + pick);
                                    }
                                }
                            }
                        }

                        dp[pos][previousPicked][firstWasPicked][numberPicked] = max == Integer.MIN_VALUE ? -1 : max;
                    }
                }
            }
        }

        return dp[0][0][0][0];
    }
}