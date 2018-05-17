package com.radium4ye.algorithm.other;

/**
 * 活动选择 贪心算法
 *
 * @author radium4ye
 */
public class ActivitySelectGreedy {

    private final int[] start;
    private final int[] end;
    private final int allCount;

    /**
     * @param start 活动开始时间
     * @param end   结束时间
     */
    public ActivitySelectGreedy(int[] start, int[] end, int startTime, int endTime) {
        this.start = start;
        this.end = end;
        //检查参数
        if (start == null || end == null || start.length != end.length) {
            throw new IllegalArgumentException("参数错误");
        }

        //活动总数
        allCount = start.length;

        System.out.println(select(startTime, endTime));
    }

    private int select(int startTime, int endTime) {

        int minEnd = -1;


        for (int i = 0; i < allCount; i++) {

            //不符合时间的活动
            if (start[i] < startTime || end[i] > endTime) {
                continue;
            }

            if (minEnd == -1) {
                minEnd = end[i];
            } else {
                minEnd = Math.min(minEnd, end[i]);
            }
        }

        //找不到有效的活动
        if (minEnd == -1) {
            return 0;
        }

        return 1 + select(minEnd, endTime);
    }

}
