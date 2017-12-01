package com.radium4ye.algorithm.search;

/**
 * symbol table
 * 定义基本符号表的一些通用方法
 * @author Radium
 */
public interface BaseST<Key extends Comparable<Key>,Value> {

    /**
     * 将键值对存入表中
     * 若值为 null 则将键从表中删除
     * @param key   键
     * @param value 值
     */
    void put(Key key,Value value);

    /**
     * 获取键 key 对应的值
     * @param key   键
     * @return      值
     */
    Value get(Key key);

    /**
     * 从表中删去键 key (及其对应的值)
     * @param key
     */
    void delete(Key key);

    /**
     * 键 key 在表中是否有对应的值
     * @param key
     * @return
     */
    boolean contains(Key key);

    /**
     * 表中的键值数
     * @return
     */
    int size();

    /**
     * 表中所有键的集合
     * @return
     */
    Iterable<Key> keys();

}
