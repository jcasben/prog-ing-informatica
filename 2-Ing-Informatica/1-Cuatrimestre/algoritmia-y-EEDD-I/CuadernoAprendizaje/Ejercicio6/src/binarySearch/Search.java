package binarySearch;

import java.util.Comparator;

/**
 * Contains the implementation of the generic binary
 * search with {@link Comparable} and {@link Comparator}.
 * @author jcasben
 * @author linkcla
 */
public class Search {
    /**
     * <p>Generic implementation of the binary search. Uses
     *    compareTo for doing the comparisons. It requires that the
     *    object that we want to search and the set of elements
     *    both implement the interface {@link Comparable}.</p>
     * <br>
     * <p>Formal specification:
     *     <li>Precondition: elements.length > 1 && target != null &&
     *                        ∀j 0 < j < elements.length: elements[j] != null &&
     *                        ∀i 0 < i < elements.length - 1 : elements[i]  elements [i+1]</li>
     *     <li>Post condition: (-1 → (∀i 0 < i < elements.length: target != elements[i])) &&
     *                        (!-1 → (target == elements[center]))</li></p>
     *
     * @param elements array that we want to iterate on.
     * @param target element that we want to find.
     * @return the element's index if it was found; -1 if it wasn't found.
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
     * <p>Generic implementation of the binary search.
     * Requires of a {@link Comparator} for comparing the
     * elements of the set.</p>
     * <br>
     * <p>Formal specification:
     *     <li>Precondition: elements.length > 1 && target != null &&
     *                        ∀j 0 < j < elements.length: elements[j] != null &&
     *                        ∀i 0 < i < elements.length - 1 : elements[i]  elements [i+1]</li>
     *     <li>Post condition: (-1 → (∀i 0 < i < elements.length: target != elements[i])) &&
     *                        (!-1 → (target == elements[center])) </li></p>
     *
     * @param elements array that we want to iterate on.
     * @param target element that we want to find.
     * @param comparator object of the class {@link Comparator}.
     * @return the element's index if it was found; -1 if it wasn't found.
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
