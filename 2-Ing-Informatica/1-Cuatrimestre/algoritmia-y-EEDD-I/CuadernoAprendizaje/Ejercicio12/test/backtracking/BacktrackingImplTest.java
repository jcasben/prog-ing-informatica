package backtracking;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antoni
 */
public class BacktrackingImplTest {

    /**
     * Test of sumM method, of class BacktrackingImpl.
     */
    @Test
    public void testSumM() {
        System.out.println("sumM");
        int M = 5;
        int a[] = {1, 3, 1, 5, 2};
        ArrayList<ArrayList<Integer>> expResult = new ArrayList<>();
        
        ArrayList<Integer> s = new ArrayList<>();
        s.add(5);
        expResult.add(s);
        
        s = new ArrayList<>();
        s.add(3);s.add(2);
        expResult.add(s);
        
        s = new ArrayList<>();
        s.add(1);s.add(3);s.add(1);
        expResult.add(s);

        BacktrackingImpl instance = new BacktrackingImpl();
        ArrayList<ArrayList<Integer>> result = instance.sumM(a, M);
        assertEquals(expResult, result);
    }

    /**
     * Test of sumM method, of class BacktrackingImpl.
     */
    @Test
    public void testSumM0() {
        System.out.println("sumM0");
        int M = 0;
        int a[] = {1, 3, 1, 5, 2};
        ArrayList<ArrayList<Integer>> expResult = new ArrayList<>();

        ArrayList<Integer> s = new ArrayList<>();
        expResult.add(s);

        BacktrackingImpl instance = new BacktrackingImpl();
        ArrayList<ArrayList<Integer>> result = instance.sumM(a, M);
        assertEquals(expResult, result);
    }

    /**
     * Test of sumM method, of class BacktrackingImpl.
     */
    @Test
    public void testSumMEmptyArray() {
        System.out.println("sumMEmptyArray");
        int M = 5;
        int a[] = {};
        ArrayList<ArrayList<Integer>> expResult = new ArrayList<>();

        BacktrackingImpl instance = new BacktrackingImpl();
        ArrayList<ArrayList<Integer>> result = instance.sumM(a, M);
        assertEquals(expResult, result);
    }
}
