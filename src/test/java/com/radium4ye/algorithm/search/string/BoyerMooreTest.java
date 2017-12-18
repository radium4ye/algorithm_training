package com.radium4ye.algorithm.search.string;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class BoyerMooreTest {
    @Test
    public void match() throws Exception {
        String target = "NDSjasdsAnNEDASXCASDQ";
        String pattern = "ASXC";
        assertEquals(BoyerMoore.match(target,pattern),13);
        assertEquals(BoyerMoore.match(target,"AAA"),-1);
        assertEquals(BoyerMoore.match(pattern,target),-1);
    }

    private String target;
    private String pattern;
    @Before
    public void setup() throws Exception {
        pattern = getRandomStr(100);
        target = getRandomStr(100000000);
    }

    @Test
    public void compare() throws Exception {
        long l1 = System.currentTimeMillis();
        System.out.println(BoyerMoore.match(target,pattern));
        long l2 = System.currentTimeMillis();
        System.out.println("BoyerMoore:time:" + (l2 - l1));

        long l3 = System.currentTimeMillis();
        System.out.println(target.indexOf(pattern));
        long l4 = System.currentTimeMillis();
        System.out.println("origin:time:" + (l4 - l3));
    }


    final static String CHARS = "abcdegjhijklmnopqrsquvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    /**
     * 获得一定长度的随机字符串
     * @param len
     * @return
     */
    public static String getRandomStr(int len){
        StringBuffer sb = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < len; i++) {
            sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return sb.toString();
    }
}