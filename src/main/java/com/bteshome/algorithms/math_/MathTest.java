package com.bteshome.algorithms.math_;


import com.bteshome.algorithms.strings_.StringAlgorithms1;

public class MathTest {
    public static void test() {
        //System.out.println(Arrays.toString(MathAlgorithms1.countBits(5)));

        //System.out.println(MathAlgorithms1.reverseInteger(-123));

        //System.out.println(MathAlgorithms1.stringToInteger("-1"));

        //System.out.println(MathAlgorithms2.isPowerOfThree(27));

        int[] array = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                21,22,23,24,25,26,27,28,29,30
        };
        System.out.println(MathAlgorithms3.subsets(array).size());
    }
}
