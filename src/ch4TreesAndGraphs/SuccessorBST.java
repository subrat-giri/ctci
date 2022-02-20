package ch4TreesAndGraphs;

public class SuccessorBST<E extends Comparable<E>> {
    public static class Node<E> {
        private final E data;
        private Node<E> left;
        private Node<E> right;
        private Node<E> parent;

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
            node.parent = parent;
        }
        return node;
    }

    public Node<E> successor(Node<E> node) {
        if (node == null) return null;
        if (node.right != null) {
            return findLeftMostNodeRootedAt(node.right);
        }
        //this is the highest node at root with no right subtree.
        if (node.parent == null) return null;
        Node<E> temp = node;
        while (temp.parent != null && (temp == temp.parent.right)) {
            temp = temp.parent;
        }
        if (temp.parent == null) {
            //reached root Node. So the current element was the highest child.
            return null;
        } else {
            return temp.parent;
        }
    }

    private Node<E> findLeftMostNodeRootedAt(Node<E> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        SuccessorBST<Integer> bst = new SuccessorBST<>();
        bst.insert(12);
        bst.insert(42);
        bst.insert(5);
        Node<Integer> node = bst.insert(16);
        bst.insert(20);
        bst.insert(30);
        bst.insert(55);
        bst.insert(18);
        Node<Integer> node1 = bst.insert(98);
        bst.insert(10);
        Node<Integer> node2 = bst.insert(11);

        System.out.println(bst.successor(node).data);
        //System.out.println(bst.successor(node1).data);
        System.out.println(bst.successor(node2).data);


    }

}
