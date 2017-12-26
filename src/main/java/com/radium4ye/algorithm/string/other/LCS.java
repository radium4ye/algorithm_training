package com.radium4ye.algorithm.string.other;

/**
 * 最长公共子序问题
 *
 * @author radium4ye
 */
public class LCS {

    private final String x;
    private final String y;
    private String[][] result;

    public LCS(String x, String y) {
        this.x = x;
        this.y = y;
        result = new String[x.length() + 1][y.length() + 1];
    }

    public String lcs() {
        return lcs(x, x.length(), y, y.length());
    }

    private String lcs(String x, int xEnd, String y, int yEnd) {

        if (xEnd == 0 || yEnd == 0) {
            return "";
        }

        if (result[xEnd][yEnd] != null) {
            return result[xEnd][yEnd];
        }

        String select = null;

        //1个字的字符串
        if (xEnd == 1) {
            String strX = x.substring(0, 1);
            if (y.indexOf(strX) < yEnd) {
                select = strX;
            } else {
                select = "";
            }
        }

        if (yEnd == 1) {
            String strY = y.substring(0, 1);
            if (x.substring(0, xEnd).contains(strY)) {
                select = strY;
            } else {
                select = "";
            }
        }

        if (select == null) {
            char charX = x.charAt(xEnd - 1);
            char charY = y.charAt(yEnd - 1);

            //如果相同
            if (charY == charX) {
                select = lcs(x, xEnd - 1, y, yEnd - 1) + charX;
            } else {

                //不相等，选择较大的字符串
                String lcsX = lcs(x, xEnd, y, yEnd - 1);
                String lcsY = lcs(x, xEnd - 1, y, yEnd);

                select = lcsX.length() > lcsY.length() ? lcsX : lcsY;
            }
        }

        //做备忘
        result[xEnd][yEnd] = select;

        return select;
    }
}
