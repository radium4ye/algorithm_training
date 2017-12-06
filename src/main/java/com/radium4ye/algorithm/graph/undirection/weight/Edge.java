package com.radium4ye.algorithm.graph.undirection.weight;

import lombok.Data;

/**
 * 加权无向边
 *
 * @author radium4ye
 */
@Data
public class Edge implements Comparable<Edge> {

    /**
     * 顶点1
     */
    private int vertices1;

    /**
     * 顶点2
     */
    private int vertices2;

    /**
     * 权重
     */
    private Double weight;

    /**
     * 构造一条边
     *
     * @param vertices1 顶点1
     * @param vertices2 顶点2
     * @param weight    该边的权重
     */
    public Edge(int vertices1, int vertices2, Double weight) {
        this.vertices1 = vertices1;
        this.vertices2 = vertices2;
        this.weight = weight;
    }

    /**
     * 获取该边的另一个顶点
     *
     * @param vertices 其中一个顶点
     * @return 另一个顶点
     */
    public int getAnotherVertices(int vertices) {
        if (vertices1 == vertices) {
            return vertices2;
        } else if (vertices2 == vertices) {
            return vertices1;
        } else {
            throw new IllegalArgumentException("传入的顶点不为该边的顶点");
        }
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight.compareTo(o.getWeight());
    }
}
