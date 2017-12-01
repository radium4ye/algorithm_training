package com.radium4ye.algorithm.sort.pq;

/**
 * 优先级队列 定义基础API
 * @author Radium
 */
public interface BasePQ<Key extends Comparable<Key>> {

    /**
     * 先优先级队列插入一个元素
     * @param k 元素
     */
    void insert(Key k);

    /**
     * 获取最大的元素
     * @return
     */
    Key max();

    /**
     * 删除并返回最大的元素
     * @return
     */
    Key delMax();

    /**
     * @return 返回队列是否为空
     */
    boolean isEmpty();

    /**
     *
     * @return 返回优先级队列中元素个数
     */
    int size();
}
