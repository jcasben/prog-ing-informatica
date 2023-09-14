public class Node<E> {
    private E element;
    private Node<E> next;

    public Node(E element) {
        this.element = element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public E getElement() {
        return element;
    }

    public void setNext(Node<E> nodo) {
        next = nodo;
    }

    public Node<E> getNext() {
        return next;
    }
}
