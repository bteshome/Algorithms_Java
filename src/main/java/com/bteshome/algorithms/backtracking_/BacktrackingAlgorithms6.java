package com.bteshome.algorithms.backtracking_;

import java.util.Arrays;

public class BacktrackingAlgorithms6 {
    /**
     * https://leetcode.com/problems/unique-paths-iii/description/
     * */
    public static int uniquePathsIII(int[][] grid) {
        if (grid == null) {
            return 0;
        }

        int nonObstacleCells = 0;
        int startRow = -1;
        int startCol = -1;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    startRow = i;
                    startCol = j;
                } else if (grid[i][j] == 0) {
                    nonObstacleCells++;
                }
            }
        }

        return uniquePathsIII(grid, startRow, startCol, nonObstacleCells);
    }

    private static int uniquePathsIII(int[][] grid, int row, int col, int unvisitedNonObstacleCells) {
        var paths = 0;

        if (col > 0) {
            paths += uniquePathsIIIVisitCell(grid, row, col - 1, unvisitedNonObstacleCells);
        }

        if (col < grid[0].length - 1) {
            paths += uniquePathsIIIVisitCell(grid, row, col + 1, unvisitedNonObstacleCells);
        }

        if (row > 0) {
            paths += uniquePathsIIIVisitCell(grid, row - 1, col, unvisitedNonObstacleCells);
        }

        if (row < grid.length - 1) {
            paths += uniquePathsIIIVisitCell(grid, row + 1, col, unvisitedNonObstacleCells);
        }

        return paths;
    }

    private static int uniquePathsIIIVisitCell(int[][] grid, int row, int col, int unvisitedNonObstacleCells) {
        var paths = 0;

        if (grid[row][col] == 2) {
            if (unvisitedNonObstacleCells == 0) {
                paths += 1;
            }
        } else if (grid[row][col] == 0) {
            grid[row][col] = -1;
            paths = uniquePathsIII(grid, row, col, unvisitedNonObstacleCells - 1);
            grid[row][col] = 0;
        }

        return paths;
    }

    /**
     * https://leetcode.com/problems/sudoku-solver/
     * */
    public static void solveSudoku(char[][] board) {
        if (board == null) {
            return;
        }

        var solved = solveSudoku(board, 0, 0);
        System.out.println(solved);
        System.out.println(" ");
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }

    private static boolean solveSudoku(char[][] board, int row, int col) {
        if (row == board.length) {
            return true;
        }

        if (board[row][col] != '.') {
            int[] nextCell = getNextCell(board, row, col);
            return solveSudoku(board, nextCell[0], nextCell[1]);
        }

        for (char num = '1'; num <= '9'; num++) {
            if (isRowValid(board, row, num) && isColumnValid(board, col, num) && isBoxValid(board, row, col, num)) {
                board[row][col] = num;
                int[] nextCell = getNextCell(board, row, col);
                if (solveSudoku(board, nextCell[0], nextCell[1])) {
                    return true;
                }
                board[row][col] = '.';
            }
        }

        return false;
    }

    private static int[] getNextCell(char[][] board, int row, int col) {
        var nextRow = row;
        var nextCol = col;
        if (col < board[0].length - 1) {
            nextCol++;
        } else {
            nextRow++;
            nextCol = 0;
        }
        return new int[]{nextRow, nextCol};
    }

    private static boolean isRowValid(char[][] board, int row, char c) {
        for (int j = 0; j < board[0].length; j++) {
            if (board[row][j] == c) {
                return false;
            }
        }
        return true;
    }

    private static boolean isColumnValid(char[][] board, int col, char c) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == c) {
                return false;
            }
        }
        return true;
    }

    private static boolean isBoxValid(char[][] board, int row, int col, char c) {
        int colStart = (col / 3) * 3;
        int rowStart = (row / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + rowStart][j + colStart] == c) {
                    return false;
                }
            }
        }

        return true;
    }
}
