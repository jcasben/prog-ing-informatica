package list;

/**
 * Node class that is used to implement the Ordered List.
 *
 * @param <E> type that will contain the node.
 * @author jcasben
 * @author linkcla
 */
public class Node<E> {
    private E element;
    private Node<E> next;

    /**
     * Constructor of the class {@link Node}.
     *
     * @param element element that contains the node.
     */
    public Node(E element) {
        this.element = element;
        this.next = null;
    }

    /**
     * Setter method for element attribute.
     *
     * @param element element to be set.
     */
    public void setElement(E element) {
        this.element = element;
    }

    /**
     * Getter of the element attribute.
     *
     * @return element of the node.
     */
    public E getElement() {
        return element;
    }

    /**
     * Setter for next attribute.
     *
     * @param node next node to be set.
     */
    public void setNext(Node<E> node) {
        next = node;
    }

    /**
     * Getter fot next attribute.
     *
     * @return next node.
     */
    public Node<E> getNext() {
        return next;
    }
}
