package com.radium4ye.algorithm.graph.direction.weight;

import com.radium4ye.algorithm.graph.direction.HasCycle;
import com.radium4ye.structure.MyQueue;
import com.radium4ye.structure.MyStack;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
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
    private boolean[] onQueue;

    /**
     * 标价即将需要松弛的点
     */
    private Queue<Integer> queue;

    /**
     * 到每个顶点的路径
     */
    @Getter
    private Double[] distTo;

    /**
     * 记录到该顶点的路径
     */
    private DiEdge[] edgeTo;

    /**
     * relax 次数
     */
    private int cost;

    /**
     * 循环
     */
    private MyStack<DiEdge> cycle = null;

    /**
     * @param diGraph
     * @param startVertices
     */
    public BellmanFordSP(EdgeWeightDiGraph diGraph, int startVertices) {
        onQueue = new boolean[diGraph.getVertices()];
        queue = new MyQueue<>();
        distTo = new Double[diGraph.getVertices()];
        edgeTo = new DiEdge[diGraph.getVertices()];

        for (int i = 0; i < diGraph.getVertices(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        //需要先 是否存在校验负权重还

        //标记起始点，并将起始点的所有领边都加入
        distTo[startVertices] = 0.0;
        onQueue[startVertices] = true;
        queue.add(startVertices);

        while (!queue.isEmpty()) {
            Integer relaxVertices = queue.remove();
            onQueue[relaxVertices] = false;
            relax(diGraph, relaxVertices);
        }

    }

    /**
     * 松弛某个顶点的所有领边
     *
     * @param graph         图
     * @param relaxVertices 带松弛的点
     */
    private void relax(EdgeWeightDiGraph graph, int relaxVertices) {

        for (DiEdge diEdge : graph.adj(relaxVertices)) {
            int from = diEdge.getFrom(), to = diEdge.getTo();

            //检查并松弛
            if (distTo[from] + diEdge.getWeight() < distTo[to]) {
                distTo[to] = distTo[from] + diEdge.getWeight();
                edgeTo[to] = diEdge;

                if (!onQueue[to]) {
                    onQueue[to] = true;
                    queue.add(to);
                }
            }

//            if (cost++ % graph.getVertices() == 0) {
                findNegativeCycle();
                if (hasNegativeCycle()) {
                    //找到环 终止运算
                    return;
                }
//            }
        }


    }

    /**
     * 查询循环。如果有循环，一定是负循环
     */
    private void findNegativeCycle() {
        EdgeWeightDiGraph tempGraph = new EdgeWeightDiGraph(edgeTo.length);
        Arrays.stream(edgeTo).filter(Objects::nonNull).forEach(tempGraph::addEdge);
        HasCycle hasCycle = new HasCycle(tempGraph);
        cycle = hasCycle.getCycle();
    }

    /**
     * 判断是否包含负权重循环
     * 如果有循环，一定是负循环
     *
     * @return {@code true 包含循环}
     */
    public boolean hasNegativeCycle() {
        return cycle != null;
    }
}
