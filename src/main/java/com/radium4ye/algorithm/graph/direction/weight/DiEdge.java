package com.radium4ye.algorithm.graph.direction.weight;

import lombok.Data;

/**
 * 加权有向边
 *
 * @author radium4ye
 */
@Data
public class DiEdge implements Comparable<DiEdge> {

    /**
     * 起始顶点
     */
    private int from;

    /**
     * 结束顶点
     */
    private int to;

    /**
     * 权重
     */
    private Double weight;

    /**
     * 构造一条边
     *
     * @param from   起始顶点
     * @param to     结束顶点
     * @param weight 该边的权重
     */
    public DiEdge(int from, int to, Double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(DiEdge o) {
        return this.weight.compareTo(o.getWeight());
    }
}
