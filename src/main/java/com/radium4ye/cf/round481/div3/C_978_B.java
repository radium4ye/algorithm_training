package com.radium4ye.cf.round481.div3;

import java.util.Scanner;

/**
 * @author radium4ye
 */
public class C_978_B {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        String str = s.next();

        int xNum = 0;
        int removeNum = 0;
        for (int i = 0; i < str.length(); i++) {

            if(str.charAt(i) == 'x'){
                xNum ++;
            }else {
                xNum = 0;
            }

            if(xNum >=3){
                removeNum ++ ;
            }
        }
        System.out.println(removeNum);
    }
}
