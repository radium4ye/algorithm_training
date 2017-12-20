package com.radium4ye.algorithm.string;

/**
 * Run-length encoding
 * 游程编码
 *
 * @author radium4ye
 */
public class RLE {

    /**
     * 编码
     *
     * @param str 待编码的字符
     * @return 编码后的字符
     */
    public static String encode(String str) {

        StringBuilder result = new StringBuilder();

        //定义好当前的字符 以及出现的次数
        int count = 1;
        char select = str.charAt(0);

        for (int i = 1; i < str.length(); i++) {

            char now = str.charAt(i);

            //如果包含数字，待编码的字符有问题
            if (now >= '0' && now <= '9') {
                throw new IllegalArgumentException("编码字符不允许出现数字");
            }

            //如果相等
            if (now == select) {
                count++;
            } else {
                result.append(count);
                result.append(select);

                count = 1;
                select = now;
            }

        }

        result.append(count);
        result.append(select);

        return result.toString();
    }

    /**
     * 游程解码
     *
     * @param str 待解码的字符
     * @return 解码后的结果
     */
    public static String decode(String str) {

        StringBuilder result = new StringBuilder();

        //将传入的字符，分割成数字和字符两部分
        String[] symbol = str.split("\\d+");
        String[] nums = str.split("\\D+");

        if (symbol.length - 1 != nums.length) {
            throw new IllegalArgumentException("传入的字符有误");
        }

        for (int i = 0; i < nums.length; i++) {
            int num = Integer.valueOf(nums[i]);

            for (int j = 0; j < num; j++) {
                result.append(symbol[i + 1]);
            }
        }

        return result.toString();
    }
}
