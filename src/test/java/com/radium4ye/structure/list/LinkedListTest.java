package com.radium4ye.structure.list;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class LinkedListTest {
    @Test
    public void hasCycle() throws Exception {
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            linkedList.add(i);
        }

        Field field = LinkedList.class.getDeclaredField("head");
        field.setAccessible(true);
        LinkedList.Node node = (LinkedList.Node) field.get(linkedList);
        LinkedList.Node temp = node;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(node);

        assertEquals(linkedList.hasCycle(),true);

    }

    @Test
    public void test(){
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < 5; i++) {
            linkedList.add(i);
        }

        linkedList.reverse();
        assertEquals(linkedList.hasCycle(),false);
    }
}