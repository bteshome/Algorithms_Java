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
}
