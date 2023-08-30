package betting_game;

import java.util.*;
public class Functions {

    LT in = new LT();
    Integer money = 1;

    //GETTER: Crea una cabecera con el título introducido por el usuario
    public void header(String title){

        System.out.println("******************************************************");
        System.out.println("******************************************************");
        System.out.println(title);
        System.out.println("******************************************************");
        System.out.println("******************************************************");
        System.out.println("");
    }

    public void game(){

        boolean exit = false;
        Character option = ' ';
        int user_number, CPU_number, bet, dice;
        user_number = CPU_number = bet = 0;


        header("JUEGO DE APUESTAS");
        System.out.println("");

        money = askMoney();

         while(!exit || money <= 0){
             System.out.println("Quieres jugar o quieres salir? (j/s)");
             option = characterValidation(option);

             if(option == 'j'){

                 bet = askBet();
                 user_number = askUser();
                 CPU_number = askCPU(user_number);
                 dice = dice();
                 System.out.println("Ha salido el número: " + dice);
                 money = play(user_number,CPU_number,dice,bet);

             } else if (option == 's' ) {
                 exit = true;
             }
         }
    }

    //Pide al usuario cuanto dinero quiere apostar
    public Integer askMoney(){

        System.out.println("¿Cuánto dinero quieres introducir para empezar? (No puede ser 0, un número negativo o un número con decimales)");
        money = integerValidation(money);

        return money;
    }

    public int askBet(){

        Integer bet = 0;
        System.out.println("¿Cuánto dinero quieres apostar? (No puede ser 0, un número negativo o un número con decimales)" );
        bet = integerValidation(bet);

        return bet;
    }

    //Pide al usuario el número que quiere apostar
    public int askUser(){

        Integer user_number = 0;

        while(user_number <= 0 || user_number>6) {

            System.out.print("¿A qué número quieres apostar?(No puede ser 0, un número negativo o un número con decimales) ");
            user_number = integerValidation(user_number);
        }
        return user_number;
    }

    //Genera el número que apostará la CPU
    public int askCPU(int user_number){

        int CPU_number = dice();

        while(user_number == CPU_number) {
            CPU_number = dice();
        }

        System.out.println("La CPU apuesta al: " + CPU_number);

        return CPU_number;
    }

    //Genera un número aleatorio entre 1 y 6
    public int dice(){
        Random rndm = new Random();

        return rndm.nextInt(6) + 1;
    }

    public int play(int user_number,int CPU_number,int dice, int bet){

        if (((dice == CPU_number) && (user_number == dice)) || ((dice != CPU_number) && (user_number != dice))){

            System.out.println("¡La CPU y el usuario han empatado!");
        } else if ((dice == user_number)) {

            money = money + bet;
            System.out.println("¡Ha ganado el usuario!");
            System.out.println("Te quedan " + money + " €");

        } else if ((dice == CPU_number)) {

            money = money - bet;
            System.out.println("¡Ha ganado la CPU!");
            System.out.println("Te quedan " + money + " €");
        }

        return money;
    }

    /*
    GETTER: valida que la entrada por teclado del usuario sea un número entero. Si no lo es, salta un error y pide al usuario que vuelva a introducir el número. Hace return del número introducido por teclado.
     */
    public Integer integerValidation (Integer entry) {
        entry = in.llegirSencer();
        while (entry == null) {
            System.out.println("ERROR: lo que has introducido no es un número entero.");
            entry = in.llegirSencer();
        }
        return entry;
    }

    /*
   GETTER: valida que la entrada por teclado del usuario sea un número entero. Si no lo es, salta un error y pide al usuario que vuelva a introducir el número. Hace return del número introducido por teclado.
    */
    public Character characterValidation (Character entry) {
        entry = in.llegirCaracter();
        while (entry == null) {
            System.out.println("ERROR: caracter incorrecto.");
            entry = in.llegirCaracter();
        }
        return entry;
    }
}
