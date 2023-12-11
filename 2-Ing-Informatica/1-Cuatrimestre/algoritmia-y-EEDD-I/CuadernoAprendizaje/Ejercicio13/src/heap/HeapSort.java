package heap;

/**
 * Interfície base per implementar algorisme d'ordenació basat amb un monticle
 * @author Marc Link y Jesús Castillo
 */
public interface HeapSort <E extends Comparable<E>> {
    
    /**
     * Donat un conjunt de dade h, l'ordena
     * @param h conjunt de dades que s'ordenarà
     */
    void heapSort(E[] h);
}
