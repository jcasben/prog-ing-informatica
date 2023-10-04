import TAD.*;

import java.util.Comparator;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // ---------- EJEMPLO CON PILA DE CURSOR ----------
        System.out.println("---------- EJEMPLO CON PILA DE CURSOR ----------");
        CursorStack<Integer> cursorStack = new CursorStack<>();
        cursorStack.push(4);
        cursorStack.push(5);
        cursorStack.push(6);
        try {
            System.out.println(cursorStack.top());
            cursorStack.pop();
            System.out.println(cursorStack.top());
            cursorStack.pop();
            System.out.println(cursorStack.top());
            cursorStack.pop();
        } catch (Stack.EmptyStackException e) {
            System.err.println(e.getMessage());
        }
        // ---------- EJEMPLO CON PILA DE PUNTEROS ----------
        System.out.println("---------- EJEMPLO CON PILA DE PUNTEROS ----------");
        PointerStack<Integer> pointerStack = new PointerStack<>();
        pointerStack.push(1);
        pointerStack.push(2);
        pointerStack.push(3);
        try {
            System.out.println(pointerStack.top());
            pointerStack.pop();
            System.out.println(pointerStack.top());
            pointerStack.pop();
            System.out.println(pointerStack.top());
            pointerStack.pop();
        } catch (Stack.EmptyStackException e) {
            System.err.println(e.getMessage());
        }
 
        // ---------- EJEMPLO CON COLA DE CURSOR ----------
        System.out.println("---------- EJEMPLO CON COLA DE CURSOR ----------");
        CursorQueue<Integer> cursorQueue = new CursorQueue<>();
        cursorQueue.enqueue(50);
        cursorQueue.enqueue(60);
        cursorQueue.enqueue(70);
        try {
            System.out.println(cursorQueue.first());
            System.out.println(cursorQueue.dequeue());
            System.out.println(cursorQueue.dequeue());
            System.out.println(cursorQueue.dequeue());
        } catch (Queue.EmptyQueueException e) {
            System.err.println(e.getMessage());
        }
        // ---------- EJEMPLO CON COLA DE PUNTEROS ----------
        System.out.println("---------- EJEMPLO CON COLA DE PUNTEROS ----------");
        PointerQueue<Integer> pointerQueue = new PointerQueue<>();
        pointerQueue.enqueue(10);
        pointerQueue.enqueue(20);
        pointerQueue.enqueue(30);
        try {
            System.out.println(pointerQueue.first());
            System.out.println(pointerQueue.dequeue());
            System.out.println(pointerQueue.dequeue());
            System.out.println(pointerQueue.dequeue());
        } catch (Queue.EmptyQueueException e) {
            System.err.println(e.getMessage());
        }
        // ---------- EJEMPLO ORDENACION CON COMPARABLE ----------
        System.out.println("---------- EJEMPLO ORDENACION CON COMPARABLE ----------");
        Random ran = new Random();
        Integer [] ints = new Integer[ran.nextInt(20)];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = ran.nextInt(100);
            System.out.print(ints[i] + ", ");
        }
        System.out.println("\n---- ORDENAMOS EL ARRAY ----");
        Integer [] orderedInts = Main.orderWithComparable(ints);
        for (Integer orderedInt : orderedInts) {
            System.out.print(orderedInt + ", ");
        }
        // ---------- EJEMPLO ORDENACION CON COMPARATOR ----------
        System.out.println("\n---------- EJEMPLO ORDENACION CON COMPARATOR ----------");
        Integer [] ints2 = new Integer[ran.nextInt(20)];
        for (int i = 0; i < ints2.length; i++) {
            ints2[i] = ran.nextInt(100);
            System.out.print(ints2[i] + ", ");
        }
        System.out.println("\n---- ORDENAMOS EL ARRAY ----");
        Integer [] orderedInts2 = Main.orderWithComparator(ints2, Comparator.naturalOrder());
        for (Integer orderedInt : orderedInts2) {
            System.out.print(orderedInt + ", ");
        }
    }

    /**
     *  P: {t.length > 0}
     *  Q: {(t.length == x.length) && (∀i 0 <= i < x.lenght -1 : x[i].compareTo(x[i+1]) < 0) &&
     *      (∀i 0 <= i < x.lenght : (∀j 0 <= j < t.length: x[i] == t[j])}
     *
     * @param t El array desordenado.
     * @param <T> Clase del objeto.
     * @return x El array ordenado.
     */
    private static <T extends Comparable<T>> T[] orderWithComparable(T[] t) {
        T[] x = t;
        int N = x.length;
        T tmp;
        int i = 0;

        while (i < N - 1) {
            int lj = N;
            for (int j = N - 2; j >= i; j--) {
                if (x[j + 1].compareTo(t[j]) < 0) {
                    tmp = x[j + 1];
                    x[j + 1] = x[j];
                    x[j] = tmp;
                }
                lj = j;
            }
            i = lj + 1;
        }
        return x;
    }

    private static <T> T[] orderWithComparator(T[] t, Comparator<T> c) {
        int N = t.length;
        T tmp;
        int i = 0;

        while (i < N - 1) {
            int lj = N;
            for (int j = N - 2; j >= i; j--) {
                if (c.compare(t[j + 1], t[j]) < 0) {
                    tmp = t[j + 1];
                    t[j + 1] = t[j];
                    t[j] = tmp;
                }
                lj = j;
            }
            i = lj + 1;
        }
        return t;
    }
}