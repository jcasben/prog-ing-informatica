package LAS_searcher;

public class Auxiliar_funct {

    LT in;

    public Auxiliar_funct(){

    }
    
    public String find_LAS(){

        in = new LT();
        Character letter = ' ', previous_letter = ' ', previous_letter2 = ' ';
        int counter = 0;

        System.out.println("Por favor, introduce carácteres y yo te diré cuantos grupos de 'las' has introducido.");

        while (letter != '.'){

            System.out.println("Introduce un carácter.");
            if ((previous_letter2 == 'l') && (previous_letter == 'a') && (letter == 's')){
                counter++;
            }

            previous_letter2 = previous_letter;
            previous_letter = letter;

            letter = in.llegirCaracter();
            while(letter ==  null){
                System.out.println("ERROR: lo que has introducido no es un carácter");
                letter = in.llegirCaracter();
            }
        }
        return "Has encontrado " + counter + " grupos de 'LAS'";
    }

    public void in_validation(Character entry){

        while(entry ==  null){
            System.out.println("ERROR: lo que has introducido no es un carácter");
            entry = in.llegirCaracter();
        }
    }

    
}
