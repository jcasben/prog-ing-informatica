package TAD;

import java.util.ArrayList;

/**
 * Clase que implementa una cola usando un cursor.
 * Implementa la interfaz Queue.
 *
 * @param <E> Tipo de elemento almacenado en la cola.
 */
public class CursorQueue<E> implements Queue<E> {
    private final ArrayList<E> elements;
    private int first;

    /**
     * Constructor que inicializa la cola.
     */
    public CursorQueue() {
        elements = new ArrayList<>();
        /*
         La cola se inicializa vacía, lo denotamos iniciando el índice del primer
         elemento a -1.
        */
        first = -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enqueue(E element) {
        if (first == -1) first++;
        elements.add(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E dequeue() throws EmptyQueueException {
        if (first == -1) throw new EmptyQueueException();
        return elements.get(first++);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E first() throws EmptyQueueException {
        if (elements.isEmpty()) throw new EmptyQueueException();
        return elements.get(first);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return false;
    }
}

