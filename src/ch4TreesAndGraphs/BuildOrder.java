package ch4TreesAndGraphs;

import java.util.*;
import java.util.stream.Collectors;

public class BuildOrder<E> {

    private static class Node<E> {
        E data;
        List<Node<E>> list;

        Node(E data) {
            this.data = data;
            list = new LinkedList<>();
        }

        E getData() {
            return data;
        }
        void directTo(Node<E> node) {
            list.add(node);
        }

        List<Node<E>> getDirectedNodes() {
            return list;
        }
    }
    private final List<Node<E>> listOfNodes = new ArrayList<>();

    public void addVertex(E data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid argument.");
        }
        boolean exist = listOfNodes.stream().anyMatch(node -> node.data.equals(data));
        if (exist) throw new IllegalArgumentException("Element already exist.");
        Node<E> node = new Node<>(data);
        listOfNodes.add(node);
    }

    public void directTo(E from, E to) {
        Node<E> node1 = listOfNodes.stream().filter(node -> node.data.equals(to)).findFirst().orElseThrow();
        listOfNodes.stream().filter(node -> node.getData().equals(from))
                .findFirst().ifPresent(node -> node.directTo(node1));
    }

    public List<E> getTopologicalSort() {
        Map<E, State> stateMap = listOfNodes.stream().collect(Collectors.toMap(Node::getData, e -> State.UNVISITED));
        Deque<E> topologicalStack = new LinkedList<>();
        for (Node<E> node : listOfNodes) {
            if (State.UNVISITED.equals(stateMap.get(node.getData()))) {
                visitDFSMode(node, stateMap, topologicalStack);
            }
        }
        return topologicalStack.stream().toList();
    }

    private void visitDFSMode(Node<E> node, Map<E, State> stateMap, Deque<E> topologicalStack) {
        stateMap.replace(node.getData(), State.VISITED);
        List<Node<E>> adjacentNodes = node.getDirectedNodes();
        for (Node<E> e : adjacentNodes) {
            if (State.UNVISITED.equals(stateMap.get(e.getData()))) {
                visitDFSMode(e, stateMap, topologicalStack);
            }
        }
        topologicalStack.push(node.getData());
    }

    private enum State {
        UNVISITED, VISITED
    }

    public static void main(String[] args) {
        BuildOrder<Character> graph = new BuildOrder<>();
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