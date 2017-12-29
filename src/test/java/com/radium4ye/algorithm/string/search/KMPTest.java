package com.radium4ye.algorithm.string.search;

import org.junit.Before;
import org.junit.Test;

/**
 * @author radium4ye
 */
public class KMPTest {
    static String base = "!111111asassasaas,111111asassasaas,abcbcdaabcbcda";
    static String pattern = "abcbcdaabcbcdaabcbcdaabcbcda";
//    static String base = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//    static String pattern = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
//    static String base = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//    static String pattern = "aaaaaaaavvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv";
    static boolean sign = true;

    @Before
    public void setUp() {
        if (sign) {
            StringBuilder stringBuilder = new StringBuilder(base);
            for (int i = 0; i < 22; i++) {
                stringBuilder = stringBuilder.append(stringBuilder);
            }
            stringBuilder.append(pattern);
            base = stringBuilder.toString();
            sign = false;
        }
    }


    @Test
    public void kmp() {
        long l1 = System.currentTimeMillis();
        KMP kmp = new KMP(pattern);
        kmp.match(base);
        long l2 = System.currentTimeMillis();
        System.out.print("kmp:");
        System.out.println(l2 - l1);
    }

//    @Test
//    public void kmpDFA() {
//        long l1 = System.currentTimeMillis();
//        KMPByDFA kmp = new KMPByDFA(pattern);
//        kmp.search(base);
//        long l2 = System.currentTimeMillis();
//        System.out.print("kmpDFA:");
//        System.out.println(l2-l1);
//    }

    @Test
    public void normal() {
        long l1 = System.currentTimeMillis();
        base.indexOf(pattern);
        long l2 = System.currentTimeMillis();
        System.out.print("normal:");
        System.out.println(l2 - l1);
    }

    @Test
    public void bm() {
        long l1 = System.currentTimeMillis();
        BoyerMoore.match(base, pattern);
        long l2 = System.currentTimeMillis();
        System.out.print("bm:");
        System.out.println(l2 - l1);
    }


//    @Test
//    public void rk() {
//        long l1 = System.currentTimeMillis();
//        RabinKarp rabinKarp = new RabinKarp("eeeee");
//        System.out.println(rabinKarp.search(base));
//        long l2 = System.currentTimeMillis();
//        System.out.print("rk:");
//        System.out.println(l2-l1);
//    }

}