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

    /**
     * 判断链表是否包含环
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @return
     */
    public boolean hasCycle() {
        //定义两个指针，一个指针一次走1个节点，另一个指针一次走2个节点
        if(head == null){
            return false;
        }
        Node point1 = head;
        Node point2 = head;

        while (true){
            //如果出现空节点，说明链表已经走完了
            if(point2.next == null || point2.next.next == null){
                return false;
            }
            point2 = point2.next.next;
            point1 = point1.next;

            //如果两个指针相遇说明有环
            if(point1 == point2){
                return true;
            }
        }

    }

    @Data
    public static class Node<T> {
        private T value;
        private Node<T> next;
    }
}
