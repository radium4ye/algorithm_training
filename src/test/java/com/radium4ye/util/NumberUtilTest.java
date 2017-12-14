package com.radium4ye.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class NumberUtilTest {


    @Test
    public void atoi() throws Exception {

        assertEquals(NumberUtil.atoi(" +1B", 13),24);
        assertEquals(NumberUtil.atoi("-1B", 14),-25);
        assertEquals(NumberUtil.atoi("-ZZ", 36),-1295);

    }

    @Test
    public void itoa() throws Exception {
        assertEquals(NumberUtil.itoa(24, 13),"1B");
        assertEquals(NumberUtil.itoa(-24, 13),"-1B");
        assertEquals(NumberUtil.itoa(-1295, 36),"-ZZ");
    }

}