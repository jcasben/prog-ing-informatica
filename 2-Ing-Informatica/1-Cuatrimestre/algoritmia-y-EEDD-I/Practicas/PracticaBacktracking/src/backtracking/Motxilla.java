/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package backtracking;

/**
 * Interfície per resoldre la variant de la motxilla del projecte. Donat un 
 * conjunt d'elements, cadascun tendrà 2 pesos i un profit. La variant del 
 * nostre problema de la Motxilla ha de determinar quins elements 
 * ha d'incloure una col·lecció de forma que cada pes sigui inferior o 
 * igual a un límit determinat i el profit sigui el més gran possible.
 * @author antoni
 */
public interface Motxilla {
    /**
     * Resol la variant de la motxilla de l'enunciat de forma recursiva
     * @param a conjunt d'elements
     * @param W1 >= 0, límit per al weight1
     * @param W2 >= 0, límit per al weight2
     * @return conjunt de forma que cada pes sigui inferior o 
     * igual a un límit determinat i el profit sigui el més gran possible
     */
    public ElementMotxilla[] recursiu(ElementMotxilla a[], double W1, double W2);
    
    /**
     * Resol la variant de la motxilla de l'enunciat de forma iterativa
     * @param a conjunt d'elements
     * @param W1 >= 0, límit per al weight1
     * @param W2 >= 0, límit per al weight2
     * @return conjunt de forma que cada pes sigui inferior o 
     * igual a un límit determinat i el profit sigui el més gran possible
     */
    public ElementMotxilla[] iteratiu(ElementMotxilla a[], double W1, double W2);
}
