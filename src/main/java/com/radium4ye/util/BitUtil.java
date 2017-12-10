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
     * @param num   参数
     * @return 对数
     */
    public static int log2(int num) {
        return log(num,2);
    }

    /**
     * 已 2 为低数取对数
     * @param num       参数
     * @param baseNum   底数
     * @return 对数
     */
    public static int log(int num,int baseNum) {
        return (int) Math.ceil(Math.log(num) / Math.log(baseNum));
    }
}
