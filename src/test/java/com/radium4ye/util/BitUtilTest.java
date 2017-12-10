package com.radium4ye.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class BitUtilTest {

    @Test
    public void exchangeBit() throws Exception {
        int temp = 0b1011;
        int after = BitUtil.exchangeBit(temp, 1, 2);
        assertSame(after, 0b1101);

    }

    @Test
    public void removeLowOne() throws Exception {
        int temp = 0b1100;
        int after = BitUtil.removeLowOne(temp);
        assertSame(after, 0b1000);
    }

    @Test
    public void getLowOne() throws Exception {
        int temp = 0b1100;
        int after = BitUtil.getLowOne(temp);
        assertSame(after, 0b0100);
        assertSame(BitUtil.log2(after), 2);
    }

    /**
     * 计算二进制数中 1 的个数
     * 时间复杂度 由 n -> c (1的个数)
     */
    @Test
    public void calcOneNum() {
        int num = 0b11101011;
        int count = 0;

        while (num > 0) {
            count++;
            num = BitUtil.removeLowOne(num);
        }

        assertSame(count, 6);
    }

    @Test
    public void test() {
        int a = 1;
        int b = 12;
        b ^= a ^= b;
        a ^= b;
        System.out.println(a);
        System.out.println(b);

    }

}