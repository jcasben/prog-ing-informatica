package matriu;

/**
 *
 * @author Jesús Castillo Benito y Marc Link Cladera
 * @param <E>
 */
public class MatriuImpl<E extends Comparable<E>> implements Matriu<E> {

    private E[] matrix;
    private final int rows;
    private final int columns;

    /**
     * Es crea una matriu representada en un array per files
     *
     * @param cols numero de columnas de la matriz.
     * @param rows numero de filas de la matriz.
     */
    public MatriuImpl(int rows, int cols) {
        this.rows = rows;
        this.columns = cols;
        this.matrix = (E[]) new Object[this.rows * this.columns];
    }

    /**
     * Es crea una matriu representada en un array per files
     *
     * @param matriu valores para la matriz.
     * @param cols numero de columnas.
     * @param rows numero de filas.
     */
    public MatriuImpl(E[] matriu, int rows, int cols) {
        this.matrix = matriu;
        this.rows = rows;
        this.columns = cols;
    }

    @Override
    public void set(E e, int row, int col) {
        if (row > this.rows || col > this.columns || row < 0 || col < 0) throw new IndexOutOfBoundsException();
        matrix[((row) * columns + col)] = e;
    }

    @Override
    public E get(int row, int col) {
        if (row > this.rows || col > this.columns || col < 0 || row < 0) throw new IndexOutOfBoundsException();
        return matrix[((row) * columns + col)];
    }

    /**
     * {@inheritDoc}
     * @pre colums ≥ 0 ∧ rows ≥ 0
     * @post Devuelve verdadero si la matriz es simetrica y falso en caso contrario.
     * <br><br><br>
     * <p>
     *     <b>Complejidad:</b>  O(n^2) ya que cada nodo del arbol binario se estaría comparando con el que está al
     *     mismo nivel pero en la otra rama.
     * </p>
     *  <br>
     *  <p>
     *      <b>Casos posibles:</b>
     *      <li>
     *          - colums != rows: La matriz no será cuadrada, requisito esencial para que sea simetrica
     *      </li>
     *      <li>
     *          - colums == rows: El codigo se ejecutará comprobando si el elemento en la posicion[fila,columna] es
     *          igual al elemento en la posicion[columna, fila].
     *      </li>
     *
     *  </p>
     *  <br>
     *  <p>
     *      <b>Proof</b>
     *      <li>
     *          Todos los estados de la precondicion se tratan ya que el codigo trata desde column = 0 y row = 0 hasta
     *          row == rows y column == columns
     *      </li>
     *      <li>
     *          Cuando llegamos al caso base para ya que estaremos en la última casilla de 'abajo a la derecha' de la
     *          matriz.
     *      </li>
     *      <li>
     *          Las llamadas recursivas cumplen la precondición y la postcondición.
     *      </li>
     *      <li>
     *          El tamaño de los elementos a tratar en la recursividad se van disminuyendo ya que llamamos a la
     *          recusividad con "row+1" y "column+1" acercandonos(+1) cada vez a rows y columns respectivamente.
     *      </li>
     *  </p>
     *
     */
    @Override
    public boolean isSymmetricalRecursiu() {
        if (columns != rows) return false;

        return isSymetrical(0,0);
    }

    private boolean isSymetrical(int row, int column) {
        // si hemos llegado al final
        if(row == rows) return true;

        if(get(row,column) != get(column,row)) return false;

        if(column == columns-1) {
            return isSymetrical(row+1,0);
        } else {
            return isSymetrical(row, column + 1);
        }
    }

    /**
     * {@inheritDoc}
     * @pre colums ≥ 0 ∧ rows ≥ 0.
     * @post Devuelve verdadero si la matriz es simetrica y falso en caso contrario.
     * <br><br><br>
     * <p>
     *     <b>Complejidad:</b>  O(n^2) ya que cada nodo del arbol binario se estaría comparando con el que está al
     *     mismo nivel pero en la otra rama.
     * </p>
     *  <br>
     *  <p>
     *      <b>Casos posibles:</b>
     *      <li>
     *          - colums != rows: La matriz no será cuadrada, requisito esencial para que sea simetrica
     *      </li>
     *      <li>
     *          - colums == rows: El codigo se ejecutará comprobando si el elemento en la posicion[fila,columna] es
     *          igual al elemento en la posicion[columna, fila].
     *      </li>
     */
    @Override
    public boolean isSymmetricalIteratiu() {
        if (columns != rows) return false;
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if(get(i,j) != get(j,i)) return false;
            }
        }

        return true;
    }
}
