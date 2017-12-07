package com.radium4ye.algorithm.graph.undirection.weight;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class EdgeWeightGraphTest {

    EdgeWeightGraph graph;

    @Before
    public void setUp() throws Exception {
//        graph = new EdgeWeightGraph(6);
//        graph.addEdge(new Edge(0,1,1.0));
//        graph.addEdge(new Edge(0,2,1.2));
//        graph.addEdge(new Edge(1,3,0.7));
//        graph.addEdge(new Edge(2,3,0.6));
//        graph.addEdge(new Edge(1,5,1.3));
//
//        graph.addEdge(new Edge(1,4,2.0));
//        graph.addEdge(new Edge(4,5,0.4));
//        graph.addEdge(new Edge(3,5,0.5));

        BufferedReader br = new BufferedReader(new InputStreamReader(EdgeWeightGraphTest.class.getClassLoader().getResourceAsStream("1000EWG.txt")));
        Integer vertices = Integer.valueOf(br.readLine());
        graph = new EdgeWeightGraph(vertices);
        Integer edge = Integer.valueOf(br.readLine());
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split("\\s");
            graph.addEdge(new Edge(Integer.valueOf(data[0]),Integer.valueOf(data[1]),Double.valueOf(data[0])));

        }
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