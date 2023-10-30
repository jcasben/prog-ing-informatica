package list;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for {@link CursorOrderedList} and
 * its methods.
 *
 * @author jcasben
 * @author linkcla
 */
public class CursorOrderedListTest {
    private CursorOrderedList<Integer> list;

    /**
     * Constructor of {@link CursorOrderedListTest}.
     */
    public CursorOrderedListTest() {
    }

    /**
     * Code that will be executed before each test.
     */
    @Before
    public void setUp() {
        list = new CursorOrderedList<>(5,Integer.class);
    }

    /**
     * Test of add method, of class {@link CursorOrderedList}.
     *
     * @throws list.List.FullListException exception for when the list is full.
     */
    @Test
    public void testAdd() throws List.FullListException {
        System.out.println("Add test");
        list.add(4);
        assertTrue(list.contains(4));
    }

    /**
     * Test of add method, of class {@link CursorOrderedList}.
     *
     * @throws List.FullListException exception for when the
     * list is full.
     */
    @Test
    public void testAddFullList() throws List.FullListException {
        System.out.println("Add test");
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        assertThrows(List.FullListException.class,()->{list.add(9);});
    }

    /**
     * Test of clear method, of class {@link CursorOrderedList}.
     */
    @Test
    public void testClear() {
        System.out.println("Clear test");
        list.clear();
        assertTrue(list.isEmpty());
    }

    /**
     * Test of contains method, of class {@link CursorOrderedList}.
     *
     * @throws list.List.FullListException exception for when the list is full.
     */
    @Test
    public void testContains() throws List.FullListException {
        System.out.println("Contains test");
        list.add(1);
        assertTrue(list.contains(1));
        assertFalse(list.contains(2));
    }

    /**
     * Test of isEmpty method, of class {@link CursorOrderedList}.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("Is Empty test");
        assertTrue(list.isEmpty());
    }

    /**
     * Test of remove method, of class {@link CursorOrderedList}.
     *
     * @throws list.List.EmptyListException exception for when the lis is empty.
     * @throws list.List.FullListException exception for when the list is full.
     */
    @Test
    public void testRemove() throws List.EmptyListException, List.FullListException {
        System.out.println("Remove test");
        list.add(1);
        list.remove(1);
        assertTrue(list.isEmpty());
    }

    /**
     * Test of remove method, of class {@link CursorOrderedList}.
     *
     * @throws List.EmptyListException exception for when the list
     * is empty.
     */
    @Test
    public void testRemoveEmptyList() throws List.EmptyListException {
        System.out.println("Remove Empty List exception test");
        assertThrows(List.EmptyListException.class,()->{list.remove(1);});
    }
}
