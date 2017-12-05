package com.radium4ye.algorithm.graph.direction;

/**
 * 用于计算图中多点可达性
 * e.g. Jvm 的标记 - 清除算法
 *
 * @author radium4ye
 */
public class DirectedDFS {

    /**
     * 标记该顶点是否可达|是否检查过
     */
    private boolean[] marked;

    /**
     * 检索流程中，每一个节点的上一个检索节点
     * {@code key 子节点}
     * {@code value 父节点}
     */
    protected int[] edgeTo;

    /**
     * 构造算法，传入必要参数
     *
     * @param diGraph       优先图
     * @param initVertices  初始顶点
     */
    public DirectedDFS(DiGraph diGraph,int initVertices) {
        marked = new boolean[diGraph.getVertices()];
        edgeTo = new int[diGraph.getVertices()];

        //遍历该顶点
        dfs(diGraph, initVertices);

    }

    /**
     * 进行深度优先搜索
     * @param diGraph   有向图
     * @param vertices  起始顶点
     */
    private void dfs(DiGraph diGraph,final int vertices) {
        marked[vertices] = true;

        //遍历该节点的所有邻节点
        diGraph.adj(vertices).iterator()
                .forEachRemaining(nextVertices -> {
                    if (!marked[nextVertices]) {
                        //添加路径
                        edgeTo[nextVertices] = vertices;
                        dfs(diGraph, nextVertices);
                    }
                });
    }

    /**
     * 获取该顶点是否标记
     *
     * @param vertices
     * @return
     */
    public boolean marked(int vertices) {
        return marked[vertices];
    }
}
