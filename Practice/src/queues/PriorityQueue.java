package queues;

public class PriorityQueue<E> {
//    private static class PrioritizedElement<E> {
//        E elem;
//        int priority;
//        PrioritizedElement(E elem, int priority) {
//            this.elem = elem;
//            this.priority = priority;
//        }
//    }
//    private Object[] array;
//    private int capacity;
//    private int size;
//
//    public PriorityQueue(int initialCapacity) {
//        this.capacity = initialCapacity;
//        array = new Object[capacity];
//        size = 0;
//    }
//
//    private static <E> void swap(E[] array, int x, int y) {
//        if (x < 0 || x >= array.length || y < 0 || y >= array.length) {
//            throw new IllegalArgumentException("Invalid Index.");
//        }
//        E temp = array[x];
//        array[x] = array[y];
//        array[y] = temp;
//    }
//
//    private static int leftChild(int i) {
//        return i * 2 + 1;
//    }
//
//    private static int rightChild(int i) {
//        return i * 2 + 2;
//    }
//    private <E> void heapify(int index, int heapSize, Comparator<? super E> comparator) {
//        int l , r, h = index;
//        if ((l = leftChild(index)) < heapSize && comparator.compare(array[l], array[index]) > 0) {
//            h = l;
//        }
//        if ((r = rightChild(index)) < heapSize && comparator.compare(array[r], array[h]) > 0) {
//            h = r;
//        }
//        if (h != index) {
//            swap(array, index, h);
//            heapify(h, heapSize, comparator);
//        }
//    }
//
//    public void add(E elem, int priority) {
//        PrioritizedElement<E> pElem = new PrioritizedElement<>(elem, priority);
//
//    }
}
