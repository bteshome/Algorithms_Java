package com.bteshome.algorithms.matrix_;

public class MatrixAlgorithms1 {
    /**
     * https://leetcode.com/problems/search-a-2d-matrix-ii/
     * */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int row = 0;
        int col = matrix[0].length - 1;

        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (target < matrix[row][col]) {
                col--;
            } else {
                row++;
            }
        }

        return false;
    }
}
