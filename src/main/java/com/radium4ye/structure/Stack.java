package com.radium4ye.structure;

import lombok.Data;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * @author Radium
 */
public class Stack<Item> extends MyCollection<Item> implements Iterable<Item> {

    /**
     * 链表首结点
     */
    private Node<Item> first;

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
        if (first != null) {
            node.setNext(first);
        }

        //重新设置首节点，并修改集合大小
        count++;
        first = node;
    }

    /**
     * 弹出一个元素
     *
     * @return 栈顶的元素
     */
    public Item pop() {
        if (first == null) {
            throw new EmptyStackException();
        }

        Item item = first.getItem();
        first = first.getNext();
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
