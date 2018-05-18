package com.radium4ye.cf.round481.div3;

import java.util.Scanner;

/**
 * @author radium4ye
 */
public class C_978_E {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int w = s.nextInt();
        int max = 0;
        int min = w;
        int now = 0;

        for (int i = 0; i < n; i++) {
            now += s.nextInt();

            max = Math.max(max, now);
            min = Math.min(min, now);
        }
        if(min<0){
            min = Math.abs(min);
        }else {
            min = 0;
        }

        //小于0 代表结果不存在
        int result = (w - max) - min + 1;
        if(result < 0){
            result = 0;
        }
        System.out.println(result);
    }

}
