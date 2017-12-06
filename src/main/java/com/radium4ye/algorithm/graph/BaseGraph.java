package com.radium4ye.algorithm.graph;

import lombok.Getter;

/**
 * @author radium4ye
 */
@Getter
public abstract class BaseGraph {

    /**
     * 顶点数
     */
    protected final int vertices;

    /**
     * 边数
     */
    protected int edge;

    /**
     * 定义顶点总数
     * @param vertices
     */
    protected BaseGraph(int vertices) {
        this.vertices = vertices;
    }

    /**
     * 校验顶点的有效性
     *
     * @param v 带校验的顶点
     * @throws IllegalArgumentException 非法参数，顶点不正确
     */
    protected void validateVertex(int v) throws IllegalArgumentException {
        if (v < 0 || v >= vertices) {
            throw new IllegalArgumentException("顶点 " + v + " 不在 0 和 " + (vertices - 1) + " 之间");
        }
    }
}
