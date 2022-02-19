package graph;

import java.util.Iterator;
import java.util.LinkedList;

public class MyBinaryTree<E> {
    private static class Node<E> {
        E data;
        Node<E> left;
        Node<E> right;

        Node(E data) {
            this.data = data;
        }
    }

    private Node<E> root;
    private int size;

    public MyBinaryTree(E[] data) {
        LinkedList<Node<E>> queue = new LinkedList<>();
        Node<E> node = root = new Node<>(data[0]);
        queue.addLast(node);
        for (int i = 0; i < data.length/2; i++) {
            node = queue.removeFirst();
            int left = i * 2 + 1;
            int right  = i * 2 + 2;
            if (left < data.length) {
                node.left = new Node<>(data[left]);
                queue.addLast(node.left);
            }
            if (right < data.length) {
                node.right = new Node<>(data[right]);
                queue.addLast(node.right);
            }
        }
        queue.clear();
    }

    public Iterator<E> getPreOrderIterator() {
        return new PreOrderIterator();
    }

    private class PreOrderIterator implements Iterator<E> {
        LinkedList<Node<E>> stack = new LinkedList<>();
        //Think about iteration from any root of subtree;
        PreOrderIterator() {
            if (root != null) {
                stack.push(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            Node<E> node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            return node.data;
        }
    }


    public Iterator<E> getInOrderIterator() {
        return new InOrderIterator();
    }

    private class InOrderIterator implements Iterator<E> {
        LinkedList<Node<E>> stack = new LinkedList<>();
        LinkedList<Node<E>> processedStack = new LinkedList<>();
        //Think about iteration from any root of subtree;
        InOrderIterator() {
            Node<E> node = root;
            stack.push(node);
            while (node != null) {
                node = stack.pop();
                if (node.right != null) {
                    stack.push(node.right);
                }
                stack.push(node);
                if (node.left != null) {
                    stack.push(node.left);
                }
                processedStack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            Node<E> node = stack.pop();
            if (node == processedStack.peek()) {
                processedStack.pop();

            }
            if (!stack.isEmpty() && (stack.peek()  != processedStack.peek())) {
                Node<E> temp = stack.peek();
                while (temp != null) {
                    temp = stack.pop();
                    if (temp.right != null) {
                        stack.push(temp.right);
                    }
                    stack.push(temp);
                    if (temp.left != null) {
                        stack.push(temp.left);
                    }
                    processedStack.push(temp);
                    temp = temp.left;
                }
            }
            return node.data;
        }
    }

    public Iterator<E> getPostOrderIterator() {
        return new PostOrderIterator();
    }

    private class PostOrderIterator implements Iterator<E> {
        LinkedList<Node<E>> stack = new LinkedList<>();
        LinkedList<Node<E>> processedStack = new LinkedList<>();
        //Think about iteration from any root of subtree;
        PostOrderIterator() {
            Node<E> node = root;
            stack.push(node);
            while (node != null) {
                node = stack.peek();
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
                processedStack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            Node<E> node = stack.pop();
            if (node == processedStack.peek()) {
                processedStack.pop();

            }
            if (!stack.isEmpty() && (stack.peek()  != processedStack.peek())) {
                Node<E> temp = stack.peek();
                while (temp != null) {
                    temp = stack.peek();
                    if (temp.right != null) {
                        stack.push(temp.right);
                    }
                    if (temp.left != null) {
                        stack.push(temp.left);
                    }
                    processedStack.push(temp);
                    temp = temp.left;
                }
            }
            return node.data;
        }
    }
}
