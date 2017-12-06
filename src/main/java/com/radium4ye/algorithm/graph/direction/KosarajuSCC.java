package com.radium4ye.algorithm.graph.direction;

import com.radium4ye.structure.MyStack;
import lombok.Getter;

/**
 * Kosaraju-Sharir 算法 （实现容易，理解困难）
 * 强连通计算相关
 * <p>
 * 该算法使用的是邻接表来组成图
 * 时间|空间都和 V+E 成正比
 * <p>
 * GR 图 = G 图的逆向图
 * s->v = 图中包含 s 到 v 的路径
 * 证明：
 * 假设构造函数中的（GR 图的逆后序）遍历顺序为 s.....v
 * 根据 dfs 搜索规则知：每个和 s 的连通顶点 v，都会再构造函数中被 dfs(G,s) 所访问到
 * 只要证明 G 中包含 v->s 即可，等价于 GR 中存在 s->v
 * 按照逆后续深度优选搜索得知，在 GR 中进行 dfs 中 dfs(GR,v) 必先比 dfs(GR,s) 先结束，
 * 只有两种情况，其一 dfs(GR,v) 比 dfs(GR,s) 先开始（而且先结束），由于我们得知 GR 中包含 v-> s 所以这个情况不可能发生
 * 其二只有可能包含在 dfs(GR,s) 中，
 * 这种情况意味着 GR 图中有一条路径 s->v,G 图中 v->s
 * 证毕
 *
 * @author radium4ye
 */
@Getter
public class KosarajuSCC {
    /**
     * 节点是否检查过了
     */
    private boolean[] marked;

    /**
     * 连通分量数
     */
    private int count;

    /**
     * 每个顶点的 连通分量标识符
     */
    private int[] stronglyComponent;

    /**
     * 算法构造，传入有向图
     * @param diGraph   有向图
     */
    public KosarajuSCC(DiGraph diGraph) {
        marked = new boolean[diGraph.getVertices()];
        stronglyComponent = new int[diGraph.getVertices()];

        //获取反向图的逆后续排列
        Topological topological = new Topological(diGraph.reverse());
        MyStack<Integer> reversePost = topological.getReversePostOrder();

        //根据反向图的逆后续来遍历原图
        while (!reversePost.isEmpty()) {
            Integer vertices = reversePost.pop();
            if (!marked[vertices]) {
                dfs(diGraph, vertices);
                count++;
            }
        }

    }

    /**
     * 进行深度优先搜索
     * @param diGraph   有向图
     * @param vertices  顶点
     */
    public void dfs(DiGraph diGraph, int vertices) {
        marked[vertices] = true;
        stronglyComponent[vertices] = count;

        diGraph.adj(vertices).forEach(nextVertices -> {
            if (!marked[nextVertices]) {
                dfs(diGraph, nextVertices);
            }
        });
    }

    /**
     * 判断两个顶点是否强连通
     * @param v1    顶点1
     * @param v2    顶点2
     * @return      是否强连通
     */
    public boolean stronglyConnect(int v1, int v2) {
        return stronglyComponent[v1] == stronglyComponent[v2];
    }
}
