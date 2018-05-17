package com.radium4ye.structure.tree;

import org.junit.Test;

/**
 * @author radium4ye
 */
public class ReaBlackTreeTest {

    private static final Object PRESENT = new Object();


    @Test
    public void put() throws Exception {
        ReaBlackTree reaBlackTree = new ReaBlackTree();
        reaBlackTree.put(5,PRESENT);
        reaBlackTree.put(4,PRESENT);
        reaBlackTree.put(6,PRESENT);
        reaBlackTree.put(2,PRESENT);
        reaBlackTree.put(3,PRESENT);

        reaBlackTree.delete(3);

    }

}