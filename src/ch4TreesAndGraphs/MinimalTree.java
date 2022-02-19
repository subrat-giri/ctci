package ch4TreesAndGraphs;

import com.google.common.base.Preconditions;

import java.util.Iterator;
import java.util.LinkedList;

public class MinimalTree<E> {
    private final Node<E> root;
    private int size;
    private static class Node<E> {
        E data;
        Node<E> left;
        Node<E> right;
        Node(E data) {
            this.data = data;
        }
    }

    public MinimalTree(E[] sortedArray) {
        Preconditions.checkArgument(sortedArray.length > 0);
        root = createBST(sortedArray, 0 , sortedArray.length);
    }

    private Node<E> createBST(E[] sortedArray, int start, int end) {
        if (start >= end) {
            return null;
        }
        int mid = start + (end - start)/2;
        Node<E> node = new Node<>(sortedArray[mid]);
        node.left = createBST(sortedArray, start, mid);
        node.right = createBST(sortedArray, mid+ 1, end);
        return node;
    }

    public Iterator<E> getInOrderIterator() {
        return new InOrderIterator();
    }

    private class InOrderIterator implements Iterator<E> {
        LinkedList<Node<E>> stack = new LinkedList<>();
        LinkedList<Node<E>> processedStack = new LinkedList<>();

        InOrderIterator() {
            if (root != null) {
                stack.push(root);
                moveToLeftMostNode(root);
            }
        }

        private void moveToLeftMostNode(Node<E> node) {
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
                moveToLeftMostNode(temp);
            }
            return node.data;
        }
    }

    public static void main(String[] args) {
        MinimalTree<Integer> bst = new MinimalTree<>(new Integer[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20});
        Iterator<Integer> itr1 = bst.getInOrderIterator();
        while (itr1.hasNext()) {
            System.out.print(itr1.next() + " ");
        }
    }
}
