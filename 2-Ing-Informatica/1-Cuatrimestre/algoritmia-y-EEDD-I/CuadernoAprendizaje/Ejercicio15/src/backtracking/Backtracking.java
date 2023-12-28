/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package backtracking;

/**
 * Interfície per al mètodes de Backtracking que implementarem durant les
 * sessions pràctiques
 * @author antoni
 */
public interface Backtracking {
    
    /**
     * Donat un map, indica si és pot colorejar amb un màxim de nColors de forma 
 que les regions veïnades del mapa, no comparteixin nColors.
     * @param map mapa, on per cada regió es llisten les regions veïnades
     * @param nColors número màxim de colors a utilitzar
     * @return indica si es pot colorejar amb el nColors o no
     */
    boolean mapColorRecursive(int map[][], int nColors);
}
