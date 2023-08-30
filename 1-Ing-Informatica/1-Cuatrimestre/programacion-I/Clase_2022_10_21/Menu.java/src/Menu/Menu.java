package Menu;

public class Menu {

    private void Start(){

        AuxiliarFnct funct = new AuxiliarFnct();
        funct.header("M E N Ú");
        funct.counterMenu();

    }

    public static void main(String[] args) {
       
        (new Menu()).Start();
    }
}
