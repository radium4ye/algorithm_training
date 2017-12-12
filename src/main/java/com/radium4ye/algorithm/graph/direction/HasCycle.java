package com.radium4ye.algorithm.graph.direction;

import com.radium4ye.algorithm.graph.direction.weight.DiEdge;
import com.radium4ye.algorithm.graph.direction.weight.EdgeWeightDiGraph;
import com.radium4ye.structure.MyStack;
import lombok.Getter;

/**
 * 检查是否包含负循环
 *
 * @author radium4ye
 */
public class HasCycle {

    /**
     * 该顶点是否检查过了
     */
    private boolean[] marked;

    /**
     * 该顶点是否在栈帧中
     */
    private boolean[] onStack;

    /**
     * 到该顶点的路径
     */
    private DiEdge[] edgeTo;

    /**
     * 环的路径
     */
    @Getter
    private MyStack<DiEdge> cycle;

    /**
     * 构造函数
     * @param diGraph   需要检查的图
     */
    public HasCycle(EdgeWeightDiGraph diGraph) {
        //初始化变量、遍历每一个顶点
        marked = new boolean[diGraph.getVertices()];
        onStack = new boolean[diGraph.getVertices()];
        edgeTo = new DiEdge[diGraph.getVertices()];
        for (int i = 0; i < diGraph.getVertices(); i++) {
            if (!marked[i]) {
                dfs(diGraph, i);
            }
        }
    }

    /**
     * 进行深度优先计算
     * @param diGraph   图
     * @param vertices  检查的顶点
     */
    public void dfs(EdgeWeightDiGraph diGraph, int vertices) {
        //标记参数
        marked[vertices] = true;
        //用于检查是否循环
        onStack[vertices] = true;

        //遍历该点的所有领边
        for (DiEdge diEdge : diGraph.adj(vertices)) {
            int to = diEdge.getTo();

            //已经找到循环
            if (cycle != null) {
                return;
            }

            //如果没被标记，继续递归
            else if (!marked[to]) {
                edgeTo[to] = diEdge;
                dfs(diGraph, to);
            }

            //如果 to 顶点在递归栈中，说明有循环
            else if (onStack[to]) {
                cycle = new MyStack<>();

                DiEdge f = diEdge;
                while (f.getFrom() != to) {
                    cycle.push(f);
                    f = edgeTo[f.getFrom()];
                }
                cycle.push(f);

                return;
            }
        }

        onStack[vertices] = false;
    }
}
