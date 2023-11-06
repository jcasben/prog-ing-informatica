package searchs;

/**
 * It is capable of performing linear searches in an iterative way
 * and in a recursive way.
 *
 * @param <E> type of element of the data set.
 * @author jcasben
 * @author linkcla
 */
public class LinearSearch<E> {
    private int numberOfAccess;

    /**
     * Constructor method for {@link LinearSearch}.
     */
    public LinearSearch() {
        numberOfAccess = 0;
    }

    /**
     * Performs a linear search for a specific element in the given array using an iterative approach.
     * <br>
     * Complexity order: O(n) because it goes through the set of elements in the worst case.
     * @param array The array in which to search for the element.
     * @param element The element to be searched for.
     * @return The index of the element in the array if found; otherwise, returns -1.
     */
    public int linealSearchIterative(E[] array, E element) {
        for (int i = 0; i < array.length; i++) {
            numberOfAccess++;
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Performs a linear search for a specific element in the given array using a recursive approach.
     * <br>
     * Complexity order: O(n) because it goes through the set of elements in the worst case.
     * @param array The array in which to search for the element.
     * @param element The element to be searched for.
     * @param index The current index being checked.
     * @return The index of the element in the array if found; otherwise, returns -1.
     */
    public int linealSearchRecursive(E[] array, E element, int index) {
        numberOfAccess++;

        if (index == array.length - 1) {
            return -1;
        }
        if (array[index].equals(element)) {
            return index;
        }
        return linealSearchRecursive(array, element, index + 1);
    }

    /**
     * Getter method for numberOfAccess.
     * @return the amount of access to an element of the set.
     */
    public int getNumberOfAccess() {
        return numberOfAccess;
    }
}
