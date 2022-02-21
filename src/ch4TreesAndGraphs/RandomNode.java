package ch4TreesAndGraphs;

import java.util.Random;

public class RandomNode<E> {
    private static class Node<E> {
        E data;
        Node<E> left;
        Node<E> right;
        int totalNodes;
        Node(E data) {
            this.data = data;
            totalNodes = 1;
        }
        void updateNodeCount() {
            int newCount = 1;
            if (left != null) {
                newCount += left.totalNodes;
            }
            if (right != null) {
                newCount += right.totalNodes;
            }
            this.totalNodes = newCount;
        }
    }
    private Node<E> root;
    
    public RandomNode(E[] array) {
        root = createBalancedBinaryTree(array, 0 , array.length);
        updateCount(root);
    }

    private Node<E> createBalancedBinaryTree(E[] array, int start, int end) {
        if (start >= end) {
            return null;
        }
        int mid = start + (end - start)/2;
        Node<E> node = new Node<>(array[mid]);
        node.left = createBalancedBinaryTree(array, start, mid);
        node.right = createBalancedBinaryTree(array, mid+ 1, end);
        return node;
    }

    private void updateCount(Node<E> node) {
        if (node == null) return;
        updateCount(node.left);
        updateCount(node.right);
        node.updateNodeCount();
    }

    public E getRandomNode() {
        return getRandomNode(root).data;
    }

    private Node<E> getRandomNode(Node<E> node) {
        Random rand = new Random();
        int number = rand.nextInt(node.totalNodes);
        //Edge case handled for node with no child. random number is bound to be 0.
        if (number == 0) {
            return node;
        }
        if (node.left != null && number <= node.left.totalNodes) {
            return getRandomNode(node.left);
        } else {
            return getRandomNode(node.right);
        }
    }

    public static void main(String[] args) {
        RandomNode<Integer> tree = new RandomNode<>(new Integer[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24,
                26, 28, 30, 32, 34, 36});
        System.out.println(tree.getRandomNode());
    }
}
