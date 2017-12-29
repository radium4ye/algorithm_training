package com.radium4ye.algorithm.other;

/**
 * 0-1 背包问题
 *
 * @author radium4ye
 */
public class KnapsackProblem {

    /**
     * 背包容量
     */
    private int knapsackCapacity;

    /**
     * 价值
     */
    private int[] worth;

    /**
     * 重量
     */
    private int[] weight;

    /**
     * 选择到的索引位置 | 背包剩余量
     * 值：背包中的价值
     */
    private int[][] meno;

    public KnapsackProblem(int knapsackCapacity, int[] worth, int[] weight) {
        this.knapsackCapacity = knapsackCapacity;
        this.worth = worth;
        this.weight = weight;
        meno = new int[worth.length + 1][knapsackCapacity + 1];
        System.out.println(select(0, knapsackCapacity));
    }

    /**
     * 选择
     *
     * @param index          当前选择的索引位置
     * @param remainCapacity 剩余的容量
     * @return 价值
     */
    public int select(int index, int remainCapacity) {

        for (int i = 0; i < worth.length; i++) {
            for (int w = 0; w <= knapsackCapacity; w++) {

                //如果重量大于背包剩余量
                if (weight[i] <= w) {

                    if (worth[i] + meno[i][w-weight[i]] > meno[i][w]) {
                        meno[i + 1][w] = worth[i] +  meno[i][w-weight[i]];
                    } else {
                        meno[i + 1][w] = meno[i][w];
                    }

                } else {
                    meno[i + 1][w] = meno[i][w];
                }
            }
        }


        return meno[worth.length][knapsackCapacity];
    }
}
