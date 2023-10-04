/**
 * Esta interfaz define las operaciones basicas para una estructura de datos tipo cola.
 * Una cola es una estructura de datos que sigue el principio FIFO (First In, First Out),
 * lo que significa que el primer elemento añadido es el primero en ser retirado.
 *
 * @param <E> El tipo de elementos que se almacenan en la cola.
 */
package TAD;

public interface Queue<E> {

    /**
     * Agrega un elemento al final de la cola.
     *
     * @param element El elemento que se va a agregar a la cola.
     */
    void enqueue(E element);

    /**
     * Retira y devuelve el elemento en el frente de la cola.
     *
     * @return El elemento en el frente de la cola.
     * @throws EmptyQueueException Si la cola esta vacia y se intenta realizar la operacion de desencolar.
     */
    E dequeue() throws EmptyQueueException;

    /**
     * Devuelve el primer elemento en el frente de la cola.
     *
     * @return El elemento en el frente de la cola.
     * @throws EmptyQueueException Si la cola esta vacia y se intenta realizar la operacion de ver el primer elemento.
     */
    E first() throws EmptyQueueException;

    /**
     * Determina si la cola esta vacia o no.
     *
     * @return True si la cola esta vacia, false si contiene elementos.
     */
    boolean isEmpty();

    /**
     * Excepcion personalizada para indicar que la cola está vacia.
     */
    class EmptyQueueException extends Exception {

        /**
         * Constructor que establece un mensaje de error predeterminado.
         */
        public EmptyQueueException() {
            super("ERROR: La cola esta vacia.");
        }
    }
}
