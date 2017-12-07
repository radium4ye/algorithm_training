package com.radium4ye.algorithm.graph.undirection.weight;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class EdgeWeightGraphTest {

    EdgeWeightGraph graph;

    @Before
    public void setUp() throws Exception {
        graph = new EdgeWeightGraph(6);
        graph.addEdge(new Edge(0,1,1.0));
        graph.addEdge(new Edge(0,2,1.2));
        graph.addEdge(new Edge(1,3,0.7));
        graph.addEdge(new Edge(2,3,0.6));
        graph.addEdge(new Edge(1,5,1.3));

        graph.addEdge(new Edge(1,4,2.0));
        graph.addEdge(new Edge(4,5,0.4));
        graph.addEdge(new Edge(3,5,0.5));
    }

//    @Test
//    public void constructor() throws Exception {
//
//    }

    @Test
    public void mst() throws Exception {
        MST mst = new MST(graph);
        mst.getSelectEdges().forEach(edge -> {
            System.out.println(edge);
        });
    }
}