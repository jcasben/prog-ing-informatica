package list;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for {@link ReferenceOrderedList} and
 * its methods.
 *
 * @author jcasben
 * @author linkcla
 */
public class ReferenceOrderedListTest {
    private ReferenceOrderedList<Integer> list;

    /**
     * Constructor for {@link ReferenceOrderedList}.
     */
    public ReferenceOrderedListTest() {
    }

    /**
     * Code that will be executed before each test.
     */
    @Before
    public void setUp() {
        list = new ReferenceOrderedList<>();
    }

    /**
     * Test of add method, of class {@link ReferenceOrderedList}.
     */
    @Test
    public void testAdd() {
        System.out.println("Add test");
        list.add(4);
        assertTrue(list.contains(4));
    }

    /**
     * Test of clear method, of class {@link ReferenceOrderedList}.
     */
    @Test
    public void testClear() {
        System.out.println("Clear test");
        list.clear();
        assertTrue(list.isEmpty());
    }

    /**
     * Test of contains method, of class {@link ReferenceOrderedList}.
     */
    @Test
    public void testContains() {
        System.out.println("Contains test");
        list.add(1);
        assertTrue(list.contains(1));
        assertFalse(list.contains(2));
    }

    /**
     * Test of isEmpty method, of class {@link ReferenceOrderedList}.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("Is Empty test");
        assertTrue(list.isEmpty());
    }

    /**
     * Test of remove method, of class {@link ReferenceOrderedList}.
     *
     * @throws list.List.EmptyListException exception for when the list is empty.
     */
    @Test
    public void testRemove() throws List.EmptyListException {
        System.out.println("Remove test");
        list.add(1);
        list.remove(1);
        assertTrue(list.isEmpty());
    }

    /**
     * Test of remove method, of class {@link ReferenceOrderedList}.
     *
     * @throws List.EmptyListException exception for when the
     * List is empty.
     */
    @Test
    public void testRemoveEmptyList() throws List.EmptyListException {
        System.out.println("Remove Empty List exception test");
        assertThrows(List.EmptyListException.class,()->{list.remove(1);});
    }
    
}
