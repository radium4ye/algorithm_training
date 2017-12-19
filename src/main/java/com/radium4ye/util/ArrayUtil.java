package com.radium4ye.util;

/**
 * @author radium4ye
 */
public class ArrayUtil {

    /**
     * 螺旋打印数组
     */
    public static void spiralPrint(int[][] array) {
        int xMin = 0, xMax = array.length - 1,
                yMin = 0, yMax = array[0].length - 1;

        int startX = 0, startY = 0;

        /**
         * {@code true 向右|向下}
         * {@code false 向左|向上}
         */
        boolean direction = true;
        //是否水平
        boolean horizontal = true;
        while (true) {

            System.out.print(array[startX][startY] + " ");

            //退出循环
            if(xMax <= xMin && yMax <= yMin){
                break;
            }

            if (horizontal) {
                if (direction) {
                    //水平向右边


                    if (startY < yMax) {
                        startY++;
                    } else {
                        startX++;
                        horizontal = !horizontal;
                        xMin++;
                    }

                } else {
                    //水平向左

                    if (startY > yMin) {
                        startY--;
                    } else {
                        startX--;
                        horizontal = !horizontal;
                        xMax--;
                    }

                }

            } else {

                if (direction) {
                    //竖直向下

                    if (startX < xMax) {
                        startX++;
                    } else {
                        startY--;
                        direction = !direction;
                        horizontal = !horizontal;
                        yMax--;
                    }

                } else {

                    //竖直向上

                    if (startX > xMin) {
                        startX--;
                    } else {
                        startY++;
                        direction = !direction;
                        horizontal = !horizontal;
                        yMin++;
                    }

                }

            }
        }

    }


    /**
     * 将数组进行旋转
     * 空间复杂度O(1)
     * 时间复杂度O(n)
     *
     * @param array       原始数组
     * @param rotateStart 旋转开始位置
     */
    public static Object[] rotate(Object[] array, int rotateStart) {

        //将数组分割成2部分
        int path1 = 0;
        int pathLen1 = rotateStart;

        int path2 = rotateStart;
        int pathLen2 = array.length - rotateStart;

        while (true) {
            if (pathLen1 == pathLen2) {
                //直接互换
                exchange(array, path1, path2, pathLen1);
                break;
            } else if (pathLen1 > pathLen2) {
                //将path2 和 path1首部进行互换
                exchange(array, path1, path2, pathLen2);
                path1 = path1 + pathLen2;
                pathLen1 = pathLen1 - pathLen2;
            } else {
                //将path1 和 path2尾部进行互换
                exchange(array, path1, path2 + pathLen2 - pathLen1, pathLen1);
                pathLen2 -= pathLen1;
            }
        }
        return array;
    }

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
                if (oldPosition[j] == i) {
                    indexOldPosition = j;
                    break;
                }
            }

            int ltCount = countLt(oldPosition, indexOldPosition, i);

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

    /**
     * 交换数组中元素位置
     *
     * @param array  数组
     * @param index1 元素1的索引
     * @param index2 元素2的索引
     */
    public static void exchange(Object[] array, int index1, int index2, int len) {
        if (index1 == index2) {
            return;
        }

        for (int i = 0; i < len; i++) {
            exchange(array, index1 + i, index2 + i);
        }
    }

    /**
     * 交换数组中元素位置
     *
     * @param array  数组
     * @param index1 元素1的索引
     * @param index2 元素2的索引
     */
    public static void exchange(Object[] array, int index1, int index2) {
        if (index1 == index2) {
            return;
        }

        Object temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
