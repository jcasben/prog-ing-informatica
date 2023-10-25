package binarySearch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

/**
 * Clase encargada de los tests de los metodos de la clase {@link Search}.
 * @author jcasben
 * @author linkcla
 */
class SearchTest {
    private Integer [] array;
    private Comparator<Integer> integerComparator;

    /**
     * Antes de cada test, inicializa un array de {@link Integer} y un {@link Comparator} para hacer las pruebas.
     */
    @BeforeEach
    void setUp() {
        array = new Integer[]{1, 2, 3, 4, 5};
        integerComparator = Integer::compare;
    }

    /**
     * Comprueba si el metodo binarySearchComparable() encuentra un elemento de tipo {@link Integer} dentro del array y
     * devuelve en indice correcto.
     */
    @Test
    void testBinarySearchComparableExisting() {
        int result = Search.binarySearchComparable(array, 3);
        assertEquals(2, result);
    }

    /**
     * Comprueba si el metodo binarySearchComparable() no encuentra un elemento de tipo {@link Integer} dentro del array
     * y devuelve -1.
     */
    @Test
    void testBinarySearchComparableNotExisting() {
        int result = Search.binarySearchComparable(array, 7);
        assertEquals(-1, result);
    }

    /**
     * Comprueba si el metodo binarySearchComparator() encuentra un elemento de tipo {@link Integer} dentro del array y
     * devuelve el indice correcto.
     */
    @Test
    void testBinarySearchComparatorExisting() {
        int result = Search.binarySearchComparator(array, 3, integerComparator);
        assertEquals(2, result);
    }

    /**
     * Comprueba si el metodo binarySearchComparator() no encuentra un elemento de tipo {@link Integer} dentro del array
     * y devuelve -1.
     */
    @Test
    void testBinarySearchComparatorNotExisting() {
        int result = Search.binarySearchComparator(array, 8, integerComparator);
        assertEquals(-1, result);
    }

    /**
     * Comprueba si el metodo binarySearchComparator() encuentra un elemento de tipo {@link String} y devuelve el indice
     * correcto.
     */
    @Test
    void testBinarySearchComparatorStrings() {
        String [] stringArray = {"algoritmia","ec-ii","estadistica","redes","so-i"};
        Comparator<String> stringComparator = String::compareTo;
        int result = Search.binarySearchComparator(stringArray, "algoritmia", stringComparator);
        assertEquals(0, result);
    }
}