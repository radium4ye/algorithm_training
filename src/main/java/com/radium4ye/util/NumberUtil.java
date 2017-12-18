package com.radium4ye.util;

/**
 * 数字相关操作工具类
 *
 * @author radium4ye
 */
public class NumberUtil {

    public static final char ZERO = '0';
    public static final String ADD = "+";
    public static final String REDUCE = "-";

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

    /**
     * 进行 String 的整形乘法运算
     */
    public static String stringMultiply(String x, String y) {
        if (x == null || y == null) {
            return "";
        }
        //标记计算后的符号位置
        boolean symbol = true;

        {
            //去除符号
            while (x.startsWith(ADD)){
                x = x.substring(1);
            }
            while (x.startsWith(REDUCE)){
                x = x.substring(1);
                symbol = !symbol;
            }

            while (y.startsWith(ADD)){
                y = y.substring(1);
            }
            while (y.startsWith(REDUCE)){
                y = y.substring(1);
                symbol = !symbol;
            }
        }

        //进行补位
        int subtractResult = x.length() - y.length();
        if (subtractResult < 0) {
            StringBuilder xBuilder = new StringBuilder(x);
            for (int i = 0; i < -subtractResult; i++) {
                xBuilder.insert(0, "0");
            }
            x = xBuilder.toString();
        }

        if (subtractResult > 0) {
            StringBuilder builder = new StringBuilder(y);
            for (int i = 0; i < subtractResult; i++) {
                builder.insert(0, "0");
            }
            y = builder.toString();
        }

        String result = mapMultiply(x, y);
        StringBuilder sb = new StringBuilder(result);

        //首部去除多余的0
        while (sb.charAt(0) == ZERO && sb.length() > 1){
            sb = sb.deleteCharAt(0);
        }

        //添加符号
        if(!symbol){
            sb = sb.insert(0,"-");
        }

        return sb.toString();
    }

    /**
     * 字符相乘  拆分成一个个小乘法
     */
    private static String mapMultiply(String x, String y) {
        if (x.length() == 1 && y.length() == 1) {
            return String.valueOf((x.charAt(0) - '0') * (y.charAt(0) - '0'));
        }

        //进行划分成小问题
        int xlen = x.length() / 2;
        int ylen = y.length() / 2;

        String xh = x.substring(0, xlen);
        String xl = x.substring(xlen);
        String yh = y.substring(0, ylen);
        String yl = y.substring(ylen);

        if("".equals(xh)){
            xh = "0";
        }
        if("".equals(yh)){
            yh = "0";
        }

        StringBuilder xhyh = new StringBuilder(mapMultiply(xh, yh));
        for (int i = 0; i < (x.length() - xlen) + (y.length() - ylen); i++) {
            xhyh.append("0");
        }

        StringBuilder xhyl = new StringBuilder(mapMultiply(xh, yl));
        for (int i = 0; i < (x.length() - xlen); i++) {
            xhyl.append("0");
        }

        StringBuilder xlyh = new StringBuilder(mapMultiply(xl, yh));
        for (int i = 0; i < (y.length() - ylen); i++) {
            xlyh.append("0");
        }

        String xlyl = mapMultiply(xl, yl);
        //进行累加

        String result = stringAdd(stringAdd(stringAdd(xhyh.toString(), xhyl.toString()), xlyh.toString()), xlyl);

        return result;
    }

    /**
     * 字符串加法
     */
    public static String stringAdd(String x, String y) {
        //是否有进位
        int advance = 0;
        //现在累加的位置，低位开始
        int position = 0;

        int maxLen = Math.max(x.length(), y.length());

        StringBuilder result = new StringBuilder();
        StringBuilder xReverse = new StringBuilder(x).reverse();
        StringBuilder yReverse = new StringBuilder(y).reverse();
        //如有没加完，或者有进位
        while (position < maxLen || advance > 0) {
            int xp = 0;
            if (x.length() > position) {
                xp = xReverse.charAt(position) - '0';
            }

            int yp = 0;
            if (y.length() > position) {
                yp = yReverse.charAt(position) - '0';
            }

            int xpyp = yp + xp + advance;
            if (xpyp >= 10) {
                xpyp = xpyp - 10;
                advance = 1;
            }else {
                advance = 0;
            }
            result.insert(0, xpyp);
            position ++;
        }

        return result.toString();
    }
}
