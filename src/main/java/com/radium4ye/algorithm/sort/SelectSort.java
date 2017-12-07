package com.radium4ye.algorithm.sort;

import static com.radium4ye.algorithm.sort.Example.exchange;

/**
 * 选择排序
 * 时间复杂度 O(n^2)
 * 空间复杂度 O(1)
 * 是否稳定：否
 *
 * @author radium4ye
 */
public class SelectSort {

    /**
     * 选择排序
     *
     * @param array 待排序的数组
     * @return 排好序的数组
     */
    public static <T> Comparable<T>[] sort(Comparable<T>[] array) {
        return sort(array, true);
    }

    /**
     * 选择排序
     *
     * @param array 待排序的数组
     * @param desc  是否为降序
     * @return 排好序的数组
     */
    public static <T> Comparable<T>[] sort(Comparable<T>[] array, boolean desc) {
        int arrayLen = array.length;
        for (int i = 0; i < arrayLen; i++) {
            int selectIndex = select(array, i, arrayLen, desc);
            exchange(array, i, selectIndex);
        }

        return array;
    }


    /**
     * 选择数组中最小|大的元素
     * 左闭（lo）右开（hi）
     *
     * @param array 数组
     * @param lo    开始位置
     * @param hi    结束位置
     * @param max   是否选择最大的
     * @return 该元素的索引
     */
    public static int select(Comparable[] array, int lo, int hi, boolean max) {
        //校验范围
        if (hi < lo) {
            return -1;
        }
        if (lo >= array.length) {
            throw new ArrayIndexOutOfBoundsException("lo 参数超出数组长度");
        }

        int selectIndex = lo;

        for (int i = lo + 1; i < hi; i++) {
            //比较当前元素和之前的元素
            int compareResult = array[i].compareTo(array[selectIndex]);

            //判断是都需要替换选择的元素
            boolean needReplace = (max && compareResult > 0) || (!max && compareResult < 0);
            if (needReplace) {
                selectIndex = i;
            }
        }

        return selectIndex;
    }


}
