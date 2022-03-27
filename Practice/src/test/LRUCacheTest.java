package test;

import ch7ObjectOrientedDesign.LRUCache;

public class LRUCacheTest {
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>();
        System.out.println(cache.put(3, "abc"));
        System.out.println(cache.put(4, "bcd"));
        System.out.println(cache.put(5, "cde"));
        System.out.println(cache.get(3));
        System.out.println(cache.put(6, "def"));
        System.out.println(cache.remove(6));
        System.out.println(cache.get(4));
        System.out.println(cache.put(7, "efg"));
        System.out.println(cache.get(5));
        System.out.println();
        System.out.println(cache.get(3));
        System.out.println(cache.get(7));
        System.out.println(cache.get(6));
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
