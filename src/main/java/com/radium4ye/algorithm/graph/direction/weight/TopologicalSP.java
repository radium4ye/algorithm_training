package com.radium4ye.algorithm.graph.direction.weight;

import com.radium4ye.algorithm.graph.direction.Topological;
import lombok.Getter;

/**
 * 基于拓扑排序的最短路径树
 * 局限性：无环
 * 优点：能在线性时间处理解决最短路径问题，比 Dijkstra 算法更快、更简单
 * 能处理权重为负的边
 * 解决相关问题：最长的路径
 * <p>
 * 顶点松弛的顺序是基于拓扑排序（逆后序）的，因为在顶点最后一次松弛的时候，
 * 算法已经将指向该顶点的边都已经遍历完成了，已经获取到最短边
 *
 * @author radium4ye
 */
public class TopologicalSP {

    /**
     * {@code null} 表示不可达
     * 记录当前数到所有节点的最小边
     */
    @Getter
    private DiEdge[] toEdge;


    /**
     * 记录到该点的总费用
     */
    @Getter
    private double[] toDist;

    /**
     * 基于拓扑排序的最短路径
     *
     * @param diGraph 加权有向图（满足局限性的图）
     */
    public TopologicalSP(EdgeWeightDiGraph diGraph, int startVertices) {
        toEdge = new DiEdge[diGraph.getVertices()];
        toDist = new double[diGraph.getVertices()];

        for (int i = 0; i < diGraph.getVertices(); i++) {
            toDist[i] = Double.POSITIVE_INFINITY;
        }
        toDist[startVertices] = 0;

        //拓扑排序
        Topological topological = new Topological(diGraph);

        topological.getReversePostOrder().forEach(vertices -> diGraph.adj(vertices).forEach(this::relax));
    }

    public void relax(DiEdge diEdge) {
        int form = diEdge.getForm(), to = diEdge.getTo();
        double weight = diEdge.getWeight() + toDist[form];

        if (weight < toDist[to]) {
            toDist[to] = weight;
            toEdge[to] = diEdge;
        }

    }
}
