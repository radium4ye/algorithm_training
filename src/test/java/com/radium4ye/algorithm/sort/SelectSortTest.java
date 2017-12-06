package com.radium4ye.algorithm.sort;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class SelectSortTest {


    @Test
    public void sort() throws Exception {
        Integer[] arrays = {6, 8, 2, 3, 5, 7, 1, 9, 4};
        assertArrayEquals(SelectSort.sort(arrays), new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1});
        assertArrayEquals(SelectSort.sort(arrays, false), new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});

    }

}