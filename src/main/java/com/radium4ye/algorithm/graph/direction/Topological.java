package com.radium4ye.algorithm.graph.direction;

import com.radium4ye.structure.MyQueue;
import com.radium4ye.structure.MyStack;
import lombok.Getter;

import java.util.Queue;

/**
 * 拓扑排序
 * 前序：再递归调用之前加入队列
 * 后序：再递归调用之后加入队列
 * 逆后序：再递归调用之后压入栈  用于计算时间的先后顺序
 *
 * @author radium4ye
 */
@Getter
public class Topological {

    /**
     * 前序
     */
    private Queue<Integer> preOrder;

    /**
     * 后序
     */
    private Queue<Integer> postOrder;

    /**
     * 逆后序
     */
    private MyStack<Integer> reversePostOrder;

    /**
     * 是否检查过
     */
    private boolean[] marked;

    /**
     * 构建拓扑排序
     * @param diGraph
     */
    public Topological(DiGraph diGraph) {
        preOrder = new MyQueue<>();
        postOrder = new MyQueue<>();
        reversePostOrder = new MyStack<>();
        marked = new boolean[diGraph.getVertices()];

        for (int i = 0; i < diGraph.getVertices(); i++) {
            if (!marked[i]) {
                dfs(diGraph, i);
            }
        }
    }

    /**
     * 进行深度优先搜索
     *
     * @param diGraph  有向图
     * @param vertices 起始顶点
     */
    private void dfs(DiGraph diGraph, final int vertices) {
        marked[vertices] = true;
        preOrder.add(vertices);
        //遍历该节点的所有邻节点
        diGraph.adj(vertices)
                .forEach(nextVertices -> {
                    if (!marked[nextVertices]) {
                        dfs(diGraph, nextVertices);
                    }
                });
        postOrder.add(vertices);
        reversePostOrder.push(vertices);
    }

}
