package TAD;

import java.util.ArrayList;

/**
 * Clase que implementa una pila usando un cursor.
 * Implementa la interfaz Stack.
 *
 * @param <E> Tipo de elemento almacenado en la pila.
 */
public class CursorStack<E> implements Stack<E> {
    private final ArrayList<E> elements;
    private int top;

    /**
     * Constructor que inicializa la pila.
     */
    public CursorStack() {
        elements = new ArrayList<>();
        // Como inicializamos la pila vacía, indicamos que top = -1
        this.top = -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void push(E element) {
        elements.add(element);
        top++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pop() throws EmptyStackException{
        if (elements.isEmpty()) throw new EmptyStackException();
        top--;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E top() throws EmptyStackException {
        if (top == -1) throw new EmptyStackException();
        return elements.get(top);
    }
}
