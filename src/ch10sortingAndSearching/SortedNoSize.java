package ch10sortingAndSearching;

public class SortedNoSize {

    public static int binarySearch(Listy list, int item, int start, int end) {
        while (start <= end) {
            int mid = (end + start) / 2, a;
            if ((a = list.elementAt(mid)) == -1 || item < a) {
                end = mid - 1;
            } else if (item > a) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int search(Listy list, int item) {
        if (list == null) return -1;
        if (list.elementAt(0) == item) return 0;
        int expIndex = 1, a;
        while ((a = list.elementAt(expIndex)) != -1 && a <= item) {
            if (a == item) {
                return expIndex;
            }
            expIndex = expIndex * 2;
        }
        return binarySearch(list, item, expIndex / 2, expIndex);
    }

    private static class Listy {
        int[] array;
        Listy(int[] array) {
            this.array = array;
        }
        int elementAt(int i) {
            return i >= 0 && i < array.length ? array[i] : -1;
        }
    }
    public static void main(String[] args) {
        System.out.println(search(new Listy(new int[]{12, 14, 15, 16, 18, 23, 45, 37}), 12));
        System.out.println(search(new Listy(new int[]{12, 14, 15, 16, 18, 23, 45, 37}), 15));
        System.out.println(search(new Listy(new int[]{12, 14, 15, 16, 18, 23, 45, 55}), 55));
    }
}
