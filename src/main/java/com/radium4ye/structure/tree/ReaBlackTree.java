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
    private Node root;


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
    private Node put(Node baseNode, Key key, Value value) {

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
            Node node = new Node();
            node.setKey(key);
            node.setValue(value);
            node.setColor(RED);
            baseNode = node;
        }

        baseNode = checkStructure(baseNode);
        return baseNode;
    }


    /**
     * 删除节点
     *
     * @param key
     */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("传入的键不能为null");
        }
        if (root == null) {
            return;
        }

        //左右节点均不为红，将根节点变红，构成4节点准备
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }

        root = delete(root, key);

        if (root != null) {
            root.color = BLACK;
        }
    }


    private Node delete(Node baseNode, Key key) {

        //需要将该节点变成 一个 3、4节点 才可进行删除操作，不然会破坏黑键结构
        if (baseNode.left != null && !isRed(baseNode.left)) {
            baseNode.color = !baseNode.color;
            baseNode.left.color = !baseNode.left.color;
            baseNode.right.color = !baseNode.right.color;
        }

        int compareResult = baseNode.getKey().compareTo(key);

        if (compareResult == 0) {
            if (baseNode.right == null && baseNode.left == null) {
                return null;
            }
        } else if (compareResult > 0) {
            //在左子节点中
            if (isRed(baseNode.left)) {
                baseNode.left = delete(baseNode.left, key);
            } else {
                //todo
            }
        } else {
            //在右子节点中
            if (isRed(baseNode.right)) {
                baseNode.right = delete(baseNode.right, key);
            } else {
                //右子节点不为红，转化当前节点和右边节点的颜色
                baseNode = rightRotate(baseNode);
                baseNode.right = delete(baseNode.right, key);
            }
        }

        baseNode = checkStructure(baseNode);
        return baseNode;
    }

    public void deleteMin() {


    }

    public Node deleteMin(Node node) {

        return node;
    }


    /**
     * 检查节点
     *
     * @param node
     */
    private Node checkStructure(Node node) {

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
            flipColors(node);
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
    private Node leftRotate(Node node) {
        Node temp = node.right;
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
    private Node rightRotate(Node node) {
        Node temp = node.left;
        node.setLeft(temp.right);
        temp.setRight(node);

        //交换颜色
        node.color = temp.color ^ node.color;
        temp.color = temp.color ^ node.color;
        node.color = temp.color ^ node.color;
        return temp;
    }

    /**
     * 转化颜色，用于传递红节点
     *
     * @param h
     */
    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    @Data
    @ToString(exclude = {"left", "right"})
    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        /**
         * @code false 黑节点
         * @code true  红节点
         */
        private boolean color;
    }
}
