package com.radium4ye.algorithm.graph.direction;

import com.radium4ye.algorithm.graph.BaseGraph;
import com.radium4ye.structure.Bag;
import lombok.Getter;

/**
 * 有向图
 *
 * @author radium4ye
 */
@Getter
public class DiGraph extends BaseGraph {

    /**
     * 顶点的领边
     */
    private Bag<Integer>[] adjacent;

    /**
     * 构造一个图
     *
     * @param vertices 图中顶点总数
     */
    public DiGraph(int vertices) {
        super(vertices);
        this.edge = 0;
        this.adjacent = new Bag[vertices];

        for (int i = 0; i < vertices; i++) {
            adjacent[i] = new Bag<>();
        }
    }


    /**
     * 添加一条边
     *
     * @param verticesStart 开始顶点
     * @param verticesEnd   结束顶点
     */
    public void addEdge(int verticesStart, int verticesEnd) {
        validateVertex(verticesEnd);
        validateVertex(verticesStart);

        Bag<Integer> bag = adjacent[verticesStart];
        bag.add(verticesEnd);
        edge++;
    }

    /**
     * 获取该顶点的边信息
     *
     * @param vertices 顶点
     * @return 该顶点的所有边
     */
    public Iterable<Integer> adj(int vertices) {
        return adjacent[vertices];
    }

    /**
     * 将有向图进行翻转
     *
     * @return 翻转后的有向图
     */
    public DiGraph reverse() {
        DiGraph diGraph = new DiGraph(vertices);

        //遍历每个顶点，并将相应的边，反向添加到新图中
        for (int i = 0; i < vertices; i++) {
            Bag<Integer> bag = adjacent[i];

            //索引值就是起始节点
            int verticesStart = i;
            bag.forEach(verticesEnd -> diGraph.addEdge(verticesEnd, verticesStart));
        }

        return diGraph;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices).append(" 点, ").append(edge).append(" 边 \t\n");

        //遍历所有顶点
        for (int i = 0; i < vertices; i++) {

            s.append(i).append(": ");

            //将该顶点的领边都打印出来
            adjacent[i].forEach(otherVertices -> s.append(otherVertices).append(" "));
            s.append("\t\n");
        }
        return s.toString();
    }

}
