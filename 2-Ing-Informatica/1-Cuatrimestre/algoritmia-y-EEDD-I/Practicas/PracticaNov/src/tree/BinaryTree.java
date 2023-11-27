package tree;

/**
 * Interfície per a una Arbre Binary sense elements repetits que permet les 
 * operacions definides en aquesta interfície.
 * @author Jesús Castillo Benito y Marc Link Cladera
 * @param <E> Elements que permet emmagatzemar l'arbre binari
 */
public interface BinaryTree<E extends Comparable<E>> {
    
    /**
     * Indica si l'arbre és buit
     * @return el booleà indica si l'arbre és buit o no
     * Complejidad: O(1)
     */
    public boolean isEmpty();   
    
    /**
     * Inserta l'element e dins l'arbre. Si l'element ja és dins l'arbre 
     * no fa res. Per decidir on insertar, comença per l'arrel. Si l'element és
     * més petit mira el subarbre esquerre, si és més gran el subarbre dret. 
     * Així recursivament fins arribar a la fulla on ha d'insertar.
     * @param e Element a insertar
     * Complejidad: O(log(n))
     */
    public void insert(E e);
    
    
    /**
     * Indica si l'element e forma part de l'arbre
     * @param e element que comprova si forma part de l'arbre
     * @return true si e forma part de l'arbre, false en cas contrari
     * Complejidad: O(n)
     */
    public boolean contains(E e);
    
    /**
     * Indica la longitud de la branca més llarga de l'arbre
     * @return la longitud de la branca més llarga de l'arbre.
     * Complejidad: O(n)
     */
    public int longestBranch();
    
    /**
     * Donar un element e, es retorna el pare/mare d'aquest element. L'element e
     * ha d'estar dins l'arbre. Si l'element és l'arrel o no està dins l'arbre
     * ha de retornar null.
     * @param e element del qual es vol obtenir el pare/mare
     * @return retorna el pare/mare de l'element e en l'arbre.
     * Complejidad: O(n)
     */
    public E getMother(E e);
    
    /**
     * Retorna l'element que està a l'arrel de l'arbre. Si és un arbre buit ha 
     * retornar null
     * @return element que està a l'arrel de l'arbre. Si és un arbre buit ha 
     * retornar null
     * Complejidad: O(1)
     */
    public E getRoot();
}
