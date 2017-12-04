package com.radium4ye.algorithm.graph.unorder;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 深度优先算法
 * 用于计算 点与点之间是否联通 | 联通子图的数量
 *
 * @author Radium
 */
@Data
public class DepthFirstSearch {

    /**
     * 标记该节点是否检查过了
     */
    private Map<Integer,Boolean> marked = new HashMap<>();

    /**
     * 该联通子图中顶点数量数量
     */
    private int verticesCount;

    /**
     * 每个联通子图中的顶点集合
     */
//    private List<List<Integer>> verticesSet = new ArrayList<>();

    /**
     * 无向图
     */
    private Graph graph;

    /**
     * 构造一次计算
     * @param graph         无向图
     * @param intiVertices  初始节点
     */
    public DepthFirstSearch(Graph graph,int intiVertices) {
        this.graph = graph;
        dfs(graph,intiVertices);
    }

    /**
     * 进行递归检查子节点
     * @param graph     无向图
     * @param vertices  检查的节点
     */
    private void dfs(Graph graph,int vertices){
        //设置该点已被检查过了
        marked.put(vertices,true);
        verticesCount ++;
        for (int nextVertices : graph.adj(vertices)){
            //如果下个点没被检查过就进行检查
            if(!marked.getOrDefault(nextVertices,false)){
                dfs(graph,nextVertices);
            }
        }
    }

    /**
     * 该节点和 v 节点是否联通
     * @param v
     */
    public boolean marked(int v){
        return marked.getOrDefault(v,false);
    }
}

