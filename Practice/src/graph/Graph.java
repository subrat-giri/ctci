package graph;

import java.util.*;

public class Graph<E> {
    private final ArrayList<Node<E>> nodes;
    private final int size;
    private static class Node<E> {
        E data;
        List<Integer> children;
        Node(E data) {
            this.data = data;
            children = new LinkedList<>();
        }

        List<Integer> getChildList() {
            return children;
        }
    }

    public Graph(int numOfVertices, E[] data) {
        this.size = numOfVertices;
        nodes = new ArrayList<>(numOfVertices);
        for (int i = 0; i < numOfVertices; i++) {
            nodes.add(i, new Node<>(data[i]));
        }
    }

    public void connect(int vertex, int ... connectedWith) {
        for (int i : connectedWith) {
            if (vertex < 0 || vertex >= size || i < 0 || i >= size) {
                throw new IllegalArgumentException("Invalid Nodes");
            }
            nodes.get(vertex).getChildList().add(i);
        }
    }

    public Iterator<E> getBFSIterator() {
        return new BFSIterator();

    }

    public Iterator<E> getBFSIterator(int statIndex) {
        return new BFSIterator(statIndex);
    }

    private class BFSIterator implements Iterator<E> {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[size];

        BFSIterator() {
            queue.add(0);
            visited[0] = true;
        }

        BFSIterator(int startIndex) {
            queue.add(startIndex);
            visited[startIndex] = true;
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public E next() {
            int visitingIndex = queue.remove();
            Node<E> node = nodes.get(visitingIndex);
            for (Integer i : node.getChildList()) {
                if (!visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
            if (queue.isEmpty()) {
                for (int i = 0; i < size; i++) {
                    if (!visited[i]) {
                        queue.add(i);
                        visited[i] = true;
                    }
                }
            }
            return node.data;
        }
    }

    public Iterator<E> getDFSIterator() {
        return new DFSIterator();

    }

    public Iterator<E> getDFSIterator(int statIndex) {
        return new DFSIterator(statIndex);
    }

    private class DFSIterator implements Iterator<E> {
        Deque<Integer> stack = new LinkedList<>();
        boolean[] visited = new boolean[size];

        DFSIterator() {
            stack.push(0);
            visited[0] = true;
        }

        DFSIterator(int startIndex) {
            stack.push(startIndex);
            visited[startIndex] = true;
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            int visitingIndex = stack.pop();
            Node<E> node = nodes.get(visitingIndex);
            for (Integer i : node.getChildList()) {
                if (!visited[i]) {
                    stack.push(i);
                    visited[i] = true;
                }
            }
            if (stack.isEmpty()) {
                for (int i = 0; i < size; i++) {
                    if (!visited[i]) {
                        stack.push(i);
                        visited[i] = true;
                    }
                }
            }
            return node.data;
        }
    }
}
