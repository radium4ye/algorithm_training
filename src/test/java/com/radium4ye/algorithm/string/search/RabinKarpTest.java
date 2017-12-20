package com.radium4ye.algorithm.string.search;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class RabinKarpTest {
    @Test
    public void search() throws Exception {

        RabinKarp rabinKarp = new RabinKarp("123qwe");
        System.out.println(rabinKarp.search("1234567890123qwe"));
    }

}