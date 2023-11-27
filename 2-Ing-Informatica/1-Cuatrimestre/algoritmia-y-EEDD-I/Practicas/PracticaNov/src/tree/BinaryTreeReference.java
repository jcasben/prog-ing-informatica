package tree;
/**
 *
 * @author Jesús Castillo Benito y Marc Link Cladera
 */
public class BinaryTreeReference<E extends Comparable<E>> implements BinaryTree<E> {

    private TreeNode<E> root;
    public BinaryTreeReference() {
        root = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(E e) {
        // Pone en root el nuevo árbol con el elemento añadido
        root = insertRecursive(root, e);
    }

    private TreeNode<E> insertRecursive(TreeNode<E> current, E e) {
        // Si el nodo actual es nulo, crea un nuevo nodo con el elemento
        if (current == null) {
            return new TreeNode<>(e);
        }

        // Compara el elemento del nodo actual con el elemento a insertar
        int comparisonResult = e.compareTo(current.getItem());

        // Decide en qué subárbol insertar de manera recursiva
        if (comparisonResult < 0) {
            current.setLeft(insertRecursive(current.getLeft(), e));
        } else if (comparisonResult > 0) {
            current.setRight(insertRecursive(current.getRight(), e));
        }

        // Si el elemento ya esta en el árbol no hará nada.
        return current;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(E e) {
        return containsRecursive(root, e);
    }

    private boolean containsRecursive(TreeNode<E> current, E e) {
        // Si el nodo actual es nulo, el elemento no está en el árbol
        if (current == null) {
            return false;
        }

        // Compara el elemento del nodo actual con el elemento buscado
        if (current.getItem().equals(e)) {
            return true;
        }

        // Busca en el subárbol izquierdo y derecho de manera recursiva
        boolean leftResult = containsRecursive(current.getLeft(), e);
        boolean rightResult = containsRecursive(current.getRight(), e);

        // Devuelve true si el elemento está en alguno de los subárboles
        return leftResult || rightResult;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int longestBranch() {
        return longestBranchRecursive(root);
    }

    private int longestBranchRecursive(TreeNode<E> current) {
        if (current == null) {
            return 0;
        }
        // El tamaño de la rama actual es el máximo entre la rama izquierda y derecha, más 1
        int tamLeft = longestBranchRecursive(current.getLeft());
        int tamRight = longestBranchRecursive(current.getRight());

        if (current.getItem().equals(root.getItem())){
            return Math.max(tamLeft,tamRight);
        }
        return 1 + Math.max(tamLeft,tamRight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E getMother(E e) {
        return getMotherRecursive(root, e);
    }

    private E getMotherRecursive(TreeNode<E> current, E e) {
        // Si el nodo actual es nulo, el elemento no está en el árbol
        if (current == null) {
            return null;
        }

        // Compara el elemento del nodo izquierdo con el elemento buscado
        if (current.getLeft() != null && current.getLeft().getItem().equals(e)) {
            return current.getItem();
        }

        // Compara el elemento del nodo derecho con el elemento buscado
        if (current.getRight() != null && current.getRight().getItem().equals(e)) {
            return current.getItem();
        }

        // Busca en el subárbol izquierdo y derecho de manera recursiva
        var leftResult = (E) getMotherRecursive(current.getLeft(), e);
        var rightResult = (E) getMotherRecursive(current.getRight(), e);

        // Devuelve el resultado no nulo encontrado en los subárboles
        return (leftResult != null) ? leftResult : rightResult;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E getRoot() {
        return root.getItem();
    }

}
