/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backtracking;

/**
 * Element per a la motxilla emmagatzema de l'element informació genèrica, el
 * pes 1 (weight1>=0), el pes2 (weight2>=0) i el profit>=0.
 * @author antoni
 */
public class ElementMotxilla<E> {

    private E element;
    private double weigth1;
    private double weigth2;
    private double profit;

    /**
     * Permet construir un element per a la motxilla
     * @param element Informació genèrica de l'element
     * @param weigth1 >=0, pes1 de l'element
     * @param weigth2 >=0, pes2 de l'element
     * @param profit >=0, profit de l'element
     */
    public ElementMotxilla(E element, double weigth1, double weigth2, double profit) {
        if((weigth1<0)||(weigth2<0)||(profit<0)) 
            throw new IllegalArgumentException("weigth1, weigth2 "
                    + "i profit han de ser >= 0 ");
        this.element = element;
        this.weigth1 = weigth1;
        this.weigth2 = weigth2;
        this.profit = profit;
    }

    /**
     * Getter Element
     * @return Informació genèrica de l'element
     */
    public E getElement() {
        return element;
    }

    /**
     * Getter weight1
     * @return pes1 de l'element
     */
    public double getWeigth1() {
        return weigth1;
    }

    /**
     * Getter weight2
     * @return pes2 de l'element
     */
    public double getWeigth2() {
        return weigth2;
    }

    /**
     * Getter profit
     * @return profit de l'element
     */
    public double getProfit() {
        return profit;
    }

    /**
     * D'un conjunt de l'elements per a la motilla calcula el profit total
     * @param result conjunt d'elements per a la motxilla
     * @return suma de tots els profits
     */
    public static double profit(ElementMotxilla[] result) {
        double p = 0.0;
        for (int i = 0; i < result.length; i++) {
            p = p + result[i].profit;
        }
        return p;
    }

    /**
     * D'un conjunt de l'elements per a la motilla calcula el weigt1 total
     * @param result conjunt d'elements per a la motxilla
     * @return suma de tots els weight1
     */
    public static double w1(ElementMotxilla[] result) {
        double w = 0.0;
        for (int i = 0; i < result.length; i++) {
            w = w + result[i].weigth1;
        }
        return w;
    }

    /**
     * D'un conjunt de l'elements per a la motilla calcula el weight2 total
     * @param result conjunt d'elements per a la motxilla
     * @return suma de tots els weight2
     */
    public static double w2(ElementMotxilla[] result) {
        double w = 0.0;
        for (int i = 0; i < result.length; i++) {
            w = w + result[i].weigth2;
        }
        return w;
    }
}
