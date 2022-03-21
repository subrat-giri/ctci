package ch10sortingAndSearching;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class GroupAnagrams {

    public static String sort(String str) {
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        return String.valueOf(arr);
    }

    public static final Comparator<String> anagramComparator = (a,b) -> {
        String sA = sort(a);
        String sB = sort(b);
        return sA.compareTo(sB);
    };

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

    public static void groupAnagram(String[] array) {
        quickSort(array, 0, array.length - 1, anagramComparator);
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"abd", "bcs", "djg", "fkd", "dba", "kfd", "jgd"};
        groupAnagram(arr);
        System.out.println(Arrays.toString(arr));
    }
}
