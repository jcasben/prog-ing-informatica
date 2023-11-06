package searchs;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the methods in the class {@link LinearSearchTest}.
 *
 * @author jcasb
 * @author linkcla
 */
public class LinearSearchTest {
    private LinearSearch<Integer> linearSearch;
    private final Integer [] set;

    /**
     * Constructor method for {@link LinearSearchTest}. It creates a data set with length
     * 10^6 where each element has as value its index.
     */
    public LinearSearchTest() {
        set = new Integer[1000000];
        for (int i = 0; i < set.length; i++) {
            set[i] = i;
        }
    }

    /**
     * Code that will be executed before each test.
     */
    @Before
    public void setUp() {
        linearSearch = new LinearSearch<>();
    }

    /**
     * Test if the iterative linear search finds the first element. It also counts the number of times that the program
     * makes access to an element of the set.
     */
    @Test
    public void linearSearchIterativeFirstElement() {
        int index = linearSearch.linealSearchIterative(set, 0);
        System.out.println("Searching for element 0... Amount of access to the data set elements(linear iterative): "
                + linearSearch.getNumberOfAccess());
        assertEquals(0, index);
    }

    /**
     * Test if the recursive linear search finds the first element. It also counts the number of times that the program
     * makes access to an element of the set.
     */
    @Test
    public void linearSearchRecursiveFirstElement() {
        int index = linearSearch.linealSearchRecursive(set, 0, 0);
        System.out.println("Searching for element 0... Amount of access to the data set elements(linear recursive): "
                + linearSearch.getNumberOfAccess());
        assertEquals(0, index);
    }

    /**
     * Test if the iterative linear search finds the last element. It also counts the number of times that the program
     * makes access to an element of the set.
     */
    @Test
    public void linearSearchIterativeLastElement() {
        int index = linearSearch.linealSearchIterative(set, 99_999);
        System.out.println("Searching for element 99_999... Amount of access to the data set elements(linear iterative): "
                + linearSearch.getNumberOfAccess());
        assertEquals(99_999, index);
    }

    /**
     * Test if the recursive linear search finds the last element. It also counts the number of times that the program
     * makes access to an element of the set.
     */
    @Test
    public void linearSearchRecursiveLastElement() {
        assertThrows(StackOverflowError.class, () -> linearSearch.linealSearchRecursive(set, 99_999, 0));
        System.out.println("Searching for element 99_999... Amount of access to the data set elements(linear recursive): "
                + linearSearch.getNumberOfAccess());
    }

    /**
     * Test if the iterative linear search returns -1 when the target doesn't exist in the set. It also counts the
     * number of times that the program makes access to an element of the set.
     */
    @Test
    public void linearSearchIterativeNotExisting() {
        int index = linearSearch.linealSearchIterative(set, 9_999_999);
        System.out.println("Searching for element 9_999_999... Amount of access to the data set elements(linear iterative): "
                + linearSearch.getNumberOfAccess());
        assertEquals(-1, index);
    }

    /**
     * Test if the recursive linear search returns -1 when the target doesn't exist in the set. It also counts the
     * number of times that the program makes access to an element of the set.
     */
    @Test
    public void linearSearchRecursiveNotExisting() {
        assertThrows(StackOverflowError.class, () -> linearSearch.linealSearchRecursive(set, 99_9999, 0));
        System.out.println("Searching for element 9_999_999... Amount of access to the data set elements(linear recursive): "
                + linearSearch.getNumberOfAccess());
    }
}
