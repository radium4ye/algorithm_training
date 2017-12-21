package com.radium4ye.structure.list;

import lombok.Data;

/**
 * 基于链表
 *
 * @author radium4ye
 */
public class LinkedList<T> {

    private Node<T> head;

    public boolean add(T t) {
        Node<T> node = new Node<>();
        node.setValue(t);
        if (head == null) {
            head = node;
        } else {
            Node<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }

        return true;
    }

    /**
     * 翻转
     */
    public void reverse() {
        if (head != null) {
            reverse(head);
        }
    }

    /**
     * 翻转
     *
     * @param node 待翻转的节点
     */
    private void reverse(Node node) {

        if (node.next != null) {
            reverse(node.next);
            node.next.next = node;
            node.next = null;
        } else {
            head = node;
        }


    }



    @Data
    public static class Node<T> {
        private T value;
        private Node<T> next;
    }
}
