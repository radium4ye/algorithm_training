package com.radium4ye.algorithm.graph.direction.weight;

import com.radium4ye.structure.MyStack;
import lombok.Getter;

/**
 * 通过 Dijkstra 算法生成最短路径
 * 局限性：无法处理边的权重为负数的情况
 * 优势：最坏的情况下也有较好的性能
 * <p>
 * 这种排序算法是利用贪心算法的方法通过局部最优 达到全局最优
 * e.g. 网络路由（链路状态算法）成本计算
 *
 * @author radium4ye
 */
public class DijkstraSP {


    /**
     * {@code null} 表示不可达
     * 记录当前数到所有节点的最小边
     */
    private DiEdge[] toEdge;

    /**
     * 记录到该点的总费用
     */
    @Getter
    private Double[] distTo;

    /**
     * 记录当前节点是否被标记过
     */
    private boolean[] marked;

    /**
     * 记录选择的点
     */
    @Getter
    private MyStack<DiEdge> select = new MyStack<>();

    /**
     * 构造函数，传入必要参数
     *
     * @param diGraph       加权有向图
     * @param startVertices 开始节点
     */
    public DijkstraSP(EdgeWeightDiGraph diGraph, int startVertices) {
        marked = new boolean[diGraph.getVertices()];
        toEdge = new DiEdge[diGraph.getVertices()];
        distTo = new Double[diGraph.getVertices()];

        for (int i = 0; i < diGraph.getVertices(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[startVertices] = 0.0;
        relax(diGraph, startVertices);
    }

    public void relax(EdgeWeightDiGraph diGraph, int vertices) {
        marked[vertices] = true;
        //查询该点的领边
        diGraph.adj(vertices).forEach(diEdge -> {
            //目标顶点
            int toVertices = diEdge.getTo();

            //如果目标顶点被检查过了，就不进行重复检查 回环
            if (marked[toVertices]) {
                return;
            }

            //如果到该边有更短路径 更新
            if (distTo[diEdge.getFrom()] + diEdge.getWeight() < distTo[diEdge.getTo()]) {
                toEdge[diEdge.getTo()] = diEdge;
                //路径费用为当前边的费用 + 到该边起始节点的费用
                distTo[diEdge.getTo()] = distTo[diEdge.getFrom()] + diEdge.getWeight();

            }

        });


        //选择所有边中的最短边
        int minIndex = -1;
        double minWeight = Double.POSITIVE_INFINITY;
        for (int i = 0; i < diGraph.getVertices(); i++) {

            //如果目标顶点被检查过了(已经加入了)
            if (marked[i]) {
                continue;
            }

            if (distTo[i] < minWeight) {
                minIndex = i;
            }
        }

        if (minIndex != -1) {
            DiEdge minDiEdge = toEdge[minIndex];
            select.push(minDiEdge);

            relax(diGraph, minDiEdge.getTo());
        }

    }
}
