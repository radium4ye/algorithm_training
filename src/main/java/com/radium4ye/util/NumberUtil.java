package com.radium4ye.util;

/**
 * 数字相关操作工具类
 * Created by Radium on 2017/12/13.
 */
public class NumberUtil {

    public static void main(String[] args) {
        System.out.println(atoi("-1B",13));
        System.out.println(atoi("+1B",13));
    }


    /**
     * ascii 转数字（十进制）
     * @param num   待转换的数字
     * @param d     进制
     * @return      转还好的数字
     */
    public static int atoi(String num,int d){
        int length = num.length();

        int result = 0 ;

        for (int i = length -1; i >= 0; i--) {
            char thisChar = num.charAt(i);
            int thisNum;
            if(thisChar >= '0' && thisChar <= '9'){
                thisNum = thisChar - '0';
            }else if(thisChar >= 'A' && thisChar <= 'E'){
                thisNum = 10 + thisChar - 'A';
            }else{
               throw new NumberFormatException("输入数字有误");
            }

            result += thisNum * Math.pow(d,length - i - 1);
        }

        return result;
    }
}
