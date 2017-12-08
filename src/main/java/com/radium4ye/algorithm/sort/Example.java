package com.radium4ye.algorithm.sort;

import java.util.stream.Stream;

/**
 * @author Radium
 */
public class Example {

    public static void sort(Comparable[] comparables){

    }

    public static boolean less(Comparable c1,Comparable c2){
        return c1.compareTo(c2) < 0;
    }

    public static void show(Comparable[] a){
        Stream.of(a).forEach(item -> System.out.print(item + " "));
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a){

        for (int i = 1; i < a.length; i++) {
            if(less(a[i],a[i-1])){
                return false;
            }
        }
        return true;
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

    /**
     * 交换数组中元素位置
     *
     * @param array  数组
     * @param index1 元素1的索引
     * @param index2 元素2的索引
     */
    public static void exchange(int[] array, int index1, int index2) {
        if (index1 == index2) {
            return;
        }

        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
