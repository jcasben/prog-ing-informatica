package backtracking;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test de la classe BacktrackingImpl, que implementa l'algorisme de Backtracking.
 * @author Marc Link y Jesús Castillo
 */
public class BacktrackingImplTest {
    
    public BacktrackingImplTest() {
    }

    /**
     * Test of mapColor method, of class BacktrackingImpl.
     */
    @Test
    public void testMapColorRecursive() {
        System.out.println("mapColorRecursive");
        int[][] map = {{1,3},{0,2,3,4},{1,4},{0,1},{1,2}};
        int nColors = 3;
        BacktrackingImpl instance = new BacktrackingImpl();
        boolean expResult = true;
        boolean result = instance.mapColorRecursive(map, nColors);
        assertEquals(expResult, result);
    }

    /**
     * Test of mapColor method, of class BacktrackingImpl.
     */
    @Test
    public void testMapColorRecursive2Colors() {
        System.out.println("mapColorRecursive2Colors");
        int[][] map = {{1,3},{0,2,3,4},{1,4},{0,1},{1,2}};
        int nColors = 2;
        BacktrackingImpl instance = new BacktrackingImpl();
        boolean expResult = false;
        boolean result = instance.mapColorRecursive(map, nColors);
        assertEquals(expResult, result);
    }

    /**
     * Test of mapColor method, of class BacktrackingImpl.
     */
    @Test
    public void testMapColorRecursiveNotPossible() {
        System.out.println("mapColorRecursiveNotPossible");
        int[][] map = {{1,3},{0,2,3,4},{1,4},{0,1},{1,2}};
        int nColors = 1;
        BacktrackingImpl instance = new BacktrackingImpl();
        boolean expResult = false;
        boolean result = instance.mapColorRecursive(map, nColors);
        assertEquals(expResult, result);
    }

    /**
     * Test of mapColor method, of class BacktrackingImpl.
     */
    @Test
    public void testMapColorIterative() {
        System.out.println("mapColorIterative");
        int[][] map = {{1,3},{0,2,3,4},{1,4},{0,1},{1,2}};
        int nColors = 3;
        BacktrackingImpl instance = new BacktrackingImpl();
        boolean expResult = true;
        boolean result = instance.mapColorIterative(map, nColors);
        assertEquals(expResult, result);
    }

    /**
     * Test of mapColor method, of class BacktrackingImpl.
     */
    @Test
    public void testMapColorIterative2Colors() {
        System.out.println("mapColorIterative2Colors");
        int[][] map = {{1,3},{0,2,3,4},{1,4},{0,1},{1,2}};
        int nColors = 2;
        BacktrackingImpl instance = new BacktrackingImpl();
        boolean expResult = false;
        boolean result = instance.mapColorIterative(map, nColors);
        assertEquals(expResult, result);
    }

    /**
     * Test of mapColor method, of class BacktrackingImpl.
     */
    @Test
    public void testMapColorIterativeNotPossible() {
        System.out.println("mapColorIterativeNotPossible");
        int[][] map = {{1,3},{0,2,3,4},{1,4},{0,1},{1,2}};
        int nColors = 1;
        BacktrackingImpl instance = new BacktrackingImpl();
        boolean expResult = false;
        boolean result = instance.mapColorIterative(map, nColors);
        assertEquals(expResult, result);
    }
}