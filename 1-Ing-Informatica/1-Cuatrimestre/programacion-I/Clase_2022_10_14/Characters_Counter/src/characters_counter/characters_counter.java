package characters_counter;

public class characters_counter {
    
    private void inicio(){
    
        Character letter;
        int counter = 0;
        LT in =  new LT();
        
        System.out.println("Introduce una letra");
        letter = in.llegirCaracter();
        
        while(letter == null){
            System.out.println("ERROR: lo que has introducido no es una letra");
            letter = in.llegirCaracter();
        }
        
        while (letter != '.'){
            
            while(letter ==  null){
                System.out.println("ERROR: lo que has introducido no es una letra");
                letter = in.llegirCaracter();
            }

            if (letter == 'a'){
                counter = counter + 1;                
            } 
            System.out.println("Introduce una letra");
            letter = in.llegirCaracter();
            while(letter ==  null){
                System.out.println("ERROR: lo que has introducido no es una letra");
                letter = in.llegirCaracter();
            }
            
        }
        
        System.out.println("Se han encontrado " + counter + " a");
    }

    public static void main(String[] args) {
        
        (new characters_counter()).inicio();
    }   
}
