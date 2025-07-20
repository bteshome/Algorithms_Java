package com.bteshome.algorithms.dynamicProgramming_;

public class DPAlgorithms20 {
    /**
     * leetcode https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
     * */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int buy = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];
            if (price < buy) {
                buy = price;
            } else {
                maxProfit = Math.max(maxProfit, price - buy);
            }
        }

        return maxProfit;
    }

    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
     * */
    public static int maxProfitII(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;

        int overallProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - prices[i-1];
            if (profit > 0)
                overallProfit += profit;
        }

        return overallProfit;
    }

    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
     * */
    public static int maxProfitWithCooldownTopDown(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;

        Integer[][][] dp = new Integer[prices.length][2][2];
        return maxProfitWithCooldownTopDown(prices, 0, 0, 0, dp);
    }

    private static int maxProfitWithCooldownTopDown(int[] prices, int day, int purchased, int soldPrevDay, Integer[][][] dp) {
        if (day == prices.length)
            return 0;

        if (dp[day][purchased][soldPrevDay] != null)
            return dp[day][purchased][soldPrevDay];

        int max = 0;

        if (purchased == 1) {
            int skip = maxProfitWithCooldownTopDown(prices, day + 1, 1, 0, dp);
            int sell = prices[day] + maxProfitWithCooldownTopDown(prices, day + 1, 0, 1, dp);
            max = Math.max(skip, sell);
        } else {
            max = maxProfitWithCooldownTopDown(prices, day + 1, 0, 0, dp);
            if (soldPrevDay == 0) {
                int buy = -prices[day] + maxProfitWithCooldownTopDown(prices, day + 1, 1, 0, dp);
                max = Math.max(max, buy);
            }
        }

        dp[day][purchased][soldPrevDay] = max;

        return dp[day][purchased][soldPrevDay];
    }

    public static int maxProfitWithCooldownBottomUp(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;

        int n = prices.length;
        int[][][] dp = new int[n + 1][2][2];

        for (int i = n - 1; i >= 0; i--) {
            for (int purchased = 0; purchased < 2; purchased++) {
                for (int soldPrevDay = 0; soldPrevDay < 2; soldPrevDay++) {
                    int max = 0;

                    if (purchased == 1) {
                        int skip = dp[i + 1][1][0];
                        int sell = prices[i] + dp[i + 1][0][1];
                        max = Math.max(skip, sell);
                    } else {
                        max = dp[i + 1][0][0];
                        if (soldPrevDay == 0) {
                            int buy = -prices[i] + dp[i + 1][1][0];
                            max = Math.max(max, buy);
                        }
                    }

                    dp[i][purchased][soldPrevDay] = max;
                }
            }
        }

        return dp[0][0][0];
    }

    public static int maxProfitWithCooldownBottomUpSpaceOptimized(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;

        int n = prices.length;
        int[][] dpNextDay = new int[2][2];
        int[][] dpCurrentDay = new int[2][2];
        int[][] temp = null;

        for (int i = n - 1; i >= 0; i--) {
            for (int purchased = 0; purchased < 2; purchased++) {
                for (int soldPrevDay = 0; soldPrevDay < 2; soldPrevDay++) {
                    int max = 0;

                    if (purchased == 1) {
                        int skip = dpNextDay[1][0];
                        int sell = prices[i] + dpNextDay[0][1];
                        max = Math.max(skip, sell);
                    } else {
                        max = dpNextDay[0][0];
                        if (soldPrevDay == 0) {
                            int buy = -prices[i] + dpNextDay[1][0];
                            max = Math.max(max, buy);
                        }
                    }

                    dpCurrentDay[purchased][soldPrevDay] = max;
                }
            }

            temp = dpNextDay;
            dpNextDay = dpCurrentDay;
            dpCurrentDay = temp;
        }

        return dpNextDay[0][0];
    }

    /* https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee */
    public static int maxProfitWithTransactionFeeTopDown(int[] prices, int fee) {
        if (prices == null)
            return 0;

        Integer[][] dp = new Integer[prices.length][2];

        return maxProfitWithTransactionFeeTopDown(prices, fee, 0, false, dp);
    }

    private static int maxProfitWithTransactionFeeTopDown(int[] prices, int fee, int pos, boolean purchased, Integer[][] dp) {
        if (pos == prices.length)
            return 0;

        int purchasedIndex = purchased ? 1 : 0;

        if (dp[pos][purchasedIndex] != null)
            return dp[pos][purchasedIndex];

        int max = 0;

        if (purchased) {
            int skip = maxProfitWithTransactionFeeTopDown(prices, fee, pos + 1, purchased, dp);
            int sell = prices[pos] - fee + maxProfitWithTransactionFeeTopDown(prices, fee, pos + 1, false, dp);
            max = Math.max(skip, sell);
        } else {
            int skip = maxProfitWithTransactionFeeTopDown(prices, fee, pos + 1, purchased, dp);
            int buy = -prices[pos] + maxProfitWithTransactionFeeTopDown(prices, fee, pos + 1, true, dp);
            max = Math.max(skip, buy);
        }

        dp[pos][purchasedIndex] = max;

        return dp[pos][purchasedIndex];
    }

    public static int maxProfitWithTransactionBottomUp(int[] prices, int fee) {
        if (prices == null)
            return 0;

        int n = prices.length;
        int[][] dp = new int[n + 1][2];

        for (int i = n - 1; i >= 0; i--) {
            for (int purchased = 0; purchased < 2; purchased++) {
                int max = 0;

                if (purchased == 1) {
                    int skip = dp[i + 1][1];
                    int sell = prices[i] - fee + dp[i + 1][0];
                    max = Math.max(skip, sell);
                } else {
                    int skip = dp[i + 1][0];
                    int buy = -prices[i] + dp[i + 1][1];
                    max = Math.max(skip, buy);
                }

                dp[i][purchased] = max;
            }
        }

        return dp[0][0];
    }

    public static int maxProfitWithTransactionBottomUpSpaceOptimized(int[] prices, int fee) {
        if (prices == null)
            return 0;

        int n = prices.length;
        int[] dpNextDay = new int[2];
        int[] dpCurrentDay = new int[2];
        int[] temp = null;

        for (int i = n - 1; i >= 0; i--) {
            for (int purchased = 0; purchased < 2; purchased++) {
                int max = 0;

                if (purchased == 1) {
                    int skip = dpNextDay[1];
                    int sell = prices[i] - fee + dpNextDay[0];
                    max = Math.max(skip, sell);
                } else {
                    int skip = dpNextDay[0];
                    int buy = -prices[i] + dpNextDay[1];
                    max = Math.max(skip, buy);
                }

                dpCurrentDay[purchased] = max;
            }

            temp = dpNextDay;
            dpNextDay = dpCurrentDay;
            dpCurrentDay = temp;
        }

        return dpNextDay[0];
    }

    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
     * */
    public static int maxProfitIIITopDown(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;

        Integer[][][] dp = new Integer[prices.length][3][2];
        return maxProfitIIITopDown(prices, 0, 0, 0, dp);
    }

    public static int maxProfitIIITopDown(int[] prices, int pos, int numberOfTransactions, int purchased, Integer[][][] dp) {
        if (pos == prices.length)
            return 0;

        if (dp[pos][numberOfTransactions][purchased] != null)
            return dp[pos][numberOfTransactions][purchased];

        int max = 0;

        if (purchased == 1) {
            int skip = maxProfitIIITopDown(prices, pos + 1, numberOfTransactions, 1, dp);
            int sell = prices[pos] + maxProfitIIITopDown(prices, pos + 1, numberOfTransactions, 0, dp);
            max = Math.max(sell, skip);
        } else {
            max = maxProfitIIITopDown(prices, pos + 1, numberOfTransactions, 0, dp);
            if (numberOfTransactions < 2) {
                int buy = -prices[pos] + maxProfitIIITopDown(prices, pos + 1, numberOfTransactions + 1, 1, dp);
                max = Math.max(max, buy);
            }
        }

        dp[pos][numberOfTransactions][purchased] = max;

        return dp[pos][numberOfTransactions][purchased];
    }

    public static int maxProfitIIIBottomUp(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;

        int n = prices.length;
        int[][][] dp = new int[n + 1][3][2];

        for (int i = n - 1; i >= 0; i--) {
            for (int numberOfTransactions = 0; numberOfTransactions < 3; numberOfTransactions++) {
                for (int purchased = 0; purchased < 2; purchased++) {
                    int max = 0;

                    if (purchased == 1) {
                        int skip = dp[i + 1][numberOfTransactions][1];
                        int sell = prices[i] + dp[i + 1][numberOfTransactions][0];
                        max = Math.max(sell, skip);
                    } else {
                        max = dp[i + 1][numberOfTransactions][0];
                        if (numberOfTransactions < 2) {
                            int buy = -prices[i] + dp[i + 1][numberOfTransactions + 1][1];
                            max = Math.max(max, buy);
                        }
                    }

                    dp[i][numberOfTransactions][purchased] = max;
                }
            }
        }

        return dp[0][0][0];
    }

    public static int maxProfitIIIBottomUpSpaceOptimized(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;

        int n = prices.length;
        int[][] dpNextDay = new int[3][2];
        int[][] dpCurrentDay = new int[3][2];
        int[][] temp = null;

        for (int i = n - 1; i >= 0; i--) {
            for (int numberOfTransactions = 0; numberOfTransactions < 3; numberOfTransactions++) {
                for (int purchased = 0; purchased < 2; purchased++) {
                    int max = 0;

                    if (purchased == 1) {
                        int skip = dpNextDay[numberOfTransactions][1];
                        int sell = prices[i] + dpNextDay[numberOfTransactions][0];
                        max = Math.max(sell, skip);
                    } else {
                        max = dpNextDay[numberOfTransactions][0];
                        if (numberOfTransactions < 2) {
                            int buy = -prices[i] + dpNextDay[numberOfTransactions + 1][1];
                            max = Math.max(max, buy);
                        }
                    }

                    dpCurrentDay[numberOfTransactions][purchased] = max;
                }
            }

            temp = dpNextDay;
            dpNextDay = dpCurrentDay;
            dpCurrentDay = temp;
        }

        return dpNextDay[0][0];
    }

    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv
     * */
    public static int maxProfitIVTopDown(int k, int[] prices) {
        if (prices == null)
            return 0;

        Integer[][][] dp = new Integer[prices.length][2][k + 1];
        return maxProfitIVTopDown(prices, 0, 0, k, dp);
    }

    private static int maxProfitIVTopDown(int[] prices, int day, int purchased, int transactionsRemaining, Integer[][][] dp) {
        if (day == prices.length)
            return 0;

        if (dp[day][purchased][transactionsRemaining] != null)
            return dp[day][purchased][transactionsRemaining];

        int max = 0;

        if (purchased == 1) {
            int skip = maxProfitIVTopDown(prices, day + 1, 1, transactionsRemaining, dp);
            int sell = prices[day] + maxProfitIVTopDown(prices, day + 1, 0, transactionsRemaining, dp);
            max = Math.max(skip, sell);
        } else {
            max = maxProfitIVTopDown(prices, day + 1, 0, transactionsRemaining, dp);
            if (transactionsRemaining > 0) {
                int buy = -prices[day] + maxProfitIVTopDown(prices, day + 1, 1, transactionsRemaining - 1, dp);
                max = Math.max(max, buy);
            }
        }

        dp[day][purchased][transactionsRemaining] = max;

        return dp[day][purchased][transactionsRemaining];
    }

    public static int maxProfitIVBottomUp(int k, int[] prices) {
        if (prices == null || prices.length < 2 || k < 1)
            return 0;

        int n = prices.length;
        int[][][] dp = new int[n + 1][2][k + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int purchased = 0; purchased < 2; purchased++) {
                for (int transactionsRemaining = 0; transactionsRemaining <= k ; transactionsRemaining++) {
                    int max = 0;

                    if (purchased == 1) {
                        int skip = dp[i + 1][1][transactionsRemaining];
                        int sell = prices[i] + dp[i + 1][0][transactionsRemaining];
                        max = Math.max(skip, sell);
                    } else {
                        max = dp[i + 1][0][transactionsRemaining];
                        if (transactionsRemaining > 0) {
                            int buy = -prices[i] + dp[i + 1][1][transactionsRemaining - 1];
                            max = Math.max(max, buy);
                        }
                    }

                    dp[i][purchased][transactionsRemaining] = max;
                }
            }
        }

        return dp[0][0][k];
    }

    public static int maxProfitIVBottomUpSpaceOptimized(int k, int[] prices) {
        if (prices == null || prices.length < 2 || k < 1)
            return 0;

        int n = prices.length;
        int[][] dpNextDay = new int[2][k + 1];
        int[][] dpCurrentDay = new int[2][k + 1];
        int[][] temp = null;

        for (int i = n - 1; i >= 0; i--) {
            for (int purchased = 0; purchased < 2; purchased++) {
                for (int transactionsRemaining = 0; transactionsRemaining <= k ; transactionsRemaining++) {
                    int max = 0;

                    if (purchased == 1) {
                        int skip = dpNextDay[1][transactionsRemaining];
                        int sell = prices[i] + dpNextDay[0][transactionsRemaining];
                        max = Math.max(skip, sell);
                    } else {
                        max = dpNextDay[0][transactionsRemaining];
                        if (transactionsRemaining > 0) {
                            int buy = -prices[i] + dpNextDay[1][transactionsRemaining - 1];
                            max = Math.max(max, buy);
                        }
                    }

                    dpCurrentDay[purchased][transactionsRemaining] = max;
                }
            }

            temp = dpNextDay;
            dpNextDay = dpCurrentDay;
            dpCurrentDay = temp;
        }

        return dpNextDay[0][k];
    }
}
