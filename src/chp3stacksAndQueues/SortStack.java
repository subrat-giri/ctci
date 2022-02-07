package chp3stacksAndQueues;

import java.util.Arrays;
import java.util.Stack;

public class SortStack {

    public static <E extends Comparable<E>> Stack<E> sort(Stack<E> stack) {
        Stack<E> temp = new Stack<>();
        int counter = 0;
        while(!stack.isEmpty()) {
            E item = stack.pop();
            int count = transferElementSmallerThan(temp, stack, item);
            temp.push(item);
            transferElements(stack, temp, count);
        }
        return temp;
    }

    private static <E extends Comparable<E>> void transferElements(Stack<E> stack, Stack<E> temp, int count) {
        while (count-- > 0 && !stack.isEmpty()) {
            temp.push(stack.pop());
        }
    }

    private static <E extends Comparable<E>> int transferElementSmallerThan(Stack<E> from, Stack<E> to, E elem) {
        E item;
        int counter = 0;
        while (!from.isEmpty() && (item = from.peek()).compareTo(elem) < 0) {
            to.push(from.pop());
            counter++;
        }
        return counter;
    }

//    public static void main(String[] args) {
//        Stack<Integer> stack = new Stack<>();
//        stack.push(3);
//        stack.push(8);
//        stack.push(9);
//        stack.push(5);
//        stack.push(2);
//        stack.push(7);
//        stack.push(5);
//        stack = SortStack.sort(stack);
//        System.out.println(Arrays.toString(stack.toArray()));
//    }
}
