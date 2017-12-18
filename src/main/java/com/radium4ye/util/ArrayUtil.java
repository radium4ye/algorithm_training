package com.radium4ye.util;

/**
 * @author radium4ye
 */
public class ArrayUtil {

    /**
     * 空间复杂度O(1) 在原数组按照给定的序列排成新的数组
     *
     * @param origin      原数组
     * @param newPosition 新的序列
     */
    public static void changePosition(Object[] origin, int[] newPosition) {

        //待交换的值 以及原始位置
        Object temp;
        int change;
        for (int i = 0; i < newPosition.length; i++) {
            change = newPosition[i];
            int gtCount = countGt(newPosition, i, change);

            //获取到该索引值对应的对象
            change += gtCount;
            temp = origin[change];

            //将origin[i..change-1] 都右移一位
            for (int j = change - 1; j >= i; j--) {
                origin[j + 1] = origin[j];
            }

            origin[i] = temp;
        }

    }

    /**
     * 空间复杂度O(1) 将转化后的数组按照老的序列获取到原始数组
     *
     * @param changeArray 转化后的数组
     * @param oldPosition 老的序列
     * @see #changePosition(Object[], int[])
     */
    public static void recoveryPosition(Object[] changeArray, int[] oldPosition) {

        //待交换的值 以及原始位置
        Object temp;
        int change;

        for (int i = 0; i < changeArray.length; i++) {
            //获取 i 在 oldPosition 中的索引
            int indexOldPosition = -1;
            for (int j = 0; j < oldPosition.length; j++) {
                if(oldPosition[j] == i){
                    indexOldPosition = j;
                    break;
                }
            }

            int ltCount = countLt(oldPosition,indexOldPosition,i);

            change = indexOldPosition + ltCount;
            temp = changeArray[change];

            //将 changeArray[i..change-1]右移1位
            for (int j = change - 1; j >= i; j--) {
                changeArray[j + 1] = changeArray[j];
            }
            changeArray[i] = temp;
        }
    }

    /**
     * 用于辅助该算法
     *
     * @param array   比较数值
     * @param end     结束位置
     * @param compare 比较的值
     * @return 比 compare 大的数值有多少个
     * 计算该范围内比这个值大的数值 有多少个
     * @see #changePosition(Object[], int[])
     */
    private static int countGt(int[] array, int end, int compare) {
        int result = 0;

        for (int i = 0; i < end; i++) {
            if (array[i] > compare) {
                result++;
            }
        }

        return result;
    }

    /**
     * 用于辅助该算法
     *
     * @param array   比较数值
     * @param start   开始位置
     * @param compare 比较的值
     * @return 比 compare 大的数值有多少个
     * @see #recoveryPosition(Object[], int[])
     * 计算该范围内比这个值大的数值 有多少个
     */
    private static int countLt(int[] array, int start, int compare) {
        int result = 0;

        for (int i = start; i < array.length; i++) {
            if (array[i] < compare) {
                result++;
            }
        }

        return result;
    }
}
