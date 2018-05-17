package com.radium4ye.structure.list;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class DeLinkedListTest {
    @Test
    public void add() throws Exception {
        DeLinkedList<Integer> linkedList = new DeLinkedList<>();

        linkedList.add(5);
        linkedList.add(6);
        linkedList.add(3);
        linkedList.add(2);
        linkedList.add(0);
        linkedList.add(9);

        for (int i = 0; i < 10; i++) {
            System.out.println(linkedList.remove());
        }
    }

}