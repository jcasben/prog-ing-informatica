package peak;

import java.awt.Point;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jesús Castillo Benito y Marc Link Cladera
 */
public class PeakImplTest {
    
    /**
     * Test of peak method, of class PeakImpl.
     */
    @Test
    public void testPeak() throws Exception {
        System.out.println("peak");
        int[] a = {1,3,5,7,8,5,2,1};
        Point expResult = new Point(8,4);
        Peak p = new PeakImpl();
        Point result = p.peak(a);
        assertEquals(expResult, result);
    }

    /**
     * Tests the 'peak' method with an empty array, expecting an exception to be thrown.
     * This test is designed to verify the behavior of the 'peak' method when provided with
     * an empty array, ensuring that it handles this scenario appropriately.
     */
    @Test
    public void testPeakEmptyArray() {
        System.out.println("Testing peak with an empty array");
        int[] a = {};
        Peak p = new PeakImpl();
        assertThrows(Exception.class, () -> p.peak(a));
    }

    /**
     * Test of peak method with the peak at the beginning of the array.
     */
    @Test
    public void testPeakAtBeginning() throws Exception {
        System.out.println("Testing peak with an element at the beginning of the array");
        int[] a = {5, 6, 4, 2, 1, 3, 5, 7, 9};
        Point expResult = new Point(6, 1);
        Peak p = new PeakImpl();
        Point result = p.peak(a);
        assertEquals(expResult, result);
    }

    /**
     * Test of peak method with the peak at the end of the array.
     */
    @Test
    public void testPeakAtEnd() throws Exception {
        System.out.println("Testing peak with an element at the end of the array");
        int[] a = {2, 4, 6, 8, 10, 9};
        Point expResult = new Point(10, 4);
        Peak p = new PeakImpl();
        Point result = p.peak(a);
        assertEquals(expResult, result);
    }

    /**
     * Tests the 'peak' method with an array that does not comply with the specified precondition,
     * expecting an exception to be thrown. This test is designed to verify the behavior of the 'peak'
     * method when given an array that does not adhere to the expected pattern of increasing values
     * until a certain index and then decreasing values.
     */
    @Test
    public void testPeakException() {
        System.out.println("Testing peak with an array that does not comply with the precondition");
        int[] a = {2, 4, 6, 8, 10, 12};
        Peak p = new PeakImpl();
        assertThrows(Exception.class, () -> p.peak(a));
    }
}
