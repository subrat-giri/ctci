package sort;

import java.util.*;

public class BucketSort {

    public static int hash(int i, int max, int bucketSize) {
        return (int)((double)i / max) * bucketSize;
    }

    public static void bucketSort(int[] array) {
        int bucketSize = (int) Math.sqrt(array.length);
        int max = RadixSort.findMax(array);
        List<List<Integer>> buckets = new ArrayList<>(bucketSize);
        for (int i = 0; i < array.length; i++) {
            buckets.add(new ArrayList<>());
        }
        for (int i = 0; i < array.length; i++) {
            buckets.get(hash(i, max, bucketSize)).add(array[i]);
        }
        for (List<Integer> bucket : buckets) {
            bucket.sort(Comparator.naturalOrder());
        }
        int[] sorted = buckets.stream().flatMap(Collection::stream).mapToInt(i -> i).toArray();
        System.arraycopy(sorted, 0, array, 0, array.length);
    }

    public static void main(String[] args) {
        int[] array = new int[]{2, 30, 7, 206, 84, 0, 307, 278, 263, 134};
        bucketSort(array);
        System.out.println(Arrays.toString(array));
    }

}
