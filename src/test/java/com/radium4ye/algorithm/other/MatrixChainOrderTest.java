package com.radium4ye.algorithm.other;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class MatrixChainOrderTest {
    MatrixChainOrder matrixChainOrder;

    @Before
    public void setUp() throws Exception {

        matrixChainOrder = new MatrixChainOrder(new int[][]{{30, 35}, {35, 15}, {15, 5}, {5, 10}, {10, 20}, {20, 25}});
    }

    @Test
    public void calc() throws Exception {
        assertEquals(matrixChainOrder.calc(0, 5),15125);
    }

}