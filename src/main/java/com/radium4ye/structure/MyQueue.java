package com.radium4ye.structure;

import lombok.Data;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * 简易队列
 *
 * @author radium4ye
 */
public class MyQueue<Item> extends AbstractMyCollection<Item> implements Queue<Item>, Iterable<Item> {

    /**
     * 链表首结点
     */
    private Node<Item> first;

    /**
     * 添加元素
     *
     * @param item 元素
     */
    @Override
    public boolean add(Item item) {
        //创建一个节点
        Node<Item> node = new Node<>();
        node.setItem(item);

        //如果集合首节点不会空，就将新的节点指向该结点
        if (first == null) {
            first = node;
        } else {
            Node<Item> lastNode = first;
            while (lastNode.getNext() != null) {
                lastNode = lastNode.getNext();
            }
            lastNode.setNext(node);
        }

        //重新设置首节点，并修改集合大小
        count++;

        return true;
    }

    /**
     * @see #add(Object)
     */
    @Override
    public boolean offer(Item item) {
        return add(item);
    }

    /**
     * 移除元素
     *
     * @return 移除队列首都的元素
     * @see #poll()
     */
    @Override
    public Item remove() {
        Item item = poll();
        if (item == null) {
            throw new NoSuchElementException();
        }

        return item;
    }

    /**
     * 移除元素
     *
     * @return 移除队列首都的元素
     */
    @Override
    public Item poll() {
        if (first == null) {
            return null;
        }

        Item item = first.getItem();
        first = first.getNext();
        count--;
        return item;
    }

    /**
     * 获取首部元素
     *
     * @return 队列首部元素
     * @see #peek()
     */
    @Override
    public Item element() {
        Item item = peek();
        if (item == null) {
            throw new NoSuchElementException();
        }

        return item;
    }

    /**
     * 获取首部元素
     *
     * @return 队列首部元素
     */
    @Override
    public Item peek() {
        if (first == null) {
            return null;
        }

        return first.getItem();
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
        return new InnerIterator<>(first);
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

}
