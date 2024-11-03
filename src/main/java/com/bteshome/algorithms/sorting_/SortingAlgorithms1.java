package com.bteshome.algorithms.sorting_;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class SortingAlgorithms1 {
    public static void doBucketSort(int[] a, int[] b) {
        var start = Instant.now();

        var buckets = new int[100];

        for (int v : a) {
            buckets[v%10]++;
        }

        int bucketStart = 0;
        for (int i = 0; i < buckets.length; i++) {
            int j = bucketStart;
            for(; j < bucketStart + buckets[i]; j++) {
                a[j] = i;
            }
            bucketStart = j;
        }

        var end = Instant.now();

        System.out.println("bucket sort seconds: " + Duration.between(start, end).toMillis());

        start = Instant.now();
        Arrays.sort(b);
        end = Instant.now();

        System.out.println("Arrays.sort() sort seconds: " + Duration.between(start, end).toMillis());
    }

    /**
     * https://leetcode.com/problems/sort-colors/
     * */
    public static void sortColors(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        int i = 0;
        int j = nums.length - 1;

        while (i <= j) {
            if (nums[i] == 0) {
                i++;
            } else {
                while (j > i + 1 && nums[j] != 0) {
                    j--;
                }
                if (nums[j] == 0) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    i++;
                    j--;
                } else {
                    break;
                }

            }
        }

        j = nums.length - 1;

        while (i <= j) {
            if (nums[i] == 1) {
                i++;
            } else {
                while (j > i + 1 && nums[j] != 1) {
                    j--;
                }
                if (nums[j] == 1) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    i++;
                    j--;
                } else {
                    break;
                }
            }
        }
    }
}
