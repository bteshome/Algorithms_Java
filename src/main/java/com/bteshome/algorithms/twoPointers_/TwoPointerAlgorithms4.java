package com.bteshome.algorithms.twoPointers_;

public class TwoPointerAlgorithms4 {
    /*
    * https://leetcode.com/problems/one-edit-distance/
    * */
    public static boolean isOneEditDistance(String s, String t) {
        if (s == null || s.isBlank())
            return t != null && t.length() == 1;

        if (t == null || t.isBlank())
            return s != null && s.length() == 1;

        if (Math.abs(s.length() - t.length()) > 1)
            return false;

        int diff = 0;

        for (int i = 0, j = 0;;) {
            if (i == s.length()) {
                if (j < t.length())
                    diff++;
                break;
            }

            if (j == t.length()) {
                if (i < s.length())
                    diff++;
                break;
            }

            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
                continue;
            }

            if (diff > 0)
                return false;

            if (s.length() > t.length())
                i++;
            else if (t.length() > s.length())
                j++;
            else {
                i++;
                j++;
            }

            diff++;
        }

        return diff == 1;
    }

    /*
    * https://leetcode.com/problems/compare-version-numbers/
    * */
    public static int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null)
            return 0;

        String[] v1Parts = version1.split("\\.");
        String[] v2Parts = version2.split("\\.");
        int numParts = Math.max(v1Parts.length, v2Parts.length);

        for (int i = 0; i < numParts; i++) {
            int v1Rev = i >= v1Parts.length ? 0 : Integer.parseInt(v1Parts[i]);
            int v2Rev = i >= v2Parts.length ? 0 : Integer.parseInt(v2Parts[i]);
            if (v1Rev == v2Rev)
                continue;
            return Integer.compare(v1Rev, v2Rev);
        }

        return 0;
    }
}
