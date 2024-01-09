/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backtracking;

/**
 * Implementació de la interfície Motxilla.
 * @author Marc Link y Jesús Castillo
 */
public class MotxillaImpl implements Motxilla{

    private double maxp;

    /**
     * Resol la variant de la motxilla amb dos pesos de forma recursiva. Utilitza la array numSols
     * modificada per el mètode auxiliar recursiu per crear un conjunt d'elements que contengui
     * el nombre d'elements correcte de cada tipus.
     * @pre a.length > 0 && (W1 >= 0 && W2 >= 0) && tots el elements de a != null.
     * @post retorna un conjunt d'elements (els elements retornats pertanyen al conjunt d'elements a)
     * de forma que es maximitzi el profit sense superar els pesos màxims (W1 i W2).
     * @complexity O(2^n) on n és el nombre d'elements de a.
     * @param a conjunt d'elements.
     * @param W1 >= 0, límit per al weight1.
     * @param W2 >= 0, límit per al weight2.
     * @return conjunt de elements de forma que es maximitzi el profit sense
     * superar els pesos màxims (W1 i W2).
     */
    @Override
    public ElementMotxilla[] recursiu(ElementMotxilla[] a, double W1, double W2) {
        maxp = 0;
        int [] t = new int[a.length];
        int [] numSols = new int[a.length];
        recursiu(W1, W2, a, t, numSols, 0, 0, 0, 0);
        int solLength = 0;
        // Calculam quants elements diferents hi ha a la solució.
        for (int numSol : numSols) {
            if (numSol == 1) solLength++;
        }
        ElementMotxilla [] sol = new ElementMotxilla[solLength];
        int j = 0;
        // Introduim els elements que ens indiqui numSols al conjunt d'elements.
        for (int i = 0; i < numSols.length; i++) {
            if (numSols[i] == 1) sol[j++] = a[i];
        }
        return sol;
    }

    /**
     * Resol la variant de la motxilla amb dos pesos de forma recursiva. Aquest mètode modifica
     * sol i introduint el nombre d'elements de cada tipus que hem de ficar a la motxilla per tenir
     * la solució optima.
     * @complexity Per a cada element de a, podem decidir ficar-lo o no, per tant tenim 2^n combinacions. Això
     * ens dóna una complexitat de O(2^n).
     * @param W1 pes maxim 1.
     * @param W2 pes maxim 2.
     * @param a conjunt d'elements.
     * @param t auxiliar per guardar les solucions.
     * @param sol solucio.
     * @param k variable per iterar.
     * @param weight1 pes actual 1.
     * @param weight2 pes actual 2.
     * @param profit profit actual.
     */
    public void recursiu(double W1, double W2, ElementMotxilla [] a, int [] t, int [] sol, int k, double weight1, double weight2, double profit) {
        t[k] = -1;
        while (t[k] < 1) {
            t[k]++;
            weight1 += a[k].getWeigth1() * t[k];
            weight2 += a[k].getWeigth2() * t[k];
            profit += a[k].getProfit() * t[k];
            if (!poda(W1, W2, weight1, weight2) && (k == t.length - 1)) {
                if (profit > maxp) {
                    maxp = profit;
                    System.arraycopy(t, 0, sol, 0, t.length); // O(n)
                }
            } else if (!poda(W1, W2, weight1, weight2) && (k < t.length - 1)) {
                recursiu(W1, W2, a, t, sol, k + 1, weight1, weight2, profit);
            }
            weight1 -= a[k].getWeigth1() * t[k];
            weight2 -= a[k].getWeigth2() * t[k];
            profit -= a[k].getProfit() * t[k];
        }
        t[k] = -1;
    }

    /**
     * Comprova si es pot fer poda. Si algun dels pesos acutals supera el seu
     * pes màxim, es poda.
     * @complexity O(1)
     * @param W1 pes maxim 1
     * @param W2 pes maxim 2
     * @param weight1 pes actual 1
     * @param weight2 pes actual 2
     * @return true si es pot fer poda, false altrament.
     */
    private boolean poda(double W1, double W2, double weight1, double weight2) {
        return !(weight1 <= W1 && weight2 <= W2);
    }

    /**
     * Resol la variant de la motxilla amb dos pesos de forma iterativa.
     * @complexity O(2^n) perque per a cada element de a, podem decidir ficar-lo o no, per tant tenim 2^n combinacions.
     * @param a conjunt d'elements
     * @param W1 >= 0, límit per al weight1
     * @param W2 >= 0, límit per al weight2
     * @return conjunt de elements de forma que es maximitzi el profit sense
     * superar els pesos màxims (W1 i W2).
     */
    @Override
    public ElementMotxilla[] iteratiu(ElementMotxilla[] a, double W1, double W2) {
        maxp = 0;
        double weight1 = 0;
        double weight2 = 0;
        double profit = 0;
        int [] t = new int[a.length];
        int [] numSols = new int[a.length];
        int k = 0;
        ElementMotxilla[] tmpSol = new ElementMotxilla[a.length];
        int solLength = 0;

        for (int i = 0; i < t.length; i++) t[i] = -1;

        while (k >= 0) {
            if (t[k] < 1) {
                t[k]++;
                weight1 += a[k].getWeigth1() * t[k];
                weight2 += a[k].getWeigth2() * t[k];
                profit += a[k].getProfit() * t[k];
                if (!poda(W1, W2, weight1, weight2) && (k == a.length - 1)) {
                    if (profit > maxp) {
                        maxp = profit;
                        System.arraycopy(t, 0, numSols, 0, t.length);
                    }
                } else if (!poda(W1, W2, weight1, weight2) && (k < a.length - 1)) {
                    k++;
                }
            } else {
                weight1 -= a[k].getWeigth1() * t[k];
                weight2 -= a[k].getWeigth2() * t[k];
                profit -= a[k].getProfit() * t[k];
                t[k] = -1;
                k--;
            }
        }

        for (int numSol : numSols) {
            if (numSol == 1) solLength++;
        }
        ElementMotxilla [] sol = new ElementMotxilla[solLength];
        int j = 0;
        // Introduim els elements que ens indiqui numSols al conjunt d'elements.
        for (int i = 0; i < numSols.length; i++) {
            if (numSols[i] == 1) sol[j++] = a[i];
        }

        return sol;
    }
}