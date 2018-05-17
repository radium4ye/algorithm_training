package com.radium4ye.algorithm.other;

/**
 * 活动选择 动态规划实现
 *
 * @author radium4ye
 */
public class ActivitySelectDynamic {

    private final int[] start;
    private final int[] end;
    private final int allCount;
    public Integer[][] activityCount;

    /**
     * @param start 活动开始时间
     * @param end   结束时间
     */
    public ActivitySelectDynamic(int[] start, int[] end, int startTime, int endTime) {
        this.start = start;
        this.end = end;
        //检查参数
        if (start == null || end == null || start.length != end.length) {
            throw new IllegalArgumentException("参数错误");
        }

        //活动总数
        allCount = start.length;

        activityCount = new Integer[endTime + 1][endTime+ 1];

        System.out.println(select(startTime,endTime));
    }

    /**
     * @param startTime 开始
     * @param endTime   结束时间
     * @return
     */
    private Integer select(int startTime, int endTime) {
        Integer count;
        if ((count = activityCount[startTime][endTime]) != null) {
            return count;
        }

        count = 0 ;

        for (int i = 0; i < allCount; i++) {

            //不符合时间的活动
            if(start[i] < startTime || end[i] > endTime){
                continue;
            }

            count = Math.max(count,
                    select(startTime,start[i]) +select(end[i],endTime) + 1);

        }

        return count;
    }
}
