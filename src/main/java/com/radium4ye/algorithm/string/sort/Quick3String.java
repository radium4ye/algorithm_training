package com.radium4ye.algorithm.string.sort;

import static com.radium4ye.algorithm.sort.Example.exchange;

/**
 * 三向字符串快排 - 三向快排的字符串版本，由整个键比较转化成了高位字符比较
 * 结合快排和高位字符优先排序算法，更好了处理相同字符或者有公共前缀的字符情况
 *
 * @author radium4ye
 */
public class Quick3String {


    /**
     * 三向字符串快排
     *
     * @param strings 待排序字符串
     * @return 排好序字符串
     */
    public static String[] sort(String[] strings) {
        return sort(strings, 0, strings.length - 1, 0);
    }

    /**
     * 进行排序
     *
     * @param strings 排序字符串
     * @param lo      开始排序位置
     * @param hi      结束位置
     * @param d       字符位置
     */
    public static String[] sort(String[] strings, int lo, int hi, int d) {

        //如果只有一个元素就不需要进行排序
        if (hi <= lo) {
            return strings;
        }

        int lt = lo, gt = hi, i = lt;

        //获取标准比较字符
        char standardChar = charAt(strings[lo], d);

        //将数组分割成 <char | =char | >char 3部分
        while (i <= gt) {
            int result = Character.compare(standardChar, charAt(strings[i], d));

            if (result < 0) {
                exchange(strings, i, gt--);
            } else if (result > 0) {
                exchange(strings, i++, lt++);
            } else {
                i++;
            }
        }

        //分成3部分进行递归排序
        sort(strings, lt, gt, d + 1);
        if (lt - 1 > lo) {
            sort(strings, lo, lt - 1, d);
        }
        if (hi > gt) {
            sort(strings, i, hi, d);
        }

        return strings;
    }


    /**
     * 获取 s 字符串在 d 位置的字符
     */
    public static char charAt(String s, int d) {
        return s.length() > d ? s.charAt(d) : 0;
    }

}
