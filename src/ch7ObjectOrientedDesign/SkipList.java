package ch7ObjectOrientedDesign;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

public class SkipList<K extends Comparable<K>, V> {
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;
        Node<K, V> above;
        Node<K, V> below;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V> head;
    private Node<K, V> tail;
    private final Random random = new Random();

    public SkipList(K minVal, K maxVal) {
        //sentinel nodes
        head = new Node<K, V>(minVal, null);
        tail = new Node<K, V>(maxVal, null);
        head.next = tail;
        tail.prev = head;
    }

    private Node<K, V> skip_search_node(K key) {
        Node<K, V> n = head;
        while (true) {
            while (n.next.key.compareTo(key) <= 0) {
                n = n.next;
            }
            if (n.key.compareTo(key) == 0) {
                return n;
            }
            if (n.below == null) {
                break;
            } else {
                n = n.below;
            }
        }
        return n;
    }

    public V skip_search(K key) {
        Node<K, V> n = skip_search_node(key);
        if (Objects.equals(n.key, key)) {
            return n.value;
        }
        return null;
    }

    public V skip_remove(K key) {
        Node<K, V> node = skip_search_node(key);
        if (node == null) {
            return null;
        }
        V value = node.value;
        do {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.above = null;
            node = node.below;
        } while (node != null);
        return value;
    }

    public V skip_insert(K key, V value) {
        Node<K, V> n = head;
        Deque<Node<K, V>> stack = new LinkedList<>();

        while (true) {
            while (n.next.key.compareTo(key) <= 0) {
                n = n.next;
            }

            // valid if passed key matches the existing key.
            if (n.key.compareTo(key) == 0) {
                V oldValue = n.value;
                do {
                    n.value = value;
                    n = n.below;
                } while (n != null);
                return oldValue;
            }

            if (n.below == null) {
                break;
            } else {
                stack.addFirst(n);
                n = n.below;
            }
        }

        //found the exact node where to insert.
        stack.addFirst(n);
        Node<K, V> belowNewNode = null;
        do {
            Node<K, V> prevOfNewNode = stack.pollFirst();
            if (prevOfNewNode != null) {
                belowNewNode = insertNodeAndLink(belowNewNode, prevOfNewNode, key, value);
            } else {
                belowNewNode = increaseLevelAndInsert(belowNewNode, key, value);
            }
        } while (random.nextBoolean());
        return belowNewNode.value;
    }


    private Node<K, V> increaseLevelAndInsert(Node<K,V> belowNewNode, K key, V value) {
        Node<K, V> newHead = new Node<>(head.key, head.value);
        newHead.below = head;
        head.above = newHead;
        head = newHead;

        Node<K, V> newTail = new Node<>(tail.key, tail.value);
        newTail.below = tail;
        tail.above = newTail;
        tail = newTail;

        head.next = tail;
        tail.prev = head;
        return insertNodeAndLink(belowNewNode, newHead, key, value);
    }

    private Node<K, V> insertNodeAndLink(Node<K, V> belowNode, Node<K,V> prevOfNewNode, K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);

        //set prev and next node.
        newNode.next = prevOfNewNode.next;
        prevOfNewNode.next.prev = newNode;
        prevOfNewNode.next = newNode;
        newNode.prev = prevOfNewNode;

        //set above and below node.
        newNode.below = belowNode;
        if (belowNode != null) {
            belowNode.above = newNode;
        }
        return newNode;
    }

    public void print_skip_list() {
        Node<K, V> levelItr, heightItr = head;
        while (heightItr != null) {
            levelItr = heightItr;
            while(levelItr != null) {
                System.out.print("[key = " + levelItr.key + " : value = " + levelItr.value + "] ");
                levelItr = levelItr.next;
            }
            heightItr = heightItr.below;
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //instantiating with max and min value of generic value.
        SkipList<Integer, String> skip_list = new SkipList<>(Integer.MIN_VALUE, Integer.MAX_VALUE);
        skip_list.skip_insert(8 , "8jity");
        skip_list.skip_insert(5 , "5jity");
        skip_list.skip_insert(7 , "7ity");
        skip_list.skip_insert(2 , "2jity");
        skip_list.skip_insert(4 , "4jity");
        skip_list.skip_insert(10 , "10jity");
        skip_list.skip_insert(8 , "8888jity");
        skip_list.skip_insert(6 , "6jity");
        skip_list.print_skip_list();
        System.out.println();
        skip_list.skip_remove(2);
        skip_list.print_skip_list();
    }
}
