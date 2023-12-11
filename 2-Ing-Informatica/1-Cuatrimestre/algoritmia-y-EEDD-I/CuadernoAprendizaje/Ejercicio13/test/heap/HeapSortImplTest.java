package heap;

import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test de la clase HeapSortImpl.
 * @author Marc Link y Jesus Castillo
 */
public class HeapSortImplTest {
    
    public HeapSortImplTest() {
    }

    /**
     * Test of heapSort method, of class HeapSortImpl.
     */
    @Test
    public void testHeapSort() {
        System.out.println("heapSort");
        Integer[] a = new Integer[100];
        Random r = new Random();

        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(100);
        }

        HeapSortImpl<Integer> hs = new HeapSortImpl<>();
        hs.heapSort(a);

        boolean result = true;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                result = false;
                break;
            }
        }
        assertTrue(result);
    }

    /**
     * Test of heapSort method, of class HeapSortImpl.
     */
    @Test
    public void testHeapSortOneElement() {
        System.out.println("heapSortUniqueElement");
        Integer[] a = new Integer[1];
        Random r = new Random();

        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(100);
        }

        HeapSortImpl<Integer> hs = new HeapSortImpl<>();
        hs.heapSort(a);

        boolean result = true;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                result = false;
                break;
            }
        }
        assertTrue(result);
    }

    /**
     * Test of heapSort method, of class HeapSortImpl.
     */
    @Test
    public void testHeapSortEmpty() {
        System.out.println("heapSortEmpty");
        Integer[] a = new Integer[0];
        Random r = new Random();

        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(100);
        }

        HeapSortImpl<Integer> hs = new HeapSortImpl<>();
        hs.heapSort(a);

        boolean result = true;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                result = false;
                break;
            }
        }
        assertTrue(result);
    }

    /**
     * Test of heapSort method, of class HeapSortImpl.
     */
    @Test
    public void testHeapSortOrdered() {
        System.out.println("heapSortOrdered");
        Integer[] a = new Integer[100];
        Random r = new Random();

        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }

        HeapSortImpl<Integer> hs = new HeapSortImpl<>();
        hs.heapSort(a);

        boolean result = true;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                result = false;
                break;
            }
        }
        assertTrue(result);
    }
}
