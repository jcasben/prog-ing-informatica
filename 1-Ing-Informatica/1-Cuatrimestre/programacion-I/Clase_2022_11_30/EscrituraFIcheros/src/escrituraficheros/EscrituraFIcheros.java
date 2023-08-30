package escrituraficheros;

import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author jcasb
 */
public class EscrituraFIcheros {
    
    private void Start() {
    
        FileWriter output = null;
        
        try{
        
            String currentDir = new File ("." ).getAbsolutePath();
            System.out.println(currentDir);
            
            output = new FileWriter("fichero.txt", true);
            output.write("Hello World! ");
        }
        catch(Exception e) {
        
            e.printStackTrace();
        }
        
    }

    public static void main(String[] args) {
        
        (new EscrituraFIcheros()).Start();
    }
    
}
