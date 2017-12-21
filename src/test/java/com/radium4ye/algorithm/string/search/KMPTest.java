package com.radium4ye.algorithm.string.search;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class KMPTest {

    @Test
    public void test(){
        KMP kmp = new KMP("ababaca");
        assertEquals(kmp.match("aaaaabbbbbababaca"),10);
    }

}