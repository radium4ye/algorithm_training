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

    private boolean[] marked;
    private boolean[] onStack;
    private DiEdge[] edgeTo;
    @Getter
    private MyStack<DiEdge> cycle;

    public HasCycle(EdgeWeightDiGraph diGraph) {
        marked = new boolean[diGraph.getVertices()];
        onStack = new boolean[diGraph.getVertices()];
        edgeTo = new DiEdge[diGraph.getVertices()];
        for (int i = 0; i < diGraph.getVertices(); i++) {
            if (!marked[i]) {
                dfs(diGraph, i);
            }
        }
    }

    public void dfs(EdgeWeightDiGraph diGraph, int vertices) {
        marked[vertices] = true;
        onStack[vertices] = true;

        for (DiEdge diEdge : diGraph.adj(vertices)) {
            int to = diEdge.getTo();

            //已经找到循环
            if(cycle != null){
                return;
            }

            //如果没被标记，继续进循环
            else if(!marked[to]){
                edgeTo[to] = diEdge;
                dfs(diGraph,to);
            }

            //如果 to 顶点在递归栈中，说明有循环
            else if(onStack[to]){
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
