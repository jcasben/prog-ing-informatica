package multiples4;

public class Functions {

    private LT in;
    private int multiple = 0;

    //Método que pregunta al usuario un número del cual buscaremos los múltiplos
    public void askMultiple(){

        Integer aux = 0;
        in = new LT();

        System.out.println("************************************************************************************************************************************");
        System.out.println("Por favor, introduce un número y luego buscaremos sus múltplos");
        System.out.println("************************************************************************************************************************************");
        multiple = integer_validation(aux);

        //Comprueba que el número introducido no sea negativo o 0.
        while(multiple <= 0) {

            System.out.println("Por favor, introduce un número y luego buscaremos sus múltplos");
            multiple = integer_validation(aux);
        }
    }
    //Método que pide números enteros por teclado al usuario y guarda estos en una matriz. Se pueden introducir tantos valores como el usuario quiera.
    public Integer[] askNumbers(){

        in = new LT();

        Integer[] numbers = new Integer[20];
        Integer aux_value = 0;
        int index = 0;
        System.out.println("************************************************************************************************************************************");
        System.out.println("Introduce números de uno en uno y cuando quieras terminar, introduce un 0. De todos los que te introduzcas, el programa te dirá" +
                " los que son múltiplos de " + multiple + ".");
        System.out.println("************************************************************************************************************************************");

        for (int i = 0; (i < numbers.length); i++) {

            System.out.println("Introduce un número");
            numbers[i] = integer_validation(aux_value);
            index++;

            //Algoritmo para agrandar el array cuando se quieren introducir más números y no caben más.
            if(index == numbers.length){

                Integer[] aux = new Integer[numbers.length + 10];

                for(int j = 0; j < numbers.length; j++){

                    aux[j] = numbers[j];
                }

                numbers = aux;
            }

            //Cuando el valor introducido es 0 deja de recibir números por teclado.
            if(numbers[i] == 0){

                break;
            }
        }

        return numbers;
    }

    //Método usado para impprimir los números de la matriz pasada por parámetro que sean multiplos de 4 y que para de imprimir cuando encuentra 0.
    public void printSolution(Integer[] matrix){

        System.out.println("************************************************************************************************************************************");
        System.out.print("De los números que has introducido... \nLos siguientes son números múltiplos de "  + multiple + ": ");

        //Imprimir solo los números múltiplos del número selccionado.
        for (int i = 0; i < matrix.length; i++) {

            //if para determinar que números son múltplos del número selccionado.
            if(matrix[i] == 0){

                break;

            } else if (matrix[i] % multiple == 0) {

                System.out.print(matrix[i] + ", ");
            }
        }

        System.out.println("");
        System.out.println("************************************************************************************************************************************");
    }

    //Método que valida que la entrada por teclado sea un número entero
    private Integer integer_validation(Integer entry){
        entry = 0;
        entry = in.llegirSencer();

        //Valida que la entrada no sea null.
        while(entry ==  null){
            System.out.println("ERROR: lo que has introducido no es un número entero.");
            entry = in.llegirSencer();
        }

        return entry;
    }
}
