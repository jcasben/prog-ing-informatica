/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hanoi;

import java.util.Stack;

/**
 *
 * @author antoni
 * @param <E>
 */
public interface Hanoi<E extends Comparable<E>>{
    /**
     * Mètode recursiu que resol Hanoi
     * @param h Altura de les torres
     * @param a torre a
     * @param b torre b
     * @param c torre c
     */
    public void recursiuHanoi(int h, Stack<E> a,
            Stack<E> b,
            Stack<E> c);
    
    /**
     * Mètode recursiu que resol Hanoi
     * @param h Altura de les torres
     * @param a torre a
     * @param b torre b
     * @param c torre c
     */
    public void iteratiuHanoi(int h, Stack<E> a,
            Stack<E> b,
            Stack<E> c);
}
