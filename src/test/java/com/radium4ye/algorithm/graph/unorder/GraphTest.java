package com.radium4ye.algorithm.graph.unorder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Radium
 */
public class GraphTest {

    private Graph graph = new Graph();

    @Before
    public void before(){
        graph.addEdge(1,2);
        graph.addEdge(1,0);
        graph.addEdge(6,7);
        graph.addEdge(7,8);
        graph.addEdge(8,9);
        graph.addEdge(9,6);
        graph.addEdge(2,0);
    }

    @Test
    public void main(){

        System.out.println(graph.toString());

        assertEquals(graph.vertices(),7);
        assertEquals(graph.getEdge(),7);
    }

    @Test
    public void dfs(){
        DepthFirstSearch search = new DepthFirstSearch(graph,6);

        assertEquals(search.getVerticesCount(),4);
        assertEquals(search.marked(8),true);
    }

}