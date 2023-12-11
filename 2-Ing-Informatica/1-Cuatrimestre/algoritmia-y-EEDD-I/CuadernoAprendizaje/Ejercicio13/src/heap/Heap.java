package heap;

/**
 * Implementación del TAD Heap, visto en clase.
 * @param <T> Tipo de los elementos del montículo.
 * @author Marc Link y Jesús Castillo.
 */
public class Heap<T extends Comparable<T>> {
    private final T[] heap;
    private int n;

    public Heap(int max) {
        heap = (T[]) new Object[max];
        this.n = 0;
    }

    /**
     * Comprueba si el montículo está vacío.
     * @return true si el montículo está vacío, false en caso contrario.
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Comprueba si el montículo está lleno.
     * @return true si el montículo está lleno, false en caso contrario.
     */
    public boolean isFull() {
        return n == heap.length;
    }

    /**
     * Inserta un elemento en el montículo.
     * @pre heap !isFull()
     * @post El elemento ha sido insertado en el montículo.
     * @complexity O(log n)
     * @param element El elemento a insertar.
     */
    public void put(T element) {
        n++;
        int i = n; int pi = i / 2;
        while ((pi > 0) && (heap[pi].compareTo(element) > 0)) {
            heap[i] = heap[pi];
            i = pi; pi = i / 2;
        }
        heap[i] = element;
    }

    /**
     * Extrae el elemento de la cima del montículo.
     * @pre heap !isEmpty()
     * @post El elemento de la cima del montículo ha sido extraído.
     * @complexity O(log n)
     * @return El elemento de la cima del montículo.
     */
    public T pop() {
        T element = heap[n];
        T root = heap[0];
        int i = 1; int lci = 2 * i;

        if ((lci <= n) && (heap[lci].compareTo(element) < 0)) lci++;
        while ((lci <= n) && (heap[lci].compareTo(element) < 0)) {
            heap[i] = heap[lci];
            i = lci; lci = 2 * i;

            if ((lci <= n) && (heap[lci].compareTo(element) < 0)) lci++;
        }
        heap[i] = element;

        return root;
    }
}
