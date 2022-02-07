package chp3stacksAndQueues;

import java.util.Arrays;
import java.util.Stack;

//This class is implemented with the enqueue costlier approach.
public class ImplementQueueUsingStack<E> {
    Stack<E> dataStack = new Stack<>();
    Stack<E> tempStack = new Stack<>();

    public static <E> void transfer(Stack<E> source, Stack<E> dest) {
        while(!source.empty()) {
            dest.push(source.pop());
        }
    }
    public void enqueue(E elem) {
        transfer(dataStack, tempStack);
        tempStack.push(elem);
        transfer(tempStack, dataStack);
    }

    public E dequeue() {
        return dataStack.pop();
    }


    public E peek() {
        return dataStack.peek();
    }

    public int size() {
        return dataStack.size();
    }

    public boolean isEmpty() {
        return dataStack.isEmpty();
    }

    public Object[] toArray() {
        return dataStack.toArray();
    }

    public E[] toArray(E[] toArray) {
        return dataStack.toArray(toArray);
    }
//    public static void main(String[] args) {
//        ImplementQueueUsingStack<Integer> queue = new ImplementQueueUsingStack<>();
//        queue.enqueue(23);
//        queue.enqueue(27);
//        queue.enqueue(20);
//        queue.enqueue(25);
//        queue.enqueue(28);
//
//        System.out.println(Arrays.toString(queue.toArray(new Integer[0])));
//
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
//        System.out.println(Arrays.toString(queue.toArray(new Integer[0])));
//
//    }
}
