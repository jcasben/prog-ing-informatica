import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/*
 * Autor: Jesus Castillo Benito
 * Titulo: Cuaderno de Aprendizaje: Ejercicio 1
 * Fecha: 12/09/2023
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        //Solicitamos al usuario que introduzca el valor de N (se asume que el usuario es perfecto).
        System.out.print("Introduce la cantidad de elementos del conjunto: ");
        int n = scanner.nextInt();

        //Inicializacion del conjunto de elementos
        ArrayList<Integer> set = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            //Los elementos aleatorios se generaran desde el 0 hasta el 20000
            int newElement = random.nextInt(20000);
            set.add(newElement);
        }

        //Utilizamos la el metodo sort() de la clase Collections para ordenar el conjunto.
        Collections.sort(set);
        System.out.println("CONJUNTO ORDENADO:\n" + set);

        //Solicitamos al usuario que introduzca el elemento que quiere buscar en el conjunto.
        System.out.print("\nIntroduce el elemento buscado: ");
        int searchedElement = scanner.nextInt();

        int index = binarySearch(set, searchedElement);
        //Si no encuentra el elemento, nos saldra un mensaje indicandolo.
        if (index < 0) {
            System.out.println("No se ha encontrado el elemento en el conjunto.");
        } else {
            System.out.println("El indice del elemento es: " + index);
        }
    }

    /**
     * Implementacion del la busqueda dicotomica.
     * @param set conjunto de elementos sobre el que se busca.
     * @param searchedElement elemento que buscamos en el conjunto.
     * @return el indice del elemento si sse encuentra y -1 si no se encuentra.
     */
    public static int binarySearch(ArrayList<Integer> set, int searchedElement) {
        int left = 0, right = set.size() - 1;
        int center = (left + right) / 2;

        while (left < right && set.get(center) != searchedElement) {
            if (set.get(center) < searchedElement) {
                left = center + 1;
            } else {
                right = center - 1;
            }
            center = (left + right) / 2;
        }
        return (set.get(center) == searchedElement ? center : -1);
    }
}