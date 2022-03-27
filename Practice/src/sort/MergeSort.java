package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeSort {
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
    //An important difference between arrays and generics is how they enforce type checking.
    // Specifically, arrays store and check type information at runtime.
    // Generics, however, check for type errors at compile-time and don't have type information at runtime.
    private static <E> void merge(E[] array, int start, int mid, int end, Comparator<? super E> comparator) {
        int leftSize = mid - start + 1;
        int rightSize = end - mid;

        List<E> left = new ArrayList<>(leftSize);
        List<E> right = new ArrayList<>(rightSize);

        // Copy data.
        left.addAll(Arrays.asList(array).subList(start, leftSize + start));
        right.addAll(Arrays.asList(array).subList(mid + 1, rightSize + mid + 1));

        int i = 0, j = 0, k = start, comp;
        for (; k <= end; k++) {
            if (i >= leftSize) {
                array[k] = right.get(j);
                j++;
            } else if (j >= rightSize) {
                array[k] = left.get(i);
                i++;
            } else {
                if (comparator.compare(left.get(i), right.get(j)) >= 0) {
                    array[k] = right.get(j);
                    j++;
                } else {
                    array[k] = left.get(i);
                    i++;
                }
            }
        }
    }

    private static <E> void mergeSort(E[] array, int start, int end, Comparator<? super E> comparator) {
        if (end <= start) return;
        int mid = start + (end - start) / 2;
        mergeSort(array, start, mid, comparator);
        mergeSort(array, mid + 1, end, comparator);
        merge(array, start, mid, end, comparator);
    }

    public static <E> void mergeSort(E[] array, Comparator<? super E> comparator) {
        mergeSort(array, 0, array.length - 1, comparator);
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

        mergeSort(persons, Comparator.comparingInt(Person::getAge));
        System.out.println(List.of(persons));
        mergeSort(persons, Comparator.comparing(Person::getName));
        System.out.println(List.of(persons));
    }
}
