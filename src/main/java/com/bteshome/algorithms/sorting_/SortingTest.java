package com.bteshome.algorithms.sorting_;

import com.bteshome.algorithms.heap_.HeapAlgorithms14;

import java.util.Random;

public class SortingTest {
    public static void test() {
        int[] a = new int[100000000];
        Random r = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(0, 100);
        }
        int[] b = a.clone();
        SortingAlgorithms1.doBucketSort(a, b);
    }
}
