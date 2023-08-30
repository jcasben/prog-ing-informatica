package multiples4;

public class Main {

    //Inicio
    private void Start(){

        //Llama a las funciones de la clase Functions (ver fichero Functions.java).
        Functions fncts = new Functions();

        fncts.askMultiple();

        //Crea un array de Integer utilizando el método askNumbers()
        Integer[] matrix = fncts.askNumbers();
        fncts.printSolution(matrix);

    }

    //Método Main
    public static void main(String[] args) {

        //Llama a Inicio
        (new Main()).Start();
    }
}
