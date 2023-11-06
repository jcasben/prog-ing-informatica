package matrix;

import java.util.Arrays;

/**
 * Implementation of a Row-Major Order Matrix for
 * storing a matrix in a linear array.
 *
 * @author jcasben
 * @author linkcla
 */
public class RMOMatrix {
    private Number[] matrix;
    private final int rows;
    private final int columns;
    private int numOfMult;

    /**
     * Constructor method of the class {@link RMOMatrix}.
     *
     * @param rows    number of rows of the matrix.
     * @param columns number of columns of the matrix.
     */
    public RMOMatrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = new Number[this.rows * this.columns];
        this.numOfMult = 0;
    }

    /**
     * Sets the specified Number at the specified position in the matrix.
     *
     * @param row    The row index where the Number will be set.
     * @param column The column index where the Number will be set.
     * @param number The Number to be set at the specified position in the matrix.
     * @throws IndexOutOfBoundsException If the provided row or column index is out of bounds for this matrix.
     */
    public void setNumber(int row, int column, Number number) throws IndexOutOfBoundsException {
        if (row > this.rows || column > this.columns || row < 0 || column < 0) throw new IndexOutOfBoundsException();
        matrix[((row) * columns + column)] = number;
    }

    /**
     * Retrieves the Number at the specified position in the matrix.
     *
     * @param row    The row index of the desired Number.
     * @param column The column index of the desired Number.
     * @return The Number at the specified position in the matrix.
     * @throws IndexOutOfBoundsException If the provided row or column index is out of bounds for this matrix.
     */
    public Number getNumberInPos(int row, int column) throws IndexOutOfBoundsException {
        if (row > this.rows || column > this.columns || column < 0 || row < 0) throw new IndexOutOfBoundsException();
        return matrix[((row) * columns + column)];
    }

    /**
     * Multiplies this RMOMatrix by another RMOMatrix and returns the result as a new RMOMatrix.
     * <br>
     * Complexity order: O(n^3)
     *
     * @param matrix The RMOMatrix to be multiplied with this matrix.
     * @return A new RMOMatrix representing the result of the matrix multiplication.
     * @throws IncompatibleDimensionException If the number of columns in this matrix is not equal to the number of
     *                                        rows in the given matrix, which makes the multiplication operation incompatible.
     */
    public RMOMatrix multMatrix(RMOMatrix matrix) throws IncompatibleDimensionException {
        if (this.columns != matrix.rows) {
            throw new IncompatibleDimensionException();
        }
        Number aux; //Para que sea más fácil de ver
        RMOMatrix resMatrix = new RMOMatrix(this.rows, matrix.columns);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < matrix.columns; j++) {
                for (int k = 0; k < this.columns; k++) {
                    if (resMatrix.getNumberInPos(i, j) == null) resMatrix.setNumber(i, j, 0);
                    aux = add(resMatrix.getNumberInPos(i, j), mult(this.getNumberInPos(i, k), matrix.getNumberInPos(k, j)));
                    resMatrix.setNumber(i, j, aux);
                }
            }
        }
        return resMatrix;
    }

    /**
     * Adds two Numbers.
     *
     * @param x Number.
     * @param y Number.
     * @return result of adding x by y.
     */
    private Number add(Number x, Number y) {
        if (x instanceof Integer || y instanceof Integer) return x.intValue() + y.intValue();
        else if (x instanceof Double || y instanceof Double) return x.doubleValue() + y.doubleValue();
        else if (x instanceof Long || y instanceof Long) return x.longValue() + y.longValue();
        else if (x instanceof Short || y instanceof Short) return x.shortValue() + y.shortValue();
        else return x.floatValue() + y.floatValue();
    }

    /**
     * Multiplies two Numbers.
     *
     * @param x Number.
     * @param y Number.
     * @return the result of multiplying x by y.
     */
    private Number mult(Number x, Number y) {
        numOfMult++;
        if (x instanceof Integer || y instanceof Integer) return x.intValue() * y.intValue();
        else if (x instanceof Double || y instanceof Double) return x.doubleValue() * y.doubleValue();
        else if (x instanceof Long || y instanceof Long) return x.longValue() * y.longValue();
        else if (x instanceof Short || y instanceof Short) return x.shortValue() * y.shortValue();
        else return x.floatValue() * y.floatValue();
    }

    /**
     * Gets the number of times the multiplication has been done between two cells.
     *
     * @return the number of times the multiplication has been done between two cells.
     */
    public int getNumOfMult() {
        return numOfMult;
    }

    /**
     * Gets the number of rows of a {@link RMOMatrix}.
     *
     * @return number of rows.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets the number of columns of a {@link RMOMatrix}.
     *
     * @return number of columns.
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Gets the linear array that represents the {@link RMOMatrix}.
     *
     * @return the array that represents the matrix.
     */
    public Number[] getMatrix() {
        return matrix;
    }

    /**
     * Sets the linear array that represents the {@link RMOMatrix}.
     * @param matrix the linear array that will represent the matrix.
     */
    public void setMatrix(Number[] matrix) {
        this.matrix = matrix;
    }

    /**
     * Determines if two {@link RMOMatrix} are equals.
     *
     * @param rmoMatrix {@link RMOMatrix} to be compared with.
     * @return true if this and rmoMatrix contain the same elements;
     * false if not.
     */
    public boolean areEquals(RMOMatrix rmoMatrix) {
        return Arrays.equals(this.getMatrix(), rmoMatrix.getMatrix());
    }

    /**
     * A custom exception to indicate an error of incompatible dimensions when multiplying matrix.
     */
    public static class IncompatibleDimensionException extends Exception {
        /**
         * Constructor method for {@link IncompatibleDimensionException}.
         */
        public IncompatibleDimensionException() {
            super("ERROR: Incompatible dimensions.");
        }
    }
}
