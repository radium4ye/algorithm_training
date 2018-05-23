package com.radium4ye.cf.round484.div2;

import java.util.Scanner;

/**
 * @author radium4ye
 */
public class C_982_A {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = Integer.valueOf(s.nextLine());
        String line = s.nextLine();
        char pre = line.charAt(0);
        //需要人来占据作为
        boolean needPersonSign = pre == '0';

        //读取n个数
        for (int i = 1; i < n; i++) {
            char now = line.charAt(i);

            if(needPersonSign && now == '0'){
                System.out.println("No");
                return;
            }else {
                needPersonSign = false;
            }

            if (pre != now) {
                pre = now;
                continue;
            }else if(now == '0'){
                //出现连续2个空位置，下一个位置必须要有人
                needPersonSign = true;
                continue;
            }else {
                System.out.println("No");
                return;
            }
        }

        if (needPersonSign) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
    }
}
