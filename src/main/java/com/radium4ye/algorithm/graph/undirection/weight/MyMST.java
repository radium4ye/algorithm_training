package com.radium4ye.algorithm.graph.undirection.weight;

import com.radium4ye.algorithm.sort.pq.HeapPriorityQueue;
import com.radium4ye.structure.MyStack;
import lombok.Getter;

/**
 * 最小生成树，算法有误
 * 自己实现，感觉在最后的遍的时候，会遍历很多无用的数据
 * 将所有边进行降序排序，并遍历一边。选择为连通的边
 * <p>
 * 空间和 edge 成正比
 * 时间和 edge * log edge 成正比
 * 时间主要和排序算法有关
 *
 * 导致生成多个连通子图，缺少这个几个连通子图之间互联的边
 *
 * @author radium4ye
 */
@Deprecated
@Getter
public class MyMST {

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
    public MyMST(EdgeWeightGraph graph) {
        marked = new boolean[graph.getVertices()];
        selectEdges = new MyStack<>();

        //获取全部边，并进行降序排序
        MyStack<Edge> myStack = graph.edges();
        //获取最小边
        HeapPriorityQueue<Edge> queue = new HeapPriorityQueue<>(myStack.size(), (o1, o2) -> -1 * o1.compareTo(o2));
        //没实现这个方法 (ノಠ益ಠ)ノ彡┻━┻
//        queue.addAll(myStack);
        while (!myStack.isEmpty()) {
            queue.offer(myStack.pop());
        }

        //选择边数
        int selectNum = 0;
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
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
