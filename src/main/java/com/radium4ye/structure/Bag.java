package com.radium4ye.structure;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Iterator;

/**
 * 链表数据结构
 *
 * @author radium4ye
 */
@Data
public class Bag<Item> implements Iterable<Item> {

    /**
     * 链表首结点
     */
    private Node<Item> top;

    /**
     * 链表总长
     */
    private int count;

    /**
     * 添加元素
     *
     * @param item 元素
     */
    public void add(Item item) {
        //创建一个节点
        Node<Item> node = new Node<>();
        node.setItem(item);

        //如果集合首节点不会空，就将新的节点指向该结点
        if (top != null) {
            node.setNext(top);
        }

        //重新设置首节点，并修改集合大小
        top = node;
        count++;
    }

    /**
     * 获取集合大小
     *
     * @return 集合大小
     */
    public int size() {
        return count;
    }

    /**
     * 判断集合对否为空
     *
     * @return {@code true} 集合为空 {@code false} 集合不为空
     */
    public boolean isEmpty() {
        return count != 0;
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
        return new InnerIterator<>(top);
    }

    /**
     * 内部调用遍历器
     *
     * @param <Item>
     */
    @AllArgsConstructor
    private static class InnerIterator<Item> implements Iterator<Item> {

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
