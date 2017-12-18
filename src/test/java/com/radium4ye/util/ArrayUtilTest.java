package com.radium4ye.util;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class ArrayUtilTest {

    @Test
    public void changePosition() throws Exception {
        Integer[] array = new Integer[]{0,1,2,3,4,5};
        int[] newPosition = new int[]{3,4,1,0,5,2};

        //改变索引在的位置
        ArrayUtil.changePosition(array,newPosition);
        System.out.println(Arrays.toString(array));

        //恢复
        ArrayUtil.recoveryPosition(array,newPosition);
        System.out.println(Arrays.toString(array));
    }

}