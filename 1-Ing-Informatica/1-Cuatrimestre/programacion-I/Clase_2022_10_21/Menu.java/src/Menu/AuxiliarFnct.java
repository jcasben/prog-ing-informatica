package Menu;

public class AuxiliarFnct {

    LT in =  new LT();

    //GETTER: funcion usada para validar una entrada de usuario de un Integer. Hay que pasarle por parámetro la entrada que queremos validar de tipo Integer.
    public Integer integer_validation(Integer entry){
        entry = 0;
        entry = in.llegirSencer();

        while(entry ==  null){
            System.out.println("ERROR: lo que has introducido no es un número entero.");
            entry = in.llegirSencer();
        }

        return entry;
    }

    //GETTER: Crea una cabecera con el título introducido por el usuario
    public void header(String title){

        System.out.println("******************************************************");
        System.out.println("******************************************************");
        System.out.println(title);
        System.out.println("******************************************************");
        System.out.println("******************************************************");
        System.out.println("");
    }

    /*
    SETTER: crea un menú con un switch que cuenta cuantas veces se entra a cada parte del menú y al escoger la opción
            de salir del menú, imprime el recuento de veces que has entrado a cada opción.
     */
    public void counterMenu(){

        boolean exit = false;
        int option_a, option_b;
        option_a = option_b = 0;
        Integer option = 0;

        System.out.println("Este programa cuenta las veces que has entrado a cada opcion del menú.");

        while (!exit){
            System.out.println("******************************************************");
            System.out.println("Por favor, introduce que quieres hacer: \n1.Opcion A\n2.Opcion B\n3.Salir");
            option = integer_validation(option);

            switch (option) {
                case 1 -> option_a++;
                case 2 -> option_b++;
                case 3 -> exit = true;
                default -> {
                }
            }
            System.out.println("");
        }
        System.out.println("******************************************************");
        System.out.println("Has entrado " + option_a + " veces en la opción A y " + option_b + " veces en la opción B");
    }
}
