package com.radium4ye.structure.tree;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author radium4ye
 */
public class BinaryTree<T extends Comparable<T>> {


    private Node<T> head;

    public boolean add(T t) {
        Node<T> node = new Node<>();
        node.setValue(t);

        if (head == null) {
            head = node;
            return true;
        }

        Node<T> temp = head;


        while (true) {
            if (t.compareTo(temp.value) > 0) {
                if (temp.right == null) {
                    temp.right = node;
                    return true;
                }
                temp = temp.right;
            } else {
                if (temp.left == null) {
                    temp.left = node;
                    return true;
                }
                temp = temp.left;
            }
        }
    }


    private boolean balance;

    /**
     * 计算整个二叉树的平衡性
     * @return {@code true 该二叉树平衡}
     */
    public boolean calcBalance() {
        balance = true;
        calcBalance(head);
        return balance;
    }

    /**
     * 计算某个节点的平衡性
     * @param node
     * @return
     */
    public int calcBalance(Node node) {
        if (node == null) {
            return 0;
        }

        int left = calcBalance(node.getLeft());
        int right = calcBalance(node.getRight());
        int max = Math.max(left, right);

        if (Math.abs(left - right) > 1) {
            balance = false;
        }

        return max + 1;
    }

    /**
     * 通过 Morris 遍历二叉树
     */
    public void printMorris() {

        Node<T> now = head;

        while (now != null) {

            //获取该节点的前序节点，如果该节点的右子节点为空，将其指向自身
            Node<T> pre = findPreNode(now);

            if (pre != null) {
                if (pre.getRight() == null) {
                    pre.setRight(now);
                } else if (pre.getRight() == now) {
                    pre.setRight(null);
                    System.out.println(now);
                    now = now.getRight();
                    continue;
                }

                now = now.getLeft();
            } else {
                //如果左子为空
                System.out.println(now);
                now = now.getRight();

            }

        }

    }

    /**
     * 获取该节点的前序节点
     *
     * @param node 节点
     * @return 前序节点
     */
    public Node<T> findPreNode(Node<T> node) {
        //空节点
        if (node == null || node.getLeft() == null) {
            return null;
        }

        Node<T> temp = node.getLeft();
        while (true) {

            //如果右节点为空，或者指向本身，返回该节点
            if (temp.getRight() == null || temp.getRight() == node) {
                return temp;
            }

            //继续获取右节点
            temp = temp.getRight();

        }
    }


    @Data
    @ToString(of = "value")
    public static class Node<T> {
        private T value;
        private Node<T> left;
        private Node<T> right;

        public T getValue() {
            return value;
        }
    }
}
