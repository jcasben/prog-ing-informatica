package list;

import java.lang.reflect.Array;

/**
 * Ordered List implemented with cursors.
 *
 * @param <E> type of data that will storage the List.
 * @author jcasben
 * @author linkcla
 */
public class CursorOrderedList<E extends Comparable<E>> implements List<E> {
    private int length;
    private E[] list;

    /**
     * Constructor of the class {@link CursorOrderedList}.
     *
     * @param tam size of the list.
     * @param clase class of the list.
     */
    public CursorOrderedList(int tam, Class<E> clase) {
        list = (E[]) Array.newInstance(clase, tam);
        length = 0;
    }

    /**
     * {@inheritDoc}
     * <li>Complexity order: O(n)</li>
     */
    @Override
    public void add(E e) throws FullListException {
        if (length == list.length) throw new FullListException();
        if(contains(e)) return;

        E aux;
        boolean displace = false;
        for (int i = 0; i < list.length; i++) {
            if (!displace && list[i] != null && (e.compareTo(list[i])) < 0) displace = true;
            if (list[i] == null){
                list[i] = e;
                break;
            }
            if (displace){
                aux = list[i];
                list[i] = e;
                e = aux;
            }
        }
        length++;
    }

    /**
     * {@inheritDoc}
     * <li>Complexity order: O(1)</li>
     */
    @Override
    public void clear() {
        length = 0;
    }

    /**
     * {@inheritDoc}
     * <li>Complexity order: O(n)</li>
     */
    @Override
    public boolean contains(E e) {
        boolean isContained = false;
        if (length == 0) return false;
        for (int i = 0;list[i] != null; i++) {
            if(list[i].equals(e)){
               isContained = true;
                break; 
            } 
        }
        return isContained;
    }

    /**
     * {@inheritDoc}
     * <li>Complexity order: O(1)</li>
     */
    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * {@inheritDoc}
     * <li>Complexity order: O(n)</li>
     */
    @Override
    public void remove(E e) throws EmptyListException {
        if (length == 0) throw new EmptyListException();
        if (!contains(e)) return;

        boolean displace = false;
        for (int i = 0; i < length; i++) {
            if (!displace && e.compareTo(list[i]) == 0){
                displace = true;
                continue;
            }
            if (displace){
                list[i-1] = list[i];
            }
        }
        list[length -1] = null;
        length--;
    }
}
