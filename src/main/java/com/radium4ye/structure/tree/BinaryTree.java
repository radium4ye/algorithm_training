package com.radium4ye.structure.tree;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author radium4ye
 */
public class BinaryTree<Key extends Comparable<Key>> {


    private Node head;

    public boolean add(Key key) {
        Node node = new Node();
        node.setKey(key);

        if (head == null) {
            head = node;
            return true;
        }

        Node temp = head;


        while (true) {
            if (key.compareTo(temp.key) > 0) {
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

    /**
     * 删除节点T
     *
     * @param t
     */
    public void delete(Key t) {
        if(t == null){
           throw new IllegalArgumentException("删除的参数不能为空");
        }

        if(head == null){
            return;
        }

        head = delete(head,t);
    }

    private Node delete(Node node,Key key) {
        int compareResult = key.compareTo(node.key);

        if(compareResult == 0){

            if(node.left == null && node.right == null){
                node = null;
            }else if(node.left == null){
                node = node.right;
            }else if(node.right == null){
                node = node.left;
            }else {
                //如果均不为空，找右子节点的后继
                Node temp = chooseMinLeftNullNode(node.right);
                temp.left = node.left;
                temp.right = node.right;
                node = temp;
            }

        }else if (compareResult < 0){
            node.left = delete(node.left,key);
        }else {
            node.right = delete(node.right,key);
        }


        return node;
    }

    /**
     * 查找最小的左边子树为空的节点
     * @param node
     * @return
     */
    private Node chooseMinLeftNullNode(Node node) {

        Node parent = null;
        while (node.left != null){
            parent = node;
            node = node.left;
        }

        //如果父亲节点不为空
        //将该节点的右节点 替换成当前位置
        if(parent != null){
            parent.left = node.right;
        }

        return node;
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

        Node now = head;

        while (now != null) {

            //获取该节点的前序节点，如果该节点的右子节点为空，将其指向自身
            Node pre = findPreNode(now);

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
    public Node findPreNode(Node node) {
        //空节点
        if (node == null || node.getLeft() == null) {
            return null;
        }

        Node temp = node.getLeft();
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
    @ToString(of = "key")
    public class Node {
        private Key key;
        private Node left;
        private Node right;
    }
}
