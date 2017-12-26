package com.radium4ye.algorithm.string.other;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class LCSTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void lcs() throws Exception {
        LCS lcs= new LCS("ACCGGTCGAGTGCGCGGAAGCCGGCGAA","GTCGTTCGGAATGCCGTTGCTCTGTAAA");
        System.out.println(lcs.lcs());
    }

}