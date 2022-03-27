package algos;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MyHashMap<K, V> implements Map<K, V> {
    public static final int MAX_CAPACITY = 1 << 30;
    private int size = 0;
    private int capacity = 1 << 2; //in multiple of 2.
    private float loadFactor = 0.75f; // default load factor.
    private Node<K, V>[]  table;

    private static class Node<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public int hashCode() {
            return Objects.hash(key) ^ Objects.hashCode(value);
        }
    }

    public MyHashMap() {
        table = (Node<K,V> []) new Node[capacity];
    }

    // will be called when capacity * load factor < size;
    private Node<K, V>[] resize() {
        Node<K, V> [] oldTab = table;
        int oldCap = capacity;
        int newCapacity = capacity << 1;
        if (newCapacity >= MAX_CAPACITY) {
            return table;
        }
        table = (Node<K,V> []) new Node[newCapacity];
        capacity = newCapacity;
        for (int i = 0; i < oldCap; i++) {
            Node<K, V> loHead = null, loTail = null, hiHead = null, hiTail = null, p;
            p = oldTab[i];
            while (p != null) {
                if ((p.hash & oldCap) == 0) {
                    //lower list
                    if (loHead == null) {
                        loHead = loTail = p;
                    } else {
                        loTail.next = p;
                        loTail = loTail.next;
                    }
                } else {
                    if (hiHead == null) {
                        hiHead = hiTail = p;
                    } else {
                        hiTail.next = p;
                        hiTail = hiTail.next;
                    }
                }
                p = p.next;
            }
            if (loTail != null) {
                loTail.next = null;
            }
            if (hiTail != null) {
                hiTail.next = null;
            }
            table[i] = loHead;
            table[i + oldCap] = hiHead;
        }
        return table;
    }

    private V putVal(int hash, K key, V value) {
        Node<K, V>[] t;
        Node<K, V> p;
        int n;
        V oldVal = null;
        if ((t = table) == null || (n = t.length) == 0) {
            n = (t = resize()).length;
        }
        p = t[hash];
        while (p != null && !Objects.equals(p.key, key)) {
            p = p.next;
        }
        if (p == null) {
            Node<K, V> newNode = new Node<>(hash, key, value);
            newNode.next = t[hash];
            t[hash] = newNode;
            size++;
            if (size >= capacity * loadFactor) {
                resize();
            }
        } else {
            oldVal = p.value;
            p.value = value;
        }
        return oldVal;
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        return key.hashCode() ^ (key.hashCode() >>> 16);
    }
    @Override
    public boolean containsKey(Object key) {
        int hashIndex = (capacity - 1) & hash((K) key);
        Node<K, V> node;
        if (table != null) {
            node = table[hashIndex];
            while (node != null) {
                if (Objects.equals(key, node.key)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        int index = 0;
        Node<K, V> t = null;
        if (table != null && size != 0) {
            do {
                do { } while (index < size && ((t = table[index++]) == null));
                while (t != null) {
                    if (Objects.equals(value, t.value)) {
                        return true;
                    }
                    t = t.next;
                }
            } while (index < size);
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int hashIndex = (capacity - 1) & hash((K) key);
        Node<K, V> node;
        if (table != null) {
            node = table[hashIndex];
            while (node != null) {
                if (Objects.equals(key, node.key)) {
                    return node.value;
                }
                node = node.next;
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        return putVal(hash(key), key, value);
    }

    @Override
    public V remove(Object key) {
        int hashIndex = (capacity - 1) & hash((K) key);
        Node<K, V> node, prev;
        if (table != null) {
            node = table[hashIndex];
            if (node == null) {
                return null;
            }
            if (Objects.equals(key, node.key)) {
                table[hashIndex] = node.next;
                return node.value;
            }
            prev = node;
            node = node.next;
            while (node != null) {
                if (Objects.equals(key, node.key)) {
                    prev.next = node.next;
                    return node.value;
                }
                prev = node;
                node = node.next;
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            putVal(hash(entry.getKey()), entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; ++i) {
            table[i] = null;
        }
    }

    @Override
    public Set<K> keySet() {

        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MyHashMap<?,?>m)) {
            return false;
        }
        if (m.size() != size()) {
            return false;
        }
        //for all items check if each key that exists here have the same value at both places.
        // be sure to check the null value with containsKey(key) method.
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (Map.Entry<K, V> entry : entrySet()) {
            hash += entry.hashCode();
        }
        return hash;
    }

    public static void main(String[] args) {
        MyHashMap<Integer, String> map = new MyHashMap<>();
        map.put(1, "sub");
        map.put(2, "dsfa");
        System.out.println(map.capacity);
        map.put(3, "adf");
        System.out.println(map.capacity);
        map.put(4, "fg");
        System.out.println(map.capacity);
        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.size());

    }
}
