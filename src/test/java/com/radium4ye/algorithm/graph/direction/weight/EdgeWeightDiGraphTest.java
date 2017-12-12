package com.radium4ye.algorithm.graph.direction.weight;

import com.radium4ye.algorithm.graph.direction.DiGraph;
import com.radium4ye.algorithm.graph.direction.Topological;
import com.radium4ye.algorithm.graph.undirection.weight.EdgeWeightGraphTest;
import com.radium4ye.structure.MyStack;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author radium4ye
 */
public class EdgeWeightDiGraphTest {

    EdgeWeightDiGraph graph;

    @Before
    public void setUp() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(EdgeWeightGraphTest.class.getClassLoader().getResourceAsStream("tinyEWG.txt")));
        Integer vertices = Integer.valueOf(br.readLine());
        graph = new EdgeWeightDiGraph(vertices);
        Integer edge = Integer.valueOf(br.readLine());
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split("\\s");
            graph.addEdge(new DiEdge(Integer.valueOf(data[0]), Integer.valueOf(data[1]), Double.valueOf(data[2])));
        }
    }

    DijkstraSP dijkstraSP;

    @Test
    public void dijkstra() {

        dijkstraSP = new DijkstraSP(graph, 0);
//        Arrays.stream(dijkstraSP.getToDist()).forEach(System.out::println);
    }

    BellmanFordSP bellmanFordSP;

    @Test
    public void bellmanFord() {
        dijkstraSP = new DijkstraSP(graph, 0);
        bellmanFordSP = new BellmanFordSP(graph, 0);
//        Arrays.stream(bellmanFordSP.getToDist()).forEach(System.out::println);

        assertArrayEquals(dijkstraSP.getToDist(), bellmanFordSP.getToDist());
    }

    @Test
    public void topologicalSP() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(EdgeWeightGraphTest.class.getClassLoader().getResourceAsStream("tinyEWDAG.txt")));
        Integer vertices = Integer.valueOf(br.readLine());
        EdgeWeightDiGraph graph = new EdgeWeightDiGraph(vertices);
        Integer edge = Integer.valueOf(br.readLine());
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split("\\s");
            graph.addEdge(new DiEdge(Integer.valueOf(data[0]), Integer.valueOf(data[1]), Double.valueOf(data[2])));
        }

        TopologicalSP topologicalSP = new TopologicalSP(graph, 5);
        for (int i = 0; i < graph.getVertices(); i++) {
            System.out.println(i + ":" + topologicalSP.getToDist()[i]);
        }
    }

    //    @Test
    public void test() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(EdgeWeightGraphTest.class.getClassLoader().getResourceAsStream("tinyEWDAG.txt")));
        Integer vertices = Integer.valueOf(br.readLine());
        DiGraph graph = new DiGraph(vertices);
        Integer edge = Integer.valueOf(br.readLine());
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split("\\s");
            graph.addEdge(Integer.valueOf(data[0]), Integer.valueOf(data[1]));
        }

        Topological topological = new Topological(graph);
        MyStack<Integer> myStack = topological.getReversePostOrder();
        while (!myStack.isEmpty()) {
            System.out.println(myStack.pop());
        }
    }

}