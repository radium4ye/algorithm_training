package com.radium4ye.algorithm.graph.direction.weight;

import com.radium4ye.algorithm.graph.undirection.weight.EdgeWeightGraphTest;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class EdgeWeightDiGraphTest {

    EdgeWeightDiGraph graph;

    @Before
    public void setUp() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(EdgeWeightGraphTest.class.getClassLoader().getResourceAsStream("10000EWG.txt")));
        Integer vertices = Integer.valueOf(br.readLine());
        graph = new EdgeWeightDiGraph(vertices);
        Integer edge = Integer.valueOf(br.readLine());
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split("\\s");
            graph.addEdge(new DiEdge(Integer.valueOf(data[0]), Integer.valueOf(data[1]), Double.valueOf(data[2])));
        }
    }

    @Test
    public void dijkstra() {
        DijkstraSP dijkstraSP = new DijkstraSP(graph, 0);
        dijkstraSP.getSelect().forEach(System.out::println);
    }

}