package com.radium4ye.structure.tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class BinaryTreeTest {

    BinaryTree<Integer> tree = new BinaryTree<>();
    @Before
    public void setUp() throws Exception {
//        tree.add(6);
//        tree.add(4);
//        tree.add(9);
//        tree.add(2);
//        tree.add(5);
//        tree.add(7);
//        tree.add(10);
//        tree.add(1);
//        tree.add(3);
//        tree.add(8);

        tree.add(4);
        tree.add(2);
        tree.add(6);
        tree.add(1);
        tree.add(3);
        tree.add(5);
        tree.add(9);
        tree.add(7);
        tree.add(8);
    }

    @Test
    public void printMorris() throws Exception {
        tree.printMorris();
    }

    @Test
    public void delete() throws Exception {
        tree.delete(6);
    }

}