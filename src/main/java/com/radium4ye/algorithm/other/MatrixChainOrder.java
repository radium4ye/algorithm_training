package com.radium4ye.algorithm.other;

/**
 * 矩阵链乘法计算
 * 计算最小代价
 *
 * @author radium4ye
 */
public class MatrixChainOrder {

    /**
     * 记录最小代价的首次乘法位置
     */
    private int[][] s;

    /**
     * 记录代价
     */
    private int[][] m;

    /**
     * n * 2 的数组
     * 表示矩阵相应的长宽
     */
    private int[][] array;

    /**
     * 传入一个 n * 2 的数组
     */
    public MatrixChainOrder(int[][] array) {

        //暂不进行格式校验
        this.array = array;
        int length = array.length;
        m = new int[length][length];
        s = new int[length][length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                s[i][j] = -1;
            }
        }

        for (int i = 0; i < length; i++) {
            s[i][i] = 0;
        }

    }

    /**
     * 进行计算最小代价
     */
    public int calc() {
        return calc(0, array.length - 1);
    }

    /**
     * 进行计算最小代价
     */
    public int calc(int start, int end) {

        if (s[start][end] >= 0) {
            return m[start][end];
        }

        //最小值和切割位置
        int minValue = Integer.MAX_VALUE;
        int minPath = -1;

        for (int i = start; i < end; i++) {

            int nowValue = calc(start, i) + calc(i + 1, end) + array[start][0] * array[i][1] * array[end][1];
            if (nowValue < minValue) {
                minValue = nowValue;
                minPath = i;
            }
        }
        s[start][end] = minPath;
        m[start][end] = minValue;

        return m[start][end];
    }
}
