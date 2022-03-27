package searching;

import sort.HeapSort;

import java.util.Comparator;

public class BinarySearch {
    public static <E> E binarySearch(E[] array, E item, Comparator<? super E> comparator) {
        int start = 0, end = array.length, mid, c;

        while (start < end) {
            mid = start + (end - start) / 2;
            if ((c = comparator.compare(item, array[mid])) == 0) {
                return item;
            } else if (c < 0) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        HeapSort.Person[] persons = new HeapSort.Person[]{
                new HeapSort.Person("Siva", 20),
                new HeapSort.Person("Amit", 15),
                new HeapSort.Person("Sabbo", 26),
                new HeapSort.Person("Manohar", 29),
                new HeapSort.Person("Subrat", 30),
                new HeapSort.Person("Aaku", 21),
                new HeapSort.Person("Kartik", 18),
        };

        HeapSort.heapSort(persons, Comparator.comparing(HeapSort.Person::getName));
        System.out.println(binarySearch(persons, new HeapSort.Person("Aaku", 21), Comparator.comparing(HeapSort.Person::getName)));
    }
}
