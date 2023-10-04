package TAD;

/**
 * Esta clase implementa la interfaz Queue y representa una cola basada en punteros.
 * Una cola es una estructura de datos que sigue el principio FIFO (First In, First Out),
 * lo que significa que el primer elemento en entrar es el primero en salir.
 *
 * @param <E> El tipo de elementos que se almacenan en la cola.
 */
public class PointerQueue<E> implements Queue<E> {
    private Node<E> first; // Referencia al primer elemento de la cola.
    private Node<E> last;  // Referencia al último elemento de la cola.
    private int countEl;   // Contador de elementos en la cola.

    /**
     * Constructor de la clase PointerQueue. Inicializa la cola como vacia.
     */
    public PointerQueue() {
        first = null;
        last = null;
        countEl = 0;
    }

    /**
     * Obtiene el numero de elementos actualmente en la cola.
     *
     * @return El numero de elementos en la cola.
     */
    public int getSize() {
        return countEl;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void enqueue(E element) {
        Node<E> tmp = new Node<>(element);
        if (isEmpty()) {
            first = tmp;
        } else {
            last.setNext(tmp);
        }
        last = tmp;
        countEl++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E dequeue() throws EmptyQueueException {
        if (isEmpty()) throw new EmptyQueueException();
        Node<E> tmp = first;
        first = first.getNext();
        countEl--;
        return tmp.getElement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E first() throws EmptyQueueException {
        if (isEmpty()) throw new EmptyQueueException();
        return first.getElement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return first == null;
    }
}
