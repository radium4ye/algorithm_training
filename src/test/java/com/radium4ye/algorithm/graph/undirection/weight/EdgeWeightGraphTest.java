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
        BufferedReader br = new BufferedReader(new InputStreamReader(EdgeWeightGraphTest.class.getClassLoader().getResourceAsStream("mediumEWG.txt")));
        Integer vertices = Integer.valueOf(br.readLine());
        graph = new EdgeWeightGraph(vertices);
        Integer edge = Integer.valueOf(br.readLine());
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split("\\s");
            graph.addEdge(new Edge(Integer.valueOf(data[0]),Integer.valueOf(data[1]),Double.valueOf(data[2])));

        }
    }

    static int allWeight = 0 ;
    static int allWeight2 = 0 ;
    @Test
    public void mst() throws Exception {
        KruskalMST mst = new KruskalMST(graph);

        mst.getSelectEdges().forEach(edge -> {
            allWeight += edge.getWeight();
        });
    }

    @Test
    public void prim() throws Exception {
        PrimMST mst = new PrimMST(graph);
        mst.getSelect().forEach(edge -> {
            allWeight2 += edge.getWeight();
        });
        assertEquals(allWeight,allWeight2);
    }


}