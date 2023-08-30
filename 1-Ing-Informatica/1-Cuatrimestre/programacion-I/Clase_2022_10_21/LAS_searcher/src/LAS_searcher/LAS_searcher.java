package LAS_searcher;

public class LAS_searcher {

    private void Start(){

        Auxiliar_funct find = new Auxiliar_funct();
        System.out.println(find.find_LAS());
    }
    public static void main(String[] args) {

        (new LAS_searcher()).Start();
    }
    
}
