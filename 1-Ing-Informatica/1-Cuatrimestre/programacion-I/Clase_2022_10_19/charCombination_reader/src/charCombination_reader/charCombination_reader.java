package charCombination_reader;

public class charCombination_reader {

    public static void main(String[] args) {

        LT in = new LT();        
        Character previous_letter = ' ', letter = ' ';
        int counter = 0;

        System.out.println("Por favor, introduce carácteres y yo te diré cuantas parejas de 'la' has introducido." +  
                        "\nIntroduce un carácter." );

        while (letter != '.'){

            if ((previous_letter == 'l') && (letter == 'a')){
                counter++;
            }

            previous_letter = letter;

            letter = in.llegirCaracter();
            while(letter ==  null){
                System.out.println("ERROR: lo que has introducido no es un carácter");
                letter = in.llegirCaracter();
            }
            System.out.println("Introduce otro carácter");
        }

        System.out.println("Has introducido " + counter + " parejas de 'la'.");
    }
}
