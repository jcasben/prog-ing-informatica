/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package backtracking;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Exemple molt senzill de test.
 *
 * @author Marc Link i Jesús Castillo
 */
public class MotxillaImplTest {

    private ElementMotxilla<String> a[];
    private Motxilla m;

    @Before
    public void setUp() {
        a = new ElementMotxilla[5];
        a[0] = new ElementMotxilla("A", 1.0, 2.0, 3.0);
        a[1] = new ElementMotxilla("B", 3.0, 1.0, 7.0);
        a[2] = new ElementMotxilla("C", 2.0, 1.0, 5.0);
        a[3] = new ElementMotxilla("D", 2.0, 1.0, 6.0);
        a[4] = new ElementMotxilla("E", 1.0, 2.0, 5.0);
        m = new MotxillaImpl();
    }

    private void test(ElementMotxilla[] result) {
        assertEquals(3.0, ElementMotxilla.w1(result), 0.0);
        assertEquals(3.0, ElementMotxilla.w2(result), 0.0);
        assertEquals(11.0, ElementMotxilla.profit(result), 0.0);
        assertEquals(2, result.length);
        assertEquals("D", result[0].getElement());
        assertEquals("E", result[1].getElement());
    }

    /**
     * Test of recursiu method, of class MotxillaImpl.
     */
    @Test
    public void testRecursiu() {
        System.out.println("recursiu");
        test(m.recursiu(a, 3.0, 5.0));
    }

    /**
     * Test of iteratiu method, of class MotxillaImpl.
     */
    @Test
    public void testIteratiu() {
        System.out.println("iteratiu");
        test(m.iteratiu(a, 3.0, 5.0));
    }

    private void test2(ElementMotxilla [] result) {
        assertEquals(5.0, ElementMotxilla.w1(result), 0.0);
        assertEquals(2.0, ElementMotxilla.w2(result), 0.0);
        assertEquals(13.0, ElementMotxilla.profit(result), 0.0);
        assertEquals(2, result.length);
        assertEquals("B", result[0].getElement());
        assertEquals("D", result[1].getElement());
    }

    /**
     * Test of recursiu method, of class MotxillaImpl.
     */
    @Test
    public void testRecursiu2() {
        System.out.println("recursiu2");
        test2(m.recursiu(a, 5.0, 3.0));
    }

    /**
     * Test of iteratiu method, of class MotxillaImpl.
     */
    @Test
    public void testIteratiu2() {
        System.out.println("iteratiu2");
        test2(m.iteratiu(a, 5.0, 3.0));
    }

    private void test3(ElementMotxilla [] result) {
        assertEquals(5.0, ElementMotxilla.w1(result), 0.0);
        assertEquals(4.0, ElementMotxilla.w2(result), 0.0);
        assertEquals(16.0, ElementMotxilla.profit(result), 0.0);
        assertEquals(3, result.length);
        assertEquals("C", result[0].getElement());
        assertEquals("D", result[1].getElement());
        assertEquals("E", result[2].getElement());
    }

    /**
     * Test of recursiu method, of class MotxillaImpl.
     */
    @Test
    public void testRecursiu3() {
        System.out.println("recursiu3");
        test3(m.recursiu(a, 5.0, 4.0));
    }

    /**
     * Test of iteratiu method, of class MotxillaImpl.
     */
    @Test
    public void testIteratiu3() {
        System.out.println("iteratiu3");
        test3(m.iteratiu(a, 5.0, 4.0));
    }
}
