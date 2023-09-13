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
        ArrayList<Integer> conjunto = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            //Los elementos aleatorios se generaran desde el 0 hasta el 20000
            int nuevoElemento = random.nextInt(20000);
            conjunto.add(nuevoElemento);
        }
        
        Collections.sort(conjunto);
        System.out.println("CONJUNTO ORDENADO:\n" + conjunto);

        //Solicitamos al usuario que introduzca el elemento que quiere buscar en el conjunto.
        System.out.print("\nIntroduce el elemento buscado: ");
        int elementoBuscado = scanner.nextInt();

        int indice = Collections.binarySearch(conjunto, elementoBuscado);
        //Si no encuentra el elemento, nos saldra un mensaje indicandolo.
        if (indice < 0) {
            System.out.println("No se ha encontrado el elemento en el conjunto.");
        } else {
            System.out.println("El indice del elemento es: " + indice);
        }
    }
}