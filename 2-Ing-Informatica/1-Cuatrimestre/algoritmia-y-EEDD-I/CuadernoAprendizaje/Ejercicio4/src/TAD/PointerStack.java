package TAD;

/**
 * Clase que implementa una pila usando punteros.
 * Implementa la interfaz Stack.
 *
 * @param <E> Tipo de elemento almacenado en la pila.
 */
public class PointerStack<E> implements Stack<E> {
    private Node<E> top;

    /**
     * Constructor que inicializa la pila.
     */
    public PointerStack() {
        top = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void push(E element) {
        Node<E> tmp = new Node<>(element);
        tmp.setNext(top);
        top = tmp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pop() throws EmptyStackException {
        if (top == null) throw new EmptyStackException();
        top = top.getNext();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E top() throws EmptyStackException {
        if (top == null) throw new EmptyStackException();
        return top.getElement();
    }
}

