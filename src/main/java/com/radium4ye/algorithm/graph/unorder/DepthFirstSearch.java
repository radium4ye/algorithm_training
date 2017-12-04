package com.radium4ye.algorithm.graph.unorder;

import lombok.Data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

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
    private Map<Integer,Boolean> marked;

    /**
     * 检索流程中，每一个节点的上一个检索节点
     * {@code key 子节点}
     * {@code value 父节点}
     */
    private Map<Integer,Integer> edgeTo;

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

    /**
     * 该节点和 v 节点是否联通
     * @param v
     */
    public boolean hasPathTo(int v){
        return marked.getOrDefault(v,false);
    }

    /**
     * 获取从起始顶点 到 目标顶点的路径
     * @param aimVertices 目标订单
     * @return 路径栈
     */
    public Stack<Integer> pathTo(int aimVertices){
        //不联通
        if(!hasPathTo(aimVertices)){
            return null;
        }

        //定义返回的数据栈
        Stack<Integer> stack = new Stack<>();
        stack.push(aimVertices);

        //一直查询父节点，直到查询不到为止
        Integer parent = edgeTo.get(aimVertices);
        while (parent != null){
            stack.push(parent);
            parent = edgeTo.get(parent);
        }

        return stack;
    }
}

