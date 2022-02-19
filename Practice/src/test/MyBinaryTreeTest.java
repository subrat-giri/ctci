package test;

import graph.MyBinaryTree;

import java.util.Iterator;

public class MyBinaryTreeTest {
    public static void main(String[] args) {
        MyBinaryTree<Integer> myTree = new MyBinaryTree<>(new Integer[]{15, 12, 25, 14, 9, 18, 7, 5,32,10});
        Iterator<Integer> itr = myTree.getPreOrderIterator();
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
        System.out.println();
        Iterator<Integer> itr2 = myTree.getInOrderIterator();
        while (itr2.hasNext()) {
            System.out.print(itr2.next() + " ");
        }

        System.out.println();

        Iterator<Integer> itr3 = myTree.getPostOrderIterator();
        while(itr3.hasNext()) {
            System.out.print(itr3.next() + " ");
        }
    }
}
