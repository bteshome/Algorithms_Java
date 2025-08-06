package com.bteshome.algorithms.backtracking_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BacktrackingAlgorithms4 {
    /**
     * https://leetcode.com/problems/subsets/description/?envType=problem-list-v2&envId=backtracking
     * NOTE:
     *  - the second solution is fater the first one
     *  - also checkout the bitmask solution (for nums.length <= 20)
     * */
    public static List<List<Integer>> subsetsApproach1(int[] nums) {
        if (nums == null)
            return List.of(List.of());

        List<List<Integer>> subsets = new ArrayList<List<Integer>>();
        subsetsApproach1(nums, 0, subsets, new ArrayList<Integer>(nums.length));

        return subsets;
    }

    private static void subsetsApproach1(int[] nums, int pos, List<List<Integer>> subsets, List<Integer> subset) {
        if (pos == nums.length) {
            subsets.add(subset.stream().toList());
            return;
        }

        subset.addLast(nums[pos]);
        subsetsApproach1(nums, pos + 1, subsets, subset);
        subset.removeLast();
        subsetsApproach1(nums, pos + 1, subsets, subset);
    }

    public List<List<Integer>> subsetsApproach2(int[] nums) {
        if (nums == null)
            return List.of(List.of());

        List<List<Integer>> subsets = new ArrayList<>();
        subsetsApproach2(nums, 0, subsets, new ArrayList<>());

        return subsets;
    }

    private static void subsetsApproach2(int[] nums, int start, List<List<Integer>> subsets, List<Integer> subset) {
        subsets.add(new ArrayList<>(subset));

        for (int i = start; i < nums.length; i++) {
            subset.add(nums[i]);
            subsetsApproach2(nums, i + 1, subsets, subset);
            subset.removeLast();
        }
    }

    /**
     * https://leetcode.com/problems/subsets-ii/
     * */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null)
            return List.of(List.of());

        List<List<Integer>> subsets = new ArrayList<>();

        Arrays.sort(nums);
        subsetsWithDup(nums, 0, subsets, new ArrayList<>());

        return subsets;
    }

    private static void subsetsWithDup(int[] nums, int start, List<List<Integer>> subsets, List<Integer> subset) {
        subsets.add(new ArrayList<>(subset));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1])
                continue;
            subset.add(nums[i]);
            subsetsWithDup(nums, i + 1, subsets, subset);
            subset.removeLast();
        }
    }

    /**
     * https://leetcode.com/problems/restore-ip-addresses/?envType=problem-list-v2&envId=backtracking&difficulty=MEDIUM
     * */
    public static List<String> restoreIpAddresses(String s) {
        var ips = new ArrayList<String>();

        if (s != null) {
            restoreIpAddresses(s, 0, "", 0, ips);
        }

        return ips;
    }

    private static void restoreIpAddresses(String s, int pos, String ip, int octetCount, ArrayList<String> ips) {
        if (octetCount == 4) {
            if (pos == s.length()) {
                ips.add(ip);
            }
            return;
        }

        if (pos == s.length()) {
            return;
        }

        if (pos > 0) {
            ip += ".";
        }

        restoreIpAddresses(s, pos + 1, ip + s.charAt(pos), octetCount + 1, ips);

        if (s.charAt(pos) != '0' && pos < s.length() - 1) {
            restoreIpAddresses(s, pos + 2, ip + s.substring(pos, pos + 2), octetCount + 1, ips);
        }

        if (s.charAt(pos) != '0' && pos < s.length() - 2) {
            var octet = s.substring(pos, pos + 3);
            if (octet.compareTo("255") <= 0) {
                restoreIpAddresses(s, pos + 3, ip + octet, octetCount + 1, ips);
            }
        }
    }
}
