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

    /**
     * https://leetcode.com/problems/rotate-image/
     * */
    public static void rotateImage(int[][] matrix) {
        if (matrix == null) {
            return;
        }

        for (int layer = 0; layer < matrix.length / 2 ; layer++) {
            rotateImageLayer(matrix, layer);
        }
    }

    private static void rotateImageLayer(int[][] matrix, int layer) {
        for (int i = layer; i < matrix.length - layer - 1; i++) {
            int temp1 = matrix[layer][i];
            int temp2 = matrix[i][matrix.length - 1 - layer];
            int temp3 = matrix[matrix.length - 1 - layer][matrix.length - 1 - i];
            int temp4 = matrix[matrix.length - 1 - i][layer];

            System.out.println(temp1);
            System.out.println(temp2);
            System.out.println(temp3);
            System.out.println(temp4);

            matrix[i][matrix.length - 1 - layer] = temp1;
            matrix[matrix.length - 1 - layer][matrix.length - 1 - i] = temp2;
            matrix[matrix.length - 1 - i][layer] = temp3;
            matrix[layer][i] = temp4;
        }
    }
}
