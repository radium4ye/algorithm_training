package com.radium4ye.algorithm.sort.pq;

import com.radium4ye.algorithm.sort.Example;

import java.util.Comparator;

/**
 * 基于索引的优先队列
 * 可以根据索引修改操作元素
 *
 * @author radium4ye
 */
public class IndexHeapPQ<Key extends Comparable<Key>> extends HeapPriorityQueue<Key> {

    /**
     * 记录 传进来的索引值 -> 数组中的索引值
     */
    private int[] symbolTable;
    /**
     * 记录 数组中的索引值 -> 传进来的索引值
     */
    private int[] reverseSymbolTable;

    public IndexHeapPQ() {
        this(INIT_SIZE, null);
    }

    public IndexHeapPQ(int initSize, Comparator<Key> comparator) {
        super(initSize, comparator);
        symbolTable = new int[initSize];
        reverseSymbolTable = new int[initSize];
    }

    @Override
    public void exchange(Object[] array, int index1, int index2) {
        super.exchange(array, index1, index2);

        int symbolIndex1 = reverseSymbolTable[index1];
        int symbolIndex2 = reverseSymbolTable[index2];

        Example.exchange(symbolTable, symbolIndex1, symbolIndex2);
        Example.exchange(reverseSymbolTable, index1, index2);
    }

    @Override
    protected void grow() {
        super.grow();

        int[] temp = new int[arraySize];
        System.arraycopy(symbolTable, 0, temp, 0, symbolTable.length);
        symbolTable = temp;

        temp = new int[arraySize];
        System.arraycopy(reverseSymbolTable, 0, temp, 0, reverseSymbolTable.length);
        reverseSymbolTable = temp;
    }


    public boolean offer(int index, Key key) {
        symbolTable[index] = count + 1;
        reverseSymbolTable[count + 1] = index;

        return super.offer(key);
    }

    @Override
    public Key poll() {
        symbolTable[reverseSymbolTable[1]] = 0;
        reverseSymbolTable[1] = 0;

        return super.poll();
    }

    public Key getByIndex(int index) {
        return (Key) elementData[symbolTable[index]];
    }

    public void change(int index, Key key) {
        int eleIndex = symbolTable[index];
        elementData[eleIndex] = key;
        swim(eleIndex);
        sink(eleIndex);
    }
}
