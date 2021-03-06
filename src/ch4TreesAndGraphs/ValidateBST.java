package ch4TreesAndGraphs;

public class ValidateBST<E extends Comparable<E>> {
    private static class Node<E> {
        E data;
        Node<E> left;
        Node<E> right;

        Node(E data) {
            this.data = data;
        }
    }
    private Node<E> root;

    public ValidateBST(E[] array, boolean isBST) {
        if (isBST) {
            for (E e : array) {
                insert(e);
            }
        } else {
            root = createBalancedBinaryTree(array, 0 , array.length);
        }
    }

    public boolean insert(E data) {
        if (data == null) return false;
        Node<E> node;
        try {
            node = new Node<>(data);
        } catch (OutOfMemoryError e) {
            return false;
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
        return true;
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

    public boolean isTreeBST(){
        return isTreeWithinRange(root, null, true, null, true);
    }

    //considering null as infinity here to accommodate generic type. Assuming duplicates on right side of the BST.
    public boolean isTreeWithinRange(Node<E> node, E min, boolean isMinIncluded, E max, boolean isMaxIncluded) {
        if (node == null) return true;
        boolean checked = true;
        if (min != null) {
            checked = isMinIncluded ? node.data.compareTo(min) >= 0 : node.data.compareTo(min) > 0;
        }
        if (max != null) {
            checked = checked && (isMaxIncluded ? max.compareTo(node.data) >= 0 : max.compareTo(node.data) > 0);
        }
        if (!checked) return false;
        if (!isTreeWithinRange(node.left, min, isMinIncluded, node.data, false)) {
            return false;
        }
        return isTreeWithinRange(node.right, node.data, true, max, isMaxIncluded);
    }

    public static void main(String[] args) {
        ValidateBST<Integer> validBST = new ValidateBST<>(new Integer[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20}, false);
        System.out.println(validBST.isTreeBST());
        ValidateBST<Integer> invalidBST = new ValidateBST<>(new Integer[]{2, 4, 6, 8, 34, 12, 14, 16, 18, 20}, false);
        System.out.println(invalidBST.isTreeBST());
        ValidateBST<String> stringBST1 = new ValidateBST<>(new String[]{"Subrat",
                "Anand", "Abhishek", "Manohar", "Rishabh", "Nitish", "Niharika", "Aniket", "Rina", "Suraj", "Abhijeet"}, false);
        System.out.println(stringBST1.isTreeBST());
        ValidateBST<String> stringBST2 = new ValidateBST<>(new String[]{"Abhijeet", "Abhishek",
                "Anand", "Manohar", "Niharika", "Nitish", "Rina", "Rishabh", "Subrat"}, false);
        System.out.println(stringBST2.isTreeBST());
    }
}
