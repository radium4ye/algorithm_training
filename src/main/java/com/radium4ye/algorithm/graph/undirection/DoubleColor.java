package com.radium4ye.algorithm.graph.undirection;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 判断图是否支持二色分图
 *
 * @author radium4ye
 */
public class DoubleColor {

    /**
     * 标记该节点是否检查过了
     */
    private Map<Integer, Boolean> marked = new HashMap<>();
    /**
     * 每个顶点的颜色
     */
    private Map<Integer, Boolean> pointColor = new HashMap<>();

    /**
     * 是否支持二色分图
     */
    @Getter
    private boolean doubleColor = true;


    /**
     * 构造函数
     *
     * @param g 无向图
     */
    public DoubleColor(Graph g) {
        //对每个顶点都进行搜索
        for (int i = 0; i < g.vertices(); i++) {
            if (!marked.getOrDefault(i, false)) {
                dfs(g, i);
            }
        }
    }

    /**
     * 对图进行深度优先搜索
     *
     * @param g     图
     * @param point 起始点
     */
    public void dfs(Graph g, int point) {
        //没被标记的点 进行标记
        marked.put(point, true);

        //遍历该点的连通节点
        for (int next : g.adj(point)) {

            //如果该顶点没被遍历过就进行递归搜索，反正检查顶点颜色是否符合要求
            if (!marked.getOrDefault(next, false)) {
                pointColor.put(next, !pointColor.get(point));
                dfs(g, next);
            } else {

                //被标记的点 进行检查
                if (Objects.equals(pointColor.get(point), pointColor.get(next))) {
                    doubleColor = false;
                }

            }
        }
    }

}
