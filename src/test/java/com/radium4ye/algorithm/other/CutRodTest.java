package com.radium4ye.algorithm.other;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class CutRodTest {
    @Test
    public void calc() throws Exception {
        CutRod cutRod = new CutRod();
        for (int i = 0; i < 10000; i++) {
            System.out.println(cutRod.calc(i));
        }
//        cutRod.calc(5490) ;
//        cutRod.calc(10000) ;
    }

}