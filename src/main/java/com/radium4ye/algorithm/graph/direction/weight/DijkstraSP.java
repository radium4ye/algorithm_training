package com.radium4ye.algorithm.graph.direction.weight;

import com.radium4ye.algorithm.sort.pq.IndexHeapPQ;
import com.radium4ye.structure.MyStack;
import lombok.Getter;

/**
 * 通过 Dijkstra 算法生成最短路径
 * 可解决权重非负的加权有向图
 * e.g. 网络路由（链路状态算法）成本计算
 *
 * @author radium4ye
 */
public class DijkstraSP {

    IndexHeapPQ<DiEdge> indexHeapPQ = new IndexHeapPQ<>();

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
        relax(diGraph, startVertices);
    }

    public void relax(EdgeWeightDiGraph diGraph, int vertices) {
        marked[vertices] = true;
        //查询该点的领边
        diGraph.adj(vertices).forEach(diEdge -> {
            //目标顶点
            int toVertices = diEdge.getTo();

            //如果目标顶点被检查过了，就不进行重复检查
            if (marked[toVertices]) {
                return;
            }

            DiEdge old = indexHeapPQ.getByIndex(toVertices);

            //取最小值的边
            if (old == null) {
                indexHeapPQ.offer(toVertices, diEdge);
            } else if (old.getWeight() > diEdge.getWeight()) {
                indexHeapPQ.change(toVertices, diEdge);
            }
        });

        DiEdge diEdge;
        do {
            diEdge = indexHeapPQ.poll();
            if (diEdge == null) {
                return;
            }
        } while (marked[diEdge.getTo()]);

        select.push(diEdge);
        relax(diGraph, diEdge.getTo());

    }
}
