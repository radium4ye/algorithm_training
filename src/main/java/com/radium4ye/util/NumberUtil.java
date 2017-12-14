package com.radium4ye.util;

/**
 * 数字相关操作工具类
 *
 * @author radium4ye
 */
public class NumberUtil {

    /**
     * ascii 转数字（十进制）
     *
     * @param num   待转换的数字
     * @param radix 进制
     * @return 转还好的数字
     */
    public static int atoi(String num, int radix) {

        if (radix < Character.MIN_RADIX) {
            throw new NumberFormatException("radix " + radix + " less than Character.MIN_RADIX");
        }

        if (radix > Character.MAX_RADIX) {
            throw new NumberFormatException("radix " + radix + " greater than Character.MAX_RADIX");
        }

        //去除空格
        num = num.trim();

        int length = num.length();
        int result = 0;

        //从低位开始逐位处理
        for (int i = length - 1; i >= 0; i--) {
            char thisChar = num.charAt(i);
            int thisNum = 0;

            //符号判断
            if (thisChar == '-' && i == 0) {
                result *= -1;
            } else if (thisChar == '+' && i == 0) {
                //不做处理
            } else {
                thisNum = digit(thisChar, radix);
                if (thisNum < 0) {
                    throw new NumberFormatException("字符'" + thisChar + "' 为非法字符");
                }
            }


            result += thisNum * Math.pow(radix, length - i - 1);
        }

        return result;
    }

    /**
     * 将整形数字 转换层 ascii
     *
     * @param num   待转换的数字
     * @param radix 进制
     * @return
     */
    public static String itoa(int num, int radix) {

        if (radix < Character.MIN_RADIX) {
            throw new NumberFormatException("radix " + radix + " less than Character.MIN_RADIX");
        }

        if (radix > Character.MAX_RADIX) {
            throw new NumberFormatException("radix " + radix + " greater than Character.MAX_RADIX");
        }

        int temp = Math.abs(num);
        StringBuilder result = new StringBuilder();

        //转换数字
        do {
            int mod = temp % radix;
            temp = temp / radix;
            result.insert(0, character(mod, radix));

        } while (temp != 0);

        //添加符号
        if (num > 0) {
            return result.toString();
        } else {
            return result.insert(0, "-").toString();
        }

    }

    /**
     * 将 char 转换层相应数字
     *
     * @param codePoint 待转化的字符
     * @param radix     转化的进制
     * @return {@code -1 转化出错}整形数字
     */
    public static int digit(char codePoint, int radix) {

        if (radix < Character.MIN_RADIX) {
            throw new NumberFormatException("radix " + radix + " less than Character.MIN_RADIX");
        }

        if (radix > Character.MAX_RADIX) {
            throw new NumberFormatException("radix " + radix + " greater than Character.MAX_RADIX");
        }

        //进行数字转换
        int thisNum = -1;
        if (codePoint >= '0' && codePoint <= '9') {
            thisNum = codePoint - '0';
        } else if (codePoint >= 'A' && codePoint <= 'Z') {
            thisNum = 10 + codePoint - 'A';
        }

        //判断范围
        if (thisNum >= radix) {
            thisNum = -1;
        }
        return thisNum;
    }

    /**
     * 将十进制的数转化成字符
     *
     * @param num   待转化ed数字
     * @param radix 进制
     * @return 字符
     */
    public static char character(int num, int radix) {
        if (radix < Character.MIN_RADIX) {
            throw new NumberFormatException("radix " + radix + " less than Character.MIN_RADIX");
        }

        if (radix > Character.MAX_RADIX) {
            throw new NumberFormatException("radix " + radix + " greater than Character.MAX_RADIX");
        }

        if (num >= 0 && num <= 9) {
            return (char) (num + '0');
        } else if (num >= 10 && num <= 35) {
            return (char) (num - 10 + 'A');
        } else {
            throw new NumberFormatException("数字'" + num + "' 超出基数'" + radix + "'");
        }

    }
}
