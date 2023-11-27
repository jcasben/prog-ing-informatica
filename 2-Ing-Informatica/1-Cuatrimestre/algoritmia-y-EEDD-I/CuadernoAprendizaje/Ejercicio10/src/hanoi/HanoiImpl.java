package hanoi;

import java.util.Stack;

/**
 *
 * @author antoni
 */
public class HanoiImpl<E extends Comparable<E>> implements Hanoi<E> {

    /**
     * <p>
     *      <b>Resuelve el problema de las Torres de Hanoi de forma recursiva.</b>
     * </p>
     *
     *
     * @param h La cantidad de discos en la torre.
     * @param a La pila de origen.
     * @param b La pila auxiliar.
     * @param c La pila de destino.
     * @pre ∀x,y ∈ A:(x ≤ y ∧ pop(A) = x ∧ pop(A) = y) / Para cualquier par de elementos x e y en el stack {@param a}
     * se debe cumplir que x ≤ y si primero se hace el pop de x y después el de y.
     * @post (∀x,y ∈ C:(x ≤ y ∧ pop(C) = x ∧ pop(C) = y)) ∧ a.empty() == true ∧ b.empty() == true / Para cualquier
     * par de elementos x e y en el stack {@param a} se debe cumplir que x ≤ y si primero se hace el pop de x y después
     * el de y.
     * <br><br><br>
     * <p>
     *     Este método mueve la torre de discos desde la pila 'a' hasta la pila 'c' utilizando la pila 'b' como auxiliar.
     *     El movimiento se realiza siguiendo las reglas del problema de las Torres de Hanoi.
     * </p>
     * <br>
     * <p>
     *     <b>Complejidad:</b> O(2^h)
     * </p>
     *  <br>
     *  <p>
     *      <b>Casos posibles:</b>
     *      <li>
     *          - h == 1: El codigo se ejecutará una única vez moviendo el elemento de la pila 'A'
     *                    a la pila 'C'.
     *      </li>
     *      <li>
     *          - h > 1: El codigo mediante recursividad movera los h-1 elementos de la pila 'A' a
     *                   la pila 'B', despúes movera el elemento que se ha quedado en la pila 'A' a
     *                   la pila 'C' y para finalizar, los elementos(h-1) que estan en la pila 'B'
     *                   los mueve a la pila 'C' recursivamente.
     *      </li>
     *
     *  </p>
     *  <br>
     *  <p>
     *      <b>Proof</b>
     *      <li>
     *          Todos los casos de la precondicion se tratan ya que el codigo trata h == 1 y h > 1 con ello conseguinmos
     *          tratar todos los elementos que cumplen h ≥ 1.
     *      </li>
     *      <li>
     *          Cuando llegamos al caso base para ya que estaremos con h == 1.
     *      </li>
     *      <li>
     *          Las llamadas recursivas cumplen la precondición y la postcondición.
     *      </li>
     *      <li>
     *          El tamaño de los elementos a tratar en la recursividad se van disminuyendo ya que llamamos a la
     *          recusividad con "h-1".
     *      </li>
     *  </p>
     *
     */
    @Override
    public void recursiuHanoi(int h, Stack<E> a, Stack<E> b, Stack<E> c) {
        if (h == 1) {
            c.push(a.pop());
        }
        if (h > 1) {
            recursiuHanoi(h-1,a,c,b);
            c.push(a.pop());
            recursiuHanoi(h-1,b,a,c);
        }
    }

    @Override
    public void iteratiuHanoi(int h, Stack<E> a, Stack<E> b, Stack<E> c) {
        //TO DO
    }

}
