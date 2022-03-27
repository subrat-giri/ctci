package ch10sortingAndSearching;

public class SparseSearch {
    public static int search(String[] array, String str) {
        return search(array, str, 0, array.length - 1);
    }

    private static int search(String[] array, String str, int start, int end) {
        if (array == null || array.length == 0 || str == null) return -1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (array[mid].equals("")) {
                int left = mid - 1, right = mid + 1;
                while ("".equals(array[left]) && "".equals(array[right])) {
                    left--;
                    right++;
                }
                if (array[left] == null) {
                    mid = right;
                } else {
                    mid = left;
                }
            }
            int a;
            if ((a = str.compareTo(array[mid])) == 0) {
                return mid;
            } else if (a > 0) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(search(new String[]{"at", "", "", "" , "ball", "", "", "car", "" , "", "", "dad"}, "car"));
    }
}
