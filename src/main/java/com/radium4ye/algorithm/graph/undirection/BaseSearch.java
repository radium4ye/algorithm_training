package com.radium4ye.algorithm.graph.undirection;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 搜索基类 定义了一些通用的方法
 * @author Radium
 */
@Data
public abstract class BaseSearch {

    /**
     * 标记该节点是否检查过了
     */
    protected Map<Integer, Boolean> marked = new HashMap<>();

    /**
     * 检索流程中，每一个节点的上一个检索节点
     * {@code key 子节点}
     * {@code value 父节点}
     */
    protected Map<Integer,Integer> edgeTo;

    /**
     * 该节点和 v 节点是否连通
     * @param v
     */
    public boolean hasPathTo(int v){
        return marked.getOrDefault(v,false);
    }

    /**
     * 获取从起始顶点 到 目标顶点的路径
     * @param aimVertices 目标订单
     * @return 路径栈
     */
    public Stack<Integer> pathTo(int aimVertices){
        //不连通
        if(!hasPathTo(aimVertices)){
            return null;
        }

        //定义返回的数据栈
        Stack<Integer> stack = new Stack<>();
        stack.push(aimVertices);

        //一直查询父节点，直到查询不到为止
        Integer parent = edgeTo.get(aimVertices);
        while (parent != null){
            stack.push(parent);
            parent = edgeTo.get(parent);
        }

        return stack;
    }

}
