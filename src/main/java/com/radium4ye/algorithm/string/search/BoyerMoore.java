package com.radium4ye.algorithm.string.search;

/**
 * BM 字符匹配算法
 *
 * @author radium4ye
 */
public class BoyerMoore {

    /**
     * 进行匹配 模式字符串在目标字符串所在的位置
     *
     * @param target  目标字符串
     * @param pattern 模式字符串
     * @return 返回的索引值 {@code -1 查不到}
     */
    public static int match(String target, String pattern) {
        return match(0, target, pattern);
    }

    /**
     * 进行匹配 模式字符串在目标字符串所在的位置
     *
     * @param start   起始位置
     * @param target  目标字符串
     * @param pattern 模式字符串
     * @return 返回的索引值 {@code -1 查不到}
     */
    public static int match(int start, String target, String pattern) {
        //计算每个字符在目标字符串中最右边的值 用于对不匹配字符进行启发式处理
        int[] right = new int[256];
        for (char i = 0; i < right.length; i++) {
            right[i] = -1;
        }
        int point = pattern.length();
        while (point > 0) {
            char patternChar = pattern.charAt(--point);
            if (point > right[patternChar]) {
                right[patternChar] = point;
            }
        }

        //目标字符的位置
        int targetPoint = start;
        //模式字符的位置，从右边开始
        int patternPoint = pattern.length() - 1;

        while (patternPoint >= 0 && (targetPoint + patternPoint) < target.length()) {
            int targetChar = target.charAt(targetPoint + patternPoint);
            int patternChar = pattern.charAt(patternPoint);

            //当个字符进行匹配
            if (targetChar == patternChar) {
                // 如果匹配 往前匹配，直到匹配结束
                patternPoint--;
            } else {
                // 不匹配  对字符串进行移动
                if (right[targetChar] < patternPoint) {
                    targetPoint = targetPoint + patternPoint - right[targetChar];
                } else {
                    targetPoint++;
                }
                patternPoint = pattern.length() - 1;
            }

        }

        //返回结果
        if (patternPoint < 0) {
            return targetPoint;
        } else {
            return -1;
        }
    }
}
