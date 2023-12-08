package backtracking;

import java.util.ArrayList;

/**
 * Interfície per al mètodes de Backtracking que implementarem durant les
 * sessions pràctiques
 * @author antoni
 */
public interface Backtracking {
    /**
     * Donat un conjunt numèric, trobar tots els subconjunts que sumen M
     * @param a conjunt númeric. Tots els elements a són >0
     * @param M Resultat suma dels subconjunts. M>=0
     * @return conjunt dels subconjunts que sumen M
     */
    ArrayList<ArrayList<Integer>> sumM(int a[], int M);
}
