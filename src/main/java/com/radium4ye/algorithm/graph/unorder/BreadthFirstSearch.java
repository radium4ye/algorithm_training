package com.radium4ye.algorithm.graph.unorder;

import lombok.Data;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 广度优先算法
 * 用于计算 最短路径
 *
 * @author Radium
 */
@Data
public class BreadthFirstSearch extends BaseSearch{

    /**
     * 构造一次计算
     * @param graph         无向图
     * @param intiVertices  初始节点
     */
    public BreadthFirstSearch(Graph graph, int intiVertices) {
        int initSize = (int) Math.ceil(graph.vertices() * 0.75);
        marked = new HashMap<>(initSize);
        edgeTo = new HashMap<>(initSize);
        bfs(graph, intiVertices);
    }

    /**
     * 进行循环检查子节点
     * @param graph     无向图
     * @param vertices  检查的节点
     */
    private void bfs(Graph graph, int vertices) {
        Queue<Integer> queue = new LinkedBlockingQueue<>();

        //当前正在检查的节点
        Integer nowVertices;
        while ((nowVertices = queue.remove()) != null) {

            //如果该顶点没被检查过 就继续
            if (!marked.getOrDefault(nowVertices, false)) {
                //设置该点已被检查过了
                marked.put(nowVertices, true);
                queue.addAll(graph.adj(nowVertices));
            }

        }
    }
}
