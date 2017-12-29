package com.radium4ye.algorithm.other;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class HuffmanTreeTest {

    @Test
    public void main() throws Exception {
        char[] chars = new char[]{'a','b','c','d','e','f'};
        int[] weights = new int[]{45,13,12,16,9,5};
        HuffmanTree huffmanTree = new HuffmanTree(chars,weights);
        huffmanTree.print();
    }

}