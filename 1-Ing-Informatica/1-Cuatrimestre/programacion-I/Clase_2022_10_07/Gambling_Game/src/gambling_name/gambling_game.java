/*
CREAR UN PROGRAMA QUE PERMITA APOSTAR UNA CANTIDAD ENTRE 2 Y 2998. GENERAR UN CARA O CRUZ MIENTRAS HAYA DINERO o MIENTRAS EL DINERO DEL USUARIO SEA 
MENOR QUE 3000€. SI SALE 0 GANA EL USUARIO Y SI SALE 1 GANA LA MÁQUINA. EL PROGRMA ACABA CUANDO EL DINERO ES MENOS o IGUAL A 0 Y GANA LA MÁQUINA
o CUANDO EL DINERO ES MAYOR O IGUAL QUE 3000
*/
package gambling_name;

import java.util.*;

public class gambling_game {

    public static void main(String[] args) {
        
        //DECLARACIÓN DE VARIABLES
        Double money;
        LT in =  new LT();
        Random rndm = new Random();
        
        //PREGUNTAR AL USUARIO CUANTO DINERO QUIERE APOSTAR
        System.out.println("¿Cuánto dinero quieres apostar? (Entre 2€ y 2998€)");
        money = in.llegirReal();
        
        //BUCLE PARA VERIFICAR QUE LO INTRODUCIDO EN LA ENTRADA SEA LO REQUERIDO POR LAS CONDICIONES
        while((money == null) || (money < 2 || (money > 2998))){
            System.out.println("ERROR: valor inesperado. Intenténtelo de nuevo.");
            money = in.llegirReal();
        }
        //BUCLE EN EL QUE SE REALIZA EL CARA O CRUZ
        while ((money > 0) && (money < 3000)){
            int coin = rndm.nextInt(2);
            
            if (coin == 0){
                money = money*2;
                System.out.println("¡Ha salido cara! El dinero del jugador se multiplica por 2 (Dinero total: )" + money);                
            }else if (coin == 1){            
                money = money/(2.5);
                System.out.println("¡Ha salido cruz! El dinero del jugador se divide entre 2.5 (Dinero total: )" + money);
            }           
        }
        
        //CONDICIONAL PARA DETERMINAR QUIÉN HA GANADO
        if(money == 0){
            System.out.println("¡Gana la máquina!");
        }else if (money >3000){
            System.out.println("¡Gana el jugador!");
        }
    }    
}
