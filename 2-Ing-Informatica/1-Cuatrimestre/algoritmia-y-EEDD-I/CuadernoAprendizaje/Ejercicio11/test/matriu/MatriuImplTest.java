package matriu;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Test class for MatriuImpl.
 * @author Marc Link
 * @author Jesus Castillo
 */
public class MatriuImplTest {
    private MatriuImpl<Integer> m1;
    private MatriuImpl<Integer> m2;
    private MatriuImpl<Integer> m3;

    @Before
    public void setUp() throws Exception {
        Integer matriu1[] = {0,1,2,1,1,2,2,2,3};
        Integer matriu2[] = {0,1,2,3,1,2,2,2,3};
        Integer matriu3[] = {0,1,2,1,1,2};
        m1 = new MatriuImpl<>(matriu1,3,3);
        m2 = new MatriuImpl<>(matriu2,3,3);
        m3 = new MatriuImpl<>(matriu3,2,3);
    }
    
    /**
     * Test of isSymmetricalRecursiu method, of class MatriuImpl.
     */
    @Test
    public void testIsSymmetricalRecursiu() {
        System.out.println("isSymmetricalRecursiu");
        assertEquals(true, m1.isSymmetricalRecursiu());
        assertEquals(false, m2.isSymmetricalRecursiu());
        assertEquals(false, m3.isSymmetricalRecursiu());
    }

    /**
     * Test of isSymetricalRecursiu method, of class MatriuImpl.
     */
    @Test
    public void testisSymetricalRecursiuUnicoElemento() {
        Integer [] matriu = {4};
        MatriuImpl<Integer> m = new MatriuImpl<>(matriu,1,1);
        assertTrue(m.isSymmetricalRecursiu());
    }

    /**
     * Test of isSymmetricalIteratiu method, of class MatriuImpl.
     */
    @Test
    public void testIsSymmetricalIteratiu() {
        System.out.println("isSymmetricalIteratiu");
        assertEquals(true, m1.isSymmetricalIteratiu());
        assertEquals(false, m2.isSymmetricalIteratiu());
        assertEquals(false, m3.isSymmetricalIteratiu());
    }

    /**
     * Test of isSymmetricalIteratiu method, of class MatriuImpl.
     */
    @Test
    public void testisSymetricalIteratiuUnicoElemento() {
        Integer [] matriu = {4};
        MatriuImpl<Integer> m = new MatriuImpl<>(matriu,1,1);
        assertTrue(m.isSymmetricalIteratiu());
    }
}
