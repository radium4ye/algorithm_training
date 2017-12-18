package com.radium4ye.util;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class NumberUtilTest {
    @Test
    public void stringAdd() throws Exception {
        Random random = new Random();
        for (int i = 0; i < 1000000; i++) {
            int random1 = random.nextInt(Integer.MAX_VALUE >> 1);
            int random2 = random.nextInt(Integer.MAX_VALUE >> 1);
            assertEquals(NumberUtil.stringAdd(String.valueOf(random1), String.valueOf(random2)), String.valueOf(random1 + random2));
        }
    }


    @Test
    public void stringMultiply() throws Exception {
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            int random1 = random.nextInt(10000);
            int random2 = random.nextInt(10000);
            assertEquals(NumberUtil.stringMultiply(String.valueOf(random1), String.valueOf(random2)), String.valueOf(random1 * random2));
        }
    }


    @Test
    public void atoi() throws Exception {

        assertEquals(NumberUtil.atoi(" +1B", 13), 24);
        assertEquals(NumberUtil.atoi("-1B", 14), -25);
        assertEquals(NumberUtil.atoi("-ZZ", 36), -1295);

    }

    @Test
    public void itoa() throws Exception {
        assertEquals(NumberUtil.itoa(24, 13), "1B");
        assertEquals(NumberUtil.itoa(-24, 13), "-1B");
        assertEquals(NumberUtil.itoa(-1295, 36), "-ZZ");
    }

}