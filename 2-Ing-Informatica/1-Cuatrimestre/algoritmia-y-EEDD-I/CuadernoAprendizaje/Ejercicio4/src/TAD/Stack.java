/**
 * Esta interfaz define las operaciones basicas para una estructura de datos tipo pila.
 * Una pila es una estructura de datos que sigue el principio LIFO (Last In, First Out),
 * lo que significa que el último elemento añadido es el primero en ser retirado.
 *
 * @param <E> El tipo de elementos que se almacenan en la pila.
 */
package TAD;

public interface Stack<E> {

    /**
     * Agrega un elemento a la parte superior de la pila.
     *
     * @param element El elemento que se va a agregar a la pila.
     */
    void push(E element);

    /**
     * Retira el elemento en la parte superior de la pila.
     */
    void pop() throws EmptyStackException;

    /**
     * Obtiene el elemento en la parte superior de la pila sin retirarlo.
     *
     * @return El elemento en la parte superior de la pila.
     * @throws EmptyStackException Si la pila está vacia y se intenta acceder al elemento superior.
     */
    E top() throws EmptyStackException;

    /**
     * Excepción personalizada para indicar que la pila está vacia.
     */
    class EmptyStackException extends Exception {

        /**
         * Constructor que establece un mensaje de error predeterminado.
         */
        public EmptyStackException() {
            super("ERROR: La pila esta vacia.");
        }
    }
}
