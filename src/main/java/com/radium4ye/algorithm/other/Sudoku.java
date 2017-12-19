package com.radium4ye.algorithm.other;

/**
 * @author radium4ye
 */
public class Sudoku {

    /**
     * 设置常数 列的数值 行的数值
     */
    public static final int COLUMN_NUM = 9;
    public static final int ROW_NUM = 9;

    private int[][] sudokuBoard = new int[9][9];

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        sudoku.setSudokuBoard(0, 0);
        sudoku.printBoard();

    }

    /**
     * 检查在该点填写这个数值是否符合数独的要求
     *
     * @param x     x坐标
     * @param y     y坐标
     * @param value 填写的值
     * @return {@code true 可以填写}
     */
    public boolean checkValue(int x, int y, int value) {

        //检查行
        for (int i = 0; i < COLUMN_NUM; i++) {
            if (sudokuBoard[x][i] == value) {
                return false;
            }
        }
        //检查列
        for (int j = 0; j < ROW_NUM; j++) {
            if (sudokuBoard[j][y] == value) {
                return false;
            }
        }

        //检查 3x3 的小方格
        int startX = x / 3 * 3;
        int startY = y / 3 * 3;
        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
                if (sudokuBoard[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 按照一行一行进行填写信息
     *
     * @param x
     * @param y
     * @return
     */
    public boolean setSudokuBoard(int x, int y) {

        if (y >= COLUMN_NUM) {
            y = 0;
            x++;
        }

        if (x >= ROW_NUM) {
            return true;
        }

        //尝试每个数值
        for (int num = 1; num <= 9; num++) {
            if (checkValue(x, y, num)) {
                sudokuBoard[x][y] = num;

                //如果之后的值都没问题
                if (setSudokuBoard(x, y + 1)) {
                    return true;
                }
            }
        }

        //该点任何数值都不对，说明上一步错误，将该步清楚数据，返回false
        sudokuBoard[x][y] = 0;
        return false;
    }

    /**
     * 打印棋盘
     */
    public void printBoard() {
        for (int i = 0; i < ROW_NUM; i++) {
            for (int j = 0; j < COLUMN_NUM; j++) {
                System.out.print(sudokuBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

}
