package com.radium4ye.algorithm.string.search;

import java.util.HashMap;
import java.util.Map;

/**
 * Knuth-Morris-pratt 算法
 * 通过确认有限状态机模拟
 *
 * @author radium4ye
 */
public class KMPByDFA {

    /**
     * 确认有限状态机
     */
    private Map<Character, int[]> dfa = new HashMap<>();

    /**
     * 模式字符串
     */
    private String pat;

    /**
     * 字符集大小
     */
    private int r = 256;

    public KMPByDFA(String pat) {
        this.pat = pat;

        for (int i = 0; i < r; i++) {
            char nowChar = (char) i;
            dfa.put(nowChar, new int[pat.length()]);

        }
        initDfa();
    }

    public int search(String target) {

        //设置初始位置 以及 初始状态
        int index = 0;
        int status = 0;

        while (index < target.length()) {

            char nowChar = target.charAt(index);
            status = dfa.get(nowChar)[status];

            //如果状态带到了最终值
            if (status == pat.length()) {
                return index + 1 - pat.length();
            }
            index++;
        }


        return -1;
    }

    /**
     * 初始化状态机
     */
    private void initDfa() {

        for (int i = 0; i < pat.length(); i++) {
            for (int j = 0; j < r; j++) {
                char nowChar = (char) j;
                String temp = pat.substring(0, i) + nowChar;
                int result = calcPre(temp);
                dfa.get(nowChar)[i] = result;
            }
        }
    }

    /**
     * 用于计算目标字符串在模式字符串前缀的位数
     *
     * @param targetStr
     * @return
     */
    private int calcPre(String targetStr) {
        int result = targetStr.length();
        while (result > 0) {
            if (pat.startsWith(targetStr)) {
                return result;
            } else {
                targetStr = targetStr.substring(1);
                result--;
            }
        }
        return result;
    }

}
