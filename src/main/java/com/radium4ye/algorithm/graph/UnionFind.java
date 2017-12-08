package com.radium4ye.algorithm.graph;

/**
 * 用于计算两个顶点是否联通
 *
 * @author radium4ye
 */
public class UnionFind {

    /**
     * 每个顶点的连通分量标识符
     */
    private int[] verticesMark;

    /**
     * 顶点数
     */
    private int vertices;

    /**
     * 构造
     *
     * @param vertices 总顶点数
     */
    public UnionFind(int vertices) {
        this.vertices = vertices;
        verticesMark = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            verticesMark[i] = i;
        }
    }

    /**
     * 联合 两个顶点
     *
     * @param v1 顶点1
     * @param v2 顶点2
     */
    public void union(int v1, int v2) {
        int mark1 = verticesMark[v1];
        int mark2 = verticesMark[v2];
        if (mark1 == mark2) {
            return;
        }

        for (int i = 0; i < vertices; i++) {
            if (verticesMark[i] == mark2) {
                verticesMark[i] = mark1;
            }
        }
    }

    /**
     * 判断 两个顶点是否连通
     *
     * @param v1 顶点1
     * @param v2 顶点2
     * @return {@code true 连通}
     */
    public boolean connected(int v1, int v2) {
        int mark1 = verticesMark[v1];
        int mark2 = verticesMark[v2];

        return mark1 == mark2;
    }
}
