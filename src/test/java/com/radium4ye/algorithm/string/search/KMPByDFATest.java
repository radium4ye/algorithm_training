package com.radium4ye.algorithm.string.search;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class KMPByDFATest {

    @Test
    public void search() throws Exception {

        String pattern = "ababcba";
        KMPByDFA dfa = new KMPByDFA(pattern);
        System.out.println(dfa.search("asdfcvasrwefsdfrgreafaaaaababcbaaaa"));
    }

}