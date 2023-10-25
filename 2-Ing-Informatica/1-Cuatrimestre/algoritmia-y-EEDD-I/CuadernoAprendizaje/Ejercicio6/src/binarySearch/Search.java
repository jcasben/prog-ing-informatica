package binarySearch;

import java.util.Comparator;

/**
 * Contiene la implementacion de la busqueda binaria generica con {@link Comparable} y con {@link Comparator}.
 * @author jcasben
 * @author linkcla
 */
public class Search {
    /**
     * <p>
     *     Implementacion generica de la busqueda binaria. Utiliza el compareTo para hacer las comparaciones.
     *     Se requiere que el objeto que se quiere buscar y el conjunto de elementos sobre los que se quiere
     *     buscar implementen la interfaz {@link Comparable}.
     * </p>
     * <br>
     * <p>
     *     Especificacion formal:
     *     <li>Precondicion:  elements.length > 1 && target != null &&
     *                        ∀j 0 < j < elements.length: elements[j] != null &&
     *                        ∀i 0 < i < elements.length - 1 : elements[i]  elements [i+1] </> </li>
     *     <li>Postcondicion: (-1 → (∀i 0 < i < elements.length: target != elements[i])) &&
     *                        (!-1 → (target == elements[center])) </li>
     * </p>
     *
     * @param elements array de elementos sobre los que se quiere buscar
     * @param target elemento que se quiere encontrar
     * @return el indice del elemento dentro del array si se ha encontrado; -1 si no se ha encontrado.
     */
    public static <E extends Comparable<E>> int binarySearchComparable(E[] elements, E target) {
        int left = 0, right = elements.length - 1;
        int center = (left + right) / 2;
        while (left < right && elements[center].compareTo(target) != 0) {
            if (elements[center].compareTo(target) < 0) left = center + 1;
            else right = center - 1;
            center = (left + right) / 2;
        }
        return (elements[center].compareTo(target) == 0 ? center : -1);
    }

    /**
     * <p>
     *     Implementacion generica de la busqueda binaria. Requiere de un {@link Comparator} para comparar los
     *     elementos del conjunto.
     * </p>
     * <br>
     * <p>
     *     Especificacion formal:
     *     <li>Precondicion:  elements.length > 1 && target != null &&
     *                        ∀j 0 < j < elements.length: elements[j] != null &&
     *                        ∀i 0 < i < elements.length - 1 : elements[i]  elements [i+1] </> </li>
     *     <li>Postcondicion: (-1 → (∀i 0 < i < elements.length: target != elements[i])) &&
     *                        (!-1 → (target == elements[center])) </li>
     * </p>
     *
     * @param elements array de elementos sobre el que queremos buscar.
     * @param target elemento que queremos encontrar.
     * @param comparator objeto de la clase {@link Comparator}.
     * @return el indice del elemento dentro del array si se ha encontrado; -1 si no se ha encontrado.
     */
    public static <E> int binarySearchComparator(E[] elements, E target, Comparator<E> comparator) {
        int left = 0, right = elements.length - 1;
        int center = left + (right - left) / 2;
        while (left <= right) {
            if (comparator.compare(elements[center], target) == 0) return center;
            else if (comparator.compare(elements[center], target) < 0) left = center + 1;
            else right = center - 1;
            center = left + (right - left) / 2;
        }
        return -1;
    }
}
