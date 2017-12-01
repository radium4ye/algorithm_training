package com.radium4ye.algorithm.search.order;

import com.radium4ye.algorithm.search.BaseST;

import java.util.NoSuchElementException;

/**
 * ordered symbol table
 * 定义有序符号表的一些通用方法
 * @author Radium
 */
public interface BaseOrderST<Key extends Comparable<Key>,Value> extends BaseST<Key,Value>{

    /**
     * 最小键
     * @return  最小键
     */
    Key min();

    /**
     * 最大键
     * @return  最大键
     */
    Key max();

    /**
     * 小于等于 {@code key} 的最大键
     * @param key
     * @return  小于等于 {@code key} 的最大键
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    Key floor(Key key);

    /**
     * 大于等于 {@code key} 的最小键
     * @param key
     * @return  大于等于 {@code key} 的最小键
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    Key ceiling(Key key);

    /**
     * {@code key} 在有序符号表中的索引值
     * @param key 键
     * @return  索引值
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    int rank(Key key);

    /**
     * {@code rank} 在有序符号表中的索引对应的键
     * @param rank 索引值
     * @return 键
     */
    Key select(int rank);

    /**
     * 删除最小值
     */
    void deleteMin();

    /**
     * 删除最大值
     */
    void deleteMax();

    /**
     * 表中在这个返回内的键值数
     * @param lo    低位
     * @param hi    高位
     * @return
     */
    int size(Key lo,Key hi);

    /**
     * 表中在这个返回内键的集合
     * @param lo    低位
     * @param hi    高位
     * @return
     */
    Iterable<Key> keys(Key lo,Key hi);
}
