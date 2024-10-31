package com.bteshome.algorithms.arrays_;

import java.util.*;

public class ArrayAlgorithms4 {
    /**
     * https://leetcode.com/problems/move-zeroes/
     * */
    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        int nextNonZeroIndex = -1;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0) {
                for (int j = (nextNonZeroIndex == -1 ? i : nextNonZeroIndex) + 1; j < nums.length; j++) {
                    if (nums[j] != 0) {
                        nextNonZeroIndex = j;
                        break;
                    }
                }

                if (nextNonZeroIndex == -1) {
                    break;
                }

                nums[i] = nums[nextNonZeroIndex];
                nums[nextNonZeroIndex] = 0;
            }
        }
    }

    /**
     * https://leetcode.com/problems/reverse-integer/
     * NOTE - there is another implementation that uses decimal math,
     *        which is faster.
     * */
    public static int reverseInteger(int x) {
        if (x == 0) {
            return x;
        }

        boolean isNegative = x < 0;

        if (isNegative) {
            x = -x;
        }

        char[] digits = Integer.toString(x).toCharArray();

        int i = 0;
        int j = digits.length - 1;

        while (i <= j) {
            char temp = digits[i];
            digits[i] = digits[j];
            digits[j] = temp;
            i++;
            j--;
        }

        String s = null;

        if (!isNegative) {
            s = String.valueOf(digits);
        } else {
            var b = new StringBuilder(digits.length + 1);
            b.append('-');
            b.append(digits);
            s = b.toString();
        }

        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return List.of();
        }

        class Entry {
            private final int a;
            private final int b;
            private final int c;

            public Entry(int a, int b, int c) {
                int[] values = new int[]{a, b, c};
                Arrays.sort(values);
                this.a = values[0];
                this.b = values[1];
                this.c = values[2];
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Entry entry = (Entry) o;
                return a == entry.a && b == entry.b && c == entry.c;
            }

            @Override
            public int hashCode() {
                return Objects.hash(a, b, c);
            }
        }

        HashSet<Entry> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            HashMap<Integer, Integer> seen = new HashMap<>();

            for (int j = 0; j < nums.length; j++) {
                int current = nums[j];
                if (i != j) {
                    int other = -current - nums[0];
                    if (seen.containsKey(other)) {
                        set.add(new Entry(nums[i], nums[j], nums[seen.get(other)]));
                    }
                }
                seen.put(current, j);
            }
        }

        return set.stream().map(e -> List.of(e.a, e.b, e.c)).toList();
    }
}
