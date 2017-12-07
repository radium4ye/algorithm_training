package com.radium4ye.algorithm.graph.undirection.weight;

import com.radium4ye.algorithm.sort.SelectSort;
import com.radium4ye.structure.MyStack;
import lombok.Getter;

/**
 * 最小生成树
 *
 * @author radium4ye
 */
@Getter
public class MST {

    /**
     * 已经检查过的顶点
     * 已经连通的顶点
     */
    private boolean[] marked;

    /**
     * 选择的边
     */
    private MyStack<Edge> selectEdges;

    /**
     * 最小生成树
     *
     * @param graph 无向加权图
     */
    public MST(EdgeWeightGraph graph) {
        marked = new boolean[graph.getVertices()];
        selectEdges = new MyStack<>();

        //获取全部边，并进行降序排序
        MyStack<Edge> myStack = graph.edges();
        Edge[] array = new Edge[myStack.size()];
        array = myStack.toArray(array);

        //降序排序，获得最小边
        SelectSort.sort(array, false);
        //选择边数
        int selectNum = 0;
        for (Edge edge : array) {

            //判断该边的两个顶点是否连通
            if (!marked[edge.getVertices1()] || !marked[edge.getVertices2()]) {

                marked[edge.getVertices1()] = true;
                marked[edge.getVertices2()] = true;
                selectNum++;
                selectEdges.push(edge);

                //如果已经生成了最小树，就结束循环
                if (selectNum >= graph.getVertices() - 1) {
                    break;
                }
            }
        }

    }
}
