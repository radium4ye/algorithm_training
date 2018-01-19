package com.radium4ye.structure.tree;

import lombok.Data;
import lombok.ToString;

/**
 * @author radium4ye
 */
public class ReaBlackTree<Key extends Comparable<Key>, Value> {

    /**
     * 颜色
     */
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    /**
     * 根节点
     */
    private Node<Key, Value> root;


    /**
     * 插入一条数据
     *
     * @param key   键
     * @param value 值
     */
    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("传入的键不能为null");
        }
        if (value == null) {
            delete(key);
            return;
        }

        root = put(root, key, value);
        root.color = BLACK;
    }

    /**
     * 插入一条数据
     *
     * @param baseNode 插入的起始节点
     * @param key      键
     * @param value    值
     */
    private Node<Key, Value> put(Node<Key, Value> baseNode, Key key, Value value) {

        //如果当前节点不会空，继续向下
        if (baseNode != null) {

            //比较当前节点和需要插入的节点 键
            if (baseNode.key.compareTo(key) > 0) {
                baseNode.left = put(baseNode.left, key, value);
            } else if (baseNode.key.compareTo(key) < 0) {
                baseNode.right = put(baseNode.right, key, value);
            } else {
                baseNode.value = value;
            }

        } else {
            //新建节点
            Node<Key, Value> node = new Node<>();
            node.setKey(key);
            node.setValue(value);
            node.setColor(RED);
            baseNode = node;
        }

        baseNode = checkStructure(baseNode);
        return baseNode;
    }


    private void delete(Key key) {
    }


    /**
     * 检查节点
     *
     * @param node
     */
    private Node<Key, Value> checkStructure(Node<Key, Value> node) {

        //右子节点为红，而左子节点不为红
        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }

        //连续两个左子节点为红，
        if (isRed(node.left) && node.left != null && isRed(node.left.left)) {
            node = rightRotate(node);
        }

        //左右节点都是红，进行颜色转化
        if (isRed(node.left) && isRed(node.right)) {
            node.left.color = BLACK;
            node.right.color = BLACK;
            node.color = RED;
        }

        return node;
    }


    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        } else {
            return node.color;
        }
    }

    /**
     * 左旋转
     *
     * @param node
     * @return
     */
    private Node<Key, Value> leftRotate(Node<Key, Value> node) {
        Node<Key, Value> temp = node.right;
        node.setRight(temp.left);
        temp.setLeft(node);

        //交换颜色
        node.color = temp.color ^ node.color;
        temp.color = temp.color ^ node.color;
        node.color = temp.color ^ node.color;
        return temp;
    }

    /**
     * 右旋转
     *
     * @param node
     * @return
     */
    private Node<Key, Value> rightRotate(Node<Key, Value> node) {
        Node<Key, Value> temp = node.left;
        node.setLeft(temp.right);
        temp.setRight(node);

        //交换颜色
        node.color = temp.color ^ node.color;
        temp.color = temp.color ^ node.color;
        node.color = temp.color ^ node.color;
        return temp;
    }


    @Data
    @ToString(exclude = {"left","right"})
    private static class Node<Key extends Comparable<Key>, Value> {
        private Key key;
        private Value value;
        private Node<Key, Value> left;
        private Node<Key, Value> right;
        /**
         * @code false 黑节点
         * @code true  红节点
         */
        private boolean color;
    }
}
