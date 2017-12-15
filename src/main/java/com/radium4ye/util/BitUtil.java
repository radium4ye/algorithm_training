package com.radium4ye.util;

/**
 * 位操作相关工具类
 *
 * @author radium4ye
 */
public class BitUtil {

    /**
     * 交换数字两位的比特位
     *
     * @param changeNum    原始数字
     * @param bitPosition1 位置1
     * @param bitPosition2 位置2
     * @return 交换bit位后的数字
     */
    public static int exchangeBit(int changeNum, int bitPosition1, int bitPosition2) {

        //判断两位 数值是否相等
        if (((changeNum >> bitPosition1) & 1) != ((changeNum >> bitPosition2) & 1)) {
            int temp = (1 << bitPosition1) | (1 << bitPosition2);
            return changeNum ^ temp;
        } else {
            return changeNum;
        }

    }

    /**
     * 消除最低位的 1 的值
     *
     * @param changeNum 原始数字
     * @return 消除完1后的数字
     */
    public static int removeLowOne(int changeNum) {
        return changeNum & (changeNum - 1);
    }

    /**
     * 获取最低位的 1 的值
     *
     * @param changeNum 原始数字
     * @return 最低位为1的数字
     */
    public static int getLowOne(int changeNum) {
        return changeNum & ~(changeNum - 1);
    }

    /**
     * 已 2 为低数取对数
     *
     * @param num 参数
     * @return 对数
     */
    public static int log2(int num) {
        return log(num, 2);
    }

    /**
     * 已 2 为低数取对数
     *
     * @param num     参数
     * @param baseNum 底数
     * @return 对数
     */
    public static int log(int num, int baseNum) {
        return (int) Math.ceil(Math.log(num) / Math.log(baseNum));
    }

    /**
     * 将2个数字进行乘法运算
     */
    public static int multiply(int num1, int num2) {

        int temp = 0;
        int r = 1;
        while (r <= num2){
            //判断 num2 在 i 这位的数字是否为0
            if((num2 & r ) > 0){
                temp = add(temp,num1 << log2(r));
            }
            r = r << 1;
        }

        return temp;
    }

    /**
     * 将2个数字进行加法运算
     */
    public static int add(int num1, int num2) {
        if (num1 < 0 || num2 < 0) {
            throw new IllegalArgumentException("暂不支持负数");
        }

        if (num1 > (Integer.MAX_VALUE >> 2) || num2 > (Integer.MAX_VALUE >> 2)) {
            throw new IllegalArgumentException("超过计算最大值：" + (Integer.MAX_VALUE >> 2));
        }
        //取最大值
        int max = (num1 >= num2) ? num1 : num2;
        //表示第几位相乘
        int r = 1;

        //是否有进位
        boolean advance = false;
        int result = 0;

        //如果当前运行的位置比最大值小，说明运行没结果继续运算
        while (r <= max || advance) {
            int x = num1 & r;
            int y = num2 & r;

            //进行异或运算
            int b = x ^ y;

            if (b > 0) {
                //有进位 将结果置0 继续运算
                if (advance) {
                    b = 0;
                }
            } else {
                if (advance) {
                    b = r;
                }
                advance = x > 0;

            }

            result = result | b;
            r = r << 1;
        }


        return result;
    }
}
