package searchs;

/**
 * It is capable of performing binary searches in an iterative way and in a recursive way.
 *
 * @param <E> type of element of the data set.
 * @author jcasben
 * @author linkcla
 */
public class BinarySearch<E extends Comparable<E>> {
    private int numberOfAccess;

    /**
     * Constructor method for {@link BinarySearch}.
     */
    public BinarySearch() {
        numberOfAccess = 0;
    }

    /**
     * Performs an iterative binary search to find the specified target element in a sorted array.
     * <br>
     * Complexity Order: O(log n), because in each iteration we reduce to half the search space.
     * @param elements An array of elements to search within.
     * @param target The element to find in the array.
     * @return The index of the target element if found, or -1 if not found.
     */
    public int binarySearchIterative(E[] elements, E target) {
        int left = 0, right = elements.length - 1;
        int center = (left + right) / 2;
        while (left <= right) {
            numberOfAccess++;
            int comparison = elements[center].compareTo(target);
            if (comparison == 0) {
                return center; // Element found.
            } else if (comparison < 0) {
                left = center + 1; // Adjust the left boundary.
            } else {
                right = center - 1; // Adjust the right boundary.
            }
            center = (left + right) / 2;
        }
        return -1; // Element not found.
    }


    /**
     * Performs a recursive binary search to find the specified target element in a sorted array of generic elements.
     * <br>
     * Complexity Order: O(log n), because in each iteration we reduce to half the search space.
     * @param array An array of elements to search within.
     * @param target The element to find in the array.
     * @param left The left boundary of the current search range.
     * @param right The right boundary of the current search range.
     * @return The index of the target element if found, or -1 if not found.
     */
    public int binarySearchRecursive(E[] array, E target, int left, int right) {
        numberOfAccess++;
        if (left > right) {
            return -1; // Element not found in the array.
        }
        int center = left + (right - left) / 2;
        if (array[center].compareTo(target) == 0) {
            return center; // Element found.
        } else if (array[center].compareTo(target) < 0) {
            return binarySearchRecursive(array, target, center + 1, right); // Search in the right half.
        } else {
            return binarySearchRecursive(array, target, left, center - 1); // Search in the left half.
        }
    }

    /**
     * Getter method for numberOfAccess.
     * @return amount of access to elements of the set.
     */
    public int getNumberOfAccess() {
        return numberOfAccess;
    }
}
