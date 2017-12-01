package com.radium4ye.algorithm.sort;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Radium
 */
public class Example {

    public static void sort(Comparable[] comparables){

    }

    private static boolean less(Comparable c1,Comparable c2){
        return c1.compareTo(c2) < 0;
    }

    private static void show(Comparable[] a){
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
}
