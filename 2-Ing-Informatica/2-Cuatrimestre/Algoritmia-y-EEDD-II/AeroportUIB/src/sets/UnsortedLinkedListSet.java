package sets;

import java.util.Iterator;

public class UnsortedLinkedListSet<E> {
    private class Node {
        private E element;
        private Node next;

        private Node(E element, Node next) {
            this.element = element;
            this.next = next;
        }
    }

    private Node first;

    public UnsortedLinkedListSet() {
        first = null;
    }

    public boolean contains(E element) {
        Node tmp = first;
        while(tmp != null) {
            if (tmp.element.equals(element)) return true;
            tmp = tmp.next;
        }

        return false;
    }

    public boolean add(E element) {
        if(!contains(element)) {
            Node n = new Node(element, first);
            first = n;

            return true;
        }

        return false;
    }

    public boolean remove(E element) {
        Node p = first;
        Node pp = null;
        boolean found = false;

        while (p != null && !found) {
            found = p.element.equals(element);
            if (!found) {
                pp = p;
                p = p.next;
            }
        }
        if (found) {
            if (pp == null) first = p.next;
            else pp.next = p.next;
        }

        return found;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public UnsortedLinkedListSetIterator iterator() {
        return new UnsortedLinkedListSetIterator();
    }

    private class UnsortedLinkedListSetIterator implements Iterator<E> {
        private Node iteratorI;

        public UnsortedLinkedListSetIterator() {
            iteratorI = first;
        }

        @Override
        public boolean hasNext() {
            return iteratorI != null;
        }

        @Override
        public E next() {
            E element = iteratorI.element;
            iteratorI = iteratorI.next;

            return element;
        }
    }
}
