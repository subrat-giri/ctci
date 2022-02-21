package ch4TreesAndGraphs;

public class FirstCommonAncestor<E extends Comparable<E>> {
    private static class Node<E> {
        E data;
        Node<E> left;
        Node<E> right;

        Node(E data) {
            this.data = data;
        }
    }

    private final Node<E> root;

    public FirstCommonAncestor(E[] array) {
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

    private static class Result {
        boolean found1;
        boolean found2;
    }
    public E findCommonAncestor(E node1, E node2) {
        Result result = new Result();
        Node<E> ancestorNode = findCommonAncestor(root, node1, node2, result);
        if (bothFound(result)) {
            return ancestorNode.data;
        } else {
            return null;
        }
    }

    public Node<E> findCommonAncestor(Node<E> node, E node1, E node2, Result result) {

        if (node == null) return null;

        boolean matchOnCurrentNode = false;
        Node<E> nodeFoundOnLeft = null;
        Node<E> nodeFoundOnRight = null;

        // Check for match with current Node
        if (node.data.equals(node1) || node.data.equals(node2)) {
            matchOnCurrentNode = true;
            if (node.data.equals(node1)) {
                result.found1 = true;
            } else {
                result.found2 = true;
            }
        }
        //Don't call this method on any node after matching both the value.
        if (!bothFound(result)) {
            nodeFoundOnLeft = findCommonAncestor(node.left, node1, node2, result);
        }

        if (!bothFound(result)) {
            nodeFoundOnRight = findCommonAncestor(node.right, node1, node2, result);
        }

        if (matchOnCurrentNode) {
            return node;
        }
        return nodeFoundOnLeft != null ? nodeFoundOnRight != null ? node : nodeFoundOnLeft : nodeFoundOnRight;
    }

    private static boolean bothFound(Result result) {
        return result.found1 && result.found2;
    }

    public static void main(String[] args) {
        FirstCommonAncestor<Integer> tree = new FirstCommonAncestor<>(new Integer[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20});
        System.out.println(tree.findCommonAncestor(2, 4));
        System.out.println(tree.findCommonAncestor(2, 8));
        System.out.println(tree.findCommonAncestor(2, 20));
        System.out.println(tree.findCommonAncestor(2, 22));
        System.out.println(tree.findCommonAncestor(6, 8));
        System.out.println(tree.findCommonAncestor(14, 8));
    }
}