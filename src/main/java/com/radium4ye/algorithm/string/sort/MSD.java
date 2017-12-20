package com.radium4ye.algorithm.string.sort;

/**
 * 高位优先字符排序
 *
 * 算法的实现注意：
 * 1.小型子数组问题，如果在在这个算法中不做特殊处理，最后会生成 s 个（需要排序的数组长度）长度为 R (字符集大小)的数组，对空间的消耗。
 * 比如在对小于 M 个字符排序的时候需要做排序算法切换。
 * 2.相同字符，如果有很多个相同的支付，就无法触发算法切换，从而导致对字符进行多次分配空间，计算频率，在普通场景很常见的问题。
 * 3.额外空间消耗，在这里会创建一个 N 长度的 aux（辅助数组），一个每一次循环都要创建 M 长度的计算频率的数组
 *
 * 时间复杂度取决与输入数组 亚线性时间 ~ 线性时间
 * @author radium4ye
 */
public class MSD {

    /**
     * 字符集的大小
     */
    public static final int R = 256;
    /**
     * 切换算法的阈值
     */
    public static final int M = 0;

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
        if (hi - M < lo) {
            //TODO 待实现
        }

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
     */
    public static int charAt(String s, int d) {
        return s.length() > d ? s.charAt(d) : 0;
    }

}
