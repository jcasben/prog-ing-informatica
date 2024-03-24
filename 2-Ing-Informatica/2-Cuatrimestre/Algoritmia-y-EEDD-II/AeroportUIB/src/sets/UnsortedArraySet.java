package sets;

import java.util.Iterator;

public class UnsortedArraySet<E> {
    private final E[] array;
    private int n;

    public UnsortedArraySet(int max) {
        array = (E[]) new Object[max];
        n = 0;
    }

    public boolean contains(E element) {
        for (E el : array) {
            if (el.equals(element)) return true;
        }

        return false;
    }

    public boolean add(E element) {
        if (n < array.length && !contains(element)) {
            array[n++] = element;
            return true;
        }

        return false;
    }

    public boolean remove(E element) {
        for (int i = 0; i < n; i++) {
            if (array[i].equals(element)) {
                array[i] = array[n - 1];
                n--;

                return true;
            }
        }

        return false;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public IteratorUnsortedArraySet iterator() {
        return new IteratorUnsortedArraySet();
    }

    private class IteratorUnsortedArraySet implements Iterator<E> {

        private int iteratorI;

        private IteratorUnsortedArraySet() {
            iteratorI = 0;
        }

        @Override
        public boolean hasNext() {
            return iteratorI < n;
        }

        @Override
        public E next() {
            return array[iteratorI++];
        }
    }
}
