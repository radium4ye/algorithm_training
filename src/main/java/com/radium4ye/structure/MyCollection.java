package com.radium4ye.structure;

/**
 * @author radium4ye
 */
public abstract class MyCollection<Item> {

    /**
     * 链表总长
     */
    protected int count;

    /**
     * 获取集合大小
     *
     * @return 集合大小
     */
    public int size() {
        return count;
    }

    /**
     * 判断集合对否为空
     *
     * @return {@code true} 集合为空 {@code false} 集合不为空
     */
    public boolean isEmpty() {
        return count != 0;
    }
}
