package ch4TreesAndGraphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CheckBalanced<E> {
    private static class Node<E> {
        E data;
        Node<E> left;
        Node<E> right;

        Node(E data) {
            this.data = data;
        }
    }
    private final Node<E> root;

    public CheckBalanced(E[] array) {
        //root = createBalancedBinaryTree(array, 0 , array.length);
        root = createSkewedBinaryTree(array, 0 , array.length);
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

    private Node<E> createSkewedBinaryTree(E[] array, int start, int end) {
        if (start >= end) {
            return null;
        }
        int randomIndex = (int) (start + Math.random() * (end - start));
        Node<E> node = new Node<>(array[randomIndex]);
        node.left = createBalancedBinaryTree(array, start, randomIndex);
        node.right = createBalancedBinaryTree(array, randomIndex+ 1, end);
        return node;
    }

    public boolean isTreeBalanced() {
        return heightIfTreeBalanced(root) >= 0;
    }

    private int heightIfTreeBalanced(Node<E> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = heightIfTreeBalanced(node.left);
        int rightHeight = heightIfTreeBalanced(node.right);
        if (!(leftHeight >= 0 && rightHeight >= 0)) {
            return -1;
        }
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Integer.max(leftHeight, rightHeight) + 1;
    }
    public List<List<E>> listOfDepths() {
        List<List<E>> listOfDepths = new ArrayList<>();
        List<E> levelList;
        Queue<Node<E>> queue = new LinkedList<>();
        int currRowCount = 0;
        int nextRowCount = 0;

        queue.add(root);
        currRowCount++;
        while (!queue.isEmpty()) {
            levelList = new LinkedList<>();
            while (currRowCount-- > 0 ) {
                Node<E> node = queue.remove();
                levelList.add(node.data);
                if (node.left != null) {
                    queue.add(node.left);
                    nextRowCount++;
                }
                if (node.right != null) {
                    queue.add(node.right);
                    nextRowCount++;
                }
            }
            currRowCount = nextRowCount;
            nextRowCount = 0;
            listOfDepths.add(levelList);
        }
        return listOfDepths;
    }

    public static void main(String[] args) {
        CheckBalanced<Integer> tree = new CheckBalanced<>(new Integer[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20});
        //We are using sorted array, tree can be drawn with the knowledge of nodes in each level.
        List<List<Integer>> listOfDepths = tree.listOfDepths();
        for (List<Integer> l : listOfDepths) {
            for (int i : l) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.print(tree.isTreeBalanced());
    }
}
