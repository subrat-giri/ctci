package sort;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class QuickSort {
    private static class Person {
        String name;
        int age;
        Person(String name, int age) {
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

    private static <E> void quickSort(E[] array, int start, int end, Comparator<E> comparator) {
        if (end <= start) return;
        int randIndex = start + new Random().nextInt(end - start + 1);
        swap(array, randIndex, end);

        int separatorIndex = start -1;
        E pivot = array[end];
        for (int i = start; i < end ; i++) {
            if (comparator.compare(array[i], pivot) < 0) {
                swap(array, ++separatorIndex, i);
            }
        }
        swap(array, ++separatorIndex, end);
        quickSort(array, start, separatorIndex - 1, comparator);
        quickSort(array, separatorIndex + 1, end, comparator);
    }
    public static <E> void quickSort(E[] array, Comparator<? super E> comparator) {
        quickSort(array, 0, array.length - 1, comparator);
    }

    public static void main(String[] args) {
        Person[] persons = new Person[]{
                new Person("Siva", 20),
                new Person("Amit", 15),
                new Person("Sabbo", 26),
                new Person("Manohar", 29),
                new Person("Subrat", 30),
                new Person("Aaku", 21),
                new Person("Kartik", 18),
        };

        quickSort(persons, Comparator.comparingInt(Person::getAge));
        System.out.println(List.of(persons));
        quickSort(persons, Comparator.comparing(Person::getName));
        System.out.println(List.of(persons));
    }
}
