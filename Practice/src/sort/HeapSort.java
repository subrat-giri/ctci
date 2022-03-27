package sort;

import java.util.Comparator;
import java.util.List;

public class HeapSort {
    public static class Person {
        String name;
        int age;
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public String toString() {
            return "[Name:" + name + " Age:" + age + "]";
        }
        public String getName() {
            return name;
        }
        public int getAge() {
            return age;
        }
    }

    private static <E> void swap(E[] array, int x, int y) {
        if (x < 0 || x >= array.length || y < 0 || y >= array.length) {
            throw new IllegalArgumentException("Invalid Index.");
        }
        E temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    //heap Sort
    private static int leftChild(int i) {
        return i * 2 + 1;
    }

    private static int rightChild(int i) {
        return i * 2 + 2;
    }
    private static <E> void heapify(E[] array, int index, int heapSize, Comparator<? super E> comparator) {
        int l , r, h = index;
        if ((l = leftChild(index)) < heapSize && comparator.compare(array[l], array[index]) > 0) {
            h = l;
        }
        if ((r = rightChild(index)) < heapSize && comparator.compare(array[r], array[h]) > 0) {
            h = r;
        }
        if (h != index) {
            swap(array, index, h);
            heapify(array, h, heapSize, comparator);
        }
    }

    public static <E> void heapSort(E[] array, Comparator<? super E> comparator) {
        int heapSize = array.length;
        for (int i = array.length / 2 - 1; i >= 0 ; i--) {
            heapify(array, i, heapSize, comparator);
        }

        for (int i = array.length - 1; i > 0 ; i--) {
            swap(array, 0, i);
            heapSize--;
            heapify(array, 0, heapSize, comparator);
        }
    }

    public static void main(String[] args) {
//        Integer[] array = new Integer[] {23, 5, 56, 75, 23, 67};
//        mergeSort(array, null);
//        System.out.println(Arrays.toString(array));

        Person[] persons = new Person[]{
                new Person("Siva", 20),
                new Person("Amit", 15),
                new Person("Sabbo", 26),
                new Person("Manohar", 29),
                new Person("Subrat", 30),
                new Person("Aaku", 21),
                new Person("Kartik", 18),
        };

        heapSort(persons, Comparator.comparingInt(Person::getAge));
        System.out.println(List.of(persons));
        heapSort(persons, Comparator.comparing(Person::getName));
        System.out.println(List.of(persons));
    }
}
