package com.radium4ye.algorithm.other;

import com.radium4ye.algorithm.other.ActivitySelectDynamic;
import com.radium4ye.algorithm.other.ActivitySelectGreedy;
import org.junit.Test;

/**
 * @author radium4ye
 */
public class ActivitySelectTest {
    @Test
    public void select() throws Exception {
        int[] start = {1,3,0,5,3,5,6 ,8 ,8 ,2 ,12};
        int[] end =   {4,5,6,7,9,9,10,11,12,14,16};

        ActivitySelectDynamic select = new ActivitySelectDynamic(start,end,0,16);

        ActivitySelectGreedy greedy = new ActivitySelectGreedy(start,end,0,16);
    }

}