import binarySearch.Search;

import java.util.Arrays;
import java.util.Comparator;

public class Ejercicio6 {
    public static void main(String[] args) {
        System.out.println("------------- PRUEBA DE BUSQUEDA BINARIA CON COMPARABLE -------------");
        String [] nombres = {"Jesus", "Marc", "Carlos", "Unai", "Pau"};
        Arrays.sort(nombres);
        System.out.println("Array \"nombres\": " + Arrays.toString(nombres));
        System.out.println(
                "Indice del array \"nombres\" que contiene el nombre \"Carlos\": " +
                        + Search.binarySearchComparable(nombres, "Carlos")
                );

        //Generamos un comparator para comparar Strings.
        Comparator<String> comparator = String::compareTo;
        System.out.println(
                "Indice del array \"nombres\" que contiene el nombre \"Pau\": " +
                        + Search.binarySearchComparator(nombres, "Pau", comparator)
        );
    }
}
