package hanoi;

import java.util.Stack;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jesús Castillo Benito y Marc Link Cladera
 */
public class HanoiImplTest {

    private Stack<Integer> a;
    private Stack<Integer> b;
    private Stack<Integer> c;
    private Stack<Integer> copyAInit;
    private int h;

    @Before
    public void setUp() {
        a = new Stack<>();
        b = new Stack<>();
        c = new Stack<>();
        h = 5;
        for (int i = h; i > 0; i--) {
            a.push(i);
        }
        copyAInit = (Stack<Integer>) a.clone();
    }

    /**
     * Test of recursiuHanoi method, of class HanoiImpl.
     * Pensau que passi aquest test, no vol dir que el Hanoi funcioni bé,
     * ja que bastaria que el mètode fes swap(a,c). 
     * Afegiu nous tests
     */
    @Test
    public void testRecursiuHanoi() {
        System.out.println("recursiuHanoi");
        assertEquals(true, a.equals(copyAInit));
        assertEquals(true, b.isEmpty());
        assertEquals(true, c.isEmpty());
        Hanoi<Integer> pHanoi = new HanoiImpl<>();
        pHanoi.recursiuHanoi(h, a, b, c);
        assertEquals(true, a.isEmpty());
        assertEquals(true, b.isEmpty());
        assertEquals(true, c.equals(copyAInit));
    }

    /**
     * Prueba el método recursiuHanoi con un solo disco.
     */
    @Test
    public void testRecursiveHanoiWithOneDisk() {
        System.out.println("recursiuHanoi con un disco");
        HanoiImpl<Integer> pHanoi = new HanoiImpl<>();
        a = new Stack<>();
        b = new Stack<>();
        c = new Stack<>();

        // Agrega un disco a la pila de origen
        a.push(1);

        pHanoi.recursiuHanoi(1, a, b, c);

        // Después de mover un disco, la pila de origen debe estar vacía y la de destino debe tener el disco
        assertTrue(a.isEmpty());
        assertFalse(c.isEmpty());
        assertEquals(1, (int) c.pop());
    }

    /**
     * Prueba el metodo recursiuHanoi en un estado itermedio.
     */
    @Test
    public void testRecursiveHanoiIntermediateState() {
        System.out.println("recursiuHanoi en un estado intermedio");
        a = new Stack<>();
        a.push(5);
        a.push(4);
        c.push(6);
        c.push(5);
        Hanoi<Integer> pHanoi = new HanoiImpl<>();
        pHanoi.recursiuHanoi(2, a, b, c);
        assertTrue(a.isEmpty());
        assertFalse(c.isEmpty());
        assertEquals(4, (int) c.pop());
        assertEquals(5, (int) c.pop());
        assertEquals(5, (int) c.pop());
        assertEquals(6, (int) c.pop());
    }

    /**
     * Test of iteratiuHanoi method, of class HanoiImpl.
     * Pensau que passi aquest test, no vol dir que el Hanoi funcioni bé,
     * ja que bastaria que el mètode fes swap(a,c). 
     * Afegiu nous tests
     */
    /*
    @Test
    public void testIteratiuHanoi() {
        System.out.println("iteratiuHanoi");
        assertEquals(true, a.equals(copyAInit));
        assertEquals(true, b.isEmpty());
        assertEquals(true, c.isEmpty());
        Hanoi pHanoi = new HanoiImpl();
        pHanoi.iteratiuHanoi(h, a, b, c);
        assertEquals(true, a.isEmpty());
        assertEquals(true, b.isEmpty());
        assertEquals(true, c.equals(copyAInit));
    }
     */


}
