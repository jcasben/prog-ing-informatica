/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package searchs;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test for the methods in the class {@link BinarySearchTest}.
 *
 * @author jcasben
 * @author linkcla
 */
public class BinarySearchTest {
    private BinarySearch<Integer> binarySearch;
    private final Integer [] set;

    /**
     * Constructor method for {@link BinarySearchTest}. It creates a data set with length
     * 10^6 where each element has as value its index.
     */
    public BinarySearchTest() {
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
        binarySearch = new BinarySearch<>();
    }

    /**
     * Test if the iterative binary search finds the first element. It also counts the number of times that
     * the program makes access to an element of the set.
     */
    @Test
    public void binarySearchIterativeFirstElement() {
        int index = binarySearch.binarySearchIterative(set, 0);
        System.out.println("Searching for element 0... Amount of access to the data set elements(binary iterative): "
                + binarySearch.getNumberOfAccess());
        assertEquals(0, index);
    }

    /**
     * Test if the recursive binary search finds the first element. It also counts the number of times that
     * the program makes access to an element of the set.
     */
    @Test
    public void binarySearchRecursiveFirstElement() {
        int index = binarySearch.binarySearchRecursive(set, 0, 0, set.length - 1);
        System.out.println("Searching for element 0... Amount of access to the data set elements(binary recursive): "
                + binarySearch.getNumberOfAccess());
        assertEquals(0, index);
    }

    /**
     * Test if the iterative binary search finds the last element. It also counts the number of times that
     * the program makes access to an element of the set.
     */
    @Test
    public void setBinarySearchIterativeLastElement() {
        int index = binarySearch.binarySearchIterative(set, set.length - 1);
        System.out.println("Searching for element 999999... Amount of access to the data set elements(binary iterative): "
                + binarySearch.getNumberOfAccess());
        assertEquals(999999, index);
    }

    /**
     * Test if the recursive binary search finds the last element. It also counts the number of times that
     * the program makes access to an element of the set.
     */
    @Test
    public void setBinarySearchRecursiveLastElement() {
        int index = binarySearch.binarySearchRecursive(set, set.length - 1, 0, set.length - 1);
        System.out.println("Searching for element 999999... Amount of access to the data set elements(binary iterative): "
                + binarySearch.getNumberOfAccess());
        assertEquals(999999, index);
    }

    /**
     * Test if the iterative binary search returns -1 when the target doesn't exist in the set. It also counts the
     * number of times that the program makes access to an element of the set.
     */
    @Test
    public void setBinarySearchIterativeNotExisting() {
        int index = binarySearch.binarySearchIterative(set, 10000000);
        System.out.println("Searching for element 9999999... Amount of access to the data set elements(binary iterative): "
                + binarySearch.getNumberOfAccess());
        assertEquals(-1, index);
    }

    /**
     * Test if the recursive binary search returns -1 when the target doesn't exist in the set. It also counts the
     * number of times that the program makes access to an element of the set.
     */
    @Test
    public void setBinarySearchRecursiveNotExisting() {
        int index = binarySearch.binarySearchRecursive(set, 10000000, 0, set.length - 1);
        System.out.println("Searching for element 9999999... Amount of access to the data set elements(binary recursive): "
                + binarySearch.getNumberOfAccess());
        assertEquals(-1, index);
    }
}
