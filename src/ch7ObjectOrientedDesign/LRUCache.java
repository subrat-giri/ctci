package ch7ObjectOrientedDesign;

import java.util.Objects;

public class LRUCache<K, V> {
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
        Node<K, V> before;
        Node<K, V> after;

        Node(K key, V value) {
             this.key = key;
             this.value = value;
        }
    }

    private final Node<K, V>[] table;
    private int size;
    private final int capacity = 1 << 2;
    private final int MAX_CACHE_SIZE = (int) (capacity * 0.75f);
    private Node<K, V> head;
    private Node<K, V> tail;

    public LRUCache() {
        table = (Node<K, V>[]) new Node[capacity];
        size = 0;
    }

    private int hash(K key) {
        int hash = key.hashCode();
        return (hash ^ (hash >>> 16)) & capacity - 1;
    }
    private Node<K, V> getNode(K key) {
        Node<K, V> curr = table[hash(key)];
        while (curr != null) {
            if (Objects.equals(curr.key, key)) {
                if (head == tail || curr == tail) {
                    return curr;
                } else if (head == curr) {
                    head = curr.after;
                    head.before = null;
                } else {
                    curr.before.after = curr.after;
                    curr.after.before = curr.before;

                }
                tail.after = curr;
                curr.before = tail;
                curr.after = null;
                tail = curr;
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }

    public V get(K key) {
        Node<K, V> node = getNode(key);
        return node == null ? null : node.value;
    }
    public V put(K key, V value) {
        Node<K, V> node = getNode(key);
        V oldVal;
        if (node == null) {
            if (size >= MAX_CACHE_SIZE) {
                remove(head.key);
            }
            Node<K, V> newNode = new Node<>(key, value);
            newNode.next = table[hash(key)];
            table[hash(key)] = newNode;
            if (head == null) {
                head = tail = newNode;
            } else {
                tail.after = newNode;
                newNode.before = tail;
                tail = newNode;
            }
            size++;
            return value;
        } else {
            oldVal = node.value;
            node.value = value;
            return oldVal;
        }
    }

    public V remove(K key) {
        Node<K, V> curr = table[hash(key)], prev = null;
        while (curr != null) {
            if (Objects.equals(curr.key, key)) {
                if (head == tail) {
                    head = tail = null;
                } else if (head == curr) {
                    head = curr.after;
                    head.before = null;
                } else if (curr == tail) {
                    tail = curr.before;
                    tail.after = null;
                } else {
                    curr.before.after = curr.after;
                    curr.after.before = curr.before;
                }
                if (prev == null) {
                    table[hash(key)] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                size--;
                return curr.value;
            }
            prev = curr;
            curr = curr.next;
        }
        return null;
    }
}
