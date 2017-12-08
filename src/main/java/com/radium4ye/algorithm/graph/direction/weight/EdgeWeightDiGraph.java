package com.radium4ye.algorithm.graph.direction.weight;

import com.radium4ye.algorithm.graph.BaseGraph;
import com.radium4ye.structure.MyStack;

/**
 * 有向加权图
 *
 * @author radium4ye
 */
public class EdgeWeightDiGraph extends BaseGraph {

    /**
     * 记录顶点的领边
     */
    private MyStack<DiEdge>[] edges;

    /**
     * 构建初始图
     *
     * @param verticesNum 顶点数
     */
    public EdgeWeightDiGraph(int verticesNum) {
        super(verticesNum);
        this.edge = 0;
        edges = new MyStack[verticesNum];
        for (int i = 0; i < verticesNum; i++) {
            edges[i] = new MyStack<>();
        }
    }

    /**
     * 添加一条边
     *
     * @param edge 有向加权边
     */
    public void addEdge(DiEdge edge) {
        //校验顶点有效性
        int v1 = edge.getForm();
        int v2 = edge.getTo();
        validateVertex(v1);
        validateVertex(v2);

        edges[v1].push(edge);
    }

    /**
     * 获取该顶点的全部边
     *
     * @param vertices 某顶点
     * @return 该顶点的全部边
     */
    public Iterable<DiEdge> adj(int vertices) {
        return edges[vertices];
    }

    /**
     * 获取所有边
     *
     * @return 全部边
     */
    public MyStack<DiEdge> edges() {
        MyStack<DiEdge> temp = new MyStack<>();

        //遍历所有顶点
        for (int i = 0; i < vertices; i++) {
            edges[i].forEach(temp::push);
        }

        return temp;
    }
}
