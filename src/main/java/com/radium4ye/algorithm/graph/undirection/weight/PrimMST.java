package com.radium4ye.algorithm.graph.undirection.weight;

import com.radium4ye.structure.MyStack;
import lombok.Getter;

import java.util.Stack;

/**
 * Prim算法
 * 遍历数据的流程感觉有点类似 Dijkstra
 * 不同的是：这里不用做累加，只用记录（树到改点）最短边即可
 *
 * @author radium4ye
 */
public class PrimMST {

    /**
     * {@code null} 表示不可达
     * 记录当前数到所有节点的最小边
     */
    private Edge[] toEdge;

    /**
     * 记录当前节点是否被标记过
     */
    private boolean[] marked;

    /**
     * 记录选择的点
     */
    @Getter
    private MyStack<Edge> select;


    public PrimMST(EdgeWeightGraph graph){
        marked = new boolean[graph.getVertices()];
        toEdge = new Edge[graph.getVertices()];
        select = new MyStack<>();

        //从点0开始
        marked[0] = true;
        addVertices(graph,0);
    }

    /**
     * 遍历该点的所有领边，过滤已经记录过的点{@link #marked}
     * 和记录值{@link #toEdge}比较，保留最短的
     *
     * @param vertices
     */
    public void addVertices(EdgeWeightGraph graph,final int vertices){

        graph.adj(vertices).forEach(nowEdge -> {
            int anotherVertices = nowEdge.getAnotherVertices(vertices);

            //没被标记过，说明该点不和树连通
            if(!marked[anotherVertices]){
                Edge oldEdge = toEdge[anotherVertices];
                if(oldEdge == null){
                    toEdge[anotherVertices] = nowEdge;
                }else if(nowEdge.compareTo(oldEdge) < 0){
                    toEdge[anotherVertices] = nowEdge;
                }
            }
        });

        //遍历所有 toEdge，取最短的一条边
        Edge min = null;
        int minIndex = -1;
        for (int i = 0; i < graph.getVertices(); i++) {
            Edge nowEdge = toEdge[i];
            if(nowEdge != null){
                if(min == null){
                    min = nowEdge;
                    minIndex = i;
                }else if(nowEdge.compareTo(min) < 0){
                    min = nowEdge;
                    minIndex = i;
                }
            }
        }

        if(min != null){
            //将当前最小边加入
            marked[minIndex] = true;
            select.push(min);
            toEdge[minIndex] = null;

            addVertices(graph,minIndex);
        }
    }

}
