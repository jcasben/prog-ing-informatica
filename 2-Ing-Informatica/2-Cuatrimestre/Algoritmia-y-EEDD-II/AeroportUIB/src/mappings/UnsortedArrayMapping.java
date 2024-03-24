package mappings;

import java.util.Iterator;

public class UnsortedArrayMapping<K, V> {
    private final K [] keys;
    private final V [] values;
    private int n;

    public UnsortedArrayMapping(int max) {
        keys = (K[]) new Object[max];
        values = (V[]) new Object[max];
        n = 0;
    }

    public V get(K key) {
        for (int i = 0; i < n; i++) {
            if (keys[i].equals(key)) return values[i];
        }

        return null;
    }

    public V put(K key, V value) {
        for (int i = 0; i < n; i++) {
            if (keys[i].equals(key)) {
                V tmp = values[i];
                values[i] = value;

                return tmp;
            }
        }

        if (n < keys.length) {
            keys[n] = key;
            values[n] = value;
            n++;
        }

        return null;
    }

    public V remove(K key) {
        for (int i = 0; i < n; i++) {
            if (keys[i].equals(key)) {
                V tmp = values[i];
                keys[i] = keys[n - 1];
                values[i] = values[n - 1];
                n--;

                return tmp;
            }
        }

        return null;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public IteratorUnsortedArrayMapping iterator() {
        return new IteratorUnsortedArrayMapping();
    }

    private class IteratorUnsortedArrayMapping implements Iterator {
        private int iteratorI;
        private IteratorUnsortedArrayMapping() {
            iteratorI = 0;
        }

        @Override
        public boolean hasNext() {
            return iteratorI < n;
        }

        @Override
        public Object next() {
            iteratorI++;
            return new Pair<>(keys[iteratorI - 1], values[iteratorI - 1]);
        }
    }

    protected static class Pair<K, V> {
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
