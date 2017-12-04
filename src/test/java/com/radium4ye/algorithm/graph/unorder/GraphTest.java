package com.radium4ye.algorithm.graph.unorder;

import com.radium4ye.algorithm.test.std.Stack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Radium
 */
public class GraphTest {

    private Graph graph = new Graph();

    @Before
    public void before() {
        graph.addEdge(1, 2);
        graph.addEdge(1, 0);
        graph.addEdge(6, 7);
        graph.addEdge(7, 8);
        graph.addEdge(8, 9);
        graph.addEdge(9, 6);
        graph.addEdge(2, 0);
        graph.addEdge(2, 5);
        graph.addEdge(6, 5);
        graph.addEdge(5, 9);
    }

    @Test
    public void main() {

        System.out.println(graph.toString());

        assertEquals(graph.vertices(), 7);
        assertEquals(graph.getEdge(), 10);
    }

    @Test
    public void dfs() {
        DepthFirstSearch search = new DepthFirstSearch(graph, 0);

//        assertEquals(search.getVerticesCount(),0);
        assertEquals(search.hasPathTo(8), true);

        java.util.Stack<Integer> stack = search.pathTo(8);
        int[] temp = new int[stack.size()];
        int point = 0;
        while (!stack.empty()) {
            temp[point++] = stack.pop();
        }

        assertArrayEquals(temp, new int[]{0, 1, 2, 5, 6, 7, 8});
    }

}