package com.radium4ye.algorithm.graph.undirection.weight;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author radium4ye
 */
public class EdgeWeightGraphTest {

    EdgeWeightGraph graph;

    @Before
    public void setUp() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(EdgeWeightGraphTest.class.getClassLoader().getResourceAsStream("10000EWG.txt")));
        Integer vertices = Integer.valueOf(br.readLine());
        graph = new EdgeWeightGraph(vertices);
        Integer edge = Integer.valueOf(br.readLine());
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split("\\s");
            graph.addEdge(new Edge(Integer.valueOf(data[0]),Integer.valueOf(data[1]),Double.valueOf(data[0])));

        }
    }

    @Test
    public void mst() throws Exception {
        MyMST mst = new MyMST(graph);
        mst.getSelectEdges().forEach(edge -> {
            System.out.println(edge);
        });
    }
}