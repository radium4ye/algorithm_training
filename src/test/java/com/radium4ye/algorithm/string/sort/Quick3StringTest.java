package com.radium4ye.algorithm.string.sort;

import com.radium4ye.algorithm.string.sort.Quick3String;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author radium4ye
 */
public class Quick3StringTest {
    @Test
    public void sort() throws Exception {
        String[] a = {
                "bn.1",
                "bn.3",
                "bn.32",
                "bn.4",
                "com.1",
                "com.2.1",
                "com.2.2",
                "com.4",
                "edu.1",
                "edu.2",
                "edu.4",
                "edu.444"
        };

        String[] c = new String[a.length];
        Stream.of(a).collect(Collectors.toList()).toArray(c);
        Quick3String.sort(c);

        assertArrayEquals(c,a);
    }


}