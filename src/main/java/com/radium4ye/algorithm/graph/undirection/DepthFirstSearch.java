package com.radium4ye.algorithm.graph.undirection;

import lombok.Data;

import java.util.HashMap;

/**
 * 深度优先算法
 * 用于计算 点与点之间是否联通 | 联通子图的数量
 *
 * @author Radium
 */
@Data
public class DepthFirstSearch extends BaseSearch {

    /**
     * 该联通子图中顶点数量数量
     */
    private int verticesCount;

    /**
     * 构造一次计算
     * @param graph         无向图
     * @param intiVertices  初始节点
     */
    public DepthFirstSearch(Graph graph,int intiVertices) {
        int initSize = (int) Math.ceil(graph.vertices() * 0.75);
        marked = new HashMap<>(initSize);
        edgeTo = new HashMap<>(initSize);
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
                edgeTo.put(nextVertices,vertices);
                dfs(graph,nextVertices);
            }
        }
    }

}

