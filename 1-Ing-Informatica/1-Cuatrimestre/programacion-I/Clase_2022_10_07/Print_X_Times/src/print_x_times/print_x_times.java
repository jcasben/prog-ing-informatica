package print_x_times;

/*CREAR UN PROGRAMA QUE PREGUNTE AL USURARIO CUANTAS VECES QUIERE IMPRIMIR EN PANTALLA, VERIFIQUE QUE LO INTRODUCIDO ES UN NÚMERO ENTERO Y POSITIVO
Y QUE IMPRIMA EN PANTALLA TANTAS VECES COMO HAYA INDICADO EL USUARIO*/

public class print_x_times {

    public static void main(String[] args) {
        
        //DECLARACIÓN DE VARIABLES
        LT in = new LT();
        Integer idx;
        
        //PREGUNTAR AL USUARIO LA CANTIDAD DE VECES QUE QUERRÁ IMPRIMIR
        System.out.println("¿Cuántas veces quieres imprimir? ");
        idx = in.llegirSencer();
        
        //BUCLE PARA EVITAR CARÁCTERES NO VÁLIDOS EN LA ENTRADA POR TECLADO: TIENE QUE SER UN NÚMERO ENTERO POSITIVO
        while((idx == null) || (idx < 0)){
            System.out.println("ERROR: caracter inesperado. Inténtalo de nuevo.");
            System.out.println("");
            System.out.print("¿Cuántas veces quieres imprimir?");
            idx = in.llegirSencer();
       }
        
        //INDICA AL USUARIO LAS VECES HA INDICADO QUE SE IMPRIMIRÁ
        System.out.println("Has introducido el número: " + idx);
        System.out.println("");
        
        //BUCLE FOR PARA IMPRIMIR TANTAS VECES COMO HAYA INDICADO EL USUARIO
        for(int i = 0;i<idx;i++){
            System.out.println("¡Holaa!");
        }
    } 
}
