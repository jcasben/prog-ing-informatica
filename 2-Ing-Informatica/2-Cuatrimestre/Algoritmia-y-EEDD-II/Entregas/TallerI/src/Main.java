/**
 * El algoritmo de ordenación que he elegido ha sido el Merge Sort sobre listas
 * enlazadas. He elegido esta modificación ya que el enunciado requería que el algoritmo
 * no reservase memoria adicional y al tratarse de una serie de nodos enlazados,
 * no se reserva ningún tipo de memoria extra por encima de los nodos que ya tenemos.
 *
 * La elección del algorimo Merge Sort se ha debido a que se requería que el algoritmo
 * tuviera un coste computacional de O(n*log n), y el Merge Sort cumple con este criterio.
 *
 * @author jcasben
 */

import java.util.*;

public class Main {
    static Scanner in;

    public static void main(String[] args) {
        in = new Scanner(System.in);
        LinkedList<Integer> list;

        // Read the first number of elements to be read
        int n = in.nextInt();
        if (n == 0) {
            return;
        }
        while(n > 0) {
            list = new LinkedList<>();
            // Fill the array with the user input
            for(int i = 0; i < n; i++) {
                 list.add(in.nextInt());
            }
            // Sort the array
            list.mergeSort();
            // Print the solution to the set of elements
            System.out.println(findTrend(list));
            // Read the next number of elements to be read
            n = in.nextInt();
        }
    }

    /**
     * Finds the trend of a set of elements.
     * @param list set of elements.
     * @return the trend of the set of elements.
     */
    private static int findTrend(LinkedList<Integer> list) {
        int actualCount = 0;
        int count = 0;
        int trend = 0;
        int actualFashion = 0;

        Node actual = list.getFirst();
        while(actual != null) {
            if (actualCount == 0) {
                actualFashion = (int) actual.getItem();
                actualCount++;
            } else if (actualFashion == (int) actual.getItem()) {
                actualCount++;
            } else {
                if (actualCount > count) {
                    count = actualCount;
                    trend = actualFashion;
                }
                actualFashion = (int) actual.getItem();
                actualCount = 1;
            }
            actual = actual.getNext();
        }

        return actualCount > count ? actualFashion : trend;
    }
}

/**
 * Represents the instance of a node. A concatenation
 * of nodes forms a {@link LinkedList}.
 */
class Node {
    private Object item;
    private Node next;
    public Node(Object item, Node next) {
        this.item = item;
        this.next = next;
    }

    public Object getItem() {
        return item;
    }

    public Node getNext() {
        return next;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

/**
 * Custom implementation of a linked list, which is formed
 * by elements of the {@link Node} class.
 * @param <E> type of element to be saved into the Nodes.
 */
class LinkedList<E extends Comparable<E>> {
    private Node first;

    public LinkedList() {
        first = null;
    }

    public void add(E element) {
        first = new Node(element, first);
    }

    public Node getFirst() {
        return first;
    }

    /**
     * Iters through the list to find the central {@link Node}.
     * @param firstNode first node of the list.
     * @return the central {@link Node} of the list.
     */
    private Node middle(Node firstNode) {
        Node slow = firstNode;
        Node fast = firstNode;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        return slow;
    }

    /**
     * First call to merge sort.
     */
    public void mergeSort() {
        first = mergeSort(first);
    }

    /**
     * Sorts a {@link LinkedList} using the merge sort algorithm.
     * @param node first {@link Node} of the {@link LinkedList}.
     * @return the first {@link Node} of the ordered {@link LinkedList}.
     */
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

    /**
     * Merges two subsets of nodes into a list.
     * @param l first {@link Node} of the first subset.
     * @param r first {@link Node} of the second subset.
     * @return the new initial {@link Node} of the list.
     */
    private Node merge(Node l, Node r) {
        Node f;
        Node current;

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