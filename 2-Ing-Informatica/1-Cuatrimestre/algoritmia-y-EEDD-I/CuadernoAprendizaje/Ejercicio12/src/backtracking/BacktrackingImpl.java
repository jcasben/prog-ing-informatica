package backtracking;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Classe implementació Backtracking
 *
 * @author Marc Link y Jesús Castillo
 */
public class BacktrackingImpl implements Backtracking {

    /**
     * Dado un conjunto numerico, encontrar todos los subconjuntos que suman M.
     * @param a cionjunto numerico.
     * @param M resultado suma de los subconjuntos.
     * @pre a!=null, M>=0
     * @post devuelve un conjunto de subconjuntos que suman M y todos los elementos pertenecen al conjunto incial.
     * @return conjunto de subconjuntos que suman M.
     * @complexity O(2^n). Relacionado con un arbol binario, la altura sería n y cada nodo tiene dos hijos
     * (que serían las dos llamadas recursivas) y el número de nodos es 2^n + 1.
     *
     * <br>
     * <p>
     *     <b>Casos posibles:</b>
     *     <li>
     *         M == 0: Se añade la solución parcial a la solución final.
     *     </li>
     *     <li>
     *         i < a.length: Se comprueba si el elemento actual es menor que M, si es así se añade a la solución parcial.
     *     </li>
     * </p>
     * <br><br>
     *
     * <p>
     *      <b>Proof</b>
     *      <li>
     *          Todos los estados de la precondicion se tratan ya que el código trata el conjunto de elementos completo,
     *          hasta que i == a.length.
     *      </li>
     *     <li>
     *         El código finaliza al llegar al caso base.
     *     </li>
     *     <li>
     *         Las llamadas recursivas cumplen la precondición y la postcondición.
     *     </li>
     *     <li>
     *         La solución final es la esperada.
     *     </li>
     *  </p>
     */
    @Override
    public ArrayList<ArrayList<Integer>> sumM(int[] a, int M) {
        ArrayList<ArrayList<Integer>> sol = new ArrayList<>();
        ArrayList<Integer> s = new ArrayList<>();
        sumM(a, M, 0, s, sol);
        sol.sort(Comparator.comparingInt(ArrayList::size));

        return sol;
    }

    /**
     * Dado un conjunto numerico, encontrar todos los subconjuntos que suman M.
     * @param a conjunto númeric.
     * @param M resultado suma de los subconjuntos.
     * @param i índice del elemento a tratar.
     * @param s solución parcial.
     * @param sol solución final.
     * @pre a!=null, M>=0, i>=0, s!=null, sol!=null
     * @post sol contiene los subconjuntos que suman M y todos los elementos pertenecen al conjunto incial.
     * @complexity O(2 ^ n). Relacionado con un arbol binario, la altura sería n y cada nodo tiene dos hijos
     *      (que serían las dos llamadas recursivas) y el número de nodos es 2^n + 1.
     * <br>
     * <p>
     *     <b>Casos posibles:</b>
     *     <li>
     *         M == 0: Se añade la solución parcial a la solución final.
     *     </li>
     *     <li>
     *         i < a.length: Se comprueba si el elemento actual es menor que M, si es así se añade a la solución parcial.
     *     </li>
     * </p>
     *
     * <br><br>
     * <p>
     *      <b>Proof</b>
     *      <li>
     *          Todos los estados de la precondicion se tratan ya que el código trata el conjunto de elementos completo,
     *          hasta que i == a.length.
     *      </li>
     *     <li>
     *         El código finaliza al llegar al caso base.
     *     </li>
     *     <li>
     *         Las llamadas recursivas cumplen la precondición y la postcondición.
     *     </li>
     *     <li>
     *         La solución final es la esperada.
     *     </li>
     *  </p>
     */
    private void sumM(int[] a, int M, int i, ArrayList<Integer> s, ArrayList<ArrayList<Integer>> sol) {
        if (M == 0) {
            sol.add(new ArrayList<>(s));
        } else if (i < a.length) {
            if (M >= a[i]) {
                s.add(a[i]);
                sumM(a, M - a[i], i + 1, s, sol);
                s.remove(s.size() - 1);
            }
            sumM(a, M, i + 1, s, sol);
        }
    }
}
