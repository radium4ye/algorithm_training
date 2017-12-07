package com.radium4ye.algorithm.graph.undirection.weight;

import com.radium4ye.algorithm.graph.BaseGraph;
import com.radium4ye.structure.MyStack;

import java.util.Iterator;

/**
 * 无向加权图
 *
 * @author radium4ye
 */
public class EdgeWeightGraph extends BaseGraph {

    /**
     * 记录顶点的领边
     */
    private MyStack<Edge>[] edges;

    /**
     * 构建初始图
     *
     * @param verticesNum 顶点数
     */
    public EdgeWeightGraph(int verticesNum) {
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
     * @param edge
     */
    public void addEdge(Edge edge) {
        //校验顶点有效性
        int v1 = edge.getVertices1();
        int v2 = edge.getVertices2();
        validateVertex(v1);
        validateVertex(v2);

        edges[v1].push(edge);
        edges[v2].push(edge);
    }

    /**
     * 获取该顶点的全部边
     *
     * @param vertices 某顶点
     * @return 该顶点的全部边
     */
    public Iterable<Edge> adj(int vertices) {
        return edges[vertices];
    }

    /**
     * 获取所有边
     *
     * @return 全部边
     */
    public MyStack<Edge> edges() {
        MyStack<Edge> temp = new MyStack<>();

        //遍历所有顶点
        for (int i = 0; i < vertices; i++) {
            Iterator<Edge> iterator = edges[i].iterator();
            int j = 0;

            //遍历该顶点的领边
            while (iterator.hasNext()) {
                Edge edgeTemp = iterator.next();

                //获取另一个顶点
                int anotherVertices = edgeTemp.getAnotherVertices(i);
                if (anotherVertices > i) {
                    temp.push(edgeTemp);
                } else if (anotherVertices == i) {
                    //存在自环 只添加一边
                    if (j % 2 == 0) {
                        temp.push(edgeTemp);
                    }
                }
                j++;
            }
        }

        return temp;
    }
}
