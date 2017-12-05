package com.radium4ye.algorithm.graph.undirection;

import lombok.Data;

import java.util.*;


/**
 * 无项图 对象
 * @author Radium
 */
@Data
public class Graph {

    /**
     * 边数
     */
    private int edge;

    /**
     * 顶点对应的对象
     */
//    private Map<Integer,Object> values = new HashMap<>();

    /**
     * 顶点的领边
     * 在无向图中 会重复记录
     * e.g. 1->2  2->1
     */
    private Map<Integer,List<Integer>> adjacent = new HashMap<>();

    /**
     *  添加一条边
     * @param vertices1 顶点1
     * @param vertices2 顶点2
     */
    public void addEdge(int vertices1,int vertices2){
        //分别添加顶点 1、2 的领边
        adjacent.computeIfAbsent(vertices1, k -> new LinkedList<>()).add(vertices2);

        adjacent.computeIfAbsent(vertices2, k -> new LinkedList<>()).add(vertices1);

        edge ++;
    }

    /**
     * 返回该顶点的领边
     * @param vertices  顶点
     * @return          领边
     */
    public List<Integer> adj(int vertices){
        return adjacent.computeIfAbsent(vertices, k -> new LinkedList<>());
    }

    /**
     * 返回图中顶点数
     * @return 总顶点数
     */
    public int vertices(){
        return adjacent.size();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices()).append(" 点, ").append(edge).append(" 边 \t\n");

        //遍历所有顶点
        for (Map.Entry<Integer,List<Integer>> entry : adjacent.entrySet()) {
            s.append(entry.getKey()).append(": ");

            //将该顶点的领边都打印出来
            for (Integer otherVertices : entry.getValue()) {
                s.append(otherVertices).append(" ");
            }
            s.append("\t\n");
        }
        return s.toString();
    }
}
