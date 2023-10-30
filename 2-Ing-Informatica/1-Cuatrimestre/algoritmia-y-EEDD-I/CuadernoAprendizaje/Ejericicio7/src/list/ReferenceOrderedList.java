package list;

/**
 * Ordered List implemented with references.
 *
 * @param <E> type of data that will store the List.
 * @author jcasben
 * @author linkcla
 */
public class ReferenceOrderedList<E extends Comparable<E>> implements List<E> {
    private Node<E> head;

    /**
     * Constructor of the class {@link ReferenceOrderedList}.
     */
    public ReferenceOrderedList() {
        head = null;
    }

    /**
     * {@inheritDoc}
     * <li>Complexity order: O(n)</li>
     */
    @Override
    public void add(E e) {
        Node<E> newElement = new Node<>(e);
        if (head == null) {
            head = newElement;
            return;
        }

        if (head.getElement().compareTo(newElement.getElement()) > 0) {
            Node<E> aux = head;
            head = newElement;
            head.setNext(aux);
            return;
        }

        boolean added = false;
        Node<E> actual = head;
        Node<E> nxt = head.getNext();
        while(!added) {
            if (actual.getElement().compareTo(newElement.getElement()) < 0) {
                if (nxt == null) {
                    head.setNext(newElement);
                    added = true;
                    continue;
                }
                if (newElement.getElement().compareTo(nxt.getElement()) < 0) {
                    actual.setNext(newElement);
                    newElement.setNext(nxt);
                    added = true;
                }
            }
            actual = nxt;
            nxt = nxt.getNext();
        }
    }

    /**
     * {@inheritDoc}
     * <li>Complexity order: O(1)</li>
     */
    @Override
    public void clear() {
        head = null;
    }

    /**
     * {@inheritDoc}
     * <li>Complexity order: O(n)</li>
     */
    @Override
    public boolean contains(E e) {
        if (this.isEmpty()) return false;
        Node<E> aux = head;
        while (aux != null){
            if(aux.getElement().equals(e)) return true;
            aux = aux.getNext();
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * <li>Complexity order: O(1)</li>
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * {@inheritDoc}
     * <li>Complexity order: O(n)</li>
     */
    @Override
    public void remove(E e) throws EmptyListException {
        if (this.isEmpty()) throw new EmptyListException();
        if (!this.contains(e)) return;

        Node<E> previous = head;
        Node<E> actual = head.getNext();

        if(previous.getElement().equals(e)) {
            head = actual;
            return;
        }

        while (actual.getNext() == null) {
            if(actual.getElement().equals(e)){
                previous.setNext(actual.getNext());
            }
            previous = actual;
            actual = actual.getNext();
        }
    }
}
