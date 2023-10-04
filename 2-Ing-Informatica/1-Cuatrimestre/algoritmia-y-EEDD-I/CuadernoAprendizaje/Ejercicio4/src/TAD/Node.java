package TAD;

/**
 * Clase que representa un nodo para una estructura de datos enlazada.
 *
 * @param <E> Tipo de elemento almacenado en el nodo.
 */
public class Node<E> {
    private E element;
    private Node<E> next;

    /**
     * Constructor que inicializa el nodo con un elemento.
     *
     * @param element Elemento a almacenar en el nodo.
     */
    public Node(E element) {
        this.element = element;
    }

    /**
     * Establece el elemento almacenado en el nodo.
     *
     * @param element Elemento a almacenar en el nodo.
     */
    public void setElement(E element) {
        this.element = element;
    }

    /**
     * Obtiene el elemento almacenado en el nodo.
     *
     * @return Elemento almacenado en el nodo.
     */
    public E getElement() {
        return element;
    }

    /**
     * Establece el siguiente nodo en la estructura enlazada.
     *
     * @param node Siguiente nodo en la estructura enlazada.
     */
    public void setNext(Node<E> node) {
        next = node;
    }

    /**
     * Obtiene el siguiente nodo en la estructura enlazada.
     *
     * @return Siguiente nodo en la estructura enlazada.
     */
    public Node<E> getNext() {
        return next;
    }
}