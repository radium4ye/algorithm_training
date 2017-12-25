package com.radium4ye.algorithm.string;

import com.radium4ye.algorithm.string.other.RLE;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class RLETest {
    @Test
    public void decode() throws Exception {
        assertEquals(RLE.decode("5a5b4x"),"aaaaabbbbbxxxx");
    }

    @Test
    public void encode() throws Exception {

        assertEquals(RLE.encode("aaaaabbbbbxxxx"),"5a5b4x");
    }

}