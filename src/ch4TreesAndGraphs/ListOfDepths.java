package ch4TreesAndGraphs;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ListOfDepths<E> {
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

    public ListOfDepths(E[] array) {
        Preconditions.checkArgument(array.length > 0);
        root = createBinaryTree(array, 0 , array.length);
    }

    private Node<E> createBinaryTree(E[] sortedArray, int start, int end) {
        if (start >= end) {
            return null;
        }
        int mid = start + (end - start)/2;
        Node<E> node = new Node<>(sortedArray[mid]);
        node.left = createBinaryTree(sortedArray, start, mid);
        node.right = createBinaryTree(sortedArray, mid+ 1, end);
        return node;
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
        ListOfDepths<Integer> tree = new ListOfDepths<>(new Integer[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20});
        List<List<Integer>> listOfDepths = tree.listOfDepths();
        for (List<Integer> l : listOfDepths) {
            for (int i : l) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
