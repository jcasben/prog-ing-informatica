/*
PROGRAMA EN EL CUAL PEDIREMOS AL USUARIO QUE VAYA INTRODUCIENDO CARÁCTERES POR TECLADO. AL ENCONTRAR UNA VOCAL MINÚSCULA ('a','e','i','o','u') 
O UN PUNTO ('.')  EL PROGRAMA PARARÁ Y NOS INDICARÁ SI HA ENCONTRADO UNA DE LAS VOCALES O SI HA ENCONTRADO UN PUNTO.
*/

package characters_searcher;

public class characters_searcher {
    
    private void inicio(){
    
        Character letter = 's';
        LT in =  new LT();
        Boolean validation = false;
        
        while ((!validation) && (letter != '.')){
            
            System.out.println("Introduce una letra");
            letter = in.llegirCaracter();
            while(letter ==  null){
                System.out.println("ERROR: lo que has introducido no es un carácter");
                letter = in.llegirCaracter();
            }
            
            if ((letter == 'a') || (letter == 'e') || (letter == 'i') || (letter == 'o') || (letter == 'u')){
                validation = true;
            }
        }
        
        if (letter == '.'){
            System.out.println("No se han encontrado ninguna vocal");
        }else{
            System.out.println("Se ha encontrado una vocal minúscula, la vocal '" + letter + "'");
        }        
    }
    
    public static void main(String[] args) {
        
        (new characters_searcher()).inicio();
    }   
}
