package com.radium4ye.algorithm.search.string;

import org.junit.Test;

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

}