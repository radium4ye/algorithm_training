package com.radium4ye.algorithm.other;

import com.radium4ye.algorithm.sort.pq.HeapPriorityQueue;
import lombok.Data;
import lombok.Getter;

/**
 * 赫夫曼树
 * 用于进行赫夫曼编码做准备工作
 *
 * @author radium4ye
 */
public class HuffmanTree {
    @Getter
    private Node head;

    public HuffmanTree(char[] chars, int[] weights) {

        //最小堆
        HeapPriorityQueue<Node> queue = new HeapPriorityQueue<>(chars.length + 1, (o1, o2) -> Integer.compare(o2.weight, o1.weight));

        for (int i = 0; i < chars.length; i++) {
            Node node = new Node(chars[i], weights[i], true);
            queue.add(node);
        }

        //如果队列中的元素大于1个，就是弹出2个元素构建树
        while (queue.size() > 1) {
            Node left = queue.poll();
            Node right = queue.poll();

            Node newNode = new Node((char) -1, left.weight + right.weight, false);
            newNode.left = left;
            newNode.right = right;

            //加新节点加会队列
            queue.add(newNode);
        }

        head = queue.poll();

    }

    /**
     * 打印当前二叉树
     */
    public void print() {
        print(head.getLeft(), "0");
        print(head.getRight(), "1");
    }

    private void print(Node node, String str) {

        if (node.isLeaf()) {
            System.out.println("字符【" + node.getAChar() + "】对应的编码为【" + str + "】");
        } else {
            //因为赫夫曼树是满二叉树
            print(node.getLeft(), str + "0");
            print(node.getRight(), str + "1");
        }
    }

    @Data
    static class Node implements Comparable<Node> {

        /**
         * 该节点的支付
         */
        char aChar;

        /**
         * 该节点的权重
         */
        int weight;

        /**
         * 是否为叶子节点
         */
        boolean leaf;

        /**
         * 左右子节点
         */
        Node left;
        Node right;

        public Node(char aChar, int weight, boolean leaf) {
            this.aChar = aChar;
            this.weight = weight;
            this.leaf = leaf;
        }


        @Override
        public int compareTo(Node o) {
            return Integer.compare(weight, o.getWeight());
        }
    }
}
