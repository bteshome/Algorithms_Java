package com.bteshome.algorithms.dynamicProgramming_;

import java.util.HashMap;
import java.util.Map;

public class DPAlgorithms4 {
    /**
     * https://leetcode.com/problems/house-robber/?envType=study-plan-v2&envId=top-interview-150
     * */
    public static int robRecursive(int[] nums) {
        if (nums == null) {
            return 0;
        }

        return robRecursive(nums, 0, new Integer[nums.length]);
    }

    private static int robRecursive(int[] nums, int pos, Integer[] cache) {
        if (pos >= nums.length) {
            return 0;
        }

        if (cache[pos] == null) {
            cache[pos] = Math.max(nums[pos] + robRecursive(nums, pos + 2, cache), robRecursive(nums, pos + 1, cache));
        }

        return cache[pos];
    }

    /**
     * https://leetcode.com/problems/house-robber/
     * Just one approach. The second dimension of the dp array is actually unnecessary,
     * it can be replaced with a fibonacci relationship.
     * */
    public static int robIterative(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int[][] dp = new int[nums.length][];
        for (int i = 0; i < nums.length; i++)
            dp[i] = new int[2];

        dp[0][1] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = nums[i] + dp[i-1][0];
        }

        return Math.max(dp[nums.length-1][0], dp[nums.length-1][1]);
    }

    /**
     * https://leetcode.com/problems/house-robber-ii/description/
     * */
    public static int robIIRecursive(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int startFrom1 = robIIRecursive(nums, nums.length - 1, new Integer[nums.length], true);
        int startFrom2 = robIIRecursive(nums, nums.length - 1, new Integer[nums.length], false);
        return Math.max(startFrom1, startFrom2);
    }

    private static int robIIRecursive(int[] nums, int pos, Integer[] cache, boolean firstWasRobbed) {
        if (pos < 0)
            return 0;
        if (pos == 0)
            return firstWasRobbed ? nums[0] : 0;

        if (cache[pos] == null) {
            int money = robIIRecursive(nums, pos - 1, cache, firstWasRobbed);
            if (pos != nums.length - 1 || !firstWasRobbed)
                money = Math.max(money, robIIRecursive(nums, pos - 2, cache, firstWasRobbed) + nums[pos]);
            cache[pos] = money;
        }

        return cache[pos];
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * https://leetcode.com/problems/house-robber-iii/
     * */
    public static int robIIIRecursive(TreeNode root) {
        if (root == null)
            return 0;

        return robIIIRecursive(root, false, new HashMap<>());
    }

    private static int robIIIRecursive(TreeNode root, boolean parentRobbed, Map<TreeNode, Integer[]> cache) {
        if (root == null)
            return 0;

        if (!cache.containsKey(root))
            cache.put(root, new Integer[2]);

        int index = parentRobbed ? 1 : 0;

        if (cache.get(root)[index] == null) {
            int maxMoney = robIIIRecursive(root.left, false, cache) + robIIIRecursive(root.right, false, cache);

            if (!parentRobbed) {
                int maxMoney2 = root.val + robIIIRecursive(root.left, true, cache) + robIIIRecursive(root.right, true, cache);
                maxMoney = Math.max(maxMoney, maxMoney2);
            }

            cache.get(root)[index] = maxMoney;
        }

        return cache.get(root)[index];
    }
}
