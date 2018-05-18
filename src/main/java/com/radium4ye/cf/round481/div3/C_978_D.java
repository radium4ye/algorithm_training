package com.radium4ye.cf.round481.div3;

import java.util.Scanner;

/**
 * @author radium4ye
 */
public class C_978_D {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = s.nextInt();
        }

        int ceil = (int) Math.ceil((double) (array[0] - array[n - 1]) / (n - 1));
        int floor = (int) Math.floor((double) (array[0] - array[n - 1]) / (n - 1));
        int result;
        if (ceil == floor) {
            result = calc(array, ceil);
        } else {
            int ceilResult = calc(array, ceil);
            int floorResult = calc(array, floor);
            result = Math.min(floorResult, ceilResult);

        }
        if (result == Integer.MAX_VALUE) {
            result = -1;
        }
        System.out.println(result);

    }

    public static int calc(int[] array, int difference) {
        int calc1 = Math.min(Math.min(calc_head(array, difference, 1), calc_head(array, difference, 0)), calc_head(array, difference, -1));
        int calc2 = Math.min(Math.min(calc_tail(array, difference, 1), calc_tail(array, difference, 0)), calc_tail(array, difference, -1));

        return Math.min(calc1, calc2);
    }

    public static int calc_head(int[] array, int difference, int add) {
        int[] afterArray = new int[array.length];

        //处理后的数据
        afterArray[0] = array[0] + add;
        for (int i = 1; i < array.length; i++) {
            afterArray[i] = afterArray[i - 1] - difference;
        }

        int adjustNum = add == 0 ? 0 : 1;


        for (int i = 1; i < array.length; i++) {
            if (afterArray[i] == array[i]) {

            } else if (Math.abs(afterArray[i] - array[i]) == 1) {
                adjustNum++;
            } else {
                return Integer.MAX_VALUE;
            }
        }

        return adjustNum;

    }


    public static int calc_tail(int[] array, int difference, int add) {
        int len = array.length;
        int[] afterArray = new int[len];

        //处理后的数据
        afterArray[len - 1] = array[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            afterArray[i] = afterArray[i + 1] + difference;
        }

        int adjustNum = add == 0 ? 0 : 1;

        for (int i = 0; i < len - 1; i++) {
            if (afterArray[i] == array[i]) {

            } else if (Math.abs(afterArray[i] - array[i]) == 1) {
                adjustNum++;
            } else {
                return Integer.MAX_VALUE;
            }
        }

        return adjustNum;

    }
}
