package ch10sortingAndSearching;

import java.util.Arrays;

public class SortedMerge {
    public static void sortedMerge(int[] a, int[] b, int lastA, int lastB) {
        int indexA = lastA - 1;
        int indexB = lastB - 1;
        int totalIndex = lastA + lastB - 1;

        while (indexB >= 0) {
            if (indexA >= 0 && a[indexA] > b[indexB]) {
                a[totalIndex--] = a[indexA--];
            } else {
                a[totalIndex--] = b[indexB--];
            }
        }
    }

    public static void main(String[] args) {
        int[] a = new int[10];
        for (int i = 0; i < 6; i++) {
            a[i] = 10 + i * i/ 2;
        }
        int[] b = new int[4];
        for (int i = 0; i < 4; i++) {
            b[i] = 1 + 10 * i / 2;
        }
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        sortedMerge(a, b, 6, 4);
        System.out.println(Arrays.toString(a));
    }
}
