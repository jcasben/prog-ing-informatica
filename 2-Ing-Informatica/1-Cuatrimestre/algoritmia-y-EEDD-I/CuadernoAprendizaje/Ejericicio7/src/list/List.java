package list;

/**
 * Interface that defines the methods that a
 * generic List must implement.
 *
 * @param <E> type of element that will storage
 *            the List.
 * @author jcasben
 * @author linkcla
 */
public interface List<E> {
    /**
     * <p>Adds the specified element to the list in the correct
     *     position.</p>
     * <br>
     * <p>Formal specification:
     *     <li>Precondition: The List must not be full of elements or
     *     out of memory. The element must not be null and has to be the
     *     same type as the Ordered List.</li>
     *     <li>Post condition: the element has to belong to the set of
     *     elements of the Ordered List. The set of elements of the Ordered
     *     List has to be the same as before adding the new element, plus the
     *     new element.</li></p>
     *
     * @param e element to the introduced in the list.
     * @throws FullListException exception for when the list is full.
     */
    void add(E e) throws FullListException;

    /**
     * <p>Empties the list.</p>
     * <br>
     * <p>Formal specification:
     *     <li>Precondition: The quantity of elements of the
     *          Ordered List must be >= 0.</li>
     *     <li>Post condition: The quantity of elements of the Ordered List
     *     must be == 0.</li></p>
     */
    void clear();

    /**
     * <p>Determines if an element exists in the list.</p>
     * <br>
     * <p>Formal specification:
     *     <li>Precondition: the set of elements of the Ordered List
     *     must not be null.</li>
     *     <li>Post condition: the set of elements of the Ordered List
     *     has to be the same as before the method.</li></p>
     *
     * @param e element to be searched in the list.
     * @return true if the element exists in the list;
     * false if it doesn't exist.
     */
    boolean contains(E e);

    /**
     * <p>Determines if the list is empty or not.</p>
     * <br>
     * <p>Formal specification:
     *     <li>Precondition: the set of elements of the Ordered List
     *     must not be null.</li>
     *     <li>Post condition: the set of elements of the Ordered List
     *     must be the same as before the method.</li></p>
     *
     * @return true if the list is empty;
     *         false if the list is not empty.
     */
    boolean isEmpty();

    /**
     * <p>Removes the specified element from the list.</p>
     * <br>
     * <p>Formal specification:
     *     <li>Precondition: the set of elements of the Ordered List
     *     must not be null.</li>
     *     <li>Post condition: the Ordered List must not contain the
     *     element that was specified to remove.</li></p>
     *
     * @param e the element to remove from the list.
     * @throws EmptyListException exception for when the list is empty.
     */
    void remove(E e) throws EmptyListException;

    /**
     * Exception that will be thrown when we try to do some
     * operation with the elements from inside the list, and
     * the list is empty.
     */
    class EmptyListException extends Exception {
        /**
         * Constructor of the {@link EmptyListException}.
         */
        public EmptyListException() {
            super("ERROR: the list is empty.");
        }
    }

    /**
     * Exception that is thrown when we try to add a new element
     * to the List, and it is full.
     */
    class FullListException extends Exception {
        /**
         * Constructor of the {@link FullListException}.
         */
        public FullListException() {
            super("ERROR: the list is full.");
        }
    }
}
