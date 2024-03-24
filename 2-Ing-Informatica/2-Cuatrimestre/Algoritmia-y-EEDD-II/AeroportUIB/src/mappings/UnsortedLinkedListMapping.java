package mappings;

import java.util.Iterator;

public class UnsortedLinkedListMapping<K, V> {
    private class Node {
        private K key;
        private V value;
        private Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node first;

    public UnsortedLinkedListMapping() {
        first = null;
    }

    public V get(K key) {
        Node p = first;
        while (p != null) {
            if (p.key.equals(key)) {
                return p.value;
            }
            p = p.next;
        }

        return null;
    }

    public V put(K key, V value) {
        Node p = first;
        while (p != null) {
            if (p.key.equals(key)) {
                V tmp = p.value;
                p.value = value;

                return tmp;
            }
            p = p.next;
        }

        first = new Node(key, value, first);

        return null;
    }

    public V remove(K key) {
        Node p = first; Node pp = null;
        while (p != null) {
            if (p.key.equals(key)) {
                V tmp = p.value;
                if (pp == null) first = p.next;
                else pp.next = p.next;

                return tmp;
            }
            pp = p; p = p.next;
        }

        return null;
    }

    public UnsortedLinkedListMappingIterator iterator() {
        return new UnsortedLinkedListMappingIterator();
    }

    private class UnsortedLinkedListMappingIterator implements Iterator {
        private Node iteratorI;

        public UnsortedLinkedListMappingIterator() {
            iteratorI = first;
        }

        @Override
        public boolean hasNext() {
            return first != null;
        }

        @Override
        public Object next() {
            Pair next = new Pair(iteratorI.key, iteratorI.value);
            iteratorI = iteratorI.next;

            return next;
        }
    }

    protected class Pair {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
