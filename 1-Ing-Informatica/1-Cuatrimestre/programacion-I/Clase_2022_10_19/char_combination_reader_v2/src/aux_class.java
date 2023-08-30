public class aux_class {

    LT in = new LT();

    public aux_class(){

    }
    
    public String find_la(){
        
        Character letter = ' ', previous_letter = ' ';
        int counter = 0;

        System.out.println("Por favor, introduce carácteres y yo te diré cuantas parejas de 'la' has introducido.");

        while (letter != '.'){

            System.out.println("Introduce un carácter.");
            if ((previous_letter == 'l') && (letter == 'a')){
                counter++;
            }

            previous_letter = letter;

            letter = in.llegirCaracter();
            while(letter ==  null){
                System.out.println("ERROR: lo que has introducido no es un carácter");
                letter = in.llegirCaracter();
            }
        }
        return "Has encontrado " + counter + " parejas de LA";
    }
}
