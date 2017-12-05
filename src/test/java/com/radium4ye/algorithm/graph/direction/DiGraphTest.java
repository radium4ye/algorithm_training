package com.radium4ye.algorithm.graph.direction;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class DiGraphTest {

    private DiGraph diGraph;

    @Before
    public void init(){
        diGraph = new DiGraph(10);
        diGraph.addEdge(0,1);
        diGraph.addEdge(2,0);
        diGraph.addEdge(2,3);
        diGraph.addEdge(3,2);
        diGraph.addEdge(4,3);
        diGraph.addEdge(4,2);
        diGraph.addEdge(0,5);
        diGraph.addEdge(5,4);
        diGraph.addEdge(3,5);
        diGraph.addEdge(6,0);
        diGraph.addEdge(6,4);

    }

    @Test
    public void reverse() throws Exception {
        System.out.println(diGraph.toString());
        System.out.println(diGraph.reverse().toString());

    }

}