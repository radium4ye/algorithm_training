package com.radium4ye.algorithm.sort.pq;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * 优先级队列 定义基础API
 *
 * @author Radium
 */
public abstract class BasePriorityQueue<Key extends Comparable<Key>> implements Queue<Key> {

    protected int count;

    /**
     * 判断队列是否为空
     *
     * @return 队列是否为空
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * 返回优先级队列中元素个数
     *
     * @return 元素个数
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * 添加元素
     *
     * @return 结果
     * @throws NoSuchElementException if this queue is empty
     */
    @Override
    public boolean add(Key key) {
        boolean result = offer(key);
        if (key == null) {
            throw new NoSuchElementException("queue is empty");
        } else {
            return result;
        }
    }

    @Override
    public abstract boolean offer(Key key);

    /**
     * 删除元素
     *
     * @return 被删除的元素
     * @throws NoSuchElementException if this queue is empty
     */
    @Override
    public Key remove() {
        Key key = poll();
        if (key == null) {
            throw new NoSuchElementException("queue is empty");
        } else {
            return key;
        }
    }

    @Override
    public abstract Key poll();

    /**
     * 检查元素
     *
     * @return 首部元素
     * @throws NoSuchElementException if this queue is empty
     */
    @Override
    public Key element() {
        Key key = peek();
        if (key == null) {
            throw new NoSuchElementException("queue is empty");
        } else {
            return key;
        }
    }

    @Override
    public abstract Key peek();

    @Override
    public boolean removeIf(Predicate<? super Key> filter) {
        return false;
    }

    @Override
    public Spliterator<Key> spliterator() {
        return null;
    }

    @Override
    public Stream<Key> stream() {
        return null;
    }

    @Override
    public Stream<Key> parallelStream() {
        return null;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Key> iterator() {
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

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Key> c) {
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
}
