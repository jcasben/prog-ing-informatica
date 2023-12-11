package heap;

/**
 * Implementación del algoritmo de ordenación HeapSort.
 * @author Marc Link y Jesús Castillo.
 */
public class HeapSortImpl<E extends Comparable<E>> implements HeapSort<E> {
    /**
     * Ordenación de un conjunto de datos h mediante el algoritmo HeapSort.
     * @pre h != null
     * @post h queda ordenado.
     * @param h conjunto de datos que se ordenará.
     * @complexity O(n log n)
     */
    @Override
    public void heapSort(E[] h) {
        int n = h.length;
        //Creamos el montículo
        for (int i = n / 2 - 1; i >= 0; i--) heapify(h, n, i);

        //Extraemos uno a uno los elementos del montículo
        for (int i = n - 1; i >= 0; i--) {
            //Movemos la raíz actual al final
            E aux = h[0];
            h[0] = h[i];
            h[i] = aux;

            //Realizamos heapify en el montículo reducido
            heapify(h, i, 0);
        }
    }

    /**
     * Función que ordena un subárbol del heap con raíz en el índice i.
     * @pre h != null
     * @post El subárbol queda ordenado.
     * @complexity O(log n)
     * @param h conjunto de datos que se ordenará.
     * @param n tamaño del montículo.
     * @param i índice de la raíz del subárbol.
     */
    private void heapify(E[] h, int n, int i) {
        int max = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        //Si el hijo izquierdo es mayor que la raíz
        if (left < n && h[left].compareTo(h[max]) > 0) max = left;

        //Si el hijo derecho es mayor que la raíz
        if (right < n && h[right].compareTo(h[max]) > 0) max = right;

        //Si el mayor no es la raíz
        if (max != i) {
            E aux = h[i];
            h[i] = h[max];
            h[max] = aux;

            //Recursivamente ordenamos el subárbol afectado
            heapify(h, n, max);
        }
    }
}
