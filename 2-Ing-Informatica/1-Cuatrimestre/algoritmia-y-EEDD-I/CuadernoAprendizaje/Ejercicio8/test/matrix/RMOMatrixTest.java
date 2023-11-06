package matrix;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the methods in the class {@link RMOMatrix}.
 *
 * @author jcasben
 * @author linkcla
 */
public class RMOMatrixTest {
    private RMOMatrix matrix1;
    private RMOMatrix matrix2;

    /**
     * Constructor of the class {@link RMOMatrixTest}.
     */
    public RMOMatrixTest() {
    }

    /**
     * Code that will be executed before each test.
     */
    @Before
    public void setUp() {
        matrix1 = new RMOMatrix(3,3);
        matrix2 = new RMOMatrix(3, 3);
    }

    /**
     * Test if multiplying 2 {@link RMOMatrix} gives the correct result.
     */
    @Test
    public void mult2RMOMatrix() {
        matrix1.setMatrix(new Number[]{1,-1,1,2,2,3,-2,-3,-1});
        matrix2.setMatrix(new Number[]{1,0,4,0,2,5,1,3,0});
        try {
            matrix1 = matrix1.multMatrix(matrix2);
        } catch (RMOMatrix.IncompatibleDimensionException e) {
            System.err.println(e.getMessage());
        }
        RMOMatrix res = new RMOMatrix(3, 3);
        res.setMatrix(new Number[]{2,1,-1,5,13,18,-3,-9,-23});
        assertTrue(matrix1.areEquals(res));
    }

    /**
     * Test if multiplying 2 {@link RMOMatrix} with incompatible dimensions throw
     * {@link matrix.RMOMatrix.IncompatibleDimensionException}.
     */
    @Test
    public void mult2RMOMatrixIncompatibleDimension() {
        matrix1.setMatrix(new Number[]{1,-1,1,2,2,3,-2,-3,-1});
        matrix2 = new RMOMatrix(2, 2);
        matrix2.setMatrix(new Number[]{1,2,3,4});
        assertThrows(RMOMatrix.IncompatibleDimensionException.class, () -> matrix1.multMatrix(matrix2));
    }
}
