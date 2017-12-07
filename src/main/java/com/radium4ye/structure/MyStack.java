package com.radium4ye.structure;

import lombok.Data;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * @author Radium
 */
public class MyStack<Item> extends AbstractMyCollection<Item> implements Iterable<Item> {

    /**
     * 链表首结点
     */
    private Node<Item> head;

    /**
     * 添加元素
     *
     * @param item 元素
     */
    public void push(Item item) {
        //创建一个节点
        Node<Item> node = new Node<>();
        node.setItem(item);

        //如果集合首节点不会空，就将新的节点指向该结点
        if (head != null) {
            node.setNext(head);
        }

        //重新设置首节点，并修改集合大小
        count++;
        head = node;
    }

    /**
     * 弹出一个元素
     *
     * @return 栈顶的元素
     */
    public Item pop() {
        if (head == null) {
            throw new EmptyStackException();
        }

        Item item = head.getItem();
        head = head.getNext();
        count--;
        return item;
    }

    /**
     * 构建一个借点，来协助完成链表
     *
     * @param <Item>
     */
    @Data
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    @Override
    public Iterator<Item> iterator() {
        return new InnerIterator<>(head);
    }

    /**
     * 内部调用遍历器
     *
     * @param <Item>
     */
    private static class InnerIterator<Item> implements Iterator<Item> {

        /**
         * 构建一个新的节点，并将老节点设置进去
         *
         * @param node 需要遍历的开始节点
         */
        InnerIterator(Node<Item> node) {
            //创建一个节点
            this.nowNode = new Node<>();
            this.nowNode.setNext(node);
        }

        /**
         * 记录当前结点
         */
        private Node<Item> nowNode;

        @Override
        public boolean hasNext() {
            return nowNode.getNext() != null;
        }

        @Override
        public Item next() {
            nowNode = nowNode.getNext();
            return nowNode.getItem();
        }
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[count];
        int i = 0;
        for (Node<Item> x = head; x != null; x = x.next) {
            result[i++] = x.item;
        }
        return result;
    }

    @Override
    public <T> T[] toArray(T[] result) {
        int i = 0;
        for (Node<Item> x = head; x != null; x = x.next) {
            result[i++] = (T) x.item;
        }
        return result;
    }
}
