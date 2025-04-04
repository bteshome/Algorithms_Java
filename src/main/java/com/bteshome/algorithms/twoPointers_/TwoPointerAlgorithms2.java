package com.bteshome.algorithms.twoPointers_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointerAlgorithms2 {
    /**
     * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
     * NOTE - there is also a binary search based solution, which is slower.
     *        This solution is significantly faster.
     * */
    public static int[] twoSumII(int[] numbers, int target) {
        if (numbers == null) {
            return null;
        }

        int low = 0;
        int high = numbers.length - 1;

        while (low <= high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[]{low + 1, high + 1};
            } else if (sum < target) {
                low++;
            } else {
                high--;
            }
        }

        return null;
    }

    /**
     * https://leetcode.com/problems/3sum/
     * */
    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null) {
            return List.of();
        }

        Arrays.sort(nums);

        var list = new ArrayList<List<Integer>>();

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int target = -nums[i];
            int low = i + 1;
            int high = nums.length - 1;

            while (low < high) {
                if (low > i + 1 && nums[low] == nums[low - 1]) {
                    low++;
                    continue;
                }
                if (high < nums.length - 1 && nums[high] == nums[high + 1]) {
                    high--;
                    continue;
                }

                int sum = nums[low] + nums[high];
                if (sum == target) {
                    list.add(List.of(nums[i], nums[low], nums[high]));
                    low++;
                    high--;
                } else if (sum < target) {
                    low++;
                } else {
                    high--;
                }
            }
        }

        return list;
    }

    /**
     * https://leetcode.com/problems/longest-palindromic-substring/
     * NOTE - there is also a DP based solution with similar time complexity O(n^2)
     *        but practically slower.
     * */
    public static String longestPalindromicSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        int maxLength = 0;
        int maxLengthLeft = 0;
        int maxLengthRight = 0;

        for (int i = 0; i < s.length(); i++) {
            int left = i;
            int right = i;

            while (left >= 0 && right < s.length()) {
                if (s.charAt(left) != s.charAt(right)) {
                    break;
                }
                int length = right - left + 1;
                if (length > maxLength) {
                    maxLength = length;
                    maxLengthLeft = left;
                    maxLengthRight = right;
                }
                left--;
                right++;
            }
        }

        for (int i = 0; i < s.length() - 1; i++) {
            int left = i;
            int right = i+1;

            while (left >= 0 && right < s.length()) {
                if (s.charAt(left) != s.charAt(right)) {
                    break;
                }
                int length = right - left + 1;
                if (length > maxLength) {
                    maxLength = length;
                    maxLengthLeft = left;
                    maxLengthRight = right;
                }
                left--;
                right++;
            }
        }

        return s.substring(maxLengthLeft, maxLengthRight + 1);
    }
}