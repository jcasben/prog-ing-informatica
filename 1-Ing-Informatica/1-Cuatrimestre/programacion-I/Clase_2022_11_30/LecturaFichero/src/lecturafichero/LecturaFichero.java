package lecturafichero;

import java.io.FileReader;

/**
 *
 * @author jcasb
 */
public class LecturaFichero {

    private void Start() {
        
        FileReader input = null;
        
        try{
        
            input = new FileReader("files/prueba.txt");
            int valor;
            char c;
            valor = input.read();
            
            while(valor != -1) {
            
                c = (char) valor;
                System.out.print(c);
                valor = input.read();
            }
            
            System.out.println();
        }
        catch(Exception e){
        
            e.printStackTrace();
        }
        finally{
        
            try{
            
                input.close();
            }
            catch(Exception e){
                
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        
        (new LecturaFichero()).Start();
    }
}
