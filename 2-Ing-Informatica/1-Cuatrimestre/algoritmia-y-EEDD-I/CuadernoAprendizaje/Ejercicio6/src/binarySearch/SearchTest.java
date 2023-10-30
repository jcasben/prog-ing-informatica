package binarySearch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

/**
 * Test class for the methods in the class {@link Search}.
 * @author jcasben
 * @author linkcla
 */
class SearchTest {
    private Integer [] array;
    private Comparator<Integer> integerComparator;

    /**
     * Before each test, initializes an {@link Integer} array and
     * a {@link Comparator} for doing the tests.
     */
    @BeforeEach
    void setUp() {
        array = new Integer[]{1, 2, 3, 4, 5};
        integerComparator = Integer::compare;
    }

    /**
     * Asserts if the method binarySearchComparable() finds an
     * element of the type {@link Integer} inside the array and
     * returns the right index.
     */
    @Test
    void testBinarySearchComparableExisting() {
        int result = Search.binarySearchComparable(array, 3);
        assertEquals(2, result);
    }

    /**
     * Asserts if the method binarySearchComparable() doesn't find
     * an element of the type {@link Integer} inside the array
     * and returns -1.
     */
    @Test
    void testBinarySearchComparableNotExisting() {
        int result = Search.binarySearchComparable(array, 7);
        assertEquals(-1, result);
    }

    /**
     * Asserts if the method binarySearchComparator() finds
     * an element of the type {@link Integer} inside the array and
     * returns the right index.
     */
    @Test
    void testBinarySearchComparatorExisting() {
        int result = Search.binarySearchComparator(array, 3, integerComparator);
        assertEquals(2, result);
    }

    /**
     * Asserts if the method binarySearchComparator() doesn't
     * find an element of the type {@link Integer} inside the
     * array and returns -1.
     */
    @Test
    void testBinarySearchComparatorNotExisting() {
        int result = Search.binarySearchComparator(array, 8, integerComparator);
        assertEquals(-1, result);
    }

    /**
     * Asserts if the method binarySearchComparator() finds an element
     * of the type {@link String} and returns the right index.
     */
    @Test
    void testBinarySearchComparatorStrings() {
        String [] stringArray = {"algoritmia","ec-ii","estadistica","redes","so-i"};
        Comparator<String> stringComparator = String::compareTo;
        int result = Search.binarySearchComparator(stringArray, "algoritmia", stringComparator);
        assertEquals(0, result);
    }
}