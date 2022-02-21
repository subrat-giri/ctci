package ch4TreesAndGraphs;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BSTSequences<E extends Comparable<E>> {
    public static class Node<E> {
        private final E data;
        private Node<E> left;
        private Node<E> right;

        private Node(E data) {
            this.data = data;
        }

        public E getData() {
            return data;
        }

    }

    private Node<E> root;

    public Node<E> insert(E data) {
        if (data == null) return null;
        Node<E> node;
        try {
            node = new Node<>(data);
        } catch (OutOfMemoryError e) {
            return null;
        }
        if (root == null) {
            root = node;
        } else {
            Node<E> temp = root;
            Node<E> parent = null;
            while (temp != null) {
                parent = temp;
                if (temp.data.compareTo(data) >= 0) {
                    temp = temp.left;
                } else {
                    temp = temp.right;
                }
            }
            if (parent.data.compareTo(data) >= 0) {
                parent.left = node;
            } else {
                parent.right = node;
            }
        }
        return node;
    }

    public List<List<E>> printAllPossibleArrays() {
        return printAllPossibleArrays(root);
    }

    private List<List<E>> printAllPossibleArrays(Node<E> node) {
        if (node == null) {
            return Collections.emptyList();
        }
        List<List<E>> listOfSequences = new ArrayList<>();
        List<List<E>> leftList = printAllPossibleArrays(node.left);
        List<List<E>> rightList = printAllPossibleArrays(node.right);
        if (leftList.isEmpty() && rightList.isEmpty()) {
            listOfSequences.add(List.of(node.data));
            return listOfSequences;
        } else if (leftList.isEmpty() || rightList.isEmpty()) {
            if (!leftList.isEmpty()) {
                leftList.forEach(list -> list.add(0, node.data));
                return leftList;
            }
            rightList.forEach(list -> list.add(0, node.data));
            return rightList;
        }
        for (List<E> lList: leftList) {
            for (List<E> rList : rightList) {
                List<E> leftFirstSeq = new ArrayList<>();
                List<E> rightFirstSeq = new ArrayList<>();

                leftFirstSeq.add(node.data);
                leftFirstSeq.addAll(lList);
                leftFirstSeq.addAll(rList);

                rightFirstSeq.add(node.data);
                rightFirstSeq.addAll(rList);
                rightFirstSeq.addAll(lList);

                listOfSequences.add(leftFirstSeq);
                listOfSequences.add(rightFirstSeq);
            }
        }
        return listOfSequences;
    }

    public static void main(String[] args) {
        BSTSequences<Integer> bstSequences = new BSTSequences<>();
        bstSequences.insert(5);
        bstSequences.insert(7);
        bstSequences.insert(3);
        bstSequences.insert(1);
        bstSequences.insert(4);
        bstSequences.insert(6);
        bstSequences.insert(8);
        List<List<Integer>> listOfSequences = bstSequences.printAllPossibleArrays();
        for (List<Integer> list : listOfSequences) {
            System.out.println(list);
        }
    }
}
