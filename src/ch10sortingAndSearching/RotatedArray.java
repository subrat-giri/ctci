package ch10sortingAndSearching;

import java.util.Comparator;

public class RotatedArray {
    public static <E> int search(E[] array, int start, int end, E item, Comparator<? super E> c) {
        if (end < start) return -1;
        int mid = start + (end - start) / 2;
        int a = c.compare(array[mid], item);
        if (a == 0) {
            return mid;
        }
        if ((a = c.compare(array[mid], array[start])) > 0) {
            if (c.compare(item, array[start]) >= 0 && c.compare(item, array[mid]) < 0) {
                return search(array, start, mid - 1, item, c);
            } else {
                return search(array, mid + 1, end, item, c);
            }
        } else if (a < 0) {
            if (c.compare(item, array[mid]) > 0 && c.compare(item, array[end]) <= 0) {
                return search(array, mid + 1, end, item, c);
            } else {
                return search(array, start, mid -1, item, c);
            }
        } else {
            if (c.compare(array[mid], array[end]) != 0) {
                return search(array, mid + 1, end, item, c);
            } else {
                int result = search(array, start, mid -1, item, c);
                if (result != -1) {
                    return result;
                } else {
                    return search(array, mid + 1, end, item, c);
                }
            }
        }
    }

    public static <E> int searchRotatedArray(E[] array, E item, Comparator<? super E> c) {
        return search(array, 0, array.length -1, item, c);
    }

    public static void main(String[] args) {
        System.out.println(searchRotatedArray(new Integer[]{12, 14, 15, 16, 18, 26, 37, 2, 6, 8, 10}, 8,
                Comparator.naturalOrder()));
    }

}
