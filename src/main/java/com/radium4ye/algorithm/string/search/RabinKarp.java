package com.radium4ye.algorithm.string.search;

/**
 * Rabin-Karp 指纹字符串查找
 * <p>
 * 关键在计算下一个hash值的公式（i是下标）
 * H(X(i+1)) = ((H(Xi) + Ti * (Q-RM)))R +T(i+M)) mod Q
 * <p>
 * 计算相邻两个数的值
 * Xi = Ti * R^(M-1) + T(i+1) * R^(M-2) + ... + T(i+M-1) * R^0
 * X(i +1) = (Xi - Ti * R^(M-1))R + T(i+M)
 * <p>
 * 计算相邻两个数的值的 除以Q 取余
 * H(Xi) = Xi mod Q;
 *
 * @author radium4ye
 */
public class RabinKarp {
    /**
     * 字符集大小
     */
    private int r = 10;

    /**
     * 散列表数组大小
     * 一个很大的素数
     */
    private int q = 997;

    /**
     * 用于方便计算下一个hash值
     */
    private int rm = 1;

    /**
     * 模式字符串的hash值
     */
    private long patHash;

    /**
     * 模式字符串
     */
    private String pat;

    /**
     * 模式字符串长度
     */
    private int m;

    /**
     * 创建算法，初始化一些参数
     * @param pat   模式字符串
     */
    public RabinKarp(String pat) {
        this.pat = pat;
        m = pat.length();

        //运行 M-1 次 计算出RM
        for (int i = 1; i <= m - 1; i++) {
            rm = (r * rm) % q;
        }

        patHash = hash(pat, 0, m);
    }

    /**
     * 搜索目标字符串中 是否包含模式字符串
     * @param target    目标字符串
     * @return  {@code -1 不包含} 其他返回的是模式字符串在目标字符串的位置
     */
    public int search(String target) {

        //长度小于模式字符串长度
        if (target.length() < m) {
            return -1;
        }

        long targetHash = hash(target, 0, m);

        int i = 0;
        while (true) {

            //如果相等，进行进一步校验
            if (targetHash == patHash && check(target,i)) {
                return i;
            } else {

                //查询字符串结束了
                if(i + m >= target.length()){
                   break;
                }
                //计算下一个hash值
                targetHash = ((targetHash + target.charAt(i) * (q - rm)) * r + target.charAt(i + m)) % q;
                i++;
            }

        }

        return -1;
    }

    /**
     * 校验2个字符串是否相等
     * @param txt   目标字符串
     * @param i     位置
     * @return  {@code true 相等}
     */
    private boolean check(String txt, int i) {
        for (int j = 0; j < m; j++) {
            if (pat.charAt(j) != txt.charAt(i + j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 用于计算 M 长度的字符串的Hash值
     * 秦九韶算法 | Horner scheme
     *
     * @param key   字符串
     * @param start 开始位置
     * @param m     计算长度
     * @return hash值
     */
    private long hash(String key, int start, int m) {
        long h = 0;

        for (int i = start; i < m + start; i++) {
            h = (r * h + key.charAt(i)) % q;
        }

        return h;
    }
}
