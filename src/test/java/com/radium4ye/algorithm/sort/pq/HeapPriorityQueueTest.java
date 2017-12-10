package com.radium4ye.algorithm.sort.pq;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class HeapPriorityQueueTest {
    HeapPriorityQueue<Integer> queue = new HeapPriorityQueue<>();

    @Test
    public void sorted() throws Exception {
        for (int i = 0; i < 20; i++) {
            queue.offer(new Random().nextInt(100));
        }

        //判断是否有序
        Integer last = queue.poll();
        while (queue.peek() != null){
            assertTrue(last >= queue.peek());
            last = queue.poll();
        }
    }

}