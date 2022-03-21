package ch8RecursionDP;

import java.util.Scanner;

public class MagicIndex {
    public static int findMagicIndex(int[] arr, int low, int high) {
        if (high < low) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (arr[mid] == mid) {
            return mid;
        }
        if (arr[mid] > mid) {
            return findMagicIndex(arr, low, mid - 1);
        } else {
            return findMagicIndex(arr, mid + 1, high);
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int size = scn.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = scn.nextInt();
        }
        System.out.println(findMagicIndex(arr, 0, size - 1));
    }
}
