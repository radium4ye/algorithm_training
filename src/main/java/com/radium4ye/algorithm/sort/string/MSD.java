package com.radium4ye.algorithm.sort.string;

/**
 * 高位优先字符排序
 *
 * @author radium4ye
 */
public class MSD {

    public static final int R = 256;

    /**
     * 目前限定的单字节的支付，暂支持 ASCII 表中的字符
     *
     * @param strings 需要排序的字符
     * @return 排好序的字符
     */
    public static String[] sort(String[] strings) {

        String[] aux = new String[strings.length];
        sort(strings, 0, strings.length, 0, aux);
        return strings;
    }

    /**
     * 对某段字符进行排序
     *
     * @param strings 需要排序的字符
     * @param lo      开始排序的位置
     * @param hi      结束位置
     * @param d       按照字符串的第几个字符为键进行排序
     * @param aux     可复用的辅助数组
     */
    private static void sort(String[] strings, int lo, int hi, int d, String[] aux) {

        //只有一个字符无需排序
        if (hi - lo <= 1) {
            return;
        }
        //小数组可以切换排序方式

        int[] count = new int[R + 1];

        //计算频率
        for (int i = lo; i < hi; i++) {
            String s = strings[i];
            int index = charAt(s, d);
            count[index + 1]++;
        }

        //转换索引
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        //归类字符，将字符填充上部计算出索引所在的位置
        for (int i = lo; i < hi; i++) {
            aux[lo + count[charAt(strings[i], d)]++] = strings[i];
        }

        //回写
        for (int i = lo; i < hi; i++) {
            strings[i] = aux[i];
        }

        //已每个字符进行递归
        for (int i = 0; i < R; i++) {
            sort(strings, lo + count[i], lo + count[i + 1], d + 1, aux);
        }

    }

    /**
     * 获取 s 字符串在 d 位置的字符
     *
     * @param s
     * @param d
     * @return
     */
    public static int charAt(String s, int d) {
        return s.length() > d ? s.charAt(d) : 0;
    }

}
