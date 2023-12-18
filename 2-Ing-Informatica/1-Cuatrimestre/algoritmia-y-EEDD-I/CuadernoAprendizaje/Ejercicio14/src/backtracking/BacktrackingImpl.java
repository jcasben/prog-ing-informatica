package backtracking;

import java.util.Arrays;

/**
 *
 * @author Jesús Castillo Benito y Marc Link Cladera
 */
public class BacktrackingImpl implements Backtracking {

    @Override
    public int[] knapSackRecursive(int W, int wt[], int val[]) {
        int n = wt.length;
        int[] included = new int[n];
        int[][] memo = new int[n + 1][W + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        knapSackHelper(W, wt, val, n, included, memo);
        return included;
    }

    /**
     * Calcula la solución óptima para el problema de la mochila utilizando el
     * enfoque de backtracking.
     * 
     * @param W        el peso máximo que puede soportar la mochila
     * @param wt       los pesos de los elementos disponibles
     * @param val      los valores de los elementos disponibles
     * @param n        el número de elementos disponibles
     * @param included un arreglo que indica si un elemento está incluido en la
     *                 solución óptima (1) o no (0)
     * @param memo     una matriz para almacenar los resultados previamente
     *                 calculados
     * @return el valor máximo que se puede obtener en la mochila
     */
    private int knapSackHelper(int W, int wt[], int val[], int n, int[] included, int[][] memo) {
        if (n == 0 || W == 0)
            return 0;
        if (memo[n][W] != -1)
            return memo[n][W];
        if (wt[n - 1] > W)
            return memo[n][W] = knapSackHelper(W, wt, val, n - 1, included, memo);
        else {
            int[] includedCopy = included.clone();
            includedCopy[n - 1] = 0;
            int notTaken = knapSackHelper(W, wt, val, n - 1, includedCopy, memo);
            included[n - 1] = 1;
            int taken = val[n - 1] + knapSackHelper(W - wt[n - 1], wt, val, n - 1, included, memo);
            if (notTaken > taken) {
                System.arraycopy(includedCopy, 0, included, 0, n);
                return memo[n][W] = notTaken;
            } else {
                return memo[n][W] = taken;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int[] knapSackIterative(int W, int[] w, int[] p) {
        int n = w.length;
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                if (i - 1 >= 0 && j - w[i - 1] >= 0) { // Check if indices are within valid range
                    dp[i][j] = Math.max(p[i - 1] + dp[i - 1][j - w[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        int[] selectedItems = new int[n];
        int i = n, j = W;
        while (i > 0 && j > 0) {
            if (i - 1 >= 0 && j - w[i - 1] >= 0 && dp[i][j] != dp[i - 1][j]) { // Check if indices are within valid
                                                                               // range
                selectedItems[i - 1] = 1;
                j -= w[i - 1];
            }
            i--;
        }

        return selectedItems;
    }
}