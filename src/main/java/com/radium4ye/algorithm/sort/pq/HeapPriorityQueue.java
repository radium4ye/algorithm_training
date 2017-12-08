package com.radium4ye.algorithm.sort.pq;

import lombok.Getter;

import java.util.Comparator;

/**
 * 默认最大堆排序
 *
 * @author radium4ye
 */
public class HeapPriorityQueue<Key extends Comparable<Key>> extends BasePriorityQueue<Key> {

    protected static final int INIT_SIZE = 10;

    /**
     * 底层数组大小
     * 包含 0 索引
     */
    protected int arraySize;

    @Getter
    protected transient Object[] elementData;

    /**
     * 比较器
     */
    private Comparator<Key> comparator;

    public HeapPriorityQueue() {
        this(INIT_SIZE, null);
    }

    public HeapPriorityQueue(int initSize, Comparator<Key> comparator) {
        if (initSize <= 1) {
            initSize = INIT_SIZE;
        }

        elementData = new Object[initSize];
        this.comparator = comparator;
        arraySize = initSize;
    }

    protected void sink(int index) {

        //获取左右子节点的索引
        int leftChildren = index * 2;
        int rightChildren = leftChildren + 1;

        int selectIndex;
        if (leftChildren > count) {
            return;
        } else if (rightChildren > count) {
            selectIndex = leftChildren;
        } else {
            //比较两个子节点，选择较大的
            int compareResult = compare((Key) elementData[leftChildren], (Key) elementData[rightChildren]);
            if (compareResult >= 0) {
                selectIndex = leftChildren;
            } else {
                selectIndex = rightChildren;
            }
        }

        int compareResult = compare((Key) elementData[index], (Key) elementData[selectIndex]);

        //如果当前节点比较小
        if (compareResult < 0) {
            //当前节点和选择的节点进行交换
            exchange(elementData, index, selectIndex);
            //继续构建
            sink(selectIndex);
        }


    }

    /**
     * 将该节点进行上浮
     *
     * @param index 节点索引
     */
    protected void swim(int index) {
        int parentIndex = index / 2;

        //如果父节点小于1 ，所以该节点已经到堆顶了
        if (parentIndex < 1) {
            return;
        }
        int result = compare((Key) elementData[index], (Key) elementData[parentIndex]);

        //前者比较大，需要替换掉父节点
        if (result > 0) {
            exchange(elementData, parentIndex, index);
            swim(parentIndex);
        }

    }

    /**
     * 使底层数组增长
     */
    protected void grow() {
        arraySize = arraySize << 1;
        Object[] temp = new Object[arraySize];
        System.arraycopy(elementData, 0, temp, 0, elementData.length);
        elementData = temp;
    }

    @Override
    public boolean offer(Key key) {
        //判断原数组类数据长度 + 需要添加的数据长度 和 数组可用长度比较
        while (count + 1 > arraySize - 1) {
            grow();
        }
        int insertIndex = count + 1;
        elementData[insertIndex] = key;
        count++;
        swim(insertIndex);

        return true;
    }

    @Override
    public Key poll() {

        //获取返回值
        Key key = (Key) elementData[1];

        //将最后一位提取上来，重新构建堆
        exchange(elementData, 1, count);

        //数组最后一位置空，便于垃圾回收
        elementData[count] = null;
        count--;

        //构建堆
        sink(1);

        return key;
    }

    @Override
    public Key peek() {
        return (Key) elementData[1];
    }

    /**
     * 比较两个节点
     *
     * @param key1 节点1
     * @param key2 节点2
     * @return {@code 1}  节点1 大于 节点2
     * {@code 0}  节点1 等于 节点2
     * {@code -1}  节点1 小于 节点2
     */
    private int compare(Key key1, Key key2) {
        if (comparator != null) {
            return comparator.compare(key1, key2);
        } else {
            return key1.compareTo(key2);
        }
    }

    /**
     * 交换数组中元素位置
     *
     * @param array  数组
     * @param index1 元素1的索引
     * @param index2 元素2的索引
     */
    protected void exchange(Object[] array, int index1, int index2) {
        if (index1 == index2) {
            return;
        }

        Object temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
