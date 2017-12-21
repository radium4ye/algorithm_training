package com.radium4ye.structure.list;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class LinkedListTest {

    @Test
    public void test(){
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < 5; i++) {
            linkedList.add(i);
        }

        linkedList.reverse();
        System.out.println(1);

    }
}