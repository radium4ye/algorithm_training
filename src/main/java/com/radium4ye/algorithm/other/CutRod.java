package com.radium4ye.algorithm.other;

import java.util.ArrayList;

/**
 * 切割钢条问题
 *
 * @author radium4ye
 */
public class CutRod {

    /**
     * 定义价格
     * 索引值为长度，值为钢条收益
     */
    private int[] p = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

    /**
     * 保存了结果
     */
    private ArrayList<Integer> result = new ArrayList<>();

    public CutRod() {
        result.add(0, 0);
    }

    /**
     * 计算n长度的钢条收益最大的值
     * 自顶向下 进行计算
     *
     * @param n
     * @return
     */
    public int calc(int n) {

        if (n <= 0) {
            return 0;
        }

        if (result.size() > n) {
            return result.get(n);
        }

        //定义最大值
        int max;
        if (n + 1 > p.length) {
            max = -1;
        } else {
            max = p[n];
        }

        //然后进行循环，获取最大值
        for (int i = 1; i < n / 2; i++) {
            max = Math.max(max, calc(i) + calc(n - i));
        }

        //设置到 result 中缓存减少重复计算
        result.add(n, max);

        return max;
    }
}
