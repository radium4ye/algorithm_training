package com.radium4ye.algorithm.string.sort;

import com.radium4ye.algorithm.string.sort.MSD;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class MSDTest {


    @Test
    public void sort() throws Exception {
        String[] a = {"B1", "C1", "B", "A1", "C3", "C2"};
        MSD.sort(a);

        assertArrayEquals(a, new String[]{"A1", "B", "B1", "C1", "C2", "C3"});
    }

}