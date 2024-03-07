package linkedList;

public class LinkedList<E extends Comparable<E>> {
    private Node first;

    public LinkedList() {
        first = null;
    }

    public void add(E element) {
        first = new Node(element, first);
    }

    private Node middle(Node firstNode) {
        Node slow = firstNode;
        Node fast = firstNode;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        return slow;
    }

    public void mergeSort() {
        first = mergeSort(first);
    }

    private Node mergeSort(Node node) {
        if (node == null || node.getNext() == null) {
            return node;
        }

        Node mid = middle(node);
        Node nextMid = mid.getNext();
        mid.setNext(null);
        Node l = mergeSort(node);
        Node r = mergeSort(nextMid);

        return merge(l, r);
    }

    private Node merge(Node l, Node r) {
        Node f = null;
        Node current = null;

        if (((E)l.getItem()).compareTo((E) r.getItem()) <= 0) {
            current = l;
            l = l.getNext();
            f = current;
        } else {
            current = r;
            r = r.getNext();
            f = current;
        }

        while (l != null && r != null) {
            if (((E)l.getItem()).compareTo((E) r.getItem()) < 0) {
                current.setNext(l);
                l = l.getNext();
            } else {
                current.setNext(r);
                r = r.getNext();
            }
            current = current.getNext();
        }

        current.setNext(l == null ? r : l);

        return f;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node actual = first;
        while (actual != null) {
            sb.append(actual.getItem());
            sb.append(" ");
            actual = actual.getNext();
        }

        return sb.toString();
    }
}
