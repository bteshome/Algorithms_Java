package com.bteshome.algorithms.backtracking_;

import java.util.ArrayList;
import java.util.List;

public class BacktrackingAlgorithms4 {
    /**
     * https://leetcode.com/problems/subsets/description/?envType=problem-list-v2&envId=backtracking
     * NOTE: checkout the bitmask solution as well, which is a bit faster (for nums.length <= 10)
     * */
    public static List<List<Integer>> subsets(int[] nums) {
        var subsets = new ArrayList<List<Integer>>();

        if (nums != null) {
            subsets(nums, 0, subsets, new ArrayList<Integer>(nums.length));
        }

        return subsets;
    }

    private static void subsets(int[] nums, int pos, ArrayList<List<Integer>> subsets, List<Integer> subset) {
        if (pos == nums.length) {
            subsets.add(subset.stream().toList());
            return;
        }

        subset.addLast(nums[pos]);
        subsets(nums, pos + 1, subsets, subset);
        subset.removeLast();
        subsets(nums, pos + 1, subsets, subset);
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
