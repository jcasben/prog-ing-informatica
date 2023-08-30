/*
REALIZAR UN PROGRAMA QUE RESUELVA UNA ECUACIÓN DE SEGUNDO GRADO. PREGUNTAR AL USUARIO LOS VALORES DE a, b Y c.
*/
package second_grade_ecuation_calc;

import static java.lang.Double.NaN;


public class Second_grade_ecuation_calc {
    public static void main(String[] args) {
        LT in = new LT();
        Double a,b,c,x1,x2;
        
        System.out.println("Para la siguiente expresión: ax^2 + bx + c = 0");
        
        do{
            System.out.println("");
            System.out.println("Introduce el valor de a: ");
            a = in.llegirReal();

            System.out.println("");
            System.out.println("Introduce el valor de b: ");
            b = in.llegirReal();

            System.out.println("");
            System.out.println("Introduce el valor de c: ");
            c = in.llegirReal();

        }while((a == null) || b == null || c == null);
        
        System.out.println("Para la expresión: (" + a + "x)^2 + (" + b + "x) + (" + c + ")");
        
        x1 = (-b + Math.sqrt((Math.pow(b,2) - 4*(a)*(c))))/2*a;
        x2 = (-b - Math.sqrt((Math.pow(b,2) - 4*(a)*(c))))/2*a;
        
        if (x1 == NaN){
            System.out.println("x1 no tiene solución");
        }else{
            System.out.println("La solución de x1 es: " + x1);
        }
        
        if (x2 == NaN){
            System.out.println("x2 no tiene solución");
        }else{
            System.out.println("La solución de x2 es: " + x2);
        }
        
        
    }
}
