/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package backtracking;

/**
 * Interfície per al mètodes de Backtracking que implementarem durant les
 * sessions pràctiques
 * 
 * @author antoni
 */
public interface Backtracking {

    /**
     * Mètode per resoldre de forma recursiva el problema de la motxilla. w.length
     * == p.lenght AND
     * w.length > 0
     * Analisis de casos:
     * 1. Si el pes del element es major que el pes de la motxilla, no es pot afegir
     * 2. Si el pes del element es menor que el pes de la motxilla, es pot afegir
     * 3. Si el pes del element es igual que el pes de la motxilla, es pot afegir
     * 
     * Proof:
     *      <li>
     *          Todos los estados de la precondicion se tratan ya que el código trata el conjunto de elementos completo.
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
     * @pre w.length == p.lenght AND w.length > 0
     * @post retorna la selecció més òptima
     * @param W pes màxim de la motxilla, >0
     * @param w conjunt de pesos de cada element, cada w[i] > 0
     * @param p conjunt del profit de cada element, cada p[i] > 0
     * @return selecció més òptima
     * @complejidad O(n*W)
     */
    public int[] knapSackRecursive(int W, int w[], int p[]);

    /**
     * Mètode per resoldre de forma iterativa el problema de la motxilla. w.length
     * == p.lenght AND
     * w.length > 0
     * Analisis de casos:
     * 1. Si el pes del element es major que el pes de la motxilla, no es pot afegir
     * 2. Si el pes del element es menor que el pes de la motxilla, es pot afegir
     * 3. Si el pes del element es igual que el pes de la motxilla, es pot afegir
     * 
     * Proof:
     *      <li>
     *          Todos los estados de la precondicion se tratan ya que el código trata el conjunto de elementos completo.
     *      </li>
     *     <li>
     *         El código finaliza.
     *     </li>
     *     <li>
     *         La solución final es la esperada.
     *     </li>
     * @pre w.length == p.lenght AND w.length > 0
     * @post retorna la selecció més òptima
     * 
     * @param W pes màxim de la motxilla, >0
     * @param w conjunt de pesos de cada element, cada w[i] > 0
     * @param p conjunt del profit de cada element, cada p[i] > 0
     * @return selecció més òptima
     * @complejidad O(n*W)
     */
    public int[] knapSackIterative(int W, int w[], int p[]);
}
