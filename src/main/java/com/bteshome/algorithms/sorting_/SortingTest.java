package com.bteshome.algorithms.sorting_;

import com.bteshome.algorithms.heap_.HeapAlgorithms14;

import java.util.Arrays;
import java.util.Random;

public class SortingTest {
    public static void test() {
        /*int[] a = new int[100000000];
        Random r = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(0, 100);
        }
        int[] b = a.clone();
        SortingAlgorithms1.doBucketSort(a, b);*/

        /*int[] a = new int[]{2,0,2,1,1,0};
        //int[] a = new int[]{1,0,0};
        SortingAlgorithms1.sortColors(a);
        System.out.println(Arrays.toString(a));*/

        System.out.println(SortingAlgorithms2.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));

    }
}
