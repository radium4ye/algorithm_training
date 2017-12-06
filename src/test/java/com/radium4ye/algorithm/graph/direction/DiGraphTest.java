package com.radium4ye.algorithm.graph.direction;

import com.radium4ye.structure.MyStack;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * @author radium4ye
 */
public class DiGraphTest {

    private DiGraph diGraph;

    @Before
    public void init() {
        diGraph = new DiGraph(10);
        diGraph.addEdge(0, 1);
        diGraph.addEdge(2, 0);
//        diGraph.addEdge(2,3);
        diGraph.addEdge(3, 2);
        diGraph.addEdge(4, 3);
        diGraph.addEdge(4, 2);
        diGraph.addEdge(0, 5);
//        diGraph.addEdge(5,4);
        diGraph.addEdge(3, 5);
        diGraph.addEdge(6, 0);
        diGraph.addEdge(6, 4);

    }

    @Test
    public void reverse() throws Exception {
        Iterator<Integer> iterator = diGraph.adj(0).iterator();
        assertEquals(iterator.next(), 5);
        assertEquals(iterator.next(), 1);

        DiGraph diGraphReverse = diGraph.reverse();
        iterator = diGraphReverse.adj(1).iterator();
        assertEquals(iterator.next(), 0);
        iterator = diGraphReverse.adj(5).iterator();
        iterator.next();
        assertEquals(iterator.next(), 0);

    }

    @Test
    public void directedDFS() throws Exception {
        DirectedDFS directedDFS = new DirectedDFS(diGraph, 0);
        assertEquals(directedDFS.marked(2), true);
        assertEquals(directedDFS.marked(6), false);
    }

    @Test
    public void topological() throws Exception {
        Topological topological = new Topological(diGraph);
        MyStack<Integer> myStack = topological.getReversePostOrder();
        while (!myStack.isEmpty()) {
            System.out.println(myStack.pop());
        }
    }

    @Test
    public void kosarajuSCC() throws Exception {
        diGraph.addEdge(2, 3);
        diGraph.addEdge(5,4);

        diGraph.addEdge(8,9);
        diGraph.addEdge(9,8);
        KosarajuSCC kosarajuSCC = new KosarajuSCC(diGraph);

        assertEquals(kosarajuSCC.stronglyConnect(4,5),true);

        IntStream.range(0, kosarajuSCC.getCount()).forEach(i -> {
            System.out.print("\t\n" + i + ":");
            for (int j = 0; j < diGraph.getVertices(); j++) {
                int value = kosarajuSCC.getStronglyComponent()[j];
                if( value == i){
                    System.out.print(" " + j);
                }
            }
        });
    }
}