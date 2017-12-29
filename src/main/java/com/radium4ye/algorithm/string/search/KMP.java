package com.radium4ye.algorithm.string.search;

/**
 * @author radium4ye
 */
public class KMP {

    /**
     * 模式字符串
     */
    private String pat;

    /**
     * 字符集大小
     */
    private int r = 256;

    public KMP(String pat) {
        this.pat = pat;
        pi = new int[pat.length() + 1];
        for (int i = 0; i <= pat.length(); i++) {
            pi[i] = -1;
        }
        pi[1] = 0;
        getLongestSuffix(pat.length());
    }

    /**
     * 进行字符串匹配
     *
     * @param target
     * @return
     */
    public int match(String target) {
        int tLen = target.length();
        int pLen = pat.length();
        int pNum = 0;

        //对整个目标字符串进行匹配
        for (int tNum = 0; tNum < tLen; tNum++) {

            //如果匹配失败，而且 pNum > 0  就将指针移动到 pNum长度最长子串位置 再继续进行匹配
            boolean sign = pat.charAt(pNum) == target.charAt(tNum);
            while (pNum > 0 && (!sign)) {
                pNum = pi[pNum];
            }

            if (sign) {
                pNum++;
            }

            //匹配到最后一个字符了
            if (pNum == pLen) {
                return tNum - pLen + 1;
            }
        }
        return -1;
    }

    private int[] pi;

    /**
     * 获取模式字符串 前s个字符中 最长后缀
     * 且该后缀也是这个字符串的前缀（最长子串）
     * 时间复杂度为O(m) 模式字符串的长度
     *
     * @param s 元素个数
     * @return 最长子串位数
     */
    private int getLongestSuffix(int s) {
        if (s <= 0 || s > pat.length()) {
            throw new IllegalArgumentException("非法索引");
        }

        if (pi[s] == -1) {
            //如果没值，就先设置成0，并获取上一个节点的值
            pi[s] = 0;
            int k = getLongestSuffix(s - 1);

            do {
                //比较最后一个字符 和 当前字符是否相等
                if (pat.charAt(k) == pat.charAt(s - 1)) {
                    pi[s] = k + 1;
                    return pi[s];
                }

                if (k > 0) {
                    k = getLongestSuffix(k);
                }
            } while (k > 0);

        }

        return pi[s];
    }
}
