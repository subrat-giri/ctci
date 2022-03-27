package graph;

import java.util.*;
import java.util.stream.Collectors;

public class DirectedGraph<E> {

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

        boolean isDirectedTo(Node<E> node) {
            return list.contains(node);
        }

        int numOfConnection() {
            return list.size();
        }

        List<E> getDirectedElements() {
            return list.stream().map(Node::getData).toList();
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
        Node<E> node = new Node<E>(data);
        listOfNodes.add(node);
    }

    public void directTo(E from, E to) {
        Node<E> node1 = listOfNodes.stream().filter(node -> node.data.equals(to)).findFirst().orElseThrow();
        listOfNodes.stream().filter(node -> node.getData().equals(from))
                .findFirst().ifPresent(node -> node.directTo(node1));
    }

    public boolean isDirectionExist(E from, E to) {
        Optional<Node<E>> optionalENode = listOfNodes.stream().filter(temp -> temp.getData().equals(to))
                .findFirst();
        if (optionalENode.isEmpty()) {
            return false;
        }
        Optional<Boolean> check = listOfNodes.stream().filter(node -> node.getData().equals(from))
                .findFirst().map(node -> node.isDirectedTo(optionalENode.get()));
        return check.orElse(false);
    }

    public List<E> getElements() {
        return listOfNodes.stream().map(Node::getData).toList();
    }

    public List<E> getDirectedElements(E from) {
        return listOfNodes.stream().filter(node -> node.getData().equals(from))
                .findFirst().map(Node::getDirectedElements).orElse(Collections.emptyList());
    }

    public Iterator<E> getDFSIterator(E element) {
        Node<E> node = listOfNodes.stream().filter(n -> n.getData().equals(element))
                .findFirst().orElseThrow();
        return new DFSIterator(node);
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

    private class DFSIterator implements Iterator<E> {
        Map<E, State> stateMap = listOfNodes.stream().collect(Collectors.toMap(Node::getData, e -> State.UNVISITED));
        Deque<Node<E>> stack;
        DFSIterator(Node<E> node) {
            stack = new LinkedList<>();
            stack.push(node);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            Node<E> node;
            do {
                node = stack.pop();
            } while (State.VISITED.equals(stateMap.get(node.data)));
            stateMap.replace(node.data, State.VISITED);
            node.getDirectedNodes().forEach(n -> {
                if (State.UNVISITED.equals(stateMap.get(n.getData()))) {
                    stack.push(n);
                }
            });
            return node.data;
        }
    }

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
