package com.radium4ye.algorithm.graph.direction.weight;

import com.radium4ye.structure.MyQueue;
import lombok.Getter;

import java.util.Queue;

/**
 * Bellman-Ford 算法
 * 局限性：无负权重环
 * <p>
 * 对每个顶点进行顺序松弛即可
 *
 * @author radium4ye
 */
public class BellmanFordSP {

    /**
     * 表示顶点是否在队列中
     */
    private boolean[] marked;

    /**
     * 标价即将需要松弛的边
     */
    private Queue<DiEdge> queue;

    /**
     * 到每个顶点的路径
     */
    @Getter
    private Double[] toDist;

    /**
     * 记录到该顶点的路径
     */
    private DiEdge[] diEdges;

    /**
     *
     * @param diGraph
     * @param startVertices
     */
    public BellmanFordSP(EdgeWeightDiGraph diGraph, int startVertices) {
        marked = new boolean[diGraph.getVertices()];
        queue = new MyQueue<>();
        toDist = new Double[diGraph.getVertices()];
        diEdges = new DiEdge[diGraph.getVertices()];

        for (int i = 0; i < diGraph.getVertices(); i++) {
            toDist[i] = Double.POSITIVE_INFINITY;
        }

        //需要先 是否存在校验负权重还

        //标记起始点，并将起始点的所有领边都加入
        toDist[startVertices] = 0.0;
        marked[startVertices] = true;
        diGraph.adj(startVertices).forEach(queue::add);

        while (!queue.isEmpty()) {
            DiEdge diEdge = queue.remove();
            int from = diEdge.getFrom(), to = diEdge.getTo();

            //检查并松弛
            if (toDist[from] + diEdge.getWeight() < toDist[to]) {
                toDist[to] = toDist[from] + diEdge.getWeight();
                diEdges[to] = diEdge;

                if (!marked[to]) {
                    marked[to] = true;
                    diGraph.adj(to).forEach(queue::add);
                }
            }

        }

    }
}
