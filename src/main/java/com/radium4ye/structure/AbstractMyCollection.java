package com.radium4ye.structure;

import java.util.Collection;
import java.util.Iterator;

/**
 * 集合抽象类，让自定义的集合可以适配器模式实现相关接口
 * @author radium4ye
 */
public abstract class AbstractMyCollection<Item> implements Collection<Item> {

    /**
     * 链表总长
     */
    protected int count;

    /**
     * 获取集合大小
     *
     * @return 集合大小
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * 判断集合对否为空
     *
     * @return {@code true} 集合为空 {@code false} 集合不为空
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public boolean add(Item item) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Item> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }
}
