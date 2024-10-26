package com.bteshome.algorithms.matrix_;

public class MatrixTest {
    public static void test() {
        var matrix = new int[][]{
                new int[]{1,4,7,11,15},
                new int[]{2,5,8,12,19},
                new int[]{3,6,9,16,22},
                new int[]{10,13,14,17,24},
                new int[]{18,21,23,26,30}
        };
        System.out.println(MatrixAlgorithms1.searchMatrix(matrix, 5));
    }
}
