import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Ejercicio3 {
    public static void main(String[] args) {
        double areaTotal = 0, perimetroTotal = 0;
        double [] areasT = new double[4], perimetrosT = new double[4];
        Random ran = new Random();
        //Cada una de las ArrayList que estan contenidas dentro del array representan la coleccion de objetos de cada
        //tipo (Circulo, Triangulo, Cuadrado, Rectangulo) en respectivo orden.
        ArrayList<Figura> figuras = new ArrayList<>();
        ArrayList<Figura> [] figurasSeparadas = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            figurasSeparadas[i] = new ArrayList<>();
        }
        //Limite de los randoms a la hora de crear las figuras aleatorias.
        final int L = 100;

        for (int i = 0; i < 10000; i++) {
            int aux = ran.nextInt(4);
            switch (aux) {
                case 0 -> {
                    Figura c = new Circulo(ran.nextDouble(L));
                    figurasSeparadas[0].add(c);
                    figuras.add(c);
                    areasT[0] += c.area();
                    perimetrosT[0] += c.perimetro();
                    areaTotal += c.area();
                    perimetroTotal += c.perimetro();
                }
                case 1 -> {
                    Figura t = new Triangulo(ran.nextDouble(L), ran.nextDouble(L));
                    figurasSeparadas[1].add(t);
                    figuras.add(t);
                    areasT[1] += t.area();
                    perimetrosT[1] += t.perimetro();
                    areaTotal += t.area();
                    perimetroTotal += t.perimetro();
                }
                case 2 -> {
                    Figura c = new Cuadrado(ran.nextDouble(L));
                    figurasSeparadas[2].add(c);
                    figuras.add(c);
                    areasT[2] += c.area();
                    perimetrosT[2] += c.perimetro();
                    areaTotal += c.area();
                    perimetroTotal += c.perimetro();
                }
                default -> {
                    Figura r = new Rectangulo(ran.nextDouble(L), ran.nextDouble(L));
                    figurasSeparadas[3].add(r);
                    figuras.add(r);
                    areasT[3] += r.area();
                    perimetrosT[3] += r.perimetro();
                    areaTotal += r.area();
                    perimetroTotal += r.perimetro();
                }
            }
        }

        for (ArrayList<Figura> figura : figurasSeparadas) {
            Collections.sort(figura);
        }
        Collections.sort(figuras, Collections.reverseOrder());
        String [] nombres = {"Circulo", "Triangulo", "Cuadrado", "Rectangulo"};
        System.out.println("La suma de las areas de todas las figuras es: " + areaTotal +"\n" +
                           "La suma de los perimetros de todas las figuras es: " + perimetroTotal + "\n");
        System.out.println("--------La suma de las areas y perimetros por separado--------\n");
        for (int i = 0; i < nombres.length; i++) {
            System.out.printf(
                    "\t * %s \n\t\tÁrea: %.2f \n\t\tPerímetro: %.2f \n",
                    nombres[i], areasT[i], perimetrosT[i]
            );
        }
        System.out.println("-------------Área máxima y mínima de cada figura--------------\n");
        for (int i = 0; i < nombres.length; i++) {
            System.out.printf(
                    "\t * %s \n\t\tÁrea max: %.2f \n\t\tÁrea min: %.2f \n",
                    figurasSeparadas[i].get(i).getClass().getName(), figurasSeparadas[i].get(figurasSeparadas[i].size()-1).area(), figurasSeparadas[i].get(i).area()
            );
        }
        System.out.println("---------------------------TOP 10-----------------------------\n\t Top 10 por areas:\n");
        for (int i = 0; i < 10; i++) {
            System.out.printf("\t\t* Top" + (i+1) + ": %s\n", figuras.get(i));
        }
        Comparator<Figura> comparadorPerimetros = Comparator.comparingDouble(Figura::perimetro);
        Collections.sort(figuras, comparadorPerimetros.reversed());
        System.out.println("\t Top 10 por perimetro:\n");
        for (int i = 0; i < 10; i++) {
            System.out.printf("\t\t* Top" + (i+1) + ": %s\n", figuras.get(i));
        }
    }
}