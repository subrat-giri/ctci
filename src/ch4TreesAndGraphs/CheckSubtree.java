package ch4TreesAndGraphs;

public class CheckSubtree<E> {
    private static class Node<E> {
        E data;
        Node<E> left;
        Node<E> right;

        Node(E data) {
            this.data = data;
        }
    }
    private final Node<E> root;

    public CheckSubtree(E[] array) {
        root = createBalancedBinaryTree(array, 0 , array.length);
    }

    private Node<E> createBalancedBinaryTree(E[] array, int start, int end) {
        if (start >= end) {
            return new Node<>(null);
        }
        int mid = start + (end - start)/2;
        Node<E> node = new Node<>(array[mid]);
        node.left = createBalancedBinaryTree(array, start, mid);
        node.right = createBalancedBinaryTree(array, mid+ 1, end);
        return node;
    }

//    public String getInOrderSting() {
//        StringBuilder builder = new StringBuilder();
//        getInOrderSting(root, builder);
//        return builder.toString();
//    }
//    private void getInOrderSting(Node<E> node, StringBuilder builder) {
//        if (node == null) return;
//        getInOrderSting(node.left, builder);
//        builder.append(node.data).append(" ");
//        getInOrderSting(node.right, builder);
//    }

    public String getPreOrderString() {
        StringBuilder builder = new StringBuilder();
        getPreOrder(root, builder);
        return builder.toString();
    }

    private void getPreOrder(Node<E> node , StringBuilder builder) {
        if (node == null) return;
        builder.append(node.data).append(" ");
        getPreOrder(node.left, builder);
        getPreOrder(node.right, builder);
    }

    public static void main(String[] args) {
        CheckSubtree<Integer> tree = new CheckSubtree<>(new Integer[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24,
        26, 28, 30, 32, 34, 36});
        System.out.println(tree.getPreOrderString());
        CheckSubtree<Integer> tree2 = new CheckSubtree<>(new Integer[]{12, 14, 16, 18});
        System.out.println(tree2.getPreOrderString());
        //tree2.manipulate();
        //System.out.println(tree2.getPreOrderString());
        if (tree.getPreOrderString().contains(tree2.getPreOrderString())) {
            System.out.println("is Sub Tree");
        }
    }
        //To check change in preorder if we tweak the tree.
//    private void manipulate() {
//        Node<E> temp = root.left.right;
//        root.left.right = root.left.left;
//        root.left.left = temp;
//    }
}
