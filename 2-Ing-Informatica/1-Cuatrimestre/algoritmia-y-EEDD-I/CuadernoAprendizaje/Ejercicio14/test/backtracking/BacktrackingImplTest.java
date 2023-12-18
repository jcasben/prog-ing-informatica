package backtracking;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antoni
 */
public class BacktrackingImplTest {
    /**
     * Test of knapSackRecursive method, of class BacktrackingImpl.
     */
    @Test
    public void testKnapSackRecursive0() {
        System.out.println("knapSackRecursive");
        int W = 3;
        int w[] = { 2, 3, 2, 1 };
        int p[] = { 3, 3, 1, 10 };
        int[] expResult = { 1, 0, 0, 1 };
        BacktrackingImpl instance = new BacktrackingImpl();
        int[] result = instance.knapSackRecursive(W, w, p);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of knapSackRecursive method, of class BacktrackingImpl.
     */
    @Test
    public void testKnapSackRecursive1() {
        BacktrackingImpl backtracking = new BacktrackingImpl();
        int[] weights = { 5, 10, 15 };
        int[] values = { 10, 30, 20 };
        int[] expected = { 1, 1, 0 };
        assertArrayEquals(expected, backtracking.knapSackRecursive(15, weights, values));
    }

    /**
     * Test of knapSackIterative method, of class BacktrackingImpl.
     */
    @Test
    public void testKnapSackIterative0() {
        System.out.println("knapSackIterative");
        int W = 3;
        int w[] = { 2, 3, 2, 1 };
        int p[] = { 3, 3, 1, 10 };
        int[] expResult = { 1, 0, 0, 1 };
        BacktrackingImpl instance = new BacktrackingImpl();
        int[] result = instance.knapSackIterative(W, w, p);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of knapSackIterative method, of class BacktrackingImpl.
     */
    @Test
    public void testKnapSackIterative1() {
        BacktrackingImpl backtracking = new BacktrackingImpl();
        int[] weights = { 5, 10, 15 };
        int[] values = { 10, 30, 20 };
        int[] expected = { 1, 1, 0 };
        assertArrayEquals(expected, backtracking.knapSackIterative(15, weights, values));
    }
}