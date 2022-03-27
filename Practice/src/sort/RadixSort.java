package sort;

import java.util.Arrays;

public class RadixSort {
    public static void radixSort(int[] array) {
        int max = findMax(array);
        int numOfDigits = findNumberOfDigits(max);
        int place = 1;
        while(numOfDigits-- > 0) {
            CountingSort.countingSort(array, place);
            place *= 10;
        }
    }

    public static int findNumberOfDigits(int number) {
        int count = 0;
        while (number != 0) {
            count++;
            number /= 10;
        }
        return count;
    }

    public static int findMax(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int j : array) {
            if (j > max) {
                max = j;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = new int[]{23, 30, 27, 20, 24, 20, 30, 27, 23, 20};
        radixSort(array);
        System.out.println(Arrays.toString(array));
    }
}
