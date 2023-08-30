public class Main {

    private void Start() {

        LT in = new LT();
        System.out.println("Introduce cuántos segundos quieres que cuente el programa");
        int segundos = in.llegirSencer();

        for (int i = 0; i<segundos; i++){
            
            wait(1);
            System.out.println("Segundo: " + (i+1));
        }

    }


    public static void main(String[] args) {

        (new Main()).Start();
    }

    private void wait(int seg){

        try{

            Thread.sleep(seg*1000);
        }
        catch (Exception e){

            e.printStackTrace();
        }
    }
}
