package ch4TreesAndGraphs;

import java.util.*;

public class RoutesBetweenNodes<E> {
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

    public RoutesBetweenNodes(E[] vertexes) {
        this.size = vertexes.length;
        nodes = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            nodes.add(i, new Node<>(vertexes[i]));
        }
    }

    public void connect(int vertex, int ... directedTo) {
        for (int i : directedTo) {
            if (vertex < 0 || vertex >= size || i < 0 || i >= size) {
                throw new IllegalArgumentException("Invalid Nodes");
            }
            nodes.get(vertex).getChildList().add(i);
        }
    }

    //using DFS
    public boolean isConnectedDFS(int vertex1, int vertex2) {
        boolean[] visited = new boolean[size];
        Arrays.fill(visited, false);
        Deque<Integer> stack = new LinkedList<>();
        stack.push(vertex1);
        visited[vertex1] = true;
        while (!stack.isEmpty()) {
            int visitedIndex = stack.pop();
            Node<E> node = nodes.get(visitedIndex);
            for (int i : node.children) {
                if (i == vertex2) {
                    return true;
                }
                if (!visited[i]) {
                    stack.push(i);
                }
            }
        }
        return false;
    }

    //using BFS
    public boolean isConnectedBFS(int vertex1, int vertex2) {
        boolean[] visited = new boolean[size];
        Arrays.fill(visited, false);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex1);
        visited[vertex1] = true;
        while (!queue.isEmpty()) {
            int visitedIndex = queue.remove();
            Node<E> node = nodes.get(visitedIndex);
            for (int i : node.children) {
                if (i == vertex2) {
                    return true;
                }
                if (!visited[i]) {
                    queue.add(i);
                }
            }
        }
        return false;
    }

//    public static void main(String[] args) {
//        RoutesBetweenNodes<String> pipeNetwork = new RoutesBetweenNodes<>(new String[]{"Jamshedpur",
//                "Ranchi", "Kharagpur", "Kolkata", "Bangalore"});
//        pipeNetwork.connect(0, 2);
//        pipeNetwork.connect(1, 0, 2);
//        pipeNetwork.connect(2, 3);
//
//        System.out.println(pipeNetwork.isConnectedDFS(0, 3));
//        System.out.println(pipeNetwork.isConnectedDFS(0, 4));
//
//        System.out.println(pipeNetwork.isConnectedBFS(0, 3));
//        System.out.println(pipeNetwork.isConnectedBFS(0, 4));
//    }
}
