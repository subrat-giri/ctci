package test.graph;

import graph.DirectedGraph;

import java.util.List;

public class DirectedGraphTest {
    public static void main(String[] args) {
        DirectedGraph<Character> graph = new DirectedGraph<>();
        graph.addVertex('a');
        graph.addVertex('b');
        graph.addVertex('c');
        graph.addVertex('d');
        graph.addVertex('e');
        graph.addVertex('f');

        graph.directTo('a', 'd');
        graph.directTo('f', 'b');
        graph.directTo('b', 'd');
        graph.directTo('f', 'a');
        graph.directTo('d', 'c');

        graph.getTopologicalSort().forEach(System.out::println);
    }
}
