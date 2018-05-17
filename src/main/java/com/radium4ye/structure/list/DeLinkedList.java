package com.radium4ye.structure.list;

import lombok.Data;

/**
 * 双端连接 基于哨兵实现
 *
 * @author radium4ye
 */
public class DeLinkedList<T> {

    private Node<T> sentinel = new Node<>();

    {
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);
    }

    private int count = 0;

    public boolean add(T t) {
        Node<T> node = new Node<>();
        node.setValue(t);

        node.setPrev(sentinel.getPrev());
        sentinel.setPrev(node);
        node.setNext(sentinel);
        node.getPrev().setNext(node);

        count ++;

        return true;
    }

    public T remove(){

        if(count <= 0){
            return null;
        }

        Node<T> result = sentinel.getNext();

        sentinel.setNext(result.getNext());
        result.getNext().setPrev(sentinel);

        return result.getValue();
    }


    @Data
    public static class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> prev;
    }
}
