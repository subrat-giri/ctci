package sort;

import java.util.Arrays;

public class CountingSort {
    public static void countingSort(int[] array, int lower, int higher) {
        int rangeSize = higher - lower + 1;
        int[] frequency = new int[rangeSize];
        int[] sorted = new int[array.length];

        for (int i = 0; i < rangeSize; i++) {
            frequency[i] = 0;
        }
        for (int j : array) {
            frequency[j - lower]++;
        }
        for (int i = 1; i < rangeSize; i++) {
            frequency[i] = frequency[i] + frequency[i - 1];
        }
        for (int j : array) {
            sorted[frequency[j - lower] - 1] = j;
            frequency[j - lower]--;
        }
        System.arraycopy(sorted, 0, array, 0, array.length);
    }

    public static void countingSort(int[] array, int place) {
        int range = 10;
        int[] frequency = new int[range];
        int[] sorted = new int[array.length];

        for (int i = 0; i < range; i++) {
            frequency[i] = 0;
        }
        for (int j : array) {
            int placeValue = (j / place) % 10;
            frequency[placeValue]++;
        }
        for (int i = 1; i < range; i++) {
            frequency[i] = frequency[i] + frequency[i - 1];
        }
        for (int i = array.length - 1; i >= 0; i--) {
            int placeValue = (array[i] / place) % 10;
            sorted[frequency[placeValue] - 1] = array[i];
            frequency[placeValue]--;
        }
        System.arraycopy(sorted, 0, array, 0, array.length);
    }

    public static void main(String[] args) {
        int[] array = new int[]{23, 30, 27, 20, 24, 20, 30, 27, 23, 20};
        countingSort(array, 20, 30);
        System.out.println(Arrays.toString(array));
    }
}
