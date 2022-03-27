package test.graph;

import ch4TreesAndGraphs.SuccessorBST;

public class MinimalTreeTest {
    public static void main(String[] args) {
        //Minimal Tree Test
//        MinimalTree<Integer> bst = new MinimalTree<>(new Integer[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20});
//
//        Iterator<Integer> itr1 = bst.getInOrderIterator();
//        while (itr1.hasNext()) {
//            System.out.print(itr1.next() + " ");
//        }

        //List of Depths test
//        ListOfDepths<Integer> tree = new ListOfDepths<>(new Integer[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20});
//
//        List<List<Integer>> listOfDepths = tree.listOfDepths();
//
//        for (List<Integer> l : listOfDepths) {
//            for (int i : l) {
//                System.out.print(i + " ");
//            }
//            System.out.println();
//        }

        //Tree balance test

//        CheckBalanced<Integer> tree = new CheckBalanced<>(new Integer[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20});
//        List<List<Integer>> listOfDepths = tree.listOfDepths();
//        for (List<Integer> l : listOfDepths) {
//            for (int i : l) {
//                System.out.print(i + " ");
//            }
//            System.out.println();
//        }
//        System.out.print(tree.isTreeBalanced());

        SuccessorBST<Integer> bst = new SuccessorBST<>();
        bst.insert(12);
        bst.insert(42);
        bst.insert(5);
        SuccessorBST.Node<Integer> node = bst.insert(16);
        bst.insert(20);
        bst.insert(30);
        bst.insert(55);
        bst.insert(18);
        SuccessorBST.Node<Integer> node1 = bst.insert(98);
        bst.insert(10);
        SuccessorBST.Node<Integer> node2 = bst.insert(11);

        System.out.println(bst.successor(node).getData());
        //System.out.println(bst.successor(node1).data);
        System.out.println(bst.successor(node2).getData());

    }
}
