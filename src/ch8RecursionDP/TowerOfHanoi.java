package ch8RecursionDP;

import java.util.Deque;
import java.util.LinkedList;

public class TowerOfHanoi {
    // moving from stack1 to stack2 using stack3
    public static void move(int noOfDisks, Deque<Integer> stack1, Deque<Integer> stack2, Deque<Integer> stack3) {
        System.out.println("Stack1:" + stack1 + "Stack2:" + stack2 + "Stack3:" + stack3);
        if (noOfDisks == 1) {
            stack2.addFirst(stack1.pollFirst());
            return;
        }
        if (noOfDisks == 2) {
            stack3.addFirst(stack1.pollFirst());
            stack2.addFirst(stack1.pollFirst());
            stack2.addFirst(stack3.pollFirst());
            return;
        }
        move(noOfDisks - 1, stack1, stack3, stack2);
        stack2.addFirst(stack1.pollFirst());
        move(noOfDisks - 1, stack3, stack2, stack1);
        System.out.println("Stack1:" + stack1 + "Stack2:" + stack2 + "Stack3:" + stack3);
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 5;
        Deque<Integer> stack1 = new LinkedList<>();
        stack1.addFirst(1);
        stack1.addFirst(2);
        stack1.addFirst(3);
        stack1.addFirst(4);
        stack1.addFirst(5);
        Deque<Integer> stack2 = new LinkedList<>();
        Deque<Integer> stack3 = new LinkedList<>();
        System.out.println(stack1);
        move(5, stack1, stack2, stack3);
        System.out.println(stack1);
        System.out.println(stack2);
        System.out.println(stack3);
    }
}
